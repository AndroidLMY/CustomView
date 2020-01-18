package com.example.lmy.customview.RecyclerviewGroupDemo;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.androidlmy.headcustomview.HeadCustomView;
import com.example.lmy.customview.R;
import com.example.lmy.customview.RecyclerviewGroupDemo.adapter.MyAdapter;
import com.example.lmy.customview.RecyclerviewGroupDemo.adapter.SimpleHeaderAdapter;
import com.example.lmy.customview.base.BaseActivity;
import com.truizlop.sectionedrecyclerview.SectionedSpanSizeLookup;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RecyclerviewGroupActivity extends BaseActivity {
    @BindView(R.id.title)
    HeadCustomView title;
    @BindView(R.id.main_rv)
    RecyclerView mainRv;
    @BindView(R.id.bt_01)
    Button bt01;
    @BindView(R.id.bt_02)
    Button bt02;
    private SimpleHeaderAdapter simpleHeaderAdapter;
    private MyAdapter myAdapter;

    private List<MyBean> list = new ArrayList<>();

    private List<MyBean.ItemBean> itemBeans = new ArrayList<>();

    public static void show(Context context) {
        context.startActivity(new Intent(context, RecyclerviewGroupActivity.class));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recyclerview_group);
        ButterKnife.bind(this);

    }

    @OnClick({R.id.bt_01, R.id.bt_02})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.bt_01:
                myAdapter = new MyAdapter(this, list);
                mainRv.setHasFixedSize(true);
                GridLayoutManager layoutManager = new GridLayoutManager(this, 4);
                SectionedSpanSizeLookup lookup = new SectionedSpanSizeLookup(myAdapter, layoutManager);
                layoutManager.setSpanSizeLookup(lookup);
                mainRv.setLayoutManager(layoutManager);
                mainRv.setAdapter(myAdapter);


                for (int i = 0; i < 100; i++) {
                    MyBean myBean = new MyBean();
                    myBean.setHeader("header " + i);
                    myBean.setFooter("footer " + i);
                    for (int j = 0; j < 5; j++) {
                        MyBean.ItemBean itemBean = new MyBean.ItemBean();
                        itemBean.setContent(i + "");
                        myBean.getList().add(itemBean);


                    }
                    list.add(myBean);
                }
                myAdapter.notifyDataSetChanged();


                break;
            case R.id.bt_02:
                simpleHeaderAdapter = new SimpleHeaderAdapter(this, list);
                mainRv.setHasFixedSize(true);
                GridLayoutManager layoutManager2 = new GridLayoutManager(this, 4);
                SectionedSpanSizeLookup lookup2 = new SectionedSpanSizeLookup(simpleHeaderAdapter, layoutManager2);
                layoutManager2.setSpanSizeLookup(lookup2);
                mainRv.setLayoutManager(layoutManager2);
                mainRv.setAdapter(simpleHeaderAdapter);
                mainRv.setOnScrollChangeListener(new View.OnScrollChangeListener() {
                    @Override
                    public void onScrollChange(View v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                        //滑动RecyclerView list的时候，根据最上面一个Item的position来切换tab
//
//                        Log.e("GGGG","findFirstCompletelyVisibleItemPosition"+layoutManager2.findFirstCompletelyVisibleItemPosition());
//                        Log.e("GGGG","findFirstVisibleItemPosition"+layoutManager2.findFirstVisibleItemPosition());
//                        Log.e("GGGG","findLastVisibleItemPosition"+layoutManager2.findLastVisibleItemPosition());
//                        Log.e("GGGG","findLastCompletelyVisibleItemPosition"+layoutManager2.findLastCompletelyVisibleItemPosition());
                        Log.e("GGGG", "/////////////////////////////////////////////////////////////////////////////////////////////");
                        int currentPosition = ((RecyclerView.LayoutParams) mainRv.getChildAt(0).getLayoutParams()).getViewAdapterPosition();
                        Log.e("GGGGcurrentPosition", itemBeans.get(currentPosition).getContent() + "//" + currentPosition);

                        Log.d("GGGG", layoutManager2.findFirstVisibleItemPosition() + list.get(layoutManager2.findFirstVisibleItemPosition()).getHeader());
                    }
                });
                for (int i = 0; i < 100; i++) {
                    MyBean myBean = new MyBean();
                    myBean.setHeader("header " + i);
                    myBean.setFooter("footer " + i);
                    for (int j = 0; j < 5; j++) {
                        MyBean.ItemBean itemBean = new MyBean.ItemBean();
                        itemBean.setContent("content " + j);
                        myBean.getList().add(itemBean);
                        itemBeans.add(itemBean);

                    }
                    list.add(myBean);
                }
                simpleHeaderAdapter.notifyDataSetChanged();
                break;
        }
    }
}
