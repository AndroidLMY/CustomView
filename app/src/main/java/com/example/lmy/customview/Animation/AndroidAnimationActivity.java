package com.example.lmy.customview.Animation;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.ethanhua.skeleton.Skeleton;
import com.ethanhua.skeleton.SkeletonScreen;
import com.example.lmy.customview.R;
import com.example.lmy.customview.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AndroidAnimationActivity extends BaseActivity {

    @BindView(R.id.bt_shitu)
    Button btShitu;
    @BindView(R.id.bt_zhen)
    Button btZhen;
    @BindView(R.id.bt_shuxing)
    Button btShuxing;
    @BindView(R.id.bt_lottie)
    Button btLottie;
    @BindView(R.id.ll_all)
    LinearLayout llAll;

    public static void show(Context context) {
        context.startActivity(new Intent(context, AndroidAnimationActivity.class));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_android_animation);
        ButterKnife.bind(this);


        final SkeletonScreen skeletonScreen = Skeleton.bind(llAll)
                .shimmer(true)//是否开启动画
                .angle(30)//shimmer的倾斜角度
//          .color(R.color.colorAccent)//shimmer的颜色
                .duration(1200)//动画时间，以毫秒为单位
                .load(R.layout.item_jiazai)//骨架屏UI
                .show(); //default count is 10
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(2000);
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            skeletonScreen.hide();
                        }
                    });
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        }).start();

    }


    @OnClick({R.id.bt_shitu, R.id.bt_zhen, R.id.bt_shuxing, R.id.bt_lottie,R.id.bt_piaoliuping})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.bt_shitu:
                ShiTuAnimActivity.show(this);
                break;
            case R.id.bt_zhen:
                DrawableAnimActivity.show(this);
                break;
            case R.id.bt_shuxing:
                ShuXingAnimActivity.show(this);
                break;
            case R.id.bt_lottie:
                LottieAnimActivity.show(this);

                break;

            case R.id.bt_piaoliuping:
                DriftingBottleActivity.show(this);
                break;


            default:
                break;
        }
    }
}
