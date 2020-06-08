package com.example.lmy.customview.md;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.List;

/**
 * @功能:
 * @Creat 2019/03/22 11:51
 * @User Lmy
 * @By Android Studio
 */
public class Viewpager2Adapter extends FragmentPagerAdapter {
    private List<Fragment> fragmentList;

    public Viewpager2Adapter(FragmentManager fm, List<Fragment> mlist) {
        super(fm);
        this.fragmentList = mlist;

    }

    @Override
    public Fragment getItem(int position) {
        return fragmentList.get(position);
    }

    @Override
    public int getCount() {
        return fragmentList.size();
    }


}
