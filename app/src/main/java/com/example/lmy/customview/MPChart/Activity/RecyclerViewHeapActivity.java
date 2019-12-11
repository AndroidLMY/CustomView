package com.example.lmy.customview.MPChart.Activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.androidlmy.headcustomview.HeadCustomView;
import com.androidlmy.headcustomview.OnClickHeadView;
import com.example.lmy.customview.MPChart.Adapter.RecyclerviewHAdapter;
import com.example.lmy.customview.R;
import com.example.lmy.customview.base.BaseActivity;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * @功能: 自定义横向堆积图
 * @Creat 2019/12/10 14:49
 * @User Lmy
 * @Compony zaituvideo
 */
public class RecyclerViewHeapActivity extends BaseActivity implements OnClickHeadView {
    @BindView(R.id.title)
    HeadCustomView title;
    @BindView(R.id.recyclerview)
    RecyclerView recyclerview;
    @BindView(R.id.tv_name_01)
    TextView tvName01;
    @BindView(R.id.tv_name_02)
    TextView tvName02;
    @BindView(R.id.tv_name_03)
    TextView tvName03;
    private LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
    private RecyclerviewHAdapter recyclerviewHAdapter;
    private ArrayList<String> list1 = new ArrayList();
    private ArrayList<String> list2 = new ArrayList();
    private ArrayList<String> list3 = new ArrayList();

    public static void show(Context context) {
        context.startActivity(new Intent(context, RecyclerViewHeapActivity.class));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view_heap);
        ButterKnife.bind(this);
        initTitle();
        for (int i = 0; i < 10; i++) {
            list1.add(String.valueOf(Math.random() * 100));
            list2.add(String.valueOf(Math.random() * 100));
            list3.add(String.valueOf(Math.random() * 100));
        }
        if (recyclerviewHAdapter == null) {
            recyclerviewHAdapter = new RecyclerviewHAdapter(this, list1, list2, list3);
            recyclerview.setLayoutManager(linearLayoutManager);
            recyclerview.setAdapter(recyclerviewHAdapter);
        } else {
            recyclerviewHAdapter.notifyDataSetChanged();
        }

    }
    @Override
    public void initTitle() {
        title.setBackgroundColor(getResources().getColor(R.color.blue));
        title.setTitle("自定义横向堆积图");
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
