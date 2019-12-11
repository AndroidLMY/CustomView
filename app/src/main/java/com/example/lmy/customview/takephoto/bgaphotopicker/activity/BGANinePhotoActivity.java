package com.example.lmy.customview.takephoto.bgaphotopicker.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.androidlmy.headcustomview.HeadCustomView;
import com.androidlmy.headcustomview.OnClickHeadView;
import com.example.lmy.customview.R;
import com.example.lmy.customview.base.BaseActivity;
import com.example.lmy.customview.takephoto.bgaphotopicker.utils.BGANinePhotoUtils;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.bingoogolapple.photopicker.widget.BGANinePhotoLayout;


public class BGANinePhotoActivity extends BaseActivity implements OnClickHeadView {
    @BindView(R.id.title)
    HeadCustomView title;
    @BindView(R.id.npl_item_moment_photos)
    BGANinePhotoLayout nplItemMomentPhotos;

    public static void show(Context context) {
        context.startActivity(new Intent(context, BGANinePhotoActivity.class));
    }

    ArrayList<String> moments = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bgashowphoto);
        ButterKnife.bind(this);
        initTitle();
        addNetImageTestData();
        nplItemMomentPhotos = findViewById(R.id.npl_item_moment_photos);
        BGANinePhotoUtils.getInstance().init(BGANinePhotoActivity.this, nplItemMomentPhotos, moments);
    }
    @Override
    public void initTitle() {
        title.setBackgroundColor(getResources().getColor(R.color.blue));
        title.setTitle("九宫格图片展示");
        title.setTitleTextColor(getResources().getColor(R.color.white));
        title.setClickCallBack(this);

    }

    /**
     * 添加网络图片测试数据
     */
    private void addNetImageTestData() {
        moments.add("http://47.100.250.181:8080/images/37WKKVZF.jpg");
        moments.add("http://47.100.250.181:8080/images/37WKKVZF.jpg");
        moments.add("http://47.100.250.181:8080/images/37WKKVZF.jpg");
        moments.add("http://47.100.250.181:8080/images/37WKKVZF.jpg");
        moments.add("http://47.100.250.181:8080/images/37WKKVZF.jpg");
        moments.add("http://47.100.250.181:8080/images/37WKKVZF.jpg");

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