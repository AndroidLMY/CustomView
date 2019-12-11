package com.example.lmy.customview.toast;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.androidlmy.headcustomview.HeadCustomView;
import com.androidlmy.headcustomview.OnClickHeadView;
import com.example.lmy.customview.R;
import com.example.lmy.customview.base.BaseActivity;
import com.sdsmdg.tastytoast.TastyToast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ToastKuActivity extends BaseActivity implements OnClickHeadView {

    @BindView(R.id.title)
    HeadCustomView title;
    @BindView(R.id.Download)
    Button Download;
    @BindView(R.id.Warning)
    Button Warning;
    @BindView(R.id.Error)
    Button Error;
    @BindView(R.id.Info)
    Button Info;
    @BindView(R.id.Default)
    Button Default;
    @BindView(R.id.Confusion)
    Button Confusion;
     public static void show(Context context) {
             context.startActivity(new Intent(context,ToastKuActivity.class));
         }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_toast_ku_actiity);
        ButterKnife.bind(this);
        initTitle();
    }


    @Override
    public void initTitle() {
        title.setBackgroundColor(getResources().getColor(R.color.blue));
        title.setTitle("Toast自定义");
        title.setTitleTextColor(getResources().getColor(R.color.white));
        title.setClickCallBack(this);
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

    @OnClick({R.id.Download, R.id.Warning, R.id.Error, R.id.Info, R.id.Default, R.id.Confusion})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.Download:
                TastyToast.makeText(getApplicationContext(), "Download", TastyToast.LENGTH_LONG, TastyToast.SUCCESS);

                break;
            case R.id.Warning:
                TastyToast.makeText(getApplicationContext(), "Warning", TastyToast.LENGTH_LONG, TastyToast.WARNING);

                break;
            case R.id.Error:
                TastyToast.makeText(getApplicationContext(), "Error", TastyToast.LENGTH_LONG, TastyToast.ERROR);

                break;
            case R.id.Info:
                TastyToast.makeText(getApplicationContext(), "Info", TastyToast.LENGTH_LONG, TastyToast.INFO);

                break;
            case R.id.Default:
                TastyToast.makeText(getApplicationContext(), "Default", TastyToast.LENGTH_LONG, TastyToast.DEFAULT);

                break;
            case R.id.Confusion:
                TastyToast.makeText(getApplicationContext(), "Confusion", TastyToast.LENGTH_LONG, TastyToast.DEFAULT);

                break;
        }
    }
}
