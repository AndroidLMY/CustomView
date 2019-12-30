package com.example.lmy.customview.FragmentViewPager;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import com.androidlmy.headcustomview.HeadCustomView;
import com.androidlmy.headcustomview.OnClickHeadView;
import com.example.lmy.customview.R;
import com.example.lmy.customview.base.BaseActivity;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @功能: Tab+ViewPager布局
 * @Creat 2019/12/10 16:35
 * @User Lmy
 * @Compony zaituvideo
 */
public class TabActivity extends BaseActivity implements OnClickHeadView {

    @BindView(R.id.title)
    HeadCustomView title;
    @BindView(R.id.tab)
    TabLayout tab;
    @BindView(R.id.viewpager)
    CustomViewPager viewpager;
    private FragmentAdapter fragmentAdapter;
    private Fragment1 fragment1;
    private Fragment2 fragment2;
    private Fragment3 fragment3;
    private List<Fragment> fragmentList;
    private String[] mTitles = new String[]{"测试1", "测试2", "测试3"};

    public static void show(Context context) {
        context.startActivity(new Intent(context, TabActivity.class));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab);
        ButterKnife.bind(this);
        tab = findViewById(R.id.tab);
        viewpager = findViewById(R.id.viewpager);
        initTitle();
        init();
    }

    @Override
    public void initTitle() {
        title.setBackgroundColor(getResources().getColor(R.color.blue));
        title.setTitle("Tab+ViewPager布局");
        title.setTitleTextColor(getResources().getColor(R.color.white));
        title.setClickCallBack(this);

    }

    private void init() {
        fragmentList = new ArrayList<>();
        fragment1 = new Fragment1();
        fragment2 = new Fragment2();
        fragment3 = new Fragment3();
        fragmentList.add(fragment1);
        fragmentList.add(fragment2);
        fragmentList.add(fragment3);
        fragmentAdapter = new FragmentAdapter(getSupportFragmentManager(), fragmentList, mTitles);
        viewpager.setAdapter(fragmentAdapter);
        viewpager.setScanScroll(true);//设置viewpager是否可以滑动
        viewpager.setOffscreenPageLimit(3);
        //将TabLayout与ViewPager绑定在一起
        tab.setupWithViewPager(viewpager);
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
