package com.example.lmy.customview.CuttoAnimation;

import android.os.Bundle;


import com.example.lmy.customview.R;
import com.example.lmy.customview.base.BaseActivity;

import butterknife.ButterKnife;

/**
 * 共享元素 效果
 */
public class SharedElementsActivity2 extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shared_elements2);
        ButterKnife.bind(this);
    }
}
