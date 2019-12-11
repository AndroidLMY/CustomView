package com.example.lmy.customview.Utils;

import android.app.Activity;
import androidx.annotation.NonNull;
import android.widget.Toast;

import java.util.List;

import pub.devrel.easypermissions.AfterPermissionGranted;
import pub.devrel.easypermissions.AppSettingsDialog;
import pub.devrel.easypermissions.EasyPermissions;

/**
 * @功能:权限管理工具类
 * @Creat 2019/03/08 15:20
 * @User Lmy
 * @By Android Studio
 */
public class PermissionUtils implements EasyPermissions.PermissionCallbacks, EasyPermissions.RationaleCallbacks {
    private static final int RC_CAMERA_AND_LOCATION = 100;
    private Activity mactivity;

    private static PermissionUtils permissionUtils;

    public static PermissionUtils getInstance() {
        if (permissionUtils == null) {
            permissionUtils = new PermissionUtils();
        }
        return permissionUtils;
    }

    @AfterPermissionGranted(RC_CAMERA_AND_LOCATION)
    public void methodRequiresTwoPermission(Activity activity, String[] perms) {
        this.mactivity = activity;
        if (EasyPermissions.hasPermissions(activity, perms)) {
            Toast.makeText(activity, "已经授权", Toast.LENGTH_SHORT).show();
        } else {
            // Do not have permissions, request them now
            EasyPermissions.requestPermissions(activity, "软件运行需要相关权限",
                    RC_CAMERA_AND_LOCATION, perms);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
    }

    @Override
    public void onPermissionsGranted(int requestCode, List<String> perms) {

        //  Check if at least one permission in the list of denied permissions has been permanently
        //  denied (user clicked "Never ask again").
        // This will display a dialog directing them to enable the permission in app settings.
        //如果用户点击永远禁止，这个时候就需要跳到系统设置页面去手动打开了
        if (EasyPermissions.somePermissionPermanentlyDenied(mactivity, perms)) {
            new AppSettingsDialog.Builder(mactivity).build().show();
        }

    }

    @Override
    public void onPermissionsDenied(int requestCode, List<String> perms) {
        Toast.makeText(mactivity, perms.toString(), Toast.LENGTH_SHORT).show();
    }


    @Override
    public void onRationaleAccepted(int requestCode) {

    }

    @Override
    public void onRationaleDenied(int requestCode) {

    }


}
