package com.example.lmy.customview.Animation;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

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

    public static void show(Context context) {
        context.startActivity(new Intent(context, AndroidAnimationActivity.class));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_android_animation);
        ButterKnife.bind(this);
    }


    @OnClick({R.id.bt_shitu, R.id.bt_zhen, R.id.bt_shuxing})
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
        }
    }
}
