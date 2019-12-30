package com.example.lmy.customview.banner;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.androidlmy.headcustomview.HeadCustomView;
import com.example.lmy.customview.R;
import com.example.lmy.customview.base.BaseActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class BannerActivity extends BaseActivity {

    @BindView(R.id.title)
    HeadCustomView title;
    @BindView(R.id.recycler)
    BannerLayout recycler;
    public static void show(Context context) {
        context.startActivity(new Intent(context, BannerActivity.class));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_banner);
        ButterKnife.bind(this);
        recycler.setAutoPlaying(false);//禁止自动滚动
        recycler.setMoveSpeed(1.4f);//手指移动速度
        List<String> list = new ArrayList<>();
        list.add("http://47.100.250.181:8080/images/37WKKVZF.jpg");
        list.add("http://47.100.250.181:8080/images/1KCX5YEG.png");
        list.add("http://47.100.250.181:8080/images/goods.png");
        list.add("http://47.100.250.181:8080/images/37WKKVZF.jpg");
        list.add("http://47.100.250.181:8080/images/1KCX5YEG.png");
        list.add("http://47.100.250.181:8080/images/goods.png");
        list.add("http://47.100.250.181:8080/images/37WKKVZF.jpg");
        list.add("http://47.100.250.181:8080/images/1KCX5YEG.png");
        list.add("http://47.100.250.181:8080/images/goods.png");
        list.add("http://47.100.250.181:8080/images/37WKKVZF.jpg");
        list.add("http://47.100.250.181:8080/images/1KCX5YEG.png");
        list.add("http://47.100.250.181:8080/images/goods.png");
        list.add("http://47.100.250.181:8080/images/37WKKVZF.jpg");
        list.add("http://47.100.250.181:8080/images/1KCX5YEG.png");
        list.add("http://47.100.250.181:8080/images/goods.png");
        WebBannerAdapter webBannerAdapter = new WebBannerAdapter(this, list);
        webBannerAdapter.setOnBannerItemClickListener(new BannerLayout.OnBannerItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Toast.makeText(BannerActivity.this, "点击了第" + position + "  项", Toast.LENGTH_SHORT).show();
            }
        });

        recycler.setAdapter(webBannerAdapter);

    }
}
