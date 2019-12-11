package com.example.lmy.customview.FragmentViewPager;

import android.app.Activity;
import android.content.Context;
import androidx.fragment.app.Fragment;

/**
 * @功能:
 * @Creat 2019/03/22 11:47
 * @User Lmy
 * @By Android Studio
 */
public abstract class BaseFragment extends Fragment {

    /**
     * Fragment当前状态是否可见
     */
    public boolean isVisible;

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (getUserVisibleHint()) {
            isVisible = true;
            onVisible();
        } else {
            isVisible = false;
            onInvisible();
        }
    }

    /**
     * 初始化控件
     */
    public void initViews() {

    }

    /**
     * 初始化数据
     */
    public void initData() {

    }

    /**
     * 加载数据
     */
    public void loadData() {

    }

    /**
     * 加载适配器
     */
    public void loadAdapter() {

    }

    /**
     * 初始化监听
     */
    public void initListener() {

    }

    /**
     * 初始适配器
     */
    public void initAdapter() {
    }

    /**
     * Fragment中getActivity()或getContext()返回null的问题
     */
    public Activity mActivity;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.mActivity = (Activity) context;
    }


    /**
     * 可见时，延迟加载
     */
    public void onVisible() {

    }


    /**
     * 不可见
     */
    public void onInvisible() {

    }

}