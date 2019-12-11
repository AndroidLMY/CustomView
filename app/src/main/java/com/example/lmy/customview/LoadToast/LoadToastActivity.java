package com.example.lmy.customview.LoadToast;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.androidlmy.headcustomview.HeadCustomView;
import com.androidlmy.headcustomview.OnClickHeadView;
import com.example.lmy.customview.R;
import com.example.lmy.customview.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoadToastActivity extends BaseActivity implements OnClickHeadView {

    @BindView(R.id.title)
    HeadCustomView title;
    @BindView(R.id.bt_01)
    Button bt01;
    @BindView(R.id.bt_02)
    Button bt02;
    @BindView(R.id.bt_03)
    Button bt03;
    @BindView(R.id.bt_04)
    Button bt04;
    public LoadToast loadToast;

    public static void show(Context context) {
        context.startActivity(new Intent(context, LoadToastActivity.class));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_load_toast);
        ButterKnife.bind(this);
        initTitle();
        final String text = "正在上传";
        loadToast = new LoadToast(this)
                .setProgressColor(Color.RED)
                .setText(text)
                .setTranslationY(100)
                .setBorderColor(Color.WHITE)
                .show();
    }

    @Override
    public void initTitle() {
        title.setBackgroundColor(getResources().getColor(R.color.blue));
        title.setTitle("Toast带加载动画");
        title.setTitleTextColor(getResources().getColor(R.color.white));
        title.setClickCallBack(this);

    }

    @Override
    public void onBackClick() {
        finish();
    }

    @Override
    public void onBackTextClick() {

    }

    @Override
    public void onRightTextClick() {

    }

    @Override
    public void onRightImgClick() {

    }

    @OnClick({R.id.bt_01, R.id.bt_02, R.id.bt_03, R.id.bt_04})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.bt_01:
                loadToast.show();
                break;
            case R.id.bt_02:
                loadToast.error();
                break;
            case R.id.bt_03:
                loadToast.success();
                break;
            case R.id.bt_04:
                loadToast.hide();
                break;
        }
    }
}
