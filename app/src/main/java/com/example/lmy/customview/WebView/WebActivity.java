package com.example.lmy.customview.WebView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;

import com.androidlmy.headcustomview.HeadCustomView;
import com.androidlmy.headcustomview.OnClickHeadView;
import com.example.lmy.customview.R;
import com.example.lmy.customview.Utils.title_utils.StatusBarUtil;
import com.example.lmy.customview.base.BaseActivity;
import com.tencent.smtt.sdk.CookieSyncManager;
import com.tencent.smtt.sdk.WebSettings;
import com.tencent.smtt.sdk.WebView;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @功能: 用户加载Web网页的activity
 * @Creat 2019/8/23 9:48
 * @User Lmy
 * @Compony zaituvideo
 */
public class WebActivity extends BaseActivity implements OnClickHeadView {

    @BindView(R.id.title)
    HeadCustomView title;
    @BindView(R.id.webview)
    WebView webview;
    private static String titlename;
    private static String url;

    public static void show(Context context, String name, String url) {
        WebActivity.titlename = name;
        WebActivity.url = url;
        context.startActivity(new Intent(context, WebActivity.class).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);
        ButterKnife.bind(this);
        initTitle();
        initWebView();
        webview.loadUrl(url);
    }

    @Override
    public void initTitle() {
        title.setBackgroundColor(getResources().getColor(R.color.blue));
        title.setTitle(titlename);
        title.setTitleTextColor(getResources().getColor(R.color.white));
        title.setClickCallBack(this);
    }

    private void initWebView() {
        WebSettings webSetting = webview.getSettings();
        webSetting.setAllowFileAccess(true);
        webSetting.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NARROW_COLUMNS);
        webSetting.setSupportZoom(true);
        webSetting.setBuiltInZoomControls(true);
        webSetting.setUseWideViewPort(true);
        webSetting.setSupportMultipleWindows(false);
        webSetting.setLoadWithOverviewMode(true);
        webSetting.setAppCacheEnabled(true);
        // webSetting.setDatabaseEnabled(true);
        webSetting.setDomStorageEnabled(true);
        webSetting.setJavaScriptEnabled(true);
        webSetting.setCacheMode(WebSettings.LOAD_NO_CACHE); // 不加载缓存内容
        webSetting.setGeolocationEnabled(true);
        webSetting.setAppCacheMaxSize(Long.MAX_VALUE);
        webSetting.setAppCachePath(this.getDir("appcache", 0).getPath());
        webSetting.setDatabasePath(this.getDir("databases", 0).getPath());
        webSetting.setGeolocationDatabasePath(this.getDir("geolocation", 0)
                .getPath());
        // webSetting.setPageCacheCapacity(IX5WebSettings.DEFAULT_CACHE_CAPACITY);
        webSetting.setPluginState(WebSettings.PluginState.ON_DEMAND);
        // webSetting.setRenderPriority(WebSettings.RenderPriority.HIGH);
        // webSetting.setPreFectch(true);
        CookieSyncManager.createInstance(this);
        CookieSyncManager.getInstance().sync();
    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (webview != null && webview.canGoBack()) {
                webview.goBack();
                return true;
            } else
                return super.onKeyDown(keyCode, event);
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    protected void onDestroy() {
        if (webview != null) {
            webview.setVisibility(View.GONE);
            webview.removeAllViews();
            webview.destroy();
        }
        super.onDestroy();

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
