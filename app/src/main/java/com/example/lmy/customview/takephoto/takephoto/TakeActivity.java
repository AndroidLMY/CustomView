package com.example.lmy.customview.takephoto.takephoto;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.androidlmy.headcustomview.HeadCustomView;
import com.androidlmy.headcustomview.OnClickHeadView;
import com.bumptech.glide.Glide;
import com.example.lmy.customview.R;
import com.example.lmy.customview.Utils.GlideImageLoader;
import com.example.lmy.customview.Utils.PermissionUtils;
import com.example.lmy.customview.Utils.title_utils.StatusBarUtil;
import com.example.lmy.customview.base.BaseActivity;

import org.devio.takephoto.app.TakePhoto;
import org.devio.takephoto.app.TakePhotoActivity;
import org.devio.takephoto.compress.CompressConfig;
import org.devio.takephoto.model.CropOptions;
import org.devio.takephoto.model.TResult;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;
import pub.devrel.easypermissions.AfterPermissionGranted;
import pub.devrel.easypermissions.AppSettingsDialog;
import pub.devrel.easypermissions.EasyPermissions;

public class TakeActivity extends TakePhotoActivity implements OnClickHeadView, EasyPermissions.PermissionCallbacks {

    @BindView(R.id.title)
    HeadCustomView title;
    @BindView(R.id.circleimage)
    CircleImageView circleimage;
    private static final int PRC_PHOTO_PREVIEW = 10086;
    //TakePhoto
    private TakePhoto takePhoto;
    private CropOptions cropOptions;//裁剪参数
    private CompressConfig compressConfig; //压缩参数
    private Uri imageUri;//图片保存路径
    String iconPath = "";

    public static void show(Context context) {
        context.startActivity(new Intent(context, TakeActivity.class));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_take_photo);
        ButterKnife.bind(this);
        initTitle();
        initTakePhoto();
    }

    private void initTakePhoto() {
        ////获取TakePhoto实例
        takePhoto = getTakePhoto();
        //设置裁剪参数
        //        cropOptions = new CropOptions.Builder().setAspectX(16).setAspectY(16)
        //        .setWithOwnCrop(false).create();
        cropOptions =
                new CropOptions.Builder().setOutputX(1600).setAspectY(800).setWithOwnCrop(false).create();
        //设置压缩参数
        compressConfig =
                new CompressConfig.Builder().setMaxSize(50 * 1024).setMaxPixel(800).create();
        takePhoto.onEnableCompress(compressConfig, true); //设置为需要压缩
    }

    public void initTitle() {
        StatusBarUtil.setRootViewFitsSystemWindows(this, true);
        //        //设置状态栏透明
        //        StatusBarUtil.setTranslucentStatus(this);
        StatusBarUtil.setStatusBarColor(this, getResources().getColor(R.color.blue));

        if (!StatusBarUtil.setStatusBarDarkTheme(this, true)) {
            //如果不支持设置深色风格 为了兼容总不能让状态栏白白的看不清, 于是设置一个状态栏颜色为半透明,
            //这样半透明+白=灰, 状态栏的文字能看得清
            StatusBarUtil.setStatusBarColor(this, 0x55000000);
        }
        title.setBackgroundColor(getResources().getColor(R.color.blue));
        title.setTitle("TakePhoto图片框架");
        title.setTitleTextColor(getResources().getColor(R.color.white));
        title.setClickCallBack(this);
    }

    @Override
    public void onBackClick() {
        finish();
    }

    @Override
    public void onBackTextClick() {

    }

    @Override
    public void onRightTextClick() {

    }

    @Override
    public void onRightImgClick() {

    }

    @OnClick(R.id.circleimage)
    public void onViewClicked() {
        methodRequiresTwoPermission();
    }


    /**
     * takephoto裁剪完成的回调
     */
    @Override
    public void takeSuccess(TResult result) {
        super.takeSuccess(result);
        iconPath = result.getImage().getOriginalPath();
        //Toast显示图片路径
        ArrayList iconPaths = result.getImages();
        GlideImageLoader.loader(this, iconPath, circleimage);
    }


    //请求码
    @AfterPermissionGranted(PRC_PHOTO_PREVIEW)
    private void methodRequiresTwoPermission() {
        String[] perms = {
                PermissionUtils.CAMERA,
                PermissionUtils.STORAGE,
        };
        if (EasyPermissions.hasPermissions(this, perms)) {
            imageUri = getImageCropUri();
            //从相册中选取图片并裁剪
            takePhoto.onPickFromGalleryWithCrop(imageUri, cropOptions);
            //从相册中选取不裁剪
            //takePhoto.onPickFromGallery();
            //表明已经授权，可以进行用户授予权限的操作
        } else {
            // Do not have permissions, request them now
            EasyPermissions.requestPermissions(this, "视频上传需要以下权限:", PRC_PHOTO_PREVIEW, perms);
        }
    }

    //获得照片的输出保存Uri
    private Uri getImageCropUri() {
        File file = new File(Environment.getExternalStorageDirectory(),
                "/temp/" + System.currentTimeMillis() + ".jpg");
        if (!file.getParentFile().exists())
            file.getParentFile().mkdirs();
        return Uri.fromFile(file);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions,
                                           int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        // EasyPermissions 权限处理请求结果
        Log.i("DDD", "onRequestPermissionsResult:" + requestCode);
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
    }

    //同意授权
    @Override
    public void onPermissionsGranted(int requestCode, List<String> perms) {

        Log.d("DDD", "onPermissionsGranted:" + requestCode + ":" + perms.size());
    }

    //拒绝授权
    @Override
    public void onPermissionsDenied(int requestCode, List<String> perms) {
        Log.d("DDD", "onPermissionsDenied:" + requestCode + ":" + perms.size());
        new AppSettingsDialog.Builder(this)
                .setTitle("提醒")
                .setRationale("请开启相关权限")
                .build()
                .show();
    }


    /**
     * 拒绝权限前往设置中开启权限的回调
     */
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == AppSettingsDialog.DEFAULT_SETTINGS_REQ_CODE) {
            // Do something after user returned from app settings screen, like showing a Toast.
            methodRequiresTwoPermission();
        }
    }
}
