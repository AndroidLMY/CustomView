package com.example.lmy.customview.base;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.lmy.customview.R;
import com.example.lmy.customview.Utils.title_utils.StatusBarUtil;
import com.zyao89.view.zloading.ZLoadingDialog;

import java.util.HashMap;
import java.util.Map;

import static com.zyao89.view.zloading.Z_TYPE.STAR_LOADING;

/**
 * @功能:
 * @Creat 2019/07/11 10:33
 * @User Lmy
 * @By Android Studio
 */
public abstract class BaseActivity extends AppCompatActivity {
    private FragmentManager manager;
    private FragmentTransaction transaction;
    protected Context context;
    //加载类型是否是上拉加载
    protected boolean isPullonloading;
    //分页的页码
    protected int page = 1;
    protected Map<String, String> parms;
    private ZLoadingDialog dialog;
    protected LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityManager.getInstance().pushOneActivity(this);
        context = getApplicationContext();
        parms = new HashMap<>();
        dialog = new ZLoadingDialog(this);
        StatusBarUtil.setRootViewFitsSystemWindows(this, true);
        //        //设置状态栏透明
        //        StatusBarUtil.setTranslucentStatus(this);
        StatusBarUtil.setStatusBarColor(this, getResources().getColor(R.color.blue));
        if (!StatusBarUtil.setStatusBarDarkTheme(this, true)) {
            //如果不支持设置深色风格 为了兼容总不能让状态栏白白的看不清, 于是设置一个状态栏颜色为半透明,
            //这样半透明+白=灰, 状态栏的文字能看得清
            StatusBarUtil.setStatusBarColor(this, 0x55000000);
        }
    }

    public void initStatusBar() {

    }

    public void initTitle() {

    }

    public void initLiveEventBus() {

    }

    public void initTablayout() {

    }

    public void initSmart() {

    }


    public void showLoadDialog() {
        if (dialog != null) {
            dialog.setLoadingBuilder(STAR_LOADING)//设置类型
                    .setLoadingColor(0xCC111111)//颜色
                    .setHintText("加载中....")
                    .setHintTextSize(16) // 设置字体大小 dp
                    .setHintTextColor(Color.GRAY)  // 设置字体颜色
                    .setDurationTime(0.5) // 设置动画时间百分比 - 0.5倍
                    .setCanceledOnTouchOutside(false)//设置不可取消
//                .setDialogBackgroundColor(Color.parseColor("#")) // 设置背景色，默认白色
                    .show();
        } else {
            dialog = new ZLoadingDialog(this);
        }
    }

    public void showLoadDialog(String title) {
        if (dialog != null) {
            dialog.setLoadingBuilder(STAR_LOADING)//设置类型
                    .setLoadingColor(0xCC111111)//颜色
                    .setHintText(title)
                    .setHintTextSize(16) // 设置字体大小 dp
                    .setHintTextColor(Color.GRAY)  // 设置字体颜色
                    .setDurationTime(0.5) // 设置动画时间百分比 - 0.5倍
                    .setCanceledOnTouchOutside(false)//设置不可取消
//                .setDialogBackgroundColor(Color.parseColor("#")) // 设置背景色，默认白色
                    .show();
        } else {
            dialog = new ZLoadingDialog(this);
            dialog.setLoadingBuilder(STAR_LOADING)//设置类型
                    .setLoadingColor(0xCC111111)//颜色
                    .setHintText(title)
                    .setHintTextSize(16) // 设置字体大小 dp
                    .setHintTextColor(Color.GRAY)  // 设置字体颜色
                    .setDurationTime(0.5) // 设置动画时间百分比 - 0.5倍
                    .setCanceledOnTouchOutside(false)//设置不可取消
//                .setDialogBackgroundColor(Color.parseColor("#")) // 设置背景色，默认白色
                    .show();
        }
    }

    public void hideLoadDialog() {
        if (dialog != null) {
            dialog.dismiss();
        }
    }
}
