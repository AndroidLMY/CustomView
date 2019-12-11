package com.example.lmy.customview.base;

import android.app.Activity;
import android.content.Context;
import android.util.Log;

import java.util.Stack;

/**
 * 在每一个activity中的onCreate方法里调用压入方法把当前activity压入管理栈中。比如在MainActivity中：
 * ActivityManager mam = ActivityManager.getInstance();
 * mam.pushOneActivity(MainActivity.this);
 * 就把当前activity压入了栈中。在退出所有Activity的地方调用退出所有Activity的方法即可退出所有activ
 */

public class ActivityManager {
    private static final String TAG = ActivityManager.class.getSimpleName();
    private static ActivityManager instance;
    private Stack<Activity> activityStack;//activity栈

    //单例模式
    public static ActivityManager getInstance() {
        if (instance == null) {
            instance = new ActivityManager();
        }
        return instance;
    }

    //把一个activity压入栈中
    public void pushOneActivity(Activity actvity) {
        if (activityStack == null) {
            activityStack = new Stack();
        }
        activityStack.add(actvity);
        Log.i(TAG, "Activity = " + actvity.getClass().getSimpleName() + "  size = " + activityStack.size());
    }

    /**
     * 获取栈顶的activity，先进后出原则
     */
    public Activity getLastActivity() {
        return activityStack.lastElement();
    }

    /**
     * 移除一个activity
     */
    public void popOneActivity(Activity activity) {
        if (activityStack != null && activityStack.size() > 0) {
            if (activity != null) {
                activity.finish();
                activityStack.remove(activity);
                activity = null;
            }
        }
    }

    /**
     * 获取当前Activity（堆栈中最后一个压入的）
     */
    public Activity currentActivity() {
        Activity activity = activityStack.lastElement();
        return activity;
    }

    /**
     * 结束当前Activity（堆栈中最后一个压入的）
     */
    public void finishActivity() {
        Activity activity = activityStack.lastElement();
        finishActivity(activity);
    }

    /**
     * 结束指定的Activity
     */
    public void finishActivity(Activity activity) {
        if (activity != null) {
            activityStack.remove(activity);
            activity.finish();
            activity = null;
        }
    }

    /**
     * 结束指定类名的Activity
     */
    public void finishActivity(Class<?> cls) {
        for (Activity activity : activityStack) {
            if (activity.getClass().equals(cls)) {
                finishActivity(activity);
            }
        }
    }

    /**
     * 退出所有activity
     */
    public void finishAllActivity1() {
        if (activityStack != null) {
            while (activityStack.size() > 0) {
                Activity activity = getLastActivity();
                if (activity == null) break;
                popOneActivity(activity);
            }
        }
    }

    /**
     * 结束所有Activity
     */
    public void finishAllActivity() {
        for (int i = 0, size = activityStack.size(); i < size; i++) {
            if (null != activityStack.get(i)) {
                activityStack.get(i).finish();
            }
        }
        activityStack.clear();
    }

    /**
     * 退出应用程序
     */
    public void AppExit(Context context) {
        try {
            finishAllActivity();
            android.app.ActivityManager activityMgr = (android.app.ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
            activityMgr.restartPackage(context.getPackageName());
            System.exit(0);
        } catch (Exception e) {
        }
    }
}
