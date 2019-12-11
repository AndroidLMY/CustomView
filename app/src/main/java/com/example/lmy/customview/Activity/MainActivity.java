package com.example.lmy.customview.Activity;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.androidlmy.headcustomview.HeadCustomView;
import com.example.lmy.customview.BGAPhotoPicker.activity.BGAPickerActivity;
import com.example.lmy.customview.CustomView.CustomviewActivity;
import com.example.lmy.customview.CustomView.MyDialog;
import com.example.lmy.customview.ExcelListview.ExcelListView;
import com.example.lmy.customview.FragmentViewPager.TabActivity;
import com.example.lmy.customview.MPChart.Activity.MainMPChartActivity;
import com.example.lmy.customview.R;
import com.example.lmy.customview.RecyclerViewAddTitle.activity.RecyclerViewActivity;
import com.example.lmy.customview.RecyclerviewCheck.CheckActivity;
import com.example.lmy.customview.SecondaryList.SecondaryListActivity;
import com.example.lmy.customview.Utils.PermissionUtils;
import com.example.lmy.customview.WebView.WebActivity;
import com.example.lmy.customview.base.BaseActivity;
import com.example.lmy.customview.updaapp.UpDataActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends BaseActivity {
    @BindView(R.id.title)
    HeadCustomView title;
    @BindView(R.id.bt_view)
    Button btView;
    @BindView(R.id.bt_bga)
    Button btBga;
    @BindView(R.id.bt_dialog)
    Button btDialog;
    @BindView(R.id.bt_recyclerview)
    Button btRecyclerview;
    @BindView(R.id.bt_statistics)
    Button btStatistics;
    @BindView(R.id.secondarylist_bt)
    Button secondarylistBt;
    @BindView(R.id.check_recyclerview)
    Button checkRecyclerview;
    @BindView(R.id.viewpager_fragment)
    Button viewpagerFragment;
    @BindView(R.id.bt_excel)
    Button btExcel;
    @BindView(R.id.updata)
    Button updata;
    @BindView(R.id.webview)
    Button webview;
    String[] perms = {
            Manifest.permission.READ_CALENDAR,//日历权限
            Manifest.permission.CAMERA,//相机
            Manifest.permission.ACCESS_FINE_LOCATION,//位置
            Manifest.permission.READ_PHONE_STATE,//手机
            Manifest.permission.WRITE_EXTERNAL_STORAGE,//存储卡
    };

    public static void show(Context context) {
        context.startActivity(new Intent(context, MainActivity.class));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initTitle();
        PermissionUtils.getInstance().methodRequiresTwoPermission(MainActivity.this, perms);
    }

    @Override
    public void initTitle() {
        title.setBackgroundColor(getResources().getColor(R.color.blue));
        title.setTitle("Demo整理");
        title.setTitleTextColor(getResources().getColor(R.color.white));
        title.setBackGone();
    }


    private void showDialog() {
        final MyDialog myDialog = new MyDialog(this);
        myDialog.setTitle("这是一个自定义的dialog？")
                .setMessage("这是内容")
                .setDialogCancelable(false)
                .setPositiveButton("确认", new MyDialog.OnMyDialogButtonClickListener() {
                    @Override
                    public void onClick() {
                        myDialog.dismiss();
                    }
                })
                .setNegativeButton("取消", new MyDialog.OnMyDialogButtonClickListener() {
                    @Override
                    public void onClick() {
                        myDialog.dismiss();
                    }
                })
                .show();
    }

    @OnClick({R.id.bt_view, R.id.bt_bga, R.id.bt_dialog, R.id.bt_recyclerview, R.id.bt_statistics, R.id.secondarylist_bt, R.id.check_recyclerview, R.id.viewpager_fragment, R.id.bt_excel, R.id.updata, R.id.webview})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.bt_view:
                CustomviewActivity.show(this);
                break;
            case R.id.bt_bga:
                BGAPickerActivity.show(this);
                break;
            case R.id.bt_dialog:
                showDialog();
                break;
            case R.id.bt_statistics:
                MainMPChartActivity.show(this);
                break;
            case R.id.bt_recyclerview:
                RecyclerViewActivity.show(this);
                break;
            case R.id.secondarylist_bt:
                SecondaryListActivity.show(this);
                break;
            case R.id.check_recyclerview:
                CheckActivity.show(this);
                break;
            case R.id.viewpager_fragment:
                TabActivity.show(this);
                break;
            case R.id.bt_excel:
                ExcelListView.show(this);
                break;
            case R.id.updata:
                UpDataActivity.show(this);
                break;
            case R.id.webview:
                WebActivity.show(this,"网页","https://www.smzdm.com/?utm_source=baidu&utm_medium=cpc&utm_campaign=0011");
                break;
        }
    }
}
