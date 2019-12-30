package com.example.lmy.customview.CuttoAnimation;

import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Pair;
import android.view.View;
import android.widget.Button;

import androidx.core.app.ActivityOptionsCompat;

import com.androidlmy.headcustomview.HeadCustomView;
import com.androidlmy.headcustomview.OnClickHeadView;
import com.example.lmy.customview.R;
import com.example.lmy.customview.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @功能: 转场动画
 * @Creat 2019/12/18 16:42
 * @User Lmy
 * @Compony zaituvideo
 */

public class AnimationActivity extends BaseActivity implements OnClickHeadView {

    @BindView(R.id.title)
    HeadCustomView title;
    @BindView(R.id.bt_01)
    Button bt01;
    @BindView(R.id.bt_02)
    Button bt02;

    public static void show(Context context) {
        context.startActivity(new Intent(context, AnimationActivity.class));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animation);
        ButterKnife.bind(this);

        initTitle();
    }

    @Override
    public void initTitle() {
        title.setBackgroundColor(getResources().getColor(R.color.blue));
        title.setTitle("转场动画");
        title.setTitleTextColor(getResources().getColor(R.color.white));
        title.setClickCallBack(this);
    }

    @OnClick({R.id.bt_01, R.id.bt_02, R.id.bt_03, R.id.sharedElements1})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.bt_01:
                ScaleActivity.show(this);
                break;
            case R.id.bt_02:
                BottomActivity.show(this);
                break;
            case R.id.bt_03:
                ExplodeActivity.show(this);
                break;
            case R.id.sharedElements1:
                startActivity(new Intent(this, SharedElementsActivity.class));
                break;
        }
    }

    @Override
    public void onBackClick() {
        finish();
    }

    @Override
    public void onBackTextClick() {

    }

    @Override
    public void onRightTextClick() {

    }

    @Override
    public void onRightImgClick() {

    }
}
