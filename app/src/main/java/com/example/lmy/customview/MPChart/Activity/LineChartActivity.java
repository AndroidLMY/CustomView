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
import com.example.lmy.customview.MPChart.Utils.LineChartMannager;
import com.example.lmy.customview.R;
import com.example.lmy.customview.base.BaseActivity;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @功能: 折线统计图带背景 和不带背景 自定义x轴 自定义缩放
 * @Creat 2019/12/10 14:49
 * @User Lmy
 * @Compony zaituvideo
 */
public class LineChartActivity extends BaseActivity implements OnClickHeadView {
    @BindView(R.id.title)
    HeadCustomView title;
    @BindView(R.id.lineChartbg)
    LineChart lineChartbg;
    @BindView(R.id.lineChartNobg)
    LineChart lineChartNobg;
    private ArrayList<String> xlist = new ArrayList<>();

    public static void show(Context context) {
        context.startActivity(new Intent(context, LineChartActivity.class));
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zhe_xian_tu);
        ButterKnife.bind(this);
        initTitle();
        ArrayList<String> namelist = new ArrayList<>();
        namelist.add("经销商A");
        namelist.add("经销商B");
        namelist.add("经销商C");
        namelist.add("经销商D");
        ArrayList<ArrayList<Entry>> list = new ArrayList<>();
        ArrayList<Entry> values;
        for (int i = 0; i < 2; i++) {
            //这一层循环用于遍历一共有几条线的数据
            values = new ArrayList<>();
            for (int k = 0; k < 20; k++) {
                values.add(new Entry(k, new Double(Math.random() * 100).intValue()));
            }
            list.add(values);
        }
        for (int i = 1; i <= 20; i++) {
            xlist.add(String.valueOf(i).concat("月"));
            //自定义x轴的数据
        }
        LineChartMannager.getInstance().initBackground(this, lineChartbg, list, namelist, xlist, 10, 100, 10);
        LineChartMannager.getInstance().initNoBackground(this, lineChartNobg, list, namelist, xlist, 10, 100, 10);

    }
    @Override
    public void initTitle() {
        title.setBackgroundColor(getResources().getColor(R.color.blue));
        title.setTitle("折线统计图");
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
