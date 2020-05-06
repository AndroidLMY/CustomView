package com.example.lmy.customview.Animation;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lmy.customview.R;
import com.example.lmy.customview.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ShiTuAnimActivity extends BaseActivity {

    @BindView(R.id.bt_pingyi)
    Button btPingyi;
    @BindView(R.id.bt_xuanzhuan)
    Button btXuanzhuan;
    @BindView(R.id.suofang)
    Button suofang;
    @BindView(R.id.bt_touming)
    Button btTouming;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.bt_zuhe)
    Button btZuhe;
    @BindView(R.id.iv_quxiao)
    ImageView ivQuxiao;
    @BindView(R.id.ll_01)
    LinearLayout ll01;
    @BindView(R.id.ll_02)
    LinearLayout ll02;
    @BindView(R.id.ll_03)
    LinearLayout ll03;
    @BindView(R.id.lldonghua)
    LinearLayout lldonghua;

    public static void show(Context context) {
        context.startActivity(new Intent(context, ShiTuAnimActivity.class));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shi_tu_anim);
        ButterKnife.bind(this);
        ll01.startAnimation(AnimationUtils.loadAnimation(ShiTuAnimActivity.this,
                R.anim.tv01_anim));
        ll02.startAnimation(AnimationUtils.loadAnimation(ShiTuAnimActivity.this,
                R.anim.tv02_anim));
        ll03.startAnimation(AnimationUtils.loadAnimation(ShiTuAnimActivity.this,
                R.anim.tv03_anim));


        ivQuxiao.startAnimation(AnimationUtils.loadAnimation(ShiTuAnimActivity.this, R.anim.ivanim));


        tvName.startAnimation(AnimationUtils.loadAnimation(ShiTuAnimActivity.this,
                R.anim.view_anim));
    }

    @OnClick({R.id.bt_pingyi, R.id.bt_xuanzhuan, R.id.suofang, R.id.bt_touming, R.id.bt_zuhe, R.id.tv_name, R.id.iv_quxiao})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.bt_pingyi:
                tvName.startAnimation(AnimationUtils.loadAnimation(ShiTuAnimActivity.this,
                        R.anim.view_anim_pingyi));
                break;
            case R.id.bt_xuanzhuan:

                tvName.startAnimation(AnimationUtils.loadAnimation(ShiTuAnimActivity.this,
                        R.anim.view_anim_xuanzhuan));
                break;
            case R.id.suofang:

                tvName.startAnimation(AnimationUtils.loadAnimation(ShiTuAnimActivity.this,
                        R.anim.view_anim_suofang));
                break;
            case R.id.bt_touming:

                tvName.startAnimation(AnimationUtils.loadAnimation(ShiTuAnimActivity.this,
                        R.anim.view_anim_touming));
                break;
            case R.id.bt_zuhe:
                tvName.startAnimation(AnimationUtils.loadAnimation(ShiTuAnimActivity.this,
                        R.anim.view_anim));
                break;
            case R.id.tv_name:
                Toast.makeText(context, "点击事件", Toast.LENGTH_SHORT).show();
                break;

            case R.id.iv_quxiao:


                ll01.startAnimation(AnimationUtils.loadAnimation(ShiTuAnimActivity.this,
                        R.anim.tv01_anim_q));
                ll02.startAnimation(AnimationUtils.loadAnimation(ShiTuAnimActivity.this,
                        R.anim.tv02_anim_q));
                ll03.startAnimation(AnimationUtils.loadAnimation(ShiTuAnimActivity.this,
                        R.anim.tv03_anim_q));
                ivQuxiao.startAnimation(AnimationUtils.loadAnimation(ShiTuAnimActivity.this, R.anim.ivanim_q));
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            Thread.sleep(2000);
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    ivQuxiao.setVisibility(View.GONE);
                                    lldonghua.setVisibility(View.GONE);
                                }
                            });
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }).start();

                break;

            default:
                break;

        }
    }
}
