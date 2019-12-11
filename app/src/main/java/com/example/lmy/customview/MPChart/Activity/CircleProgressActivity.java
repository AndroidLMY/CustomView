package com.example.lmy.customview.MPChart.Activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.androidlmy.headcustomview.HeadCustomView;
import com.androidlmy.headcustomview.OnClickHeadView;
import com.example.lmy.customview.MPChart.View.CircleProgress;
import com.example.lmy.customview.R;
import com.example.lmy.customview.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @功能: 环形进度view展示
 * @Creat 2019/12/10 14:47
 * @User Lmy
 * @Compony zaituvideo
 */
public class CircleProgressActivity extends BaseActivity implements OnClickHeadView {
    @BindView(R.id.title)
    HeadCustomView title;
    @BindView(R.id.cir_ad1)
    CircleProgress cirAd1;

    public static void show(Context context) {
        context.startActivity(new Intent(context, CircleProgressActivity.class));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_huan_xing_jin_du);
        ButterKnife.bind(this);
        initTitle();
        cirAd1.setValue(10);
    }
    @Override
    public void initTitle() {
        title.setBackgroundColor(getResources().getColor(R.color.blue));
        title.setTitle("环形进度view");
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
}
