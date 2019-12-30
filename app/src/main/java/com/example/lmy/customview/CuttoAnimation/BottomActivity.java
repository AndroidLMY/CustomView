package com.example.lmy.customview.CuttoAnimation;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Window;

import com.example.lmy.customview.R;
import com.example.lmy.customview.Utils.title_utils.StatusBarUtil;
import com.example.lmy.customview.base.BaseActivity;

public class BottomActivity extends BaseActivity {


    public static void show(Context context) {
        context.startActivity(new Intent(context,BottomActivity.class ));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bottom);
        StatusBarUtil.setRootViewFitsSystemWindows(this, true);
        //        //设置状态栏透明
        //        StatusBarUtil.setTranslucentStatus(this);
        StatusBarUtil.setStatusBarColor(this, getResources().getColor(R.color.xiashang));
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(0, R.anim.anim_slide_close_exit);
    }
}
