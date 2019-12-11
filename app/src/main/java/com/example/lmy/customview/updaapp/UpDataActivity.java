package com.example.lmy.customview.updaapp;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.core.app.NotificationCompat;
import androidx.core.content.FileProvider;

import com.androidlmy.headcustomview.HeadCustomView;
import com.androidlmy.headcustomview.OnClickHeadView;
import com.example.lmy.customview.R;
import com.example.lmy.customview.Utils.LogUtil;
import com.example.lmy.customview.base.BaseActivity;
import com.example.lmy.customview.service.DownloadService;
import com.example.lmy.customview.updaapp.okhttp.DownloadAppUtil;
import com.example.lmy.customview.updaapp.okhttp.DownloadListener;

import java.io.File;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class UpDataActivity extends BaseActivity implements OnClickHeadView {
    @BindView(R.id.title)
    HeadCustomView title;
    @BindView(R.id.bt_01)
    Button bt01;
    @BindView(R.id.bt_02)
    Button bt02;
    @BindView(R.id.bt_03)
    Button bt03;
    @BindView(R.id.bt_04)
    Button bt04;
    private Button updata;
    private String url = "http://47.100.250.181:8080/images/app_storck.apk";
    public static String filename;
    private NotificationManager notificationManager;
    private Notification notification; //下载通知进度提示
    private NotificationCompat.Builder builder;

    public static void show(Context context) {
        context.startActivity(new Intent(context, UpDataActivity.class));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_up_data);
        ButterKnife.bind(this);
        initTitle();

        updata = findViewById(R.id.updata);

    }

    @Override
    public void initTitle() {
        title.setBackgroundColor(getResources().getColor(R.color.blue));
        title.setTitle("APP自动更新的方式");
        title.setTitleTextColor(getResources().getColor(R.color.white));
        title.setClickCallBack(this);

    }

    public int getVersion() {
        PackageInfo pkg;
        int versionCode = 0;
        String versionName = "";
        try {
            pkg = this.getPackageManager().getPackageInfo(this.getApplication().getPackageName(), 0);
            versionCode = pkg.versionCode;

        } catch (PackageManager.NameNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return versionCode;
    }

    @OnClick({R.id.bt_01, R.id.bt_02, R.id.bt_03, R.id.bt_04})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.bt_01:
                Intent serviceIntent = new Intent(context, DownloadService.class);
                serviceIntent.setData(Uri.parse(url));
                serviceIntent.putExtra("name", "2.1");
                context.startService(serviceIntent);
                break;
            case R.id.bt_02:
                downLoadUpdate(url, false);
                break;
            case R.id.bt_03:
                downLoadUpdate(url, true);
                break;
            case R.id.bt_04:
                downLoadUpdate2(url);

                break;
        }
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

    public void downLoadUpdate2(String url) {
        UpDataProgressDialog dataProgressDialog = UpDataProgressDialog.getInstance(this);
        dataProgressDialog.setTitle("升级到" + "2.1" + "版本");
        dataProgressDialog.setDialogCancelable(false);//设置禁止取下载进度条
        dataProgressDialog.show();
        DownloadAppUtil mDownloadUtil = new DownloadAppUtil();
        mDownloadUtil.downloadFile(url, "2.1", new DownloadListener() {
            @Override
            public void onStart() {
                LogUtil.e("开始下载..........");
            }

            @Override
            public void onProgress(int currentLength) {
                LogUtil.e("下载进度:" + ">>>>>>>>>>" + currentLength);
                dataProgressDialog.setProgress((int) (currentLength));
            }

            @Override
            public void onFinish(String localPath) {
                LogUtil.e("下载结束..........");
                dataProgressDialog.dismiss();
                installAPK(context, new File(localPath));
            }

            @Override
            public void onFailure(String erroInfo) {
                LogUtil.e(erroInfo + "..........");
                builder.setContentTitle("下载失败")
                        .setContentText("发生未知错误")
                        .setAutoCancel(true);//设置通知被点击一次是否自动取消
                notification = builder.build();
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            Thread.sleep(200);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        notificationManager.notify(1, notification);
                    }
                }).start();
            }
        });
    }


    public void downLoadUpdate(String url, boolean isZiDong) {
        DownloadAppUtil mDownloadUtil = new DownloadAppUtil();
        mDownloadUtil.downloadFile(url, "2.1", new DownloadListener() {
            @Override
            public void onStart() {
                LogUtil.e("开始下载..........");
                initNotification();
            }

            @Override
            public void onProgress(int currentLength) {
                LogUtil.e("下载进度:" + ">>>>>>>>>>" + currentLength);
                builder.setProgress(100, (int) (currentLength), false);
                builder.setContentText("下载进度:" + (int) (currentLength) + "%");
                notification = builder.build();
                notificationManager.notify(1, notification);
            }

            @Override
            public void onFinish(String localPath) {
                LogUtil.e("下载结束..........");
                if (isZiDong) {
                    installAPK(context, new File(localPath));
                    notificationManager.cancel(1);
                    LogUtil.d("下载完成");

                } else {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(UpDataActivity.this, "下载完成", Toast.LENGTH_SHORT).show();
                        }
                    });
                    builder.setContentTitle("下载完成")
                            .setContentText("点击安装")
                            .setAutoCancel(false);//设置通知被点击一次是否自动取消
                    Intent intent = new Intent(Intent.ACTION_VIEW);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    //添加这一句表示对目标应用临时授权该Uri所代表的文件
                    intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                    // 由于没有在Activity环境下启动Activity,设置下面的标签
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                        // 版本大于 N ，开始使用 fileProvider 进行安装
                        Uri apkUri = FileProvider.getUriForFile(context, "com.example.lmy.customview.fileprovider", new File(localPath));
                        intent.setDataAndType(apkUri, "application/vnd.android.package-archive");
                    } else {
                        // 正常进行安装
                        intent.setDataAndType(Uri.fromFile(new File(localPath)), "application/vnd.android.package-archive");
                    }
                    PendingIntent pi = PendingIntent.getActivity(context, 0, intent, 0);
                    notification = builder.setContentIntent(pi).build();
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                Thread.sleep(200);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            notificationManager.notify(1, notification);
                        }
                    }).start();
                }
            }

            @Override
            public void onFailure(String erroInfo) {
                LogUtil.e(erroInfo + "..........");
                builder.setContentTitle("下载失败")
                        .setContentText("发生未知错误")
                        .setAutoCancel(true);//设置通知被点击一次是否自动取消
                notification = builder.build();
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            Thread.sleep(200);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        notificationManager.notify(1, notification);
                    }
                }).start();
            }
        });
    }

    /**
     * 下载完成自动跳转安装界面提示安装
     */
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

    //初始化通知
    private void initNotification() {
        notificationManager = (NotificationManager) this.getSystemService(Context.NOTIFICATION_SERVICE);
        builder = new NotificationCompat.Builder(this);
        builder.setContentTitle("正在更新...") //设置通知标题
                .setSmallIcon(R.mipmap.ic_launcher)
                .setLargeIcon(BitmapFactory.decodeResource(this.getResources(), R.mipmap.ic_launcher)) //设置通知的大图标
                .setPriority(NotificationCompat.PRIORITY_MAX) //设置通知的优先级：最大
                .setAutoCancel(false)//设置通知被点击一次是否自动取消
                .setContentText("下载进度:" + "0%")
                .setProgress(100, 0, false);
        notification = builder.build();//构建通知对象

    }
}
