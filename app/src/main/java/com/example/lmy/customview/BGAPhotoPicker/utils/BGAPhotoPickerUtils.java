package com.example.lmy.customview.BGAPhotoPicker.utils;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.os.Environment;
import androidx.annotation.NonNull;
import android.view.View;
import android.widget.Toast;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import cn.bingoogolapple.photopicker.activity.BGAPhotoPickerActivity;
import cn.bingoogolapple.photopicker.activity.BGAPhotoPickerPreviewActivity;
import cn.bingoogolapple.photopicker.widget.BGASortableNinePhotoLayout;
import pub.devrel.easypermissions.AfterPermissionGranted;
import pub.devrel.easypermissions.EasyPermissions;

/**
 * @功能:
 * @Creat 2019/03/06 15:08
 * @User Lmy
 * @By Android Studio
 */
public class BGAPhotoPickerUtils implements EasyPermissions.PermissionCallbacks {
    public static final int PRC_PHOTO_PICKER = 1;
    public static final int RC_CHOOSE_PHOTO = 1;
    public static final int RC_PHOTO_PREVIEW = 2;
    public static final int MULTIPLECHOICE = 101;
    public ArrayList<String> sortarrayList;//排序后的数据
    public ArrayList<String> initialarrayList;//未排序的数据


    private Activity mactivity;
    public static BGAPhotoPickerUtils BGAPhotoPickerUtils;

    /*
     * 复制下面注释到页面
     * */
//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        if (resultCode == RESULT_OK && requestCode == BGAPhotoPickerUtils.RC_CHOOSE_PHOTO) {
//            mPhotosSnpl.addMoreData(BGAPhotoPickerActivity.getSelectedPhotos(data));//设置多选数据
//        } else if (requestCode == BGAPhotoPickerUtils.RC_PHOTO_PREVIEW) {
//            //拍照返回的数据 也属于单选
//            mPhotosSnpl.setData(BGAPhotoPickerPreviewActivity.getSelectedPhotos(data));//设置单选数据
//        } else if (requestCode == BGAPhotoPickerUtils.MULTIPLECHOICE) {
//            //单选时返回的数据
//            if (data == null) {
//                Toast.makeText(this, "未选择图片", Toast.LENGTH_SHORT).show();
//            } else {
//                Glide.with(this).load(BGAPhotoPickerActivity.getSelectedPhotos(data).get(0)).into(ivShow);
//            }
//        }
//    }

    public static BGAPhotoPickerUtils getInstance() {
        if (BGAPhotoPickerUtils == null) {
            BGAPhotoPickerUtils = new BGAPhotoPickerUtils();
        }
        return BGAPhotoPickerUtils;
    }

    public void init(final Activity activity, final BGASortableNinePhotoLayout mPhotosSnpl, int maxnumber, int listnumber, int rescode) {
        this.mactivity = activity;
        mPhotosSnpl.setEditable(true);
        mPhotosSnpl.setPlusEnable(true);
        mPhotosSnpl.setSortable(true);
        mPhotosSnpl.setMaxItemCount(maxnumber);//设置最多选择图片数量
        mPhotosSnpl.setItemSpanCount(listnumber);//设置显示列数

        mPhotosSnpl.setDelegate(new BGASortableNinePhotoLayout.Delegate() {
            @Override
            public void onClickAddNinePhotoItem(BGASortableNinePhotoLayout sortableNinePhotoLayout, View view, int position, ArrayList<String> models) {
                BGAPhotoPickerUtils.getInstance().choicePhotoWrapper(activity, mPhotosSnpl);
                initialarrayList = models;

            }

            @Override
            public void onClickDeleteNinePhotoItem(BGASortableNinePhotoLayout sortableNinePhotoLayout, View view, int position, String model, ArrayList<String> models) {
                mPhotosSnpl.removeItem(position);
                initialarrayList = models;

            }

            @Override
            public void onClickNinePhotoItem(BGASortableNinePhotoLayout sortableNinePhotoLayout, View view, int position, String model, ArrayList<String> models) {
                Intent photoPickerPreviewIntent = new BGAPhotoPickerPreviewActivity.IntentBuilder(activity)
                        .previewPhotos(models) // 当前预览的图片路径集合
                        .selectedPhotos(models) // 当前已选中的图片路径集合
                        .maxChooseCount(mPhotosSnpl.getMaxItemCount()) // 图片选择张数的最大值
                        .currentPosition(position) // 当前预览图片的索引
                        .isFromTakePhoto(false) // 是否是拍完照后跳转过来
                        .build();
                activity.startActivityForResult(photoPickerPreviewIntent, RC_PHOTO_PREVIEW);
            }

            @Override
            public void onNinePhotoItemExchanged(BGASortableNinePhotoLayout sortableNinePhotoLayout, int fromPosition, int toPosition, ArrayList<String> models) {
                sortarrayList = models;

                Toast.makeText(activity, "排序发生变化", Toast.LENGTH_SHORT).show();
            }

        });

    }

    public ArrayList<String> getFile() {
        if (sortarrayList == null) {
            sortarrayList = initialarrayList;
        }
        return sortarrayList;
    }

    @AfterPermissionGranted(PRC_PHOTO_PICKER)
    public void choicePhotoWrapper(Activity context, BGASortableNinePhotoLayout mPhotosSnpl) {
        //选择多张图片时调用
        String[] perms = {Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.CAMERA};
        if (EasyPermissions.hasPermissions(context, perms)) {
            // 拍照后照片的存放目录，改成你自己拍照后要存放照片的目录。如果不传递该参数的话就没有拍照功能
            File takePhotoDir = new File(Environment.getExternalStorageDirectory(), "LMYPhotoPickerTakePhoto");
            Intent photoPickerIntent = new BGAPhotoPickerActivity.IntentBuilder(context)
                    .cameraFileDir(takePhotoDir) // 拍照后照片的存放目录，改成你自己拍照后要存放照片的目录。如果不传递该参数的话则不开启图库里的拍照功能
                    .maxChooseCount(mPhotosSnpl.getMaxItemCount() - mPhotosSnpl.getItemCount()) // 图片选择张数的最大值
                    .selectedPhotos(null) // 当前已选中的图片路径集合
                    .pauseOnScroll(false) // 滚动列表时是否暂停加载图片
                    .build();
            context.startActivityForResult(photoPickerIntent, RC_CHOOSE_PHOTO);
        } else {
            EasyPermissions.requestPermissions(context, "图片选择需要以下权限:\n\n1.访问设备上的照片\n\n2.拍照", PRC_PHOTO_PICKER, perms);
        }
    }

    @AfterPermissionGranted(PRC_PHOTO_PICKER)
    public void MultipleChoice(Activity context) {
        //图片单选时调用  例如选择头像等点击事件调用该方法即可
        String[] perms = {Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.CAMERA};
        if (EasyPermissions.hasPermissions(context, perms)) {
            // 拍照后照片的存放目录，改成你自己拍照后要存放照片的目录。如果不传递该参数的话就没有拍照功能
            File takePhotoDir = new File(Environment.getExternalStorageDirectory(), "LMYPhotoPickerTakePhoto");
            Intent photoPickerIntent = new BGAPhotoPickerActivity.IntentBuilder(context)
                    .cameraFileDir(takePhotoDir) // 拍照后照片的存放目录，改成你自己拍照后要存放照片的目录。如果不传递该参数的话则不开启图库里的拍照功能
                    .maxChooseCount(1) // 图片选择张数的最大值
                    .selectedPhotos(null) // 当前已选中的图片路径集合
                    .pauseOnScroll(false) // 滚动列表时是否暂停加载图片
                    .build();
            context.startActivityForResult(photoPickerIntent, MULTIPLECHOICE);
        } else {
            EasyPermissions.requestPermissions(context, "图片选择需要以下权限:\n\n1.访问设备上的照片\n\n2.拍照", PRC_PHOTO_PICKER, perms);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
    }

    @Override
    public void onPermissionsGranted(int requestCode, List<String> perms) {
    }

    @Override
    public void onPermissionsDenied(int requestCode, List<String> perms) {
        if (requestCode == PRC_PHOTO_PICKER) {
            Toast.makeText(mactivity, "您拒绝了「图片选择」所需要的相关权限!", Toast.LENGTH_SHORT).show();
        }
    }
}
