package com.example.lmy.customview.wecome;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DecodeFormat;
import com.bumptech.glide.request.RequestOptions;
import com.example.lmy.customview.R;
import com.example.lmy.customview.Utils.title_utils.StatusBarUtil;
import com.example.lmy.customview.WebView.WebActivity;
import com.example.lmy.customview.base.BaseActivity;

/**
 * 广告界面的设置
 */
public class AdvertisingActivity extends BaseActivity {
    public ImageView ivImage;
    public TextView tvTime;
    private static String imageUrl;
    private static int time;
    private static boolean isSkip;
    private CountDownTimer countDownTimer;
    private static Class<?> clss;
    private static AdvertisingActivity advertisingActivity;
    private static int imageint = 0;
    private static boolean isOnClick = false;
    private static String weburl;
    public static ImageView.ScaleType scaleType = ImageView.ScaleType.CENTER_CROP;

    /**
     * 有网络时网络图片的URL
     */
    public static void setImageUrl(String imageUrl) {
        AdvertisingActivity.imageUrl = imageUrl;
    }


    /**
     * 有网络时网络图片的URL
     */
    public static void setImageInt(int imageint) {
        AdvertisingActivity.imageint = imageint;
    }

    /**
     * 设置广告页面几秒后跳转
     */
    public static void setSkipTime(int time) {
        AdvertisingActivity.time = time * 1000;
    }

    /**
     * 设置右上角跳转按钮是否显示
     */
    public static void setIsSkip(boolean isSkip) {
        AdvertisingActivity.isSkip = isSkip;
    }

    public static void show(Context context, String weburls, Class<?> cls) {
        clss = cls;
        weburl = weburls;
        context.startActivity(new Intent(context, AdvertisingActivity.class));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_advertising);
        StatusBarUtil.setRootViewFitsSystemWindows(this, false);
        //设置状态栏透明
        StatusBarUtil.setTranslucentStatus(this);

        initView();

    }

    private void initView() {
        ivImage = findViewById(R.id.iv_image);
        tvTime = findViewById(R.id.tv_time);
        ivImage.setScaleType(scaleType);
        if (isConnectingToInternet(this)) {
            if (isSkip) {
                tvTime.setVisibility(View.VISIBLE);
            } else {
                tvTime.setVisibility(View.GONE);
            }
            RequestOptions options = new RequestOptions()
                    .format(DecodeFormat.PREFER_ARGB_8888);//设置图片解码格式;
            if (imageint == 0) {
                Glide.with(this)
                        .asBitmap()
                        .load(imageUrl)
                        .apply(options)
                        .into(ivImage);
            } else {
                Glide.with(this)
                        .asBitmap()
                        .load(imageint)
                        .apply(options)
                        .into(ivImage);
            }
            setCountdown(tvTime, time);
        } else {
            closeCountdown();
        }
        ivImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!weburl.equals("123")) {
                    countDownTimer.cancel();
                    closeCountdown();
                    WebActivity.show(AdvertisingActivity.this,"广告","https://blog.csdn.net/rocrocflying/article/details/47069185");
                }

            }
        });
    }
    public boolean isConnectingToInternet(Context context) {
        ConnectivityManager connectivity = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Network[] networks = connectivity.getAllNetworks();
            NetworkInfo networkInfo;
            for (Network mNetwork : networks) {
                networkInfo = connectivity.getNetworkInfo(mNetwork);
                if (networkInfo.getState().equals(NetworkInfo.State.CONNECTED)) {
                    return true;
                }
            }
        } else {
            if (connectivity != null) {
                NetworkInfo[] info = connectivity.getAllNetworkInfo();
                if (info != null)
                    for (int i = 0; i < info.length; i++) {
                        if (info[i].getState() == NetworkInfo.State.CONNECTED) {
                            return true;
                        }
                    }
            }
        }
        return false;
    }

    /**
     * 设置倒计时
     *
     * @param textView
     * @param time     1000 = 1秒;
     */
    public void setCountdown(final TextView textView, int time) {
        //实现倒计时
        countDownTimer = new CountDownTimer(time, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                textView.setText(millisUntilFinished / 1000 + "秒");
            }

            @Override
            public void onFinish() {
                textView.setText(0 + "秒");
                closeCountdown();

            }
        }.start();
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textView.setEnabled(false);
                closeCountdown();
            }
        });
    }

    public void closeCountdown() {
        if (countDownTimer != null)
            countDownTimer.cancel();
        Intent intent = new Intent(this, clss);
        startActivity(intent);
        finish();
    }
}
