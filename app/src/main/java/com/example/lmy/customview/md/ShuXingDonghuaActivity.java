package com.example.lmy.customview.md;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.core.widget.NestedScrollView;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import com.example.lmy.customview.R;
import com.example.lmy.customview.Utils.LogUtil;
import com.example.lmy.customview.base.BaseActivity;
import com.google.android.material.appbar.AppBarLayout;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ShuXingDonghuaActivity extends BaseActivity {


    @BindView(R.id.appbarlayout)
    AppBarLayout appbarlayout;
    @BindView(R.id.nestedscrollview)
    NestedScrollView nestedscrollview;
    @BindView(R.id.viewpager2)
    ViewPager2 viewpager2;
    private FragmentStateAdapter pagerAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shu_xing_donghua);
        ButterKnife.bind(this);
//        pagerAdapter = new ScreenSlidePagerAdapter(this);
//        viewpager2.setAdapter(pagerAdapter);

        nestedscrollview.setOnScrollChangeListener(new NestedScrollView.OnScrollChangeListener() {
            @Override
            public void onScrollChange(NestedScrollView v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                LogUtil.e("HHHHH" + scrollX + scrollY + oldScrollX + oldScrollY);
                if (scrollY > oldScrollY){
                    //上滑
                    LogUtil.e("HHHHH" + "上滑");
                }else{
                    //下滑
                    LogUtil.e("HHHHH" + "下滑");
                }
            }
        });
    }
}
