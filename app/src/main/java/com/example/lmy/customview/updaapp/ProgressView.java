package com.example.lmy.customview.updaapp;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.lmy.customview.R;


/**
 * @功能:
 * @Creat 2019/12/10 9:44
 * @User Lmy
 * @Compony zaituvideo
 */
public class ProgressView extends LinearLayout {
    View inflate;
    private static TextView tvJindu;
    private static LinearLayout huojian;
    private static Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 1:
                    Bundle bundle = msg.getData();
                    int progress = Integer.valueOf(bundle.getString("progress"));// 这里的orderid是一个全局变量
                    tvJindu.setText(progress + "%");
                    tvJindu.setLayoutParams(new LayoutParams(0, LayoutParams.MATCH_PARENT, progress));
                    huojian.setLayoutParams(new LayoutParams(0, LayoutParams.MATCH_PARENT, 100 - progress));
                    break;
            }
        }
    };


    public ProgressView(Context context, AttributeSet attributeSet) {
        super(context);
        inflate = LayoutInflater.from(context).inflate(R.layout.progress_view, this, true);
        tvJindu = findViewById(R.id.tv_jindu);
        huojian = findViewById(R.id.huojian);

    }

    public ProgressView(Context context) {
        this(context, null);
    }


    public static void setProgress(int progress) {
        //tv1设置权重是1
        new Thread(new Runnable() {
            @Override
            public void run() {
                Message msg = new Message();
                msg.what = 1;
                Bundle bundle = new Bundle();
                bundle.putString("progress", String.valueOf(progress));// 将服务器返回的订单号传到Bundle中，，再通过handler传出
                msg.setData(bundle);
                handler.sendMessage(msg);
            }
        }).start();
    }
}
