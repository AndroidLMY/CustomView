package com.example.lmy.customview.Activity;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.widget.ImageView;

import com.example.lmy.customview.R;
import com.example.lmy.customview.wecome.AdvertisingActivity;
import com.example.lmy.customview.wecome.GuideActivity;
import com.example.lmy.customview.wecome.WelcomeActivity;

/**
 * Creat 20190218 14:49
 * User Lmy
 * By AndroidStudio
 */
public class StartLaunchActivity extends WelcomeActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.welcome);
        AdvertisingActivity.scaleType = ImageView.ScaleType.CENTER_CROP;
    }

    @Override
    public void goGuide() {
        GuideActivity.isIndexViewShow = false;//设置底部索引隐藏
        GuideActivity.show(this, 60, new int[]{
                R.drawable.guide_01,
                R.drawable.guide_02,
                R.drawable.guide_03,
                R.drawable.guide_04,
        }, MainActivity.class);
        finish();

    }

    @Override
    public void goMain() {
        /**
         * 可以判断是否有网络
         * 有网络请求广告页图片
         * 没有网络直接进入MainAcyivity
         */
        if (isNetworkConnected(this)) {
            AdvertisingActivity.setImageUrl("http://47.100.250.181:8080/images/37WKKVZF.jpg");
            AdvertisingActivity.setIsSkip(true);
            AdvertisingActivity.setSkipTime(3);
            AdvertisingActivity.show(this, "https://www.cnblogs.com/fnlingnzb-learner/p/7531811.html", MainActivity.class);
        } else {
            MainActivity.show(this);
        }
        finish();
    }


    public boolean isNetworkConnected(Context context) {
        if (context != null) {
            ConnectivityManager mConnectivityManager = (ConnectivityManager) context
                    .getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo mNetworkInfo = mConnectivityManager.getActiveNetworkInfo();
            if (mNetworkInfo != null) {
                return mNetworkInfo.isAvailable();
            }
        }
        return false;
    }


}

