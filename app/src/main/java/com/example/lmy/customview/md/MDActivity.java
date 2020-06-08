package com.example.lmy.customview.md;

import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import com.example.lmy.customview.R;
import com.example.lmy.customview.md.fregment.TitleFragment01;
import com.example.lmy.customview.md.fregment.TitleFragment02;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author Lmy
 * @功能: android md风格的使用demo
 * @Creat
 * @Compony 永远相信美好的事物即将发生
 */
public class MDActivity extends AppCompatActivity {

    @BindView(R.id.pager)
    VerticalViewPager pager;

    public static void show(Context context) {
        context.startActivity(new Intent(context, MDActivity.class));
    }

    @BindView(R.id.bt_01)
    Button bt01;
    @BindView(R.id.bt_02)
    Button bt02;
    @BindView(R.id.bt_03)
    Button bt03;
    @BindView(R.id.bt_04)
    Button bt04;
    @BindView(R.id.bt_05)
    Button bt05;
    @BindView(R.id.bt_06)
    Button bt06;
    private Viewpager2Adapter pagerAdapter;
    private List<Fragment> fragmentList = new ArrayList<>();
    private int nu01 = 50;
    private int nu02 = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_m_d);
        ButterKnife.bind(this);
        fragmentList.add(new TitleFragment01(pager));
        fragmentList.add(new TitleFragment02(pager));
        pagerAdapter = new Viewpager2Adapter(getSupportFragmentManager(), fragmentList);
        pager.setAdapter(pagerAdapter);
        pager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                pager.index=position;
                //为ViewPager设置高度
                ViewGroup.LayoutParams params = pager.getLayoutParams();
                pager.setLayoutParams(params);
            }

            @Override
            public void onPageSelected(int position) {
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
    }

    @OnClick({R.id.bt_01, R.id.bt_02, R.id.bt_03, R.id.bt_04, R.id.bt_05, R.id.bt_06})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.bt_01:
                CoordinatorLayoutActivity.show(this);
                break;
            case R.id.bt_02:
                break;
            case R.id.bt_03:
                break;
            case R.id.bt_04:
                break;
            case R.id.bt_05:
                break;
            case R.id.bt_06:
                break;
            default:
                break;
        }
    }
}
