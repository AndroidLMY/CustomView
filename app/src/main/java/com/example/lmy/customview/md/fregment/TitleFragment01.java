package com.example.lmy.customview.md.fregment;

/**
 * @author Lmy
 * @功能:
 * @Creat 2020/5/29 9:44
 * @Compony 永远相信美好的事物即将发生
 */

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.fragment.app.Fragment;

import com.example.lmy.customview.R;
import com.example.lmy.customview.md.VerticalViewPager;

import butterknife.BindView;

public class TitleFragment01 extends Fragment {

    @BindView(R.id.content)
    LinearLayout content;
    private VerticalViewPager viewPager;

    public TitleFragment01(VerticalViewPager vp) {
        this.viewPager = vp;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(
                R.layout.fragment_title01, container, false);
        return rootView;
    }

}
