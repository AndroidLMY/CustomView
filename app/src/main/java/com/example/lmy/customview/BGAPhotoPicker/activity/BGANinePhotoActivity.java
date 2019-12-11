package com.example.lmy.customview.BGAPhotoPicker.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

import com.example.lmy.customview.BGAPhotoPicker.utils.BGANinePhotoUtils;
import com.example.lmy.customview.R;

import java.util.ArrayList;

import cn.bingoogolapple.photopicker.widget.BGANinePhotoLayout;


public class BGANinePhotoActivity extends AppCompatActivity {
    public static void show(Context context) {
        context.startActivity(new Intent(context, BGANinePhotoActivity.class));
    }

    ArrayList<String> moments = new ArrayList<>();
    private BGANinePhotoLayout nplItemMomentPhotos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bgashowphoto);
        addNetImageTestData();
        nplItemMomentPhotos = findViewById(R.id.npl_item_moment_photos);
        BGANinePhotoUtils.getInstance().init(BGANinePhotoActivity.this, nplItemMomentPhotos, moments);
    }

    /**
     * 添加网络图片测试数据
     */
    private void addNetImageTestData() {
        moments.add("http://bmob-cdn-17797.b0.upaiyun.com/2018/04/04/d0f7a0d07d3f403d95772f4f469d6f57.png");
        moments.add("http://bmob-cdn-17797.b0.upaiyun.com/2018/04/07/43b96f8d904846eab52c1c50e95ca628.jpg");
        moments.add("http://192.168.1.88:9090/dev/dispatch_list/4lfiyodc8s1s/4lt32i8xex34.jpg");
        moments.add("http://192.168.1.88:9090/dev/dispatch_list/4lfiyodc8s1s/4lt32i6n09og.jpg");
        moments.add("http://192.168.1.88:9090/dev/dispatch_list/4lfiyodc8s1s/4lt32i8dfqbk.jpg");
        moments.add("http://192.168.1.88:9090/dev/dispatch_list/4lfiyodc8s1s/4lt32i8xex34.jpg");
    }

}