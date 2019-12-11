package com.example.lmy.customview.MPChart.Utils;

import android.content.Context;
import android.graphics.Color;

import com.example.lmy.customview.R;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.formatter.IValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
import com.github.mikephil.charting.utils.ViewPortHandler;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * @功能:
 * 柱状图管理工具类
 * @Creat 2019/03/12 16:13
 * @User Lmy
 * @By Android Studio
 */
public class BarChartUtils {
    private Context context;
    private BarChart barChart;
    private static BarChartUtils barChartUtils;

    public static BarChartUtils getInstance() {
        if (barChartUtils == null) {
            barChartUtils = new BarChartUtils();
        }
        return barChartUtils;
    }

    public void InitBarChart1(Context context, BarChart barChart, ArrayList<BarEntry> barEntries, ArrayList<String> mlist, List<Integer> colorlist, int xdefaultnumber) {
        Description description = barChart.getDescription();
        description.setText("");
        description.setTextSize(10f);
        barChart.setNoDataText("No Data");
        // 集双指缩放
        barChart.setPinchZoom(false);
        barChart.animateY(2000);
        BarDataSet set1;
        if (barChart.getData() != null &&
                barChart.getData().getDataSetCount() > 0) {
            barChart.getData().notifyDataChanged();
            barChart.notifyDataSetChanged();
        } else {
            set1 = new BarDataSet(barEntries, "订单统计");
            set1.setColors(colorlist);
            ArrayList<IBarDataSet> dataSets = new ArrayList<IBarDataSet>();
            dataSets.add(set1);
            BarData data = new BarData(dataSets);
            data.setValueTextSize(10f);
//            data.setBarWidth(1f);
            data.setBarWidth(0.8f);//每个柱子的宽度 以及间距
            barChart.setData(data);
        }
        //设置X轴位置
        DecimalFormat decimalFormat = new DecimalFormat(".00"); //取小数点后面两位
        Double allDoubleMoney = (double) barEntries.size() / xdefaultnumber; //Double取小数点后面两位
        //这里设置手机屏幕展示7个 如需初始化缩放时显示10个则除以10 用x轴数据个数来除以你想要屏幕默认展示的个数
        String allStringMoney = "";//最终取到的结果
        //在方法体内加入下面的代码，便取到了四舍五入后的结果
        allStringMoney = decimalFormat.format(allDoubleMoney);//四舍五入
        barChart.zoom(Float.valueOf(allStringMoney), 1f, 0, 0);//缩放
        final XAxis xAxis = barChart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
//        xAxis.setLabelRotationAngle(-60);//柱的下面描述文字  旋转90度
        xAxis.setDrawLabels(true);
        xAxis.setDrawGridLines(false);
        xAxis.setGranularity(1f);//设置最小间隔，防止当放大时，出现重复标签。
        xAxis.setCenterAxisLabels(true);//字体下面的标签 显示在每个直方图的中间
        xAxis.setLabelCount(xdefaultnumber + 1, true);//一个界面显示10个Lable。那么这里要设置11个
        xAxis.setTextSize(9f);
        xAxis.setAvoidFirstLastClipping(false);//图表将避免第一个和最后一个标签条目被减掉在图表或屏幕的边缘
        YAxis rightYAxis = barChart.getAxisRight();
        rightYAxis.setDrawGridLines(false);
        rightYAxis.setEnabled(true);//设置右侧的y轴是否显示。包括y轴的那一条线和上面的标签都不显示
        rightYAxis.setDrawLabels(false);//设置y轴右侧的标签是否显示。只是控制y轴处的标签。控制不了那根线。
        rightYAxis.setDrawAxisLine(false);//这个方法就是专门控制坐标轴线的
        YAxis leftAxis = barChart.getAxisLeft();
        leftAxis.setGridColor(0xfff3f3f3);
        //参照线长度
//        leftAxis.setSpaceTop(20f);
        // X轴显示Value值的精度，与自定义X轴返回的Value值精度一致
        leftAxis.setGranularity(1f);
        //X轴横坐标显示的数量
        leftAxis.setLabelCount(10);
        //X轴最大坐标
        leftAxis.setAxisMaximum(100f);
        //X轴最小坐标
        leftAxis.setAxisMinimum(0f);
        leftAxis.setGridColor(Color.parseColor("#dadada"));//横向网格线色彩
        leftAxis.enableGridDashedLine(10f, 15f, 0f);//横向网格线 虚线模式绘制，例如， 像这样 ”- - - - - -”。 “lineLength”控制线段的长度，“spaceLength”控制线之间的间隔，“phase”控制起始点。
        leftAxis.setAxisLineColor(context.getResources().getColor(R.color.colorAccent));//左侧y轴线色彩
        setCustomData(barEntries, xAxis, mlist);
    }

    /**
     * 功能:
     * 横向柱状图 具体属性未完善
     *
     * @author :limingyang
     * @create ：2019/5/23 11:26
     * @created by android studiuo
     */
    public void InitBarHeapChart(Context context, BarChart barChart, ArrayList<BarEntry> barEntries, ArrayList<String> mlist, int[] colorlist, int number) {

        barChart.getDescription().setEnabled(false);
        barChart.setMaxVisibleValueCount(40);
        barChart.setPinchZoom(false);
        barChart.setDrawGridBackground(false);
        barChart.setDrawBarShadow(false);
        barChart.setDrawValueAboveBar(false);
        barChart.setHighlightFullBarEnabled(false);
        barChart.getAxisRight().setEnabled(false);
        //设置X轴位置
        DecimalFormat decimalFormat = new DecimalFormat(".00"); //取小数点后面两位
        Double allDoubleMoney = (double) barEntries.size() / number; //Double取小数点后面两位
        //这里设置手机屏幕展示7个 如需初始化缩放时显示10个则除以10 用x轴数据个数来除以你想要屏幕默认展示的个数
        String allStringMoney = "";//最终取到的结果
        //在方法体内加入下面的代码，便取到了四舍五入后的结果
        allStringMoney = decimalFormat.format(allDoubleMoney);//四舍五入
        barChart.zoom(Float.valueOf(allStringMoney), 1f, 0, 0);//缩放

        BarDataSet set1;
        if (barChart.getData() != null &&
                barChart.getData().getDataSetCount() > 0) {
            set1 = (BarDataSet) barChart.getData().getDataSetByIndex(0);
            set1.setValues(barEntries);
            barChart.getData().notifyDataChanged();
            barChart.notifyDataSetChanged();
        } else {
            set1 = new BarDataSet(barEntries, "");
            set1.setDrawIcons(false);
            set1.setColors(colorlist);
            set1.setStackLabels(new String[]{"补料-收费", "补料-免费", "反馈单"});
            ArrayList<IBarDataSet> dataSets = new ArrayList<>();
            dataSets.add(set1);
            BarData data = new BarData(dataSets);
//            data.setValueFormatter(new StackedValueFormatter(false, "", 1));
            data.setValueTextColor(Color.WHITE);
            barChart.setData(data);
        }
//        barChart.setFitBars(true);
        barChart.invalidate();
        final XAxis xAxis = barChart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
//        xAxis.setLabelRotationAngle(-60);//柱的下面描述文字  旋转90度
        xAxis.setDrawLabels(true);
        xAxis.setDrawGridLines(false);
        xAxis.setDrawAxisLine(false);//是否绘制x轴

        xAxis.setGranularity(1f);//设置最小间隔，防止当放大时，出现重复标签。
        xAxis.setCenterAxisLabels(true);//字体下面的标签 显示在每个直方图的中间
        xAxis.setLabelCount(number + 1, true);//一个界面显示10个Lable。那么这里要设置11个
        xAxis.setTextSize(9f);
        xAxis.setAvoidFirstLastClipping(false);//图表将避免第一个和最后一个标签条目被减掉在图表或屏幕的边缘
        YAxis rightYAxis = barChart.getAxisRight();
        rightYAxis.setDrawGridLines(false);
        rightYAxis.setEnabled(true);//设置右侧的y轴是否显示。包括y轴的那一条线和上面的标签都不显示
        rightYAxis.setDrawLabels(false);//设置y轴右侧的标签是否显示。只是控制y轴处的标签。控制不了那根线。
        rightYAxis.setDrawAxisLine(false);//这个方法就是专门控制坐标轴线的
        YAxis leftAxis = barChart.getAxisLeft();
        //Y轴颜色
        leftAxis.setAxisLineColor(0xffffffff);
        //Y轴参照线颜色
        leftAxis.setGridColor(0xfff3f3f3);
        //参照线长度
//        leftAxis.setSpaceTop(20f);
        // X轴显示Value值的精度，与自定义X轴返回的Value值精度一致
        leftAxis.setGranularity(1f);
        //X轴横坐标显示的数量
        leftAxis.setLabelCount(10);
        //X轴最大坐标
        leftAxis.setAxisMaximum(100f);
        //X轴最小坐标
        leftAxis.setAxisMinimum(0f);

        Legend l = barChart.getLegend();
        l.setVerticalAlignment(Legend.LegendVerticalAlignment.BOTTOM);
        l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.CENTER);
        l.setOrientation(Legend.LegendOrientation.HORIZONTAL);
        l.setDrawInside(false);
        //样式
        l.setForm(Legend.LegendForm.CIRCLE);
        //字体
        l.setFormSize(10f);
        //大小
        l.setTextSize(13f);
        l.setFormToTextSpace(10f);
        l.setXEntrySpace(10f);
        l.setEnabled(true);
        IAxisValueFormatter ix = new BarChartUtils.CustomXAxisValueFormatter(mlist);
        barChart.getXAxis().setValueFormatter(ix);
    }


    private void setCustomData(ArrayList barEntries, XAxis xAxis, ArrayList<String> mlist) {
        IAxisValueFormatter ix = new CustomXAxisValueFormatter(mlist);
        xAxis.setValueFormatter(ix);
    }

    public class CustomXAxisValueFormatter implements IAxisValueFormatter {
        private List<String> mValues;

        public CustomXAxisValueFormatter(List<String> values) {
            this.mValues = values;
        }

        @Override
        public String getFormattedValue(float value, AxisBase axis) {
            int x = (int) (value);
            if (x < 0)
                x = 0;
            if (x >= mValues.size())
                x = mValues.size() - 1;
            return mValues.get(x);
        }
    }

    public void initDoubleBarChart(Context context, BarChart barChart, ArrayList<BarEntry> BarEntry1, ArrayList<BarEntry> BarEntry2, ArrayList<String> strings) {
        Description description = new Description();
        description.setText("");
        barChart.setDescription(description);
        barChart.setNoDataText("No Data");
        //背景
        barChart.setBackgroundColor(0xffffffff);
        // 动画
        barChart.animateY(1000);
        //设置阴影
        barChart.setDrawBarShadow(false);
        //设置无边框
        barChart.setDrawBorders(true);
        // 设置是否可以触摸
        barChart.setTouchEnabled(true);
        // 是否可以拖拽
        barChart.setDragEnabled(true);
        // 是否可以缩放
        barChart.setScaleEnabled(true);
        //设置网格背景
        barChart.setGridBackgroundColor(0xffffffff);
        //设置边线宽度
        barChart.setBorderWidth(0);
        //设置边线颜色
        barChart.setBorderColor(0xffffffff);
        // 集双指缩放
        barChart.setPinchZoom(false);
        // 隐藏右边的坐标轴
        barChart.getAxisRight().setEnabled(false);
        // 隐藏左边的左边轴
        barChart.getAxisLeft().setEnabled(true);


        //设置X轴位置
        DecimalFormat decimalFormat = new DecimalFormat(".00"); //取小数点后面两位
        Double allDoubleMoney = (double) BarEntry1.size() / 7; //Double取小数点后面两位
        //这里设置手机屏幕展示7个 如需初始化缩放时显示10个则除以10 用x轴数据个数来除以你想要屏幕默认展示的个数
        String allStringMoney = "";//最终取到的结果
        //在方法体内加入下面的代码，便取到了四舍五入后的结果
        allStringMoney = decimalFormat.format(allDoubleMoney);//四舍五入
        barChart.zoom(Float.valueOf(allStringMoney), 1f, 0, 0);//缩放

        XAxis xAxis = barChart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        // 前面xAxis.setEnabled(false);则下面绘制的Grid不会有"竖的线"（与X轴有关）
        // 上面第一行代码设置了false,所以下面第一行即使设置为true也不会绘制AxisLine
        //设置轴线得颜色
        xAxis.setAxisLineColor(0xffffffff);
        xAxis.setDrawAxisLine(false);
        xAxis.setDrawGridLines(false);
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        //X轴纵向分割线，一般不设置显示
        // X轴显示Value值的精度，与自定义X轴返回的Value值精度一致
        xAxis.setGranularity(1f);
        //X轴横坐标显示的数量
        xAxis.setLabelCount(12);
        //X轴最大坐标
//        xAxis.setAxisMaximum(BarEntry1.size() + 0.5f);//
        //X轴最小坐标
//        xAxis.setAxisMinimum(0.5f);//
        xAxis.setAvoidFirstLastClipping(false);//图表将避免第一个和最后一个标签条目被减掉在图表或屏幕的边缘
//        xAxis.setSpaceBetweenLabels(2);
        //设置Y轴
        YAxis leftAxis = barChart.getAxisLeft();
        //Y轴颜色
        leftAxis.setAxisLineColor(0xffffffff);
        //Y轴参照线颜色
        leftAxis.setGridColor(0xfff3f3f3);
        //参照线长度
//        leftAxis.setSpaceTop(20f);
        // X轴显示Value值的精度，与自定义X轴返回的Value值精度一致
        leftAxis.setGranularity(1f);
        //X轴横坐标显示的数量
        leftAxis.setLabelCount(10);
        //X轴最大坐标
        leftAxis.setAxisMaximum(100f);
        //X轴最小坐标
        leftAxis.setAxisMinimum(0f);
        //设置X轴显示位置
        Legend l = barChart.getLegend();//图例
        l.setEnabled(true);   //是否使用 图例
        l.setVerticalAlignment(Legend.LegendVerticalAlignment.BOTTOM);////设置显示方向
        l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.CENTER);
        l.setOrientation(Legend.LegendOrientation.HORIZONTAL);
        l.setTextSize(10f);//设置文字大小
        l.setForm(Legend.LegendForm.CIRCLE);//正方形，圆形或线
        l.setFormSize(8f); // 设置Form的大小
        l.setFormLineWidth(2f);//设置Form的宽度
        l.setXEntrySpace(10f);//设置下面图例间距
        l.setWordWrapEnabled(true);//是否支持自动换行 目前只支持BelowChartLeft, BelowChartRight, BelowChartCenter
        BarDataSet barDataSet1 = new BarDataSet(BarEntry1, "出库数量");
        barDataSet1.setColor(0xFF35A7FF);
        BarDataSet barDataSet2 = new BarDataSet(BarEntry2, "入库数量");
        barDataSet2.setColor(0xFF33BE5D);
        ArrayList<IBarDataSet> iBarDataSets = new ArrayList<>();
        iBarDataSets.add(barDataSet1);
        iBarDataSets.add(barDataSet2);
        BarData barData = new BarData(iBarDataSets);
        float groupSpace = 0.28f;
        float barSpace = 0.06f; // x4 DataSet
        float barWidth = 0.3f; // x4 DataSet
        //关键： (0.3 + 0.06) * 2 + 0.28 = 1.00 -> interval per "group" 一定要等于1,乘以2是表示每组有两个数据
        // 设置数据
        barData.setValueFormatter(new IValueFormatter() {
            @Override
            public String getFormattedValue(float value, Entry entry, int dataSetIndex, ViewPortHandler viewPortHandler) {
                return (int) value + "";//通过重写格式化器中的方法，可以设置柱子上面的数据为整数。
            }
        });
        barData.setDrawValues(true);//是否显示柱子的数值
        barData.setValueTextSize(10f);//柱子上面标注的数值的字体大小
        barData.setBarWidth(barWidth);//每个柱子的宽度
        barChart.setData(barData);
        //如果不设置组直接的距离的话，那么两个柱子会公用一个空间，即发生重叠；另外，设置了各种距离之后，X轴方向会自动调整距离，以保持“两端对齐”
        barChart.groupBars(0.5f/*从X轴哪个位置开始显示，这个参数具体啥意思。。。*/,
                groupSpace/*组与组之间的距离*/,
                barSpace/*组中每个柱子之间的距离*/);
        IAxisValueFormatter ix = new DoubleCustomXAxisValueFormatter(strings);
        xAxis.setValueFormatter(ix);
        barChart.invalidate();
    }

    public class DoubleCustomXAxisValueFormatter implements IAxisValueFormatter {
        private List<String> mValues;

        public DoubleCustomXAxisValueFormatter(List<String> values) {
            this.mValues = values;
        }

        @Override
        public String getFormattedValue(float value, AxisBase axis) {
            int x = (int) (value);
            x = x - 1;
            if (x < 0) {
                x = 0;
            }
            if (x >= mValues.size()) {
                x = mValues.size() - 1;
            }
            return mValues.get(x);
        }
    }


}
