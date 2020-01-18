package com.example.lmy.customview.RecyclerviewGroupDemo;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

import androidx.appcompat.widget.AppCompatImageView;

/**
 * @Description: 自定义ImageView 实现宽高相等
 * @author: ZhangYW
 * @time: 2019/1/24 9:13
 */
public class MyImageView extends AppCompatImageView {

	public MyImageView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public MyImageView(Context context) {
		super(context);
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

		setMeasuredDimension(getDefaultSize(0, widthMeasureSpec),
				getDefaultSize(0, heightMeasureSpec));
		int childWidthSize = getMeasuredWidth();

		int childHeightSize = getMeasuredHeight();

		// 高度和宽度一样

		heightMeasureSpec = widthMeasureSpec = View.MeasureSpec.makeMeasureSpec(
				childWidthSize, View.MeasureSpec.EXACTLY);
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
	}
	
}
