package com.example.lmy.customview.RecyclerViewAddTitle.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.androidlmy.headcustomview.HeadCustomView;
import com.androidlmy.headcustomview.OnClickHeadView;
import com.example.lmy.customview.R;
import com.example.lmy.customview.RecyclerViewAddTitle.adapter.RecyclerAdapter;
import com.example.lmy.customview.base.BaseActivity;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @功能: RecyclerView增加头部和尾部
 * @Creat 2019/12/10 14:25
 * @User Lmy
 * @Compony zaituvideo
 */
public class RecyclerViewActivity extends BaseActivity implements OnClickHeadView {
    @BindView(R.id.title)
    HeadCustomView title;
    @BindView(R.id.recyclerview)
    RecyclerView recyclerview;
    private RecyclerAdapter adapter;
    private LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);

    public static void show(Context context) {
        context.startActivity(new Intent(context, RecyclerViewActivity.class));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view);
        ButterKnife.bind(this);
        initTitle();
        ArrayList list = new ArrayList();
        for (int i = 0; i < 30; i++) {
            list.add(String.valueOf(i));
        }
        adapter = new RecyclerAdapter(RecyclerViewActivity.this, list);
        recyclerview.setLayoutManager(linearLayoutManager);
        recyclerview.setAdapter(adapter);
        adapter.setItemOnClistener(new RecyclerAdapter.ItemOnClistener() {
            @Override
            public void itemClick(String s, int p) {
                Toast.makeText(RecyclerViewActivity.this, s, Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void initTitle() {
        title.setBackgroundColor(getResources().getColor(R.color.blue));
        title.setTitle("RecyclerView增加头部和尾部");
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
