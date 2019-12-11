package com.example.lmy.customview.CustomView;

/**
 * 功能：
 * 自定义形状的TextView 圆形 椭圆形
 * @author：zhangerpeng
 * @create：2018\12\14 0014 16:17
 * @version：2018 1.0
 * Created with IntelliJ IDEA
 */

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import androidx.annotation.ColorInt;
import android.util.AttributeSet;
import android.widget.TextView;
import com.example.lmy.customview.R;


@SuppressLint("AppCompatCustomView")
public class CircleTextView extends TextView {
    private Context mContext;

    private Paint mPaint;//画笔

    private int mPaintNormalColor = 0xFFDCDCDC;//     * 画笔颜色 默认灰色

    private int mPaintSelectColor = 0xFFDCDCDC;//     *画笔颜色 选中时的颜色,默认灰色

    private boolean isChecked = false;//是否填充颜色
    public CircleTextView(Context context) {
        super(context);
    }
    public CircleTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initPaint(context,attrs);
    }
    public CircleTextView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        initPaint(context,attrs);
    }

    public void setmPaintNormalColor(@ColorInt int mPaintNormalColor) {
        this.mPaintNormalColor = mPaintNormalColor;
    }
    public void setmPaintSelectColor(@ColorInt int mPaintSelectColor) {
        //设置选中颜色 默认灰色
        this.mPaintSelectColor = mPaintSelectColor;
    }

    /**
     * 初始化画笔和自定义属性
     */
    private void initPaint(Context context,AttributeSet attrs){
        mContext = context;
        TypedArray typeArray = context.obtainStyledAttributes(attrs, R.styleable.CircleTextView);
        mPaintNormalColor = typeArray.getColor(R.styleable.CircleTextView_paintNormalColor,mPaintNormalColor);
        mPaintSelectColor = typeArray.getColor(R.styleable.CircleTextView_paintSelectColor,mPaintSelectColor);
        isChecked = typeArray.getBoolean(R.styleable.CircleTextView_checked,isChecked);
        mPaint = new Paint();
    }
    @Override
    protected void onDraw(Canvas canvas) {
        //抗锯齿
        mPaint.setAntiAlias(true);
        if (isChecked) {
            //画笔颜色
            mPaint.setColor(mPaintSelectColor);
            mPaint.setStyle(Paint.Style.FILL);
        }else{
            //画笔颜色
            mPaint.setColor(mPaintNormalColor);
            //画笔样式:空心
            mPaint.setStyle(Paint.Style.FILL);
        }

        //创建一个区域,限制圆弧范围
        RectF rectF = new RectF();
        //设置半径,比较长宽,取最大值
        int radius = getMeasuredWidth() > getMeasuredHeight() ? getMeasuredWidth() : getMeasuredHeight();
        //设置Padding 不一致,绘制出的是椭圆;一致的是圆形
        rectF.set(getPaddingLeft(),getPaddingTop(),radius-getPaddingRight(),radius-getPaddingBottom());
        //绘制圆弧
        canvas.drawArc(rectF,0,360,false,mPaint);
        //最后调用super方法,解决文本被所绘制的圆圈背景覆盖的问题
        super.onDraw(canvas);
    }

    /**
     * 设置是否填充颜色
     * @param isSelect
     */
    public void setChecked(boolean isSelect){
        this.isChecked = isChecked;
        invalidate();
    }

    public boolean isChecked() {
        return isChecked;
    }
}