package com.example.lmy.customview.md;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.lmy.customview.R;
import com.example.lmy.customview.Utils.title_utils.StatusBarUtil;
import com.example.lmy.customview.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import jp.wasabeef.glide.transformations.BlurTransformation;

import static com.bumptech.glide.request.RequestOptions.bitmapTransform;

public class CoordinatorLayoutActivity extends BaseActivity implements ObservableScrollView.OnObservableScrollViewListener {
    @BindView(R.id.sv_main_content)
    ObservableScrollView svMainContent;
    @BindView(R.id.tv_header_title)
    TextView tvHeaderTitle;
    @BindView(R.id.ll_header_content)
    LinearLayout llHeaderContent;
    @BindView(R.id.iv_title)
    ImageView ivTitle;

    public static void show(Context context) {
        context.startActivity(new Intent(context, CoordinatorLayoutActivity.class));
    }

    private int mHeight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        StatusBarUtil.setRootViewFitsSystemWindows(this, false);
        //        //设置状态栏透明
        StatusBarUtil.setTranslucentStatus(this);
//        StatusBarUtil.setStatusBarColor(this, getResources().getColor(R.color.blue));
//
        if (!StatusBarUtil.setStatusBarDarkTheme(this, true)) {
            //如果不支持设置深色风格 为了兼容总不能让状态栏白白的看不清, 于是设置一个状态栏颜色为半透明,
            //这样半透明+白=灰, 状态栏的文字能看得清
            StatusBarUtil.setStatusBarColor(this, 0x55000000);
        }
        setContentView(R.layout.activity_coordinator_layout);
        ButterKnife.bind(this);
//        //模糊
//        Resources res = getResources();
//        //拿到初始图
//        Bitmap bmp = BitmapFactory.decodeResource(res, R.drawable.ic_bg_demo);
//        //处理得到模糊效果的图
//        Bitmap blurBitmap = ImageFilter.blurBitmap(this, bmp, 25f);
//        ivTitle.setImageBitmap(blurBitmap);
        Glide.with(this).load(R.drawable.ic_bg_demo)
                .apply(bitmapTransform(new BlurTransformation(25)))
                .into(ivTitle);

        //获取标题栏高度
        ViewTreeObserver viewTreeObserver = ivTitle.getViewTreeObserver();
        viewTreeObserver.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                ivTitle.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                mHeight = ivTitle.getHeight() - llHeaderContent.getHeight();//这里取的高度应该为图片的高度-标题栏
                //注册滑动监听
                svMainContent.setOnObservableScrollViewListener(CoordinatorLayoutActivity.this);
            }
        });


    }

    /**
     * 获取ObservableScrollView的滑动数据
     *
     * @param l
     * @param t
     * @param oldl
     * @param oldt
     */
    @Override
    public void onObservableScrollViewListener(int l, int t, int oldl, int oldt) {
        if (t <= 0) {
            //顶部图处于最顶部，标题栏透明
            llHeaderContent.setBackgroundColor(Color.argb(0, 255, 255, 255));
            tvHeaderTitle.setTextColor(Color.argb(0, 0, 0, 0));

        } else if (t > 0 && t < mHeight) {
            //滑动过程中，渐变
            float scale = (float) t / mHeight;//算出滑动距离比例
            float alpha = (255 * scale);//得到透明度

            llHeaderContent.setBackgroundColor(Color.argb((int) alpha, 255, 255, 255));
            tvHeaderTitle.setTextColor(Color.argb((int) alpha, 48, 63, 159));

        } else {
            //过顶部图区域，标题栏定色
            llHeaderContent.setBackgroundColor(Color.argb(255, 255, 255, 255));
            tvHeaderTitle.setTextColor(Color.argb(255, 0, 0, 0));

        }
    }
}
