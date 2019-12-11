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
import com.example.lmy.customview.MPChart.Utils.CombinedChartManager;
import com.example.lmy.customview.R;
import com.example.lmy.customview.base.BaseActivity;
import com.github.mikephil.charting.charts.CombinedChart;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * @功能: 组合图
 * @Creat 2019/12/10 14:48
 * @User Lmy
 * @Compony zaituvideo
 */
public class CombinationActivity extends BaseActivity implements OnClickHeadView {
    @BindView(R.id.title)
    HeadCustomView title;
    @BindView(R.id.combinedChart)
    CombinedChart combinedChart;

    public static void show(Context context) {
        context.startActivity(new Intent(context, CombinationActivity.class));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_combination);
        ButterKnife.bind(this);
        initTitle();
        //x轴数据
        List<String> xData = new ArrayList<>();
        xData.add("5.6");
        xData.add("5.7");
        xData.add("5.8");
        xData.add("5.9");
        xData.add("5.10");
        xData.add("5.11");
        xData.add("5.12");
        xData.add("5.13");
        xData.add("5.14");
        xData.add("5.15");
        //BarChart y轴数据集合
        List<Float> BarData = new ArrayList<>();
        for (int j = 0; j < 10; j++) {
            BarData.add((float) (Math.random() * 50));
        }
        //LineChart y轴数据集合
        List<Float> LineyData = new ArrayList<>();

        for (int j = 0; j < 10; j++) {
            LineyData.add((float) (Math.random() * 50 + 51));
        }
        //管理类
        CombinedChartManager combineChartManager = new CombinedChartManager(this, combinedChart);
        combineChartManager.showCombinedChart(
                xData,
                BarData,
                LineyData,
                "发货单",
                "包裹数",
                0xFF33C25E,
                0xFF33A8FF);
    }

    @Override
    public void initTitle() {
        title.setBackgroundColor(getResources().getColor(R.color.blue));
        title.setTitle("组合图");
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
