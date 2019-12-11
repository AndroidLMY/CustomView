package com.example.lmy.customview.MPChart.Utils;

import android.graphics.Color;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.LegendEntry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.PercentFormatter;

import java.util.ArrayList;
import java.util.List;

import static com.github.mikephil.charting.components.Legend.LegendForm.CIRCLE;

/**
 * @功能:
 * 饼状图管理工具类
 * @Creat 2019/03/12 16:06
 * @User Lmy
 * @By Android Studio
 */
public class PicChartUtils {
    private static PicChartUtils picChartUtils;

    public static PicChartUtils getInstance() {
        if (picChartUtils == null) {
            picChartUtils = new PicChartUtils();
        }
        return picChartUtils;
    }
    public void InitAnnulusPicChatr(PieChart picChart, ArrayList<PieEntry> entries, ArrayList<String> arraylableList, int[] colors, String CenterText) {
        picChart.setUsePercentValues(true);//设置饼图是否使用百分比
        picChart.getDescription().setEnabled(false);//不显示数据描述 右下角的文字
        picChart.setExtraOffsets(5, 10, 5, 5);
        picChart.setDragDecelerationFrictionCoef(0.95f);
        picChart.setCenterText(CenterText);//有中心圆的时候的中间 文字
//        picChart.setDrawSliceText(false);//只显示百分比
        picChart.setEntryLabelColor(Color.BLACK);//扇形文字颜色
        picChart.setEntryLabelTextSize(10f);//扇形文字大小
        picChart.setExtraOffsets(50f, 0f, 50f, 0f);//调整控件的外边距
        picChart.setDrawHoleEnabled(true);//是否显示饼图中间空白区域，默认显示
        picChart.setHoleColor(Color.WHITE);//设置中间圆盘的颜色
        picChart.setTransparentCircleColor(Color.WHITE);//–内圆边框色
        picChart.setTransparentCircleAlpha(110);//–内圆边框透明度
        picChart.setHoleRadius(80);//设置饼状图半径为0
        picChart.setTransparentCircleRadius(80);//设置中间透明圈的半径,值为所占饼图的百分比
        picChart.setDrawCenterText(true);//是否显示圆盘中间文字，默认显示
        picChart.setRotationAngle(0);//绘制的开始位置
        picChart.setRotationEnabled(true);//–允许旋转
        picChart.setHighlightPerTapEnabled(true);//允许点击其中某个扇形区域.
        // chart.setUnit(" €");
        // chart.setDrawUnitsInChart(true);
        // add a selection listener
//        picChart.setOnChartValueSelectedListener(this);
        picChart.animateXY(1400, 1000);
        // chart.spin(2000, 0, 360);
        Legend l = picChart.getLegend();
        l.setVerticalAlignment(Legend.LegendVerticalAlignment.BOTTOM);////设置显示方向
        l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.LEFT);
        l.setOrientation(Legend.LegendOrientation.HORIZONTAL);
        l.setTextSize(10f);
        l.setXEntrySpace(1f);//设置下面图例间距
        l.setDrawInside(true);//
        l.setEnabled(true);//
//        l.setWordWrapEnabled(true);//自动换行
        List<LegendEntry> entriesll = new ArrayList<>();
        //自定义图例
        for (int i = 0; i < entries.size(); i++) {
            entriesll.add(new LegendEntry(
                            arraylableList.get(i),
                            CIRCLE,
                            8f,
                            9f,
                            null, colors[i]
                    )
            );
        }
        l.setCustom(entriesll);
        picChart.setExtraBottomOffset(10);
        PieDataSet dataSet;////控件右上角的说明文字
        if (picChart.getData() != null &&
                picChart.getData().getDataSetCount() > 0) {
            dataSet = (PieDataSet) picChart.getData().getDataSetByIndex(0);
            dataSet.setValues(entries);
            picChart.getData().notifyDataChanged();
            picChart.notifyDataSetChanged();
        } else {
            dataSet = new PieDataSet(entries, "");
            dataSet.setSelectionShift(5f);//部分区域被选中时多出的长度
            dataSet.setColors(colors);
            dataSet.setSliceSpace(4);//设置间距
            dataSet.setValueLinePart1OffsetPercentage(80.f);//折线中第一段起始位置相对于区块的偏移量, 数值越大, 折线距离区块越远
            dataSet.setValueLinePart1Length(0.4f);//折线中第一段长度占比
            dataSet.setValueLinePart2Length(0.4f);///折线中第二段长度最大占比
            dataSet.setXValuePosition(PieDataSet.ValuePosition.OUTSIDE_SLICE);//隐藏扇形区域内的显示数据
            dataSet.setYValuePosition(PieDataSet.ValuePosition.OUTSIDE_SLICE);
            //数据位置INSIDE_SLICE,OUTSIDE_SLICE 可以显示折线上的数据
            PieData data = new PieData(dataSet);
            data.setValueFormatter(new PercentFormatter());//设置显示百分比
            data.setValueTextSize(10f);//折线指示文字的大小
            dataSet.setValueLineColor(Color.GRAY);//折线色彩
            data.setValueTextColor(Color.BLACK);//设置折线字体的颜色
            picChart.setData(data);
            picChart.highlightValues(null);
            picChart.invalidate();
        }

    }

}
