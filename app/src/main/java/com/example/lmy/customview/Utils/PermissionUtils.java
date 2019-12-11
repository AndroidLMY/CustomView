package com.example.lmy.customview.Utils;

import android.Manifest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @功能:存放app所有权限组
 * @Creat 2019/07/16 18:04
 * @User Lmy
 * @By Android Studio
 */
public class PermissionUtils {
    public static Map<String, String> PermissionMap = new HashMap<>();
    /*******************************app所有权限组**********************************/
    //日历
    public static final String CALENDAR = Manifest.permission.READ_CALENDAR;
    //相机
    public static final String CAMERA = Manifest.permission.CAMERA;
    //联系人
    public static final String CONTACTS = Manifest.permission.READ_CONTACTS;
    //定位
    public static final String LOCATION = Manifest.permission.ACCESS_FINE_LOCATION;
    //麦克风
    public static final String MICROPHONE = Manifest.permission.RECORD_AUDIO;
    //手机
    public static final String PHONE = Manifest.permission.READ_PHONE_STATE;
    //传感器
    public static final String SENSORS = Manifest.permission.BODY_SENSORS;
    //短信
    public static final String SMS = Manifest.permission.SEND_SMS;
    //读写
    public static final String STORAGE = Manifest.permission.READ_EXTERNAL_STORAGE;

    public static void init() {
        PermissionMap.put(Manifest.permission.READ_CALENDAR, "读取日历");
        PermissionMap.put(Manifest.permission.CAMERA, "拍照和录像");
        PermissionMap.put(Manifest.permission.READ_CONTACTS, "联系人");
        PermissionMap.put(Manifest.permission.ACCESS_FINE_LOCATION, "获取定位");
        PermissionMap.put(Manifest.permission.RECORD_AUDIO, "录音");
        PermissionMap.put(Manifest.permission.READ_PHONE_STATE, "手机");
        PermissionMap.put(Manifest.permission.BODY_SENSORS, "传感器");
        PermissionMap.put(Manifest.permission.SEND_SMS, "信息");
        PermissionMap.put(Manifest.permission.READ_EXTERNAL_STORAGE, "读写手机存储");
    }
    public static String GetPermission(List<String> parms) {
        String PermissionString = "";
        for (int i = 0; i < parms.size(); i++) {
            PermissionString = PermissionString + "\n" + PermissionMap.get(parms.get(i));
        }
        return PermissionString;
    }

    public static String GetPermission(String[] perms) {
        String PermissionString = "";
        for (int i = 0; i < perms.length; i++) {
            PermissionString = PermissionString + "\n" + PermissionMap.get(perms[i]);
        }
        return PermissionString;
    }
}
