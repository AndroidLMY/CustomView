package com.example.lmy.customview.FragmentViewPager;

import android.os.Bundle;
import androidx.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.lmy.customview.R;

/**
 * @功能:
 * @Creat 2019/03/22 11:46
 * @User Lmy
 * @By Android Studio
 */
public class Fragment1 extends BaseFragment {
    private View view;
    /**
     * 标志位，标志已经初始化完成
     */
    private boolean isPrepared;

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        // 配置setUserVisibleHint（）方法
        setUserVisibleHint(true);
        super.onActivityCreated(savedInstanceState);
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment1_layout, container, false);
        initViews(view);
        isPrepared = true;
        return view;
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        //可见的并且是初始化之后才加载
        if (isPrepared && isVisibleToUser) {
            //处理数据
        }
    }


    public void initViews(View view) {
    }

}

