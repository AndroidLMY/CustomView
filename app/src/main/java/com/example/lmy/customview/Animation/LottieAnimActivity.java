package com.example.lmy.customview.Animation;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.Animator;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;
import com.example.lmy.customview.R;

/**
 * @功能: Lottie动画框架的测试
 * @Creat 2020/5/11 13:37
 * @User Lmy
 * @Compony 永远相信美好的事情即将发生
 */
public class LottieAnimActivity extends AppCompatActivity {


    private LottieAnimationView lottieAnim;

    private Button btStart;

    private Button btEnd;
    private Button btPause;
    private Button btHuifu;

    public static void show(Context context) {
        context.startActivity(new Intent(context, LottieAnimActivity.class));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lottie_anim);
        lottieAnim = findViewById(R.id.lottie_anim);
        btStart = findViewById(R.id.bt_start);
        btEnd = findViewById(R.id.bt_end);

        btPause = findViewById(R.id.bt_pause);
        btHuifu = findViewById(R.id.bt_huifu);


        btStart.setOnClickListener(view -> {
            lottieAnim.setVisibility(View.VISIBLE);
            lottieAnim.setImageAssetsFolder("task_imgs");
            lottieAnim.setAnimation("task.json");
            lottieAnim.playAnimation();
            lottieAnim.addAnimatorListener(new Animator.AnimatorListener() {
                @Override
                public void onAnimationStart(Animator animation) {
                    Log.e("lottieAnim", "onAnimationStart");

                }

                @Override
                public void onAnimationEnd(Animator animation) {
                    Toast.makeText(LottieAnimActivity.this, "签到成功,已获得100" + "嗨币", Toast.LENGTH_SHORT).show();
                    Log.e("lottieAnim", "onAnimationEnd");

                }

                @Override
                public void onAnimationCancel(Animator animation) {
                    Log.e("lottieAnim", "onAnimationCancel");

                }

                @Override
                public void onAnimationRepeat(Animator animation) {
                    Log.e("lottieAnim", "onAnimationRepeat");

                }
            });
        });

        btEnd.setOnClickListener(view -> {
            lottieAnim.setVisibility(View.GONE);
        });
        btPause.setOnClickListener(view -> {
            lottieAnim.pauseAnimation();
        });
        btHuifu.setOnClickListener(view -> {
            lottieAnim.playAnimation();
        });

    }

}
