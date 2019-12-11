package com.example.lmy.customview.MPChart.Activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.androidlmy.headcustomview.HeadCustomView;
import com.androidlmy.headcustomview.OnClickHeadView;
import com.example.lmy.customview.MPChart.Bean.Type;
import com.example.lmy.customview.MPChart.Utils.CustomViewHBarchartUtils;
import com.example.lmy.customview.MPChart.Utils.HBarchartUtils;
import com.example.lmy.customview.R;
import com.example.lmy.customview.base.BaseActivity;
import com.github.mikephil.charting.charts.HorizontalBarChart;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @功能: 横向柱状图
 * @Creat 2019/12/10 14:48
 * @User Lmy
 * @Compony zaituvideo
 */

public class CrosswiseActivity extends BaseActivity implements OnClickHeadView {
    @BindView(R.id.title)
    HeadCustomView title;
    @BindView(R.id.container)
    LinearLayout container;
    @BindView(R.id.chart1)
    HorizontalBarChart chart1;
    View itemView;
    private List<Type> typeList;
    private double maxScale = 0;
    int color = Color.parseColor("#30A5FF");

    public static void show(Context context) {
        context.startActivity(new Intent(context, CrosswiseActivity.class));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_double_histogram_h);
        ButterKnife.bind(this);
        initTitle();
        itemView = getWindow().getDecorView();
        HBarchartUtils.getInstance().initBarChart(this, chart1);
        initData();
        CustomViewHBarchartUtils.getInstance().initChart(itemView, container, typeList, color, maxScale);
    }

    @Override
    public void initTitle() {
        title.setBackgroundColor(getResources().getColor(R.color.blue));
        title.setTitle("横向柱状图");
        title.setTitleTextColor(getResources().getColor(R.color.white));
        title.setClickCallBack(this);
    }

    private void initData() {
        typeList = new ArrayList<>();
        int min = 0;
        int max = 100;
        Random random = new Random();
        int all = 0;
        for (int i = 0; i < 10; i++) {
            Type type = new Type();
            type.setTypeName("上海红田温县工厂" + i);
            int sale = random.nextInt(max) % (max - min + 1) + min;
            type.setSale(sale);
            all = all + sale;
            typeList.add(type);
            //这个循环初始化销量和标题 用all计算所有销量的总和
        }
        for (int i = 0; i < 10; i++) {
            double typeScale = (double) typeList.get(i).getSale() / all;
            typeList.get(i).setTypeScale(typeScale);
            //取出每一项中的销量除以总销量得出百分占比
        }
        for (int i = 0; i < typeList.size(); i++) {
            if (typeList.get(i).getTypeScale() > maxScale)
                maxScale = typeList.get(i).getTypeScale();
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
