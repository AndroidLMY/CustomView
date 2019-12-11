package com.example.lmy.customview.service;

import android.app.DownloadManager;
import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.util.Log;

import androidx.core.content.FileProvider;

import java.io.File;

/**
 * @功能:
 * @Creat 2019/05/09 10:47
 * @User Lmy
 * @By Android Studio
 */
public class DownloadService extends IntentService {
    private String apkname;
    private String TAG = "DownloadService";
    public static final String BROADCAST_ACTION =
            "com.example.android.threadsample.BROADCAST";
    public static final String EXTENDED_DATA_STATUS =
            "com.example.android.threadsample.STATUS";
    public DownloadService() {
        super("DownloadService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        //获取下载地址
        String url = intent.getDataString();
        apkname = "乐造" + intent.getStringExtra("name");
        Log.i(TAG, url);
        //获取DownloadManager对象
        DownloadManager downloadManager = (DownloadManager) getSystemService(Context.DOWNLOAD_SERVICE);
        DownloadManager.Request request = new DownloadManager.Request(Uri.parse(url));
        request.setMimeType("application/vnd.android.package-archive");
        //指定APK缓存路径和应用名称，可在SD卡/Android/data/包名/file/Download文件夹中查看
        request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, apkname + ".apk");
        //设置网络下载环境为wifi
        request.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_WIFI);
        //设置显示通知栏，下载完成后通知栏自动消失
        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE);
        //设置通知栏标题
        request.setTitle("下载");
        request.setDescription("应用正在下载");
        request.setAllowedOverRoaming(false);
        //获得唯一下载id
        long requestId = downloadManager.enqueue(request);
        //将id放进Intent
        Intent localIntent = new Intent(BROADCAST_ACTION);
        localIntent.putExtra(EXTENDED_DATA_STATUS, requestId);
        //查询下载信息
        DownloadManager.Query query = new DownloadManager.Query();
        query.setFilterById(requestId);
        try {
            boolean isGoging = true;
            while (isGoging) {
                Cursor cursor = downloadManager.query(query);
                if (cursor != null && cursor.moveToFirst()) {
                    int status = cursor.getInt(cursor.getColumnIndex(DownloadManager.COLUMN_STATUS));
                    switch (status) {
                        //如果下载状态为成功
                        case DownloadManager.STATUS_SUCCESSFUL:
                            isGoging = false;
                            File file = new File(
                                    Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)
                                    , apkname + ".apk");
                            installAPK(this, file);
                            break;
                    }
                }
                if (cursor != null) {
                    cursor.close();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void installAPK(Context activity, File filePath) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        //添加这一句表示对目标应用临时授权该Uri所代表的文件
        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        // 由于没有在Activity环境下启动Activity,设置下面的标签
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            // 版本大于 N ，开始使用 fileProvider 进行安装
            Uri apkUri = FileProvider.getUriForFile(activity, "com.example.lmy.customview.fileprovider", filePath);
            intent.setDataAndType(apkUri, "application/vnd.android.package-archive");
        } else {
            // 正常进行安装
            intent.setDataAndType(Uri.fromFile(filePath), "application/vnd.android.package-archive");
        }
        activity.startActivity(intent);
    }
}
