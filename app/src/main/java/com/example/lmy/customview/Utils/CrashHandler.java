package com.example.lmy.customview.Utils;

import android.content.Context;
import android.content.Intent;
import android.os.Looper;
import android.util.Log;
import android.widget.Toast;

import com.example.lmy.customview.Activity.MainActivity;


/**
 * @功能: 全局异常的捕获 并重启程序
 * @Creat 2019/11/15 9:09
 * @User Lmy
 * @By Android Studio
 */
public class CrashHandler implements Thread.UncaughtExceptionHandler {


    private Context context;

    public CrashHandler(Context context) {
        this.context = context;
    }

    @Override
    public void uncaughtException(Thread t, Throwable e) {
        Log.e("TAG", "Errors:", e);
        showToast(t, e);
    }

    /**
     * 操作
     *
     * @param thread
     * @param e
     */
    private void showToast(Thread thread, final Throwable e) {

        new Thread(new Runnable() {
            @Override
            public void run() {
                Looper.prepare();
                Toast.makeText(context, "程序异常，正在重新启动", Toast.LENGTH_LONG).show();
                Looper.loop();
            }
        }).start();
        try {
            thread.sleep(1000);
        } catch (InterruptedException ex) {
            e.printStackTrace();
        }
        restartApp();
    }

    /**
     * 重启应用
     */
    private void restartApp() {
        Intent intent = new Intent(context, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
        android.os.Process.killProcess(android.os.Process.myPid());//再此之前可以做些退出等操作
    }
}