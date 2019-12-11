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
import com.example.lmy.customview.MPChart.Utils.BarChartUtils;
import com.example.lmy.customview.R;
import com.example.lmy.customview.base.BaseActivity;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarEntry;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @功能: 堆积柱状图 自定义x轴数据 和图例
 * @Creat 2019/12/10 14:49
 * @User Lmy
 * @Compony zaituvideo
 */
public class HeapUpBarchartActivity extends BaseActivity implements OnClickHeadView {
    @BindView(R.id.title)
    HeadCustomView title;
    @BindView(R.id.chart1)
    BarChart chart1;
    private int[] arr = {0xFFDB2C1F, 0xFFFE9400, 0xFF33A8FF};
    ArrayList<BarEntry> barEntries = new ArrayList<>();
    ArrayList<String> mnamelist = new ArrayList<>();


    public static void show(Context context) {
        context.startActivity(new Intent(context, HeapUpBarchartActivity.class));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_heap_barchart);
        ButterKnife.bind(this);
        initTitle();
        for (int i = 1; i <= 12; i++) {
            barEntries.add(new BarEntry(i, new float[]{
                    new Double(Math.random() * 30).intValue(),
                    new Double(Math.random() * 30).intValue(),
                    new Double(Math.random() * 30).intValue()}));
        }
        for (int i = 1; i <= 12; i++) {
            mnamelist.add("A" + i);
        }
        BarChartUtils.getInstance().InitBarHeapChart(this, chart1, barEntries, mnamelist, arr, 7);

    }
    @Override
    public void initTitle() {
        title.setBackgroundColor(getResources().getColor(R.color.blue));
        title.setTitle("堆积柱状图");
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
