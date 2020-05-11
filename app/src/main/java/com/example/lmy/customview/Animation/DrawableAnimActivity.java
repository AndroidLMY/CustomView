package com.example.lmy.customview.Animation;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.lmy.customview.R;
import com.example.lmy.customview.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DrawableAnimActivity extends BaseActivity {
    AnimationDrawable rocketAnimation;

    @BindView(R.id.bt_kaishi)
    Button btKaishi;
    @BindView(R.id.iv_donghua)
    ImageView ivDonghua;
    @BindView(R.id.bt_end)
    Button btEnd;
    private RecyclerView recyclerView;

    public static void show(Context context) {
        context.startActivity(new Intent(context, DrawableAnimActivity.class));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drawable_animation);
        ButterKnife.bind(this);
        ivDonghua.setBackgroundResource(R.drawable.home_anim);
        rocketAnimation = (AnimationDrawable) ivDonghua.getBackground();
//        rocketAnimation.setOneShot(false);//false为循环播放
        btKaishi.setOnClickListener(view -> {
            rocketAnimation.start();
//            rocketAnimation.stop();

        });
        btEnd.setOnClickListener(view -> {
            if (rocketAnimation.isRunning()) {
                Toast.makeText(context, "运行", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(context, "停止", Toast.LENGTH_SHORT).show();
            }
            rocketAnimation.stop();
            rocketAnimation.selectDrawable(0);
        });
    }


}
