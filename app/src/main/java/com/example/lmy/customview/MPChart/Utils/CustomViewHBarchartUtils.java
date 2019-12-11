package com.example.lmy.customview.MPChart.Utils;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.lmy.customview.R;
import com.example.lmy.customview.MPChart.Bean.Type;

import java.text.DecimalFormat;
import java.util.List;

/**
 * @功能:自定义横向柱状图工具类
 * @Creat 2019/05/13 17:55
 * @User Lmy
 * @By Android Studio
 */
public class CustomViewHBarchartUtils {


    DecimalFormat format = new DecimalFormat("##.##");

    public static CustomViewHBarchartUtils customViewHBarchartUtils;
    public static CustomViewHBarchartUtils getInstance() {
        if (customViewHBarchartUtils == null) {
            customViewHBarchartUtils = new CustomViewHBarchartUtils();
        }
        return customViewHBarchartUtils;
    }
    public void initChart(View itemView,LinearLayout container, List<Type> typeList, int color,double maxScale) {
        container.removeAllViews();
        for (int i = 0; i < typeList.size(); i++) {
            final View item = LayoutInflater.from(itemView.getContext()).inflate(R.layout.h_bar_item, container, false);
            final Type type = typeList.get(i);
            ((TextView) item.findViewById(R.id.name)).setText(type.getTypeName());
            final View bar = item.findViewById(R.id.bar);
            bar.setBackgroundColor(color);
            ((TextView) item.findViewById(R.id.percent)).setText(format.format(type.getTypeScale() * 100) + "%");
            ((TextView) item.findViewById(R.id.percent)).setTextColor(color);
            TextView barTextview = (TextView) bar;
            barTextview.setText(format.format(type.getTypeScale() * 100) + "%");
            final double finalMaxScale = maxScale;
            item.getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
                @Override
                public boolean onPreDraw() {
                    item.getViewTreeObserver().removeOnPreDrawListener(this);
                    int barContainerWidth = item.findViewById(R.id.bar_container).getWidth();
                    int percentTxtWidth = item.findViewById(R.id.percent).getWidth();
                    final int initWidth = barContainerWidth - percentTxtWidth;
                    final LinearLayout.LayoutParams lp = (LinearLayout.LayoutParams) bar.getLayoutParams();
                    lp.width = (int) (initWidth * type.getTypeScale() / finalMaxScale * 100 / 100);
                    bar.setLayoutParams(lp);
                    item.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            final int initWidth = bar.getWidth();
                            final ObjectAnimator anim = ObjectAnimator.ofFloat(bar, "alpha", 0.0F, 1.0F).setDuration(1500);
                            anim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                                @Override
                                public void onAnimationUpdate(ValueAnimator valueAnimator) {
                                    float cVal = (Float) anim.getAnimatedValue();
                                    lp.width = (int) (initWidth * cVal);
                                    bar.setLayoutParams(lp);
                                }
                            });
                            anim.start();
                        }
                    }, 0);
                    return false;
                }
            });
            container.addView(item);
        }
    }

}
