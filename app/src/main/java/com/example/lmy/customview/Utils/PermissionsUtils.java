package com.example.lmy.customview.Utils;



import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import androidx.core.app.ActivityCompat;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static androidx.core.content.FileProvider.getUriForFile;

/**
 * @功能:
 * 权限管理辅助类
 * @Creat 2019/05/06 10:54
 * @User Lmy
 * @By Android Studio
 */
public class PermissionsUtils {
    private static PermissionsUtils permissionsUtils;
    /**
     * 请求动态加载权限
     * <!--在SDCard中创建与删除文件权限  -->
     * <uses-permission android:name="android.permission.MOUNT_UNMOUNT_FILESYSTEMS"/>
     * <!-- 往SDCard写入数据权限 -->
     * <uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE"/>
     * <!--  从SDCard读取数据权限 -->
     * <uses-permission android:name="android.permission.READ_EXTERNAL_STORAGE"/>
     */
    public static final String[] SD_CARD_PERMISSIONS = {Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE};
    public static final String[] CAMERA_PERMISSIONS = {Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE}; //相机权限
    public static final String[] SD_WRITE_READ_PERMISSIONS = {Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE}; //往SDCard写入数据权限
    public static final String[] PHONE_PERMISSIONS = {Manifest.permission.CALL_PHONE};//打电话权限
    //权限请求码
    public static final int REQUEST_CODE_PERMISSION = 1010;

    // 声明一个集合，在后面的代码中用来存储用户拒绝授权的权
    static List<String> mPermissionList;//没有授权的权限
    static List<String> PermnList;//授权失败的集合

    /**
     * 单例模式
     */
    public static PermissionsUtils getInstance() {
        if (permissionsUtils == null) {
            permissionsUtils = new PermissionsUtils();
        }
        return permissionsUtils;
    }

    /**
     * @param activity
     * @param permissions
     * @return true  有  false没有
     */
    public boolean isPermissions(Activity activity, String[] permissions) {
        mPermissionList = new ArrayList<>();
        PermnList = new ArrayList<>();
        for (String permission : permissions) {
            if (ActivityCompat.checkSelfPermission(activity, permission) != PackageManager.PERMISSION_GRANTED) {
                mPermissionList.add(permission); //获取没有授权的权限
            }
        }
        if (!mPermissionList.isEmpty()) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * @param activity
     * @param permissions
     * @param requestCode
     */
    public void accreditPermissions(Activity activity, String[] permissions, int requestCode) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (isPermissions(activity, permissions)) {
                if (listener != null) {
                    listener.onAccredit();
                }
            } else {
                ActivityCompat.requestPermissions(activity, mPermissionList.toArray(new String[mPermissionList.size()]), requestCode); //请求授权
            }

        }
    }

    /**
     * 动态申请回调（在onRequestPermissionsResult里使用，配合动态申请）
     *
     * @param grantResults
     */
    public void isPermissionsResult(int[] grantResults) {
        if (grantResults.length > 0) {
            for (int grantResult : grantResults) {
                if (grantResult != PackageManager.PERMISSION_GRANTED) {
                    if (listener != null) {
                        listener.onNotAuthorized();
                    }
                    return;
                }
            }
            if (listener != null) {
                listener.onAccredit();
            }
        }
    }

    /**
     * 跳转到权限设置界面
     * @param activity
     * @param requestCode
     */
    public void toAppSetting(Activity activity, int requestCode) {
        Intent localIntent = new Intent();
        localIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        if (Build.VERSION.SDK_INT >= 9) {
            localIntent.setAction("android.settings.APPLICATION_DETAILS_SETTINGS");
            localIntent.setData(Uri.fromParts("package", activity.getPackageName(), null));
        } else if (Build.VERSION.SDK_INT <= 8) {
            localIntent.setAction(Intent.ACTION_VIEW);
            localIntent.setClassName("com.android.settings", "com.android.settings.InstalledAppDetails");
            localIntent.putExtra("com.android.settings.ApplicationPkgName", activity.getPackageName());
        }
        activity.startActivityForResult(localIntent, requestCode);
    }

    /**
     * 适配了android7 的uri文件暴露权限
     *
     * @param context
     * @param picturefile
     * @return
     */
    public Uri getFileUriPermission(Context context, File picturefile) {
        Uri uri = null;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {//7.0及以上
            uri = getUriForFile(context, context.getPackageName() + ".fileprovider", picturefile);
            context.grantUriPermission(context.getPackageName(), uri, Intent.FLAG_GRANT_READ_URI_PERMISSION); //申请url暴露权限
        } else {//7.0以下
            uri = Uri.fromFile(picturefile);
        }
        return uri;//文件对外暴露权限
    }

    private OnPermissionListener listener;

    public void setPermissionListener(OnPermissionListener listener) {
        this.listener = listener;
    }

    /**
     * 接口
     */
    public interface OnPermissionListener {
        void onAccredit();

        void onNotAuthorized();
    }

}
