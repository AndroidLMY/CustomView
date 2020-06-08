package com.example.lmy.customview.Animation;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lmy.customview.R;
import com.example.lmy.customview.base.BaseActivity;

import java.util.ArrayList;
import java.util.List;

public class DriftingBottleActivity extends BaseActivity {
    private ImageView ivDriftingbottle01;
    private ImageView ivDriftingbottle02;
    private ImageView ivDriftingbottle03;
    private ImageView ivTopCloud1;
    private ImageView ivTopSun;
    private ImageView ivTopCloud2;
    private ImageView ivTopCloud3;
    private View viewShui;
    private RecyclerView recyclerview;

    private LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
    private Button btFishing;

    private DriftingBottleAdapter adapter;

    public static void show(Context context) {
        context.startActivity(new Intent(context, DriftingBottleActivity.class));
    }

    @SuppressLint("WrongConstant")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driftingbottle);
        ivDriftingbottle01 = findViewById(R.id.iv_driftingbottle_01);
        ivDriftingbottle02 = findViewById(R.id.iv_driftingbottle_02);
        ivDriftingbottle03 = findViewById(R.id.iv_driftingbottle_03);
        ivTopCloud1 = findViewById(R.id.iv_top_cloud1);
        ivTopSun = findViewById(R.id.iv_top_sun);
        ivTopCloud2 = findViewById(R.id.iv_top_cloud2);
        ivTopCloud3 = findViewById(R.id.iv_top_cloud3);
        viewShui = findViewById(R.id.view_shui);
        recyclerview = findViewById(R.id.recyclerview);


        btFishing = findViewById(R.id.bt_fishing);

        btFishing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initRecyclerview();

            }
        });

        initBottle(ivDriftingbottle01, -17f, 17f, -17f, 4000, 0);
        initBottle(ivDriftingbottle02, 17f, -17f, 17f, 4000, 0);
        initBottle(ivDriftingbottle03, -6f, 6f, -6f, 6000, 0);
        initAnim();

    }

    private void initRecyclerview() {
        if (adapter == null) {
            adapter = new DriftingBottleAdapter(this);

            recyclerview.setLayoutManager(linearLayoutManager);
            recyclerview.setAdapter(adapter);
        } else {
            adapter.notifyDataSetChanged();
        }
    }

    /**
     * 初始化云彩和太阳的动画
     */

    @SuppressLint("WrongConstant")
    private void initAnim() {
        //太阳的旋转动画
        ObjectAnimator sun_anim = ObjectAnimator.ofFloat(ivTopSun, "rotation", 0f, 360f);
        sun_anim.setDuration(20000);
        sun_anim.setRepeatCount(ValueAnimator.INFINITE);
        sun_anim.setRepeatMode(ValueAnimator.INFINITE);
        sun_anim.start();
        //第一个云朵的动画  左右平滑移动 无限移动
        ObjectAnimator cloud_anim = ObjectAnimator.ofFloat(ivTopCloud1, "translationX", -15.0f, 15.0f, -15.0f);
        cloud_anim.setDuration(10000);
        //无限循环
        cloud_anim.setRepeatCount(ValueAnimator.INFINITE);
        cloud_anim.setRepeatMode(ValueAnimator.INFINITE);
        cloud_anim.start();
        //第二个云朵的动画  左右平滑移动 无限移动
        ObjectAnimator cloud_big_anim = ObjectAnimator.ofFloat(ivTopCloud3, "translationX", 30f, 1f, -30.0f, 30.0f);
        //30f,0f, -30.0f, 30.0f  第三个代表开始唯一的方向和左边 正向右 负向左 第一个参数和最后一个取相反即可 第二个是向开始移动的方向相反的坐标
        cloud_big_anim.setDuration(10000);
        //无限循环
        cloud_big_anim.setRepeatCount(ValueAnimator.INFINITE);
        cloud_big_anim.setRepeatMode(ValueAnimator.INFINITE);
        cloud_big_anim.setStartDelay(0);
        cloud_big_anim.start();
    }

    /**
     * 初始化瓶子的动画
     */
    @SuppressLint("WrongConstant")
    private void initBottle(View view, float animy01, float animy02, float animy03, int duration, int delay) {
        List<Animator> animators = new ArrayList<>();
        ObjectAnimator translationXAnim = ObjectAnimator.ofFloat(view, "translationX", -15.0f, 15.0f, -15.0f);
        translationXAnim.setDuration(duration);
        //无限循环
        translationXAnim.setRepeatCount(ValueAnimator.INFINITE);
        translationXAnim.setRepeatMode(ValueAnimator.INFINITE);
        translationXAnim.start();
        animators.add(translationXAnim);
        ObjectAnimator translationYAnim = ObjectAnimator.ofFloat(view, "translationY", animy01, animy02, animy03);
        translationYAnim.setDuration(duration);
        translationYAnim.setRepeatCount(ValueAnimator.INFINITE);
        translationYAnim.setRepeatMode(ValueAnimator.INFINITE);
        translationYAnim.start();
        animators.add(translationYAnim);
        AnimatorSet btnSexAnimatorSet = new AnimatorSet();
        btnSexAnimatorSet.playTogether(animators);
        btnSexAnimatorSet.setStartDelay(delay);
        btnSexAnimatorSet.start();
    }

    /**
     * 动画的demo
     */
    private void startAnimi() {

        //将TextView从常规变换成全透明，再从全透明变换成常规
        ObjectAnimator anim1 = ObjectAnimator.ofFloat(ivDriftingbottle01, "alpha", 1f, 0f, 1f);
        anim1.setDuration(3000);
        //将TextView进行一次360度的旋转
        ObjectAnimator anim2 = ObjectAnimator.ofFloat(ivDriftingbottle01, "rotation", 0f, 360f);
        anim2.setDuration(3000);
        //将TextView先向左移出屏幕，然后再移动回来
        float curTranslationX = ivDriftingbottle01.getTranslationX();
        ObjectAnimator anim3 = ObjectAnimator.ofFloat(ivDriftingbottle01, "translationX",
                curTranslationX, -500f, curTranslationX);
        anim3.setDuration(3000);
        //将TextView先向右移出屏幕，然后再移动回来
        float curTranslationX2 = ivDriftingbottle01.getTranslationX();
        ObjectAnimator anim8 = ObjectAnimator.ofFloat(ivDriftingbottle01, "translationX",
                curTranslationX, 1000f, curTranslationX);
        anim8.setDuration(3000);


        //将TextView在垂直方向上放大3倍再还原
        ObjectAnimator anim4 = ObjectAnimator.ofFloat(ivDriftingbottle01, "scaleY", 1f, 3f, 1f);
        anim4.setDuration(3000);
        //将TextView在水平方向上放大3倍再还原
        ObjectAnimator anim5 = ObjectAnimator.ofFloat(ivDriftingbottle01, "scaleX", 1f, 3f, 1f);
        anim5.setDuration(3000);

        //将TextView先向上移出屏幕，然后再移动回来
        float curTranslationY = ivDriftingbottle01.getTranslationY();
        ObjectAnimator anim6 = ObjectAnimator.ofFloat(ivDriftingbottle01, "translationY",
                curTranslationY, -500f, curTranslationY);
        anim6.setDuration(3000);
        AnimatorSet animSet = new AnimatorSet();
        animSet.playSequentially(anim1, anim2, anim3, anim4, anim8, anim5, anim6);
        animSet.start();
    }
}