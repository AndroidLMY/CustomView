package com.example.lmy.customview.MPChart.Activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.androidlmy.headcustomview.HeadCustomView;
import com.androidlmy.headcustomview.OnClickHeadView;
import com.example.lmy.customview.MPChart.Utils.BarChartUtils;
import com.example.lmy.customview.R;
import com.example.lmy.customview.base.BaseActivity;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * @功能: 柱形图展示
 * @Creat 2019/12/10 14:48
 * @User Lmy
 * @Compony zaituvideo
 */
public class BarChartActivity extends BaseActivity implements OnClickHeadView {
    @BindView(R.id.title)
    HeadCustomView title;
    @BindView(R.id.barChart)
    BarChart barChart;
    @BindView(R.id.doublebarChart)
    BarChart doublebarChart;

    public static void show(Context context) {
        context.startActivity(new Intent(context, BarChartActivity.class));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zhu_xing_tu);
        ButterKnife.bind(this);
        initTitle();
        lodData01();
        lodData02();
        barChart.setOnChartValueSelectedListener(new OnChartValueSelectedListener() {
            @Override
            public void onValueSelected(Entry e, Highlight h) {
                e.getX();       //X轴坐标 记得转 int
                e.getY();       //当前柱状图Y轴值
                e.getIcon();    //对应 BarEntry(float x, float y, Drawable icon)
                e.getData();    //对应 BarEntry(float x, float y, Object data)
                Name name = (Name) e.getData();
                Toast.makeText(BarChartActivity.this, "您点击了第" + name.getName() + "个柱状图数据", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected() {
                Toast.makeText(BarChartActivity.this, "取消选中", Toast.LENGTH_SHORT).show();
            }
        });
        doublebarChart.setOnChartValueSelectedListener(new OnChartValueSelectedListener() {
            @Override
            public void onValueSelected(Entry e, Highlight h) {
                e.getX();       //X轴坐标 记得转 int
                e.getY();       //当前柱状图Y轴值
                e.getIcon();    //对应 BarEntry(float x, float y, Drawable icon)
                e.getData();    //对应 BarEntry(float x, float y, Object data)
                Name name = (Name) e.getData();
                if (name.getType().equals("1")) {
                    Toast.makeText(BarChartActivity.this, "点击了类型为1x坐标为:" + name.getName(), Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(BarChartActivity.this, "点击了类型为2x坐标为:" + name.getName(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onNothingSelected() {
                Toast.makeText(BarChartActivity.this, "取消选中", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void initTitle() {
        title.setBackgroundColor(getResources().getColor(R.color.blue));
        title.setTitle("柱状图");
        title.setTitleTextColor(getResources().getColor(R.color.white));
        title.setClickCallBack(this);

    }

    private void lodData02() {
        Name name1, name2;
        ArrayList<BarEntry> barEntries1, barEntries2;
        ArrayList<String> strings = new ArrayList<>();
        barEntries1 = new ArrayList<>();
        barEntries2 = new ArrayList<>();
        for (int i = 1; i <= 12; i++) {
            strings.add(i + "A");
            name1 = new Name();
            name1.setName(i + "");
            name1.setType("1");
            name2 = new Name();
            name2.setName(i + "");
            name2.setType("2");
            barEntries1.add(new BarEntry(i, i, name1));
            barEntries2.add(new BarEntry(i, i, name2));
        }
        BarChartUtils.getInstance().initDoubleBarChart(this, doublebarChart, barEntries1, barEntries2, strings);
    }

    private void lodData01() {

        ArrayList<String> xAxisList = new ArrayList<>();//自定义X轴的坐标值
        ArrayList<BarEntry> barEntries = new ArrayList<BarEntry>();//柱状图y轴数据集合
        List<Integer> colorlist = new ArrayList<Integer>();//柱状图的色彩
        Name name;
        //模拟数据
        xAxisList.add("A");
        xAxisList.add("B");
        xAxisList.add("C");
        xAxisList.add("D");
        xAxisList.add("E");
        xAxisList.add("F");
        xAxisList.add("G");
        xAxisList.add("H");
        xAxisList.add("I");
        xAxisList.add("J");
        xAxisList.add("K");
        xAxisList.add("L");//自定义x轴数值
        colorlist.add(0xFF33C25E);//设置颜色
        for (int i = 1; i <= 12; i++) {
            name = new Name();
            name.setName(i + "");
            barEntries.add(new BarEntry(i, new Double(Math.random() * 100).intValue(), name));
        }//初始化y轴数据集合
        BarChartUtils.getInstance().InitBarChart1(this, barChart, barEntries, xAxisList, colorlist, 7);

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
        public String name;
        public String type;

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
