package com.example.lmy.customview.CustomView;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import androidx.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.example.lmy.customview.R;

/**
 * Creat 10:35
 * User Lmy
 */
public class CircleView extends View {

    /**
     * 圆的半径，默认画出最大圆
     */
    private float radius;
    /**
     * 圆的颜色
     */
    private int color;

    private Paint mPaint;

    public CircleView(Context context) {
        super(context);
    }

    public CircleView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        //获取设置的属性值
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.CircleView);
        radius = typedArray.getDimension(R.styleable.CircleView_radius, 0);
        color = typedArray.getColor(R.styleable.CircleView_android_color, Color.RED);
        typedArray.recycle();//注意最后要调用：typedArray.recycle()
        //初始化Paint
        mPaint = new Paint();
        mPaint.setColor(color);
        //实例化一支画笔 这里只设置了颜色
    }

    public CircleView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        RectF rect = new RectF(0, 0, 100, 100);
        //定义一个矩形区域
        RectF oval = new RectF(0,0,200,300);
        RectF rectoval = new RectF(50, 50, 200, 200);

        //计算半径，如果属性中设置了半径则使用设置的值，否则是宽和高一半的较小值
        float mRadius = radius == 0 ? Math.min(getWidth() / 2, getHeight() / 2) : radius;
        //绘制圆
        canvas.drawCircle(getWidth() / 2, //圆心的x坐标
                getHeight() / 2, //圆心的y坐标
                mRadius,//半径
                mPaint);
        /**
        //绘制扇形
        canvas.drawArc(rect, //弧线所使用的矩形区域大小
                0, //开始角度
                90, //扫过的角度
                true, //是否使用中心
                mPaint);
        //矩形区域内切椭圆
        canvas.drawOval(oval, mPaint);
        //圆角矩形
        canvas.drawRoundRect(rectoval,
                30, //x轴的半径
                30, //y轴的半径
                mPaint);
        //一个三角形
        Path path = new Path(); //定义一条路径
        path.moveTo(10, 10); //移动到 坐标10,10
        path.lineTo(50, 60);
        path.lineTo(200,80);
        path.lineTo(10, 10);
        canvas.drawPath(path, mPaint);
         **/

    }

}
