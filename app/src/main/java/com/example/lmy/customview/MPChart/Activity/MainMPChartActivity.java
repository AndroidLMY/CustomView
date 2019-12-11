package com.example.lmy.customview.MPChart.Activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.androidlmy.headcustomview.HeadCustomView;
import com.androidlmy.headcustomview.OnClickHeadView;
import com.example.lmy.customview.R;
import com.example.lmy.customview.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @功能: MPChart各种效果演示
 * @Creat 2019/12/10 14:49
 * @User Lmy
 * @Compony zaituvideo
 */
public class MainMPChartActivity extends BaseActivity implements OnClickHeadView {

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
    @BindView(R.id.bt_05)
    Button bt05;
    @BindView(R.id.bt_06)
    Button bt06;
    @BindView(R.id.bt_07)
    Button bt07;
    @BindView(R.id.bt_08)
    Button bt08;

    public static void show(Context context) {
        context.startActivity(new Intent(context, MainMPChartActivity.class));
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistics);
        ButterKnife.bind(this);
        initTitle();
    }

    @Override
    public void initTitle() {
        title.setBackgroundColor(getResources().getColor(R.color.blue));
        title.setTitle("MPChart统计");
        title.setTitleTextColor(getResources().getColor(R.color.white));
        title.setClickCallBack(this);
    }

    @OnClick({R.id.bt_01, R.id.bt_02, R.id.bt_03, R.id.bt_04, R.id.bt_05, R.id.bt_06, R.id.bt_07, R.id.bt_08})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.bt_01:
                //饼状图
                PieChartActivity.show(this);
                break;
            case R.id.bt_02:
                //柱形图
                BarChartActivity.show(this);
                break;
            case R.id.bt_03:
                //环形进度
                CircleProgressActivity.show(this);
                break;
            case R.id.bt_04:
                //折线图
                LineChartActivity.show(this);
                break;
            case R.id.bt_05:
                //组合图
                CombinationActivity.show(this);
                break;
            case R.id.bt_06:
                //横向柱状图
                CrosswiseActivity.show(this);
                break;
            case R.id.bt_07:
                //堆积柱状图
                HeapUpBarchartActivity.show(this);
                break;
            case R.id.bt_08:
                //recyclerview堆积图
                RecyclerViewHeapActivity.show(this);
                break;
        }
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


