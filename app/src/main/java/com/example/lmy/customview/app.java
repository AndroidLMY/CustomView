package com.example.lmy.customview;

import android.app.Application;
import android.content.Context;
import android.text.TextUtils;

import com.example.lmy.customview.Utils.CrashHandler;
import com.example.lmy.customview.Utils.LogUtil;
import com.jeremyliao.liveeventbus.LiveEventBus;
import com.tencent.smtt.sdk.QbSdk;

import org.litepal.LitePal;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * @功能:
 * @Creat 2019/10/23 8:56
 * @User Lmy
 * @Compony zaituvideo
 */
public class app extends Application {
    private static Context mContext;

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = getApplicationContext();
        initLogInterceptor();//Log配置
        initLitePal();//初始化LitePal
        initException();//全局异常捕获
        initLiveEventBus();//初始化LiveEventBus
        QbSdk.initX5Environment(this, null);//初始化x5浏览器

    }


    /**
     * 全局异常捕获 捕获到异常重启app
     */
    private void initException() {
        Thread.setDefaultUncaughtExceptionHandler(new CrashHandler(mContext));

    }

    /**
     * 初始化LitePal
     */
    private void initLitePal() {
        LitePal.initialize(this);
    }

    /**
     * 配置全局log打印的配置
     */
    private void initLogInterceptor() {
        LogUtil.isPrintLog = true;
        LogUtil.TGA = "com.androidlmy.stockcontrol";
    }

    /**
     * 初始化LiveEventBus
     * 1、supportBroadcast配置支持跨进程、跨APP通信
     * 2、配置LifecycleObserver（如Activity）接收消息的模式（默认值true）：
     * true：整个生命周期（从onCreate到onDestroy）都可以实时收到消息
     * false：激活状态（Started）可以实时收到消息，非激活状态（Stoped）无法实时收到消息，需等到Activity重新变成激活状
     * 态，方可收到消息
     * 3、autoClear
     * 配置在没有Observer关联的时候是否自动清除LiveEvent以释放内存（默认值false）
     */
    private void initLiveEventBus() {
        LiveEventBus.
                config().
                supportBroadcast(this).
                lifecycleObserverAlwaysActive(true).
                autoClear(false);
    }

    /**
     * @return 全局的上下文
     */
    public static Context getmContext() {
        return mContext;
    }


    /**
     * 获取进程号对应的进程名
     *
     * @param pid 进程号
     * @return 进程名
     */
    private static String getProcessName(int pid) {
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader("/proc/" + pid + "/cmdline"));
            String processName = reader.readLine();
            if (!TextUtils.isEmpty(processName)) {
                processName = processName.trim();
            }
            return processName;
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        } finally {
            try {
                if (reader != null) {
                    reader.close();
                }
            } catch (IOException exception) {
                exception.printStackTrace();
            }
        }
        return null;
    }
}
