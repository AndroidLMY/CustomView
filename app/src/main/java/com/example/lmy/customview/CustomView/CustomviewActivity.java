package com.example.lmy.customview.CustomView;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;

import com.androidlmy.headcustomview.HeadCustomView;
import com.androidlmy.headcustomview.OnClickHeadView;
import com.example.lmy.customview.R;
import com.example.lmy.customview.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class CustomviewActivity extends BaseActivity implements OnClickHeadView {
    @BindView(R.id.title)
    HeadCustomView title;
    @BindView(R.id.textview)
    CircleTextView textview;
    @BindView(R.id.switch1)
    Switch switch1;
    int anInt = 0;

    public static void show(Context context) {
        context.startActivity(new Intent(context, CustomviewActivity.class));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.customview_activity);
        ButterKnife.bind(this);
        initTitle();
    }

    @Override
    public void initTitle() {
        title.setBackgroundColor(getResources().getColor(R.color.blue));
        title.setTitle("圆形TextView");
        title.setTitleTextColor(getResources().getColor(R.color.white));
        title.setClickCallBack(this);

    }

    @OnClick(R.id.textview)
    public void onViewClicked() {
        if (anInt % 2 == 0) {
            textview.setmPaintNormalColor(Color.RED);
            textview.setText("李");
        } else {
            textview.setmPaintNormalColor(Color.BLUE);
            textview.setText("张");
        }
        anInt = anInt + 1;
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
}
