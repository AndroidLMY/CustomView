package com.example.lmy.customview.Activity;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.androidlmy.headcustomview.HeadCustomView;
import com.example.lmy.customview.AddView.AddViewActivity;
import com.example.lmy.customview.CuttoAnimation.AnimationActivity;
import com.example.lmy.customview.LoadToast.LoadToastActivity;
import com.example.lmy.customview.RecyclerviewGroupDemo.RecyclerviewGroupActivity;
import com.example.lmy.customview.Utils.LogUtil;
import com.example.lmy.customview.Utils.PermissionUtils;
import com.example.lmy.customview.banner.BannerActivity;
import com.example.lmy.customview.takephoto.TakePhotoMainActivity;
import com.example.lmy.customview.CustomView.CustomviewActivity;
import com.example.lmy.customview.CustomView.MyDialog;
import com.example.lmy.customview.ExcelListview.ExcelListView;
import com.example.lmy.customview.FragmentViewPager.TabActivity;
import com.example.lmy.customview.MPChart.Activity.MainMPChartActivity;
import com.example.lmy.customview.R;
import com.example.lmy.customview.RecyclerViewAddTitle.activity.RecyclerViewActivity;
import com.example.lmy.customview.RecyclerviewCheck.CheckActivity;
import com.example.lmy.customview.SecondaryList.SecondaryListActivity;
import com.example.lmy.customview.WebView.WebActivity;
import com.example.lmy.customview.base.BaseActivity;
import com.example.lmy.customview.toast.ToastKuActivity;
import com.example.lmy.customview.updaapp.UpDataActivity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import pub.devrel.easypermissions.AppSettingsDialog;
import pub.devrel.easypermissions.EasyPermissions;

public class MainActivity extends BaseActivity implements EasyPermissions.PermissionCallbacks {
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

    private static final int PRC_PHOTO_PREVIEW = 10086;
    String[] perms = {
            PermissionUtils.CALENDAR,
            PermissionUtils.CAMERA,
            PermissionUtils.CONTACTS,
            PermissionUtils.LOCATION,
            PermissionUtils.MICROPHONE,
            PermissionUtils.PHONE,
            PermissionUtils.SENSORS,
            PermissionUtils.SMS,
            PermissionUtils.STORAGE,
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
        PermissionUtils.init();
        methodRequiresTwoPermission();
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

    @OnClick({R.id.bt_view, R.id.bt_bga, R.id.bt_dialog,
            R.id.bt_recyclerview, R.id.bt_statistics,
            R.id.secondarylist_bt, R.id.check_recyclerview,
            R.id.viewpager_fragment, R.id.bt_excel,
            R.id.updata, R.id.webview, R.id.laodtoase,
            R.id.toast, R.id.donghua, R.id.banner, R.id.addview, R.id.recygroup

    })
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.bt_view:
                CustomviewActivity.show(this);
                break;
            case R.id.bt_bga:
                TakePhotoMainActivity.show(this);
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
                WebActivity.show(this, "网页", "https://blog.csdn.net/ww897532167/article/details/74178449");
                break;

            case R.id.laodtoase:
                LoadToastActivity.show(this);
                break;

            case R.id.toast:
                ToastKuActivity.show(this);
                break;
            case R.id.donghua:
                AnimationActivity.show(this);
                break;
            case R.id.banner:
                BannerActivity.show(this);
                break;
            case R.id.addview:
                AddViewActivity.show(this);
                break;
            case R.id.recygroup:
                RecyclerviewGroupActivity.show(this);
                break;


        }
    }

    //请求码
//    @AfterPermissionGranted(PRC_PHOTO_PREVIEW)//加上这一句注解 当全部权限同意之后重新回调这个方法
    private void methodRequiresTwoPermission() {
        //如果权限全部同意仍然会回调这个方法   所以拥有权限的时候操作放到这里就可以
        EasyPermissions.requestPermissions(this, "使用该软件需要相关权限:" + PermissionUtils.GetPermission(perms), PRC_PHOTO_PREVIEW, perms);
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions,
                                           int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        // EasyPermissions 权限处理请求结果
        LogUtil.e("onRequestPermissionsResult:" + requestCode);
        //权限没有全部同意显示提示框然后提示用户去设置开启
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
    }

    //同意授权
    @Override
    public void onPermissionsGranted(int requestCode, List<String> perms) {
        //其实这个方法不需要放成功的逻辑代码
        if (perms.size() == 9) {
            Toast.makeText(this, "同意授权啊", Toast.LENGTH_SHORT).show();
        }
        LogUtil.e("onPermissionsGranted:" + requestCode + ":" + perms.size());
    }

    //拒绝授权
    @Override
    public void onPermissionsDenied(int requestCode, List<String> perms) {
        LogUtil.e("onPermissionsDenied:" + requestCode + ":" + perms.size());
        new AppSettingsDialog.Builder(this)
                .setTitle("提醒")
                .setRationale("请开启以下相关权限" + PermissionUtils.GetPermission(perms))
                .build()
                .show();
    }

    /**
     * 拒绝权限前往设置中开启权限的回调
     */
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == AppSettingsDialog.DEFAULT_SETTINGS_REQ_CODE) {
            // Do something after user returned from app settings screen, like showing a Toast.
//            methodRequiresTwoPermission();
            LogUtil.e("onActivityResult:");
            if (EasyPermissions.hasPermissions(this, perms)) {
                //设置回来权限给全了
                LogUtil.e("设置回来权限给全了");
                Toast.makeText(MainActivity.this, "设置回来权限给全了", Toast.LENGTH_SHORT).show();
            } else {
                methodRequiresTwoPermission();
                LogUtil.e("从设置回来还是没给权限");
                //从设置回来还是没给权限
            }
        }
    }
}
