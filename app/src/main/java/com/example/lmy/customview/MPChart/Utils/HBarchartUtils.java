package com.example.lmy.customview.MPChart.Utils;

import android.content.Context;

import com.github.mikephil.charting.charts.HorizontalBarChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;

import java.util.ArrayList;

/**
 * @功能:
 * 横向柱状图管理工具类
 * @Creat 2019/05/13 17:28
 * @User Lmy
 * @By Android Studio
 */
public class HBarchartUtils {
    private static HBarchartUtils hBarchartUtils;
    private HorizontalBarChart horizontalBarChart;

    public static HBarchartUtils getInstance() {
        if (hBarchartUtils == null) {
            hBarchartUtils = new HBarchartUtils();
        }
        return hBarchartUtils;
    }

    public void initBarChart(Context context, HorizontalBarChart horizontalBarChart) {
        //设置相关属性
        this.horizontalBarChart = horizontalBarChart;
        horizontalBarChart.setDrawBarShadow(false);
        horizontalBarChart.setDrawValueAboveBar(true);
        horizontalBarChart.getDescription().setEnabled(false);
        horizontalBarChart.setMaxVisibleValueCount(60);
        horizontalBarChart.setPinchZoom(false);
        horizontalBarChart.setDrawGridBackground(false);
        //x轴
        XAxis xl = horizontalBarChart.getXAxis();
        xl.setPosition(XAxis.XAxisPosition.BOTTOM);
        xl.setDrawAxisLine(true);
        xl.setDrawGridLines(false);
        xl.setGranularity(10f);
        //y轴
        YAxis yl = horizontalBarChart.getAxisLeft();
        yl.setDrawAxisLine(true);
        yl.setDrawGridLines(true);
        yl.setAxisMinimum(0f);
        //y轴
        YAxis yr = horizontalBarChart.getAxisRight();
        yr.setDrawAxisLine(true);
        yr.setDrawGridLines(false);
        yr.setAxisMinimum(0f);
        //设置数据
        setData(12, 50);
        horizontalBarChart.setFitBars(true);
        horizontalBarChart.animateY(2500);
        Legend l = horizontalBarChart.getLegend();
        l.setVerticalAlignment(Legend.LegendVerticalAlignment.BOTTOM);
        l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.LEFT);
        l.setOrientation(Legend.LegendOrientation.HORIZONTAL);
        l.setDrawInside(false);
        l.setFormSize(8f);
        l.setXEntrySpace(4f);
    }

    private void setData(int count, float range) {
        float barWidth = 9f;
        float spaceForBar = 10f;
        ArrayList<BarEntry> yVals1 = new ArrayList<BarEntry>();
        for (int i = 0; i < count; i++) {
            float val = (float) (Math.random() * range);
            yVals1.add(new BarEntry(i * spaceForBar, val));
        }
        BarDataSet set1;
        if (horizontalBarChart.getData() != null &&
                horizontalBarChart.getData().getDataSetCount() > 0) {
            set1 = (BarDataSet) horizontalBarChart.getData().getDataSetByIndex(0);
            set1.setValues(yVals1);
            horizontalBarChart.getData().notifyDataChanged();
            horizontalBarChart.notifyDataSetChanged();
        } else {
            set1 = new BarDataSet(yVals1, "DataSet 1");
            ArrayList<IBarDataSet> dataSets = new ArrayList<IBarDataSet>();
            dataSets.add(set1);
            BarData data = new BarData(dataSets);
            data.setValueTextSize(10f);
            data.setBarWidth(barWidth);
            horizontalBarChart.setData(data);
        }
    }
}
