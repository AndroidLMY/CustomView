package com.example.lmy.customview.takephoto.bgaphotopicker.utils;

import android.Manifest;
import android.app.Activity;
import android.os.Environment;
import androidx.annotation.NonNull;
import android.view.View;
import android.widget.Toast;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import cn.bingoogolapple.photopicker.activity.BGAPhotoPreviewActivity;
import cn.bingoogolapple.photopicker.widget.BGANinePhotoLayout;
import pub.devrel.easypermissions.AfterPermissionGranted;
import pub.devrel.easypermissions.EasyPermissions;

/**
 * @功能:
 * @Creat 2019/03/06 17:00
 * @User Lmy
 * @By Android Studio
 */
public class BGANinePhotoUtils implements EasyPermissions.PermissionCallbacks {
    public static BGANinePhotoUtils bgaNinePhotoUtil;
    private static final int PRC_PHOTO_PREVIEW = 1;
    private Activity activity;

    public static BGANinePhotoUtils getInstance() {
        if (bgaNinePhotoUtil == null) {
            bgaNinePhotoUtil = new BGANinePhotoUtils();
        }
        return bgaNinePhotoUtil;
    }


    public void init(final Activity mActivity, final BGANinePhotoLayout nplItemMomentPhotos, ArrayList<String> moments) {
        this.activity = mActivity;
        nplItemMomentPhotos.setData(moments);
        nplItemMomentPhotos.setDelegate(new BGANinePhotoLayout.Delegate() {
            @Override
            public void onClickNinePhotoItem(BGANinePhotoLayout ninePhotoLayout, View view, int position, String model, List<String> models) {
                photoPreviewWrapper(mActivity, nplItemMomentPhotos);
            }
        });
    }

    /**
     * 图片预览，兼容6.0动态权限
     */
    @AfterPermissionGranted(PRC_PHOTO_PREVIEW)
    private void photoPreviewWrapper(Activity activity, BGANinePhotoLayout nplItemMomentPhotos) {
        if (nplItemMomentPhotos == null) {
            return;
        }
        String[] perms = {Manifest.permission.WRITE_EXTERNAL_STORAGE};
        if (EasyPermissions.hasPermissions(activity, perms)) {
            File downloadDir = new File(Environment.getExternalStorageDirectory(), "BGAPhotoPickerDownload");
            BGAPhotoPreviewActivity.IntentBuilder photoPreviewIntentBuilder = new BGAPhotoPreviewActivity.IntentBuilder(activity)
                    .saveImgDir(downloadDir); // 保存图片的目录，如果传 null，则没有保存图片功能

            if (nplItemMomentPhotos.getItemCount() == 1) {
                // 预览单张图片
                photoPreviewIntentBuilder.previewPhoto(nplItemMomentPhotos.getCurrentClickItem());
            } else if (nplItemMomentPhotos.getItemCount() > 1) {
                // 预览多张图片
                photoPreviewIntentBuilder.previewPhotos(nplItemMomentPhotos.getData())
                        .currentPosition(nplItemMomentPhotos.getCurrentClickItemPosition()); // 当前预览图片的索引
            }
            activity.startActivity(photoPreviewIntentBuilder.build());
        } else {
            EasyPermissions.requestPermissions(activity, "图片预览需要以下权限:\n\n1.访问设备上的照片", PRC_PHOTO_PREVIEW, perms);
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
        if (requestCode == PRC_PHOTO_PREVIEW) {
            Toast.makeText(activity, "您拒绝了「图片预览」所需要的相关权限!", Toast.LENGTH_SHORT).show();
        }
    }
}
