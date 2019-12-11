package com.example.lmy.customview.FragmentViewPager;

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
public class FragmentAdapter extends FragmentPagerAdapter {
    private String[] mTitles ;
    private List<Fragment> fragmentList;

    public FragmentAdapter(FragmentManager fm, List<Fragment> mlist, String[] mTitles) {
        super(fm);
        this.fragmentList = mlist;
        this.mTitles = mTitles;

    }

    @Override
    public Fragment getItem(int position) {
        return fragmentList.get(position);
    }

    @Override
    public int getCount() {
        return mTitles.length;
    }

    //ViewPager与TabLayout绑定后，这里获取到PageTitle就是Tab的Text
    @Override
    public CharSequence getPageTitle(int position) {
        return mTitles[position];
    }


}
