package com.example.lmy.customview.MPChart.Utils;

import android.content.Context;
import android.graphics.Color;
import android.widget.TextView;

import com.example.lmy.customview.R;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.MarkerView;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.CandleEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.github.mikephil.charting.utils.MPPointF;
import com.github.mikephil.charting.utils.Utils;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import static android.graphics.Color.BLUE;

/**
 * @功能: 折线图管理工具类
 * @Creat 2019/04/11 16:09
 * @User Lmy
 * @By Android Studio
 */
public class LineChartMannager {
    private static LineChartMannager chartManage;
    private boolean type = true;

    public static LineChartMannager getInstance() {
        if (chartManage == null) {
            chartManage = new LineChartMannager();
        }
        return chartManage;
    }

    private int[] arr = {0xFF00FFFF, 0xFF33C25E, 0xff409EFF, 0xFF635FF5, 0xFF30A6FF};

    public void initBackground(Context context, LineChart lineChart, ArrayList<ArrayList<Entry>> entrylist, ArrayList<String> namelist, ArrayList<String> xlist, int ynumber, int ymax, int xdefaultnumbe) {
        //创建描述信息
        type = true;
        Description description = new Description();
        description.setText("");
        description.setTextColor(Color.RED);
        description.setTextSize(20);
        lineChart.setDescription(description);//设置图表描述信息
        lineChart.setNoDataText("没有数据哦");//没有数据时显示的文字
        lineChart.setNoDataTextColor(BLUE);//没有数据时显示文字的颜色
        lineChart.setDrawGridBackground(false);//chart 绘图区后面的背景矩形将绘制
        lineChart.setDrawBorders(false);//禁止绘制图表边框的线
        lineChart.setExtraOffsets(10, 10, 10, 5);

        lineChart.getXAxis().setPosition(XAxis.XAxisPosition.BOTTOM);//设置x轴的位置
        lineChart.getXAxis().setDrawGridLines(false);//设置网格线是否绘制
//        lineChart.getXAxis().setLabelRotationAngle(20f);//设置文字与x轴之间的角度
//        lineChart.getAxisLeft().setAxisMaximum(300);//设置左边Y轴最大值
//        lineChart.getAxisLeft().setAxisMinimum(0);//设置左边Y轴最小值
//        lineChart.getAxisLeft().setSpaceTop(0);//设置左边Y轴最大值顶部间距
//        lineChart.getAxisLeft().setSpaceBottom(0);//设置左边Y轴最小值底部间距
//        lineChart.getAxisRight().setEnabled(false);//设置右边Y轴不可见
        lineChart.setTouchEnabled(true); // 设置是否可以触摸setDrawGridLines
        lineChart.setDragEnabled(true);// 是否可以拖拽
        lineChart.setScaleEnabled(false);// 是否可以缩放 x和y轴, 默认是true
        lineChart.setScaleXEnabled(true); //是否可以缩放 仅x轴
        lineChart.setScaleYEnabled(true); //是否可以缩放 仅y轴
        DecimalFormat decimalFormat = new DecimalFormat(".00"); //取小数点后面两位
        Double allDoubleMoney = (double) entrylist.get(0).size() / xdefaultnumbe; //Double取小数点后面两位
        //这里设置手机屏幕展示7个 如需初始化缩放时显示10个则除以10 用x轴数据个数来除以你想要屏幕默认展示的个数
        String allStringMoney = "";//最终取到的结果
        //在方法体内加入下面的代码，便取到了四舍五入后的结果
        allStringMoney = decimalFormat.format(allDoubleMoney);//四舍五入
        lineChart.zoom(Float.valueOf(allStringMoney), 1f, 0, 0);//缩放
        lineChart.setPinchZoom(true);  //设置x轴和y轴能否同时缩放。默认是否
        lineChart.setDoubleTapToZoomEnabled(true);//设置是否可以通过双击屏幕放大图表。默认是true
        lineChart.setHighlightPerDragEnabled(true);//能否拖拽高亮线(数据点与坐标的提示线)，默认是true
        lineChart.setDragDecelerationEnabled(true);//拖拽滚动时，手放开是否会持续滚动，默认是true（false是拖到哪是哪，true拖拽之后还会有缓冲）
        lineChart.setDragDecelerationFrictionCoef(0.99f);//与上面那个属性配合，持续滚动时的速度快慢，[0,1) 0代表立即停止。
        Legend l = lineChart.getLegend();//图例
        l.setVerticalAlignment(Legend.LegendVerticalAlignment.BOTTOM);////设置显示方向
        l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.CENTER);
        l.setOrientation(Legend.LegendOrientation.HORIZONTAL);
        l.setTextSize(10f);//设置文字大小
        l.setForm(Legend.LegendForm.CIRCLE);//正方形，圆形或线
        l.setFormSize(8f); // 设置Form的大小
        l.setFormLineWidth(2f);//设置Form的宽度
        l.setXEntrySpace(10f);//设置下面图例间距
        l.setWordWrapEnabled(true);//是否支持自动换行 目前只支持BelowChartLeft, BelowChartRight, BelowChartCenter

        /**
         * Entry 坐标点对象  构造函数 第一个参数为x点坐标 第二个为y点
         */
        //LineDataSet每一个对象就是一条连接线
        //判断图表中原来是否有数据
        if (lineChart.getData() != null &&
                lineChart.getData().getDataSetCount() > 0) {
            for (int i = 0; i < entrylist.size(); i++) {
                LineDataSet lineDataSet = (LineDataSet) lineChart.getData().getDataSetByIndex(i);
                lineDataSet.setValues(entrylist.get(i));
            }
            //刷新数据
            lineChart.getData().notifyDataChanged();
            lineChart.notifyDataSetChanged();
            lineChart.moveViewToX(entrylist.size());
        } else {
            //保存LineDataSet集合
            ArrayList<ILineDataSet> dataSets = new ArrayList<>();
            for (int i = 0; i < entrylist.size(); i++) {
                LineDataSet lineDataSet = new LineDataSet(entrylist.get(i), namelist.get(i));
                setLineDataSet(lineDataSet, arr[i]);
                dataSets.add(lineDataSet); // add the datasets
            }
            //创建LineData对象 属于LineChart折线图的数据集合
            LineData data = new LineData(dataSets);
            // 添加到图表中
            lineChart.setData(data);
            //绘制图表
            lineChart.invalidate();
            //获取此图表的x轴
            XAxis xAxis = lineChart.getXAxis();
            xAxis.setEnabled(true);//设置轴启用或禁用 如果禁用以下的设置全部不生效
            xAxis.setDrawAxisLine(true);//是否绘制轴线
            xAxis.setDrawGridLines(false);//设置x轴上每个点对应的线
            xAxis.setDrawLabels(true);//绘制标签  指x轴上的对应数值
            xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);//设置x轴的显示位置
            //xAxis.setTextSize(20f);//设置字体
            //xAxis.setTextColor(Color.BLACK);//设置字体颜色
            //设置竖线的显示样式为虚线
            //lineLength控制虚线段的长度
            //spaceLength控制线之间的空间
            xAxis.setLabelCount(xlist.size() - 1);//设置x轴显示标签数量  还有一个重载方法第二个参数为布尔值强制设置数量 如果启用会导致绘制点出现偏差
            xAxis.enableGridDashedLine(10f, 10f, 0f);//设置为虚线
            xAxis.setAxisLineColor(Color.WHITE);//y 设置竖线颜色
            //准备好每个点对应的x轴数值
            xAxis.setValueFormatter(new CustomXAxisValueFormatter(xlist));
            xAxis.setGranularity(1f);//设置为1f防止放大出现x轴重叠的情况

            /**
             * Y轴默认显示左右两个轴线
             */
            //获取右边的轴线
            YAxis rightAxis = lineChart.getAxisRight();
            //设置图表右边的y轴禁用
            rightAxis.setEnabled(false);
            //获取左边的轴线
            YAxis leftAxis = lineChart.getAxisLeft();
            leftAxis.setGridColor(0xFFE8E8E8);//设置网格颜色
            leftAxis.setAxisLineColor(Color.WHITE);//y 设置竖线颜色
            //设置网格线为虚线效果
//            leftAxis.enableGridDashedLine(10f, 10f, 0f);
            //是否绘制0所在的网格线
            leftAxis.setAxisMinimum(0f);//设置x轴的最小值
            leftAxis.setAxisMaximum(ymax);//设置最大值
            leftAxis.setGranularity(1f);//设置为1f防止放大出现x轴重叠的情况
            leftAxis.setLabelCount(ynumber);//设置x轴显示标签数量  还有一个重载方法第二个参数为布尔值强制设置数量 如果启用会导致绘制点出现偏差
            leftAxis.setDrawZeroLine(false);
//            //自定义的MarkerView对象
            LineChartMannager.MyMarkerView mv = new LineChartMannager.MyMarkerView(context, R.layout.custom_marker_view);
            mv.setChartView(lineChart);
            lineChart.setMarker(mv);
        }
    }

    public void initNoBackground(Context context, LineChart lineChart, ArrayList<ArrayList<Entry>> entrylist, ArrayList<String> namelist, ArrayList<String> xlist, int ynumber, int ymax, int xdefaultnumbe) {
        //创建描述信息
        type = false;
        Description description = new Description();
        description.setText("");
        description.setTextColor(Color.RED);
        description.setTextSize(20);
        lineChart.setDescription(description);//设置图表描述信息
        lineChart.setNoDataText("没有数据哦");//没有数据时显示的文字
        lineChart.setNoDataTextColor(BLUE);//没有数据时显示文字的颜色
        lineChart.setDrawGridBackground(false);//chart 绘图区后面的背景矩形将绘制
        lineChart.setDrawBorders(false);//禁止绘制图表边框的线
        lineChart.setExtraOffsets(10, 10, 10, 5);

        lineChart.getXAxis().setPosition(XAxis.XAxisPosition.BOTTOM);//设置x轴的位置
        lineChart.getXAxis().setDrawGridLines(false);//设置网格线是否绘制
//        lineChart.getXAxis().setLabelRotationAngle(20f);//设置文字与x轴之间的角度
//        lineChart.getAxisLeft().setAxisMaximum(300);//设置左边Y轴最大值
//        lineChart.getAxisLeft().setAxisMinimum(0);//设置左边Y轴最小值
//        lineChart.getAxisLeft().setSpaceTop(0);//设置左边Y轴最大值顶部间距
//        lineChart.getAxisLeft().setSpaceBottom(0);//设置左边Y轴最小值底部间距
//        lineChart.getAxisRight().setEnabled(false);//设置右边Y轴不可见
        lineChart.setTouchEnabled(true); // 设置是否可以触摸setDrawGridLines
        lineChart.setDragEnabled(true);// 是否可以拖拽
        lineChart.setScaleEnabled(false);// 是否可以缩放 x和y轴, 默认是true
        lineChart.setScaleXEnabled(true); //是否可以缩放 仅x轴
        lineChart.setScaleYEnabled(true); //是否可以缩放 仅y轴
        DecimalFormat decimalFormat = new DecimalFormat(".00"); //取小数点后面两位
        Double allDoubleMoney = (double) entrylist.get(0).size() / xdefaultnumbe; //Double取小数点后面两位
        //这里设置手机屏幕展示7个 如需初始化缩放时显示10个则除以10 用x轴数据个数来除以你想要屏幕默认展示的个数
        String allStringMoney = "";//最终取到的结果
        //在方法体内加入下面的代码，便取到了四舍五入后的结果
        allStringMoney = decimalFormat.format(allDoubleMoney);//四舍五入
        lineChart.zoom(Float.valueOf(allStringMoney), 1f, 0, 0);//缩放
        lineChart.setPinchZoom(true);  //设置x轴和y轴能否同时缩放。默认是否
        lineChart.setDoubleTapToZoomEnabled(true);//设置是否可以通过双击屏幕放大图表。默认是true
        lineChart.setHighlightPerDragEnabled(true);//能否拖拽高亮线(数据点与坐标的提示线)，默认是true
        lineChart.setDragDecelerationEnabled(true);//拖拽滚动时，手放开是否会持续滚动，默认是true（false是拖到哪是哪，true拖拽之后还会有缓冲）
        lineChart.setDragDecelerationFrictionCoef(0.99f);//与上面那个属性配合，持续滚动时的速度快慢，[0,1) 0代表立即停止。
        Legend l = lineChart.getLegend();//图例
        l.setVerticalAlignment(Legend.LegendVerticalAlignment.BOTTOM);////设置显示方向
        l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.CENTER);
        l.setOrientation(Legend.LegendOrientation.HORIZONTAL);
        l.setTextSize(10f);//设置文字大小
        l.setForm(Legend.LegendForm.CIRCLE);//正方形，圆形或线
        l.setFormSize(8f); // 设置Form的大小
        l.setFormLineWidth(2f);//设置Form的宽度
        l.setXEntrySpace(10f);//设置下面图例间距
        l.setWordWrapEnabled(true);//是否支持自动换行 目前只支持BelowChartLeft, BelowChartRight, BelowChartCenter

        /**
         * Entry 坐标点对象  构造函数 第一个参数为x点坐标 第二个为y点
         */
        //LineDataSet每一个对象就是一条连接线
        //判断图表中原来是否有数据
        if (lineChart.getData() != null &&
                lineChart.getData().getDataSetCount() > 0) {
            for (int i = 0; i < entrylist.size(); i++) {
                LineDataSet lineDataSet = (LineDataSet) lineChart.getData().getDataSetByIndex(i);
                lineDataSet.setValues(entrylist.get(i));
            }
            //刷新数据
            lineChart.getData().notifyDataChanged();
            lineChart.notifyDataSetChanged();
            lineChart.moveViewToX(entrylist.size());
        } else {
            //保存LineDataSet集合
            ArrayList<ILineDataSet> dataSets = new ArrayList<>();
            for (int i = 0; i < entrylist.size(); i++) {
                LineDataSet lineDataSet = new LineDataSet(entrylist.get(i), namelist.get(i));
                setLineDataSet(lineDataSet, arr[i]);
                dataSets.add(lineDataSet); // add the datasets
            }
            //创建LineData对象 属于LineChart折线图的数据集合
            LineData data = new LineData(dataSets);
            // 添加到图表中
            lineChart.setData(data);
            //绘制图表
            lineChart.invalidate();
            //获取此图表的x轴
            XAxis xAxis = lineChart.getXAxis();
            xAxis.setEnabled(true);//设置轴启用或禁用 如果禁用以下的设置全部不生效
            xAxis.setDrawAxisLine(true);//是否绘制轴线
            xAxis.setDrawGridLines(false);//设置x轴上每个点对应的线
            xAxis.setDrawLabels(true);//绘制标签  指x轴上的对应数值
            xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);//设置x轴的显示位置
            //xAxis.setTextSize(20f);//设置字体
            //xAxis.setTextColor(Color.BLACK);//设置字体颜色
            //设置竖线的显示样式为虚线
            //lineLength控制虚线段的长度
            //spaceLength控制线之间的空间
            xAxis.setLabelCount(xlist.size() - 1);//设置x轴显示标签数量  还有一个重载方法第二个参数为布尔值强制设置数量 如果启用会导致绘制点出现偏差
            xAxis.enableGridDashedLine(10f, 10f, 0f);//设置为虚线
            xAxis.setAxisLineColor(Color.WHITE);//y 设置竖线颜色
            //准备好每个点对应的x轴数值
            xAxis.setValueFormatter(new CustomXAxisValueFormatter(xlist));
            xAxis.setGranularity(1f);//设置为1f防止放大出现x轴重叠的情况

            /**
             * Y轴默认显示左右两个轴线
             */
            //获取右边的轴线
            YAxis rightAxis = lineChart.getAxisRight();
            //设置图表右边的y轴禁用
            rightAxis.setEnabled(false);
            //获取左边的轴线
            YAxis leftAxis = lineChart.getAxisLeft();
            leftAxis.setGridColor(0xFFE8E8E8);//设置网格颜色
            leftAxis.setAxisLineColor(Color.WHITE);//y 设置竖线颜色
            //设置网格线为虚线效果
//            leftAxis.enableGridDashedLine(10f, 10f, 0f);
            //是否绘制0所在的网格线
            leftAxis.setAxisMinimum(0f);//设置x轴的最小值
            leftAxis.setAxisMaximum(ymax);//设置最大值
            leftAxis.setGranularity(1f);//设置为1f防止放大出现x轴重叠的情况
            leftAxis.setLabelCount(ynumber);//设置x轴显示标签数量  还有一个重载方法第二个参数为布尔值强制设置数量 如果启用会导致绘制点出现偏差
            leftAxis.setDrawZeroLine(false);
//            //自定义的MarkerView对象
            LineChartMannager.MyMarkerView mv = new LineChartMannager.MyMarkerView(context, R.layout.custom_marker_view);
            mv.setChartView(lineChart);
            lineChart.setMarker(mv);
        }
    }

    public void setLineDataSet(LineDataSet lineDataSet, int Color) {
        lineDataSet.setColor(Color);
        lineDataSet.setLineWidth(1f);
        lineDataSet.setValueTextSize(10f);
        lineDataSet.setDrawCircles(true);//是否显示点相应的圆（圆为外圈圆+内圈圆）。
        lineDataSet.setCircleRadius(3f);//设置外圈圆半径。
        lineDataSet.setCircleColor(Color);//设置外圈圆颜色。
        lineDataSet.setCircleHoleRadius(0f);//设置内圈圆半径
        lineDataSet.setCircleColorHole(Color);//设置内圈圆颜色。
//        lineDataSet.enableDashedHighlightLine(10f, 5f, 0f);//点击后的高亮线的显示样式
//        lineDataSet.setHighlightLineWidth(2f);//设置点击交点后显示高亮线宽
//        lineDataSet.setHighlightEnabled(false);//是否禁用点击高亮线
//        lineDataSet.setHighLightColor(Color.RED);//设置点击交点后显示交高亮线的颜色
//        lineDataSet.setValueTextSize(9f);//设置显示值的文字大小
        lineDataSet.setDrawFilled(type);//设置禁用范围背景填充
        lineDataSet.setDrawValues(false);
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

    class MyMarkerView extends MarkerView {
        private TextView tvContent;

        public MyMarkerView(Context context, int layoutResource) {
            super(context, layoutResource);
            tvContent = (TextView) findViewById(R.id.tvContent);
        }

        @Override
        public void refreshContent(Entry e, Highlight highlight) {

            if (e instanceof CandleEntry) {
                CandleEntry ce = (CandleEntry) e;
                tvContent.setText("" + Utils.formatNumber(ce.getHigh(), 0, true));
            } else {

                tvContent.setText("" + Utils.formatNumber(e.getY(), 0, true));
            }
            super.refreshContent(e, highlight);
        }

        @Override
        public MPPointF getOffset() {
            return new MPPointF(-(getWidth() / 2), -getHeight());
        }
    }

}
