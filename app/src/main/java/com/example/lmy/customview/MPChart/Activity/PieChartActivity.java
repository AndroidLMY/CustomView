package com.example.lmy.customview.MPChart.Activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.androidlmy.headcustomview.HeadCustomView;
import com.androidlmy.headcustomview.OnClickHeadView;
import com.example.lmy.customview.MPChart.Utils.PicChartUtils;
import com.example.lmy.customview.MPChart.Utils.StringUtils;
import com.example.lmy.customview.R;
import com.example.lmy.customview.base.BaseActivity;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.LegendEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.github.mikephil.charting.components.Legend.LegendForm.CIRCLE;

/**
 * @功能: 各种样式饼状图展示
 * @Creat 2019/12/10 14:49
 * @User Lmy
 * @Compony zaituvideo
 */
public class PieChartActivity extends BaseActivity implements OnClickHeadView {
    @BindView(R.id.title)
    HeadCustomView title;
    @BindView(R.id.pic_chart1)
    PieChart picChart1;
    @BindView(R.id.pic_chart2)
    PieChart picChart2;
    @BindView(R.id.pic_chart3)
    PieChart picChart3;
    @BindView(R.id.pic_chart4)
    PieChart picChart4;

    public static void show(Context context) {
        context.startActivity(new Intent(context, PieChartActivity.class));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bing_zhuang_tu);
        ButterKnife.bind(this);
        initTitle();
        init01();
        init02();
        init03();
        init04();
        picChart2.setOnChartValueSelectedListener(new OnChartValueSelectedListener() {
            @Override
            public void onValueSelected(Entry e, Highlight h) {
                Name name = (Name) e.getData();
                Toast.makeText(PieChartActivity.this, name.getMoney() + name.getName(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected() {
            }
        });
    }


    @Override
    public void initTitle() {
        title.setBackgroundColor(getResources().getColor(R.color.blue));
        title.setTitle("饼状图");
        title.setTitleTextColor(getResources().getColor(R.color.white));
        title.setClickCallBack(this);
    }

    private void init04() {
        ArrayList<PieEntry> entries01 = new ArrayList<>();
        ArrayList<String> namelist01 = new ArrayList<>();
        entries01.add(new PieEntry(30, "柜体:30个"));
        entries01.add(new PieEntry(20, "五金配件:30个"));
        entries01.add(new PieEntry(10, "门板-特供实木:10个"));
        entries01.add(new PieEntry(40, "门板-自产:40个"));
        entries01.add(new PieEntry(30, "门板-外协:30个"));
        namelist01.add("柜体");
        namelist01.add("五金配件");
        namelist01.add("门板-特供实木");
        namelist01.add("门板-自产");
        namelist01.add("门板-外协");
        InitSectorPercentageInsidePicChatr(picChart4, entries01, namelist01, StringUtils.colors);
    }

    public void InitSectorPercentageInsidePicChatr(PieChart picChart, ArrayList<PieEntry> entries, ArrayList<String> arraylableList, int[] colors) {
        picChart.setUsePercentValues(true);//设置饼图是否使用百分比
        picChart.getDescription().setEnabled(false);//不显示数据描述 右下角的文字
        picChart.setExtraOffsets(5, 10, 5, 5);
        picChart.setDragDecelerationFrictionCoef(0.95f);
        picChart.setCenterText("");//有中心圆的时候的中间 文字
//        picChart.setDrawSliceText(false);//只显示百分比
        picChart.setEntryLabelColor(Color.BLACK);//扇形文字颜色
        picChart.setEntryLabelTextSize(10f);//扇形文字大小
        picChart.setExtraOffsets(50f, 0f, 50f, 0f);//调整控件的外边距
        picChart.setDrawHoleEnabled(true);//是否显示饼图中间空白区域，默认显示
        picChart.setHoleColor(Color.WHITE);//设置中间圆盘的颜色
        picChart.setTransparentCircleColor(Color.WHITE);//–内圆边框色
        picChart.setTransparentCircleAlpha(110);//–内圆边框透明度
        picChart.setHoleRadius(0);//设置饼状图半径为0
        picChart.setTransparentCircleRadius(0);//设置中间透明圈的半径,值为所占饼图的百分比
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
        l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.CENTER);
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
//        dataSet.setSliceSpace(4);//设置间距
            dataSet.setValueLinePart1OffsetPercentage(80.f);//折线中第一段起始位置相对于区块的偏移量, 数值越大, 折线距离区块越远
            dataSet.setValueLinePart1Length(1.1f);//折线中第一段长度占比
            dataSet.setValueLinePart2Length(1.2f);///折线中第二段长度最大占比
            dataSet.setXValuePosition(PieDataSet.ValuePosition.OUTSIDE_SLICE);//隐藏扇形区域内的显示数据
            dataSet.setYValuePosition(PieDataSet.ValuePosition.INSIDE_SLICE);
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

    private void init01() {
        ArrayList<String> arraylableList = new ArrayList();
        ArrayList<PieEntry> entries;
        entries = new ArrayList<>();
        entries.add(new PieEntry(30, "板材工人50单5000元"));
        entries.add(new PieEntry(10, "数控操作员50单5000元"));
        entries.add(new PieEntry(10, "销售50单5000元"));
        entries.add(new PieEntry(30, "财务50单5000元"));
        entries.add(new PieEntry(70, "保安50单5000元"));
        arraylableList.add("板材工人");
        arraylableList.add("数控操作员");
        arraylableList.add("销售");
        arraylableList.add("财务");
        arraylableList.add("保安");
        PicChartUtils.getInstance().InitAnnulusPicChatr(picChart1, entries, arraylableList, StringUtils.colors, "上海红田温县工厂");
    }

    private void init02() {
        ArrayList<String> arraylableList = new ArrayList();
        ArrayList<PieEntry> entries;
        entries = new ArrayList<>();
        Name name1, name2, name3, name4, name5;
        name1 = new Name();
        name2 = new Name();
        name3 = new Name();
        name4 = new Name();
        name5 = new Name();
        name1.setIndex("1");
        name1.setMoney("210元");
        name1.setName("28单");
        name2.setIndex("2");
        name2.setMoney("3400元");
        name2.setName("87单");
        name3.setIndex("3");
        name3.setMoney("3900元");
        name4.setName("90单");
        name4.setIndex("4");
        name4.setMoney("10元");
        name4.setName("54单");
        name5.setIndex("5");
        name5.setMoney("2400元");
        name5.setName("13单");
        entries.add(new PieEntry(30, "板材工人50单5000元", name1));
        entries.add(new PieEntry(10, "数控操作员50单5000元", name2));
        entries.add(new PieEntry(10, "销售50单5000元", name3));
        entries.add(new PieEntry(30, "财务50单5000元", name4));
        entries.add(new PieEntry(70, "保安50单5000元", name5));
        arraylableList.add("板材工人");
        arraylableList.add("数控操作员");
        arraylableList.add("销售");
        arraylableList.add("财务");
        arraylableList.add("保安");
        InitPicChatr(picChart2, entries, arraylableList, StringUtils.colors);

    }

    private void init03() {
        ArrayList<PieEntry> entries01 = new ArrayList<>();
        ArrayList<String> namelist01 = new ArrayList<>();
        entries01.add(new PieEntry(30, "利友"));
        entries01.add(new PieEntry(20, "长通"));
        entries01.add(new PieEntry(10, "豫德隆"));
        entries01.add(new PieEntry(40, "洪运"));
        entries01.add(new PieEntry(30, "卡行天下"));
        entries01.add(new PieEntry(30, "远航"));
        entries01.add(new PieEntry(20, "联成"));
        entries01.add(new PieEntry(10, "腾达"));
        namelist01.add("利友:40");
        namelist01.add("长通:10");
        namelist01.add("豫德隆:30");
        namelist01.add("洪运:10");
        namelist01.add("卡行天下:30");
        namelist01.add("远航:30");
        namelist01.add("联成:10");
        namelist01.add("腾达:20");
        InitAnnulusPicChatr(picChart3, entries01, namelist01, StringUtils.colors, "物流公司");

    }

    public void InitPicChatr(PieChart picChart, ArrayList<PieEntry> entries, ArrayList<String> arraylableList, int[] colors) {
        picChart.setUsePercentValues(true);//设置饼图是否使用百分比
        picChart.getDescription().setEnabled(false);//不显示数据描述 右下角的文字
        picChart.setExtraOffsets(5, 10, 5, 5);
        picChart.setDragDecelerationFrictionCoef(0.95f);
        picChart.setCenterText("上海红田温县工厂");//有中心圆的时候的中间 文字
//        picChart.setDrawSliceText(false);//只显示百分比
        picChart.setEntryLabelColor(Color.BLACK);//扇形文字颜色
        picChart.setEntryLabelTextSize(10f);//扇形文字大小
        picChart.setExtraOffsets(50.f, 0f, 50.f, 0f);//调整控件的外边距
        picChart.setDrawHoleEnabled(true);//是否显示饼图中间空白区域，默认显示
        picChart.setHoleColor(Color.WHITE);//设置中间圆盘的颜色
        picChart.setTransparentCircleColor(Color.WHITE);//–内圆边框色
        picChart.setTransparentCircleAlpha(110);//–内圆边框透明度
        picChart.setHoleRadius(0);//设置饼状图半径为0
        picChart.setTransparentCircleRadius(0);//设置中间透明圈的半径,值为所占饼图的百分比
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
        l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.CENTER);
        l.setOrientation(Legend.LegendOrientation.HORIZONTAL);
        l.setTextSize(10f);
        l.setXEntrySpace(1f);//设置下面图例间距
        l.setDrawInside(true);//
        l.setEnabled(true);//
        l.setWordWrapEnabled(true);//自动换行
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
        PieDataSet dataSet = new PieDataSet(entries, "");////控件右上角的说明文字
//        dataSet.setValueTextColor(R.color.orange_e05a17);
        dataSet.setSelectionShift(5f);//部分区域被选中时多出的长度
        dataSet.setColors(colors);
        dataSet.setSliceSpace(4);//设置间距
        dataSet.setValueLinePart1OffsetPercentage(80.f);//折线中第一段起始位置相对于区块的偏移量, 数值越大, 折线距离区块越远
        dataSet.setValueLinePart1Length(1.1f);//折线中第一段长度占比
        dataSet.setValueLinePart2Length(1.2f);///折线中第二段长度最大占比
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

    public void InitAnnulusPicChatr(PieChart picChart, ArrayList<PieEntry> entries, ArrayList<String> arraylableList, int[] colors, String CenterText) {
        picChart.setUsePercentValues(true);//设置饼图是否使用百分比
        picChart.getDescription().setEnabled(false);//不显示数据描述 右下角的文字
        picChart.setExtraOffsets(5, 10, 5, 5);
        picChart.setDragDecelerationFrictionCoef(0.95f);
        picChart.setCenterText(CenterText);//有中心圆的时候的中间 文字
//        picChart.setDrawSliceText(false);//只显示百分比
        picChart.setEntryLabelColor(Color.BLACK);//扇形文字颜色
        picChart.setEntryLabelTextSize(10f);//扇形文字大小
        picChart.setExtraOffsets(30f, 20f, 80f, 0f);//调整控件的外边距
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
        l.setVerticalAlignment(Legend.LegendVerticalAlignment.CENTER);////设置显示方向
        l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.RIGHT);
        l.setOrientation(Legend.LegendOrientation.VERTICAL);
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

    public class Name {
        public String index;
        public String name;
        public String money;

        public String getIndex() {
            return index;
        }

        public void setIndex(String index) {
            this.index = index;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getMoney() {
            return money;
        }

        public void setMoney(String money) {
            this.money = money;
        }
    }
}
