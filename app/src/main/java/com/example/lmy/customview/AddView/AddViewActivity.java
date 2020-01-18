package com.example.lmy.customview.AddView;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.androidlmy.headcustomview.HeadCustomView;
import com.androidlmy.headcustomview.OnClickHeadView;
import com.example.lmy.customview.R;
import com.example.lmy.customview.base.BaseActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @功能: 动态添加View
 * @Creat 2020/1/11 13:50
 * @User Lmy
 * @Compony JinAnChang
 */

public class AddViewActivity extends BaseActivity implements OnClickHeadView {

    @BindView(R.id.title)
    HeadCustomView title;
    @BindView(R.id.bt_addtextview)
    Button btAddtextview;
    @BindView(R.id.bt_addedittextview)
    Button btAddedittextview;
    @BindView(R.id.ll_tv_layout)
    LinearLayout llTvLayout;
    @BindView(R.id.ll_et_layout)
    LinearLayout llEtLayout;
    @BindView(R.id.bt_getdata)
    Button btGetdata;

    private List<String> stringList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_view);
        ButterKnife.bind(this);
        for (int i = 0; i <5 ; i++) {

            TextView textview = new TextView(this);
            textview.setText("你好！");
            llTvLayout.addView(textview);
        }
    }

    public static void show(Context context) {
        context.startActivity(new Intent(context, AddViewActivity.class));
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


    @OnClick({R.id.bt_addtextview, R.id.bt_addedittextview, R.id.bt_getdata})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.bt_addtextview:

                break;
            case R.id.bt_addedittextview:
                    final LinearLayout ll = (LinearLayout) LayoutInflater.from(this).inflate(R.layout.tag_item_edit_text, null);
                    ll.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
                    final EditText valueEditText = (EditText) ll.findViewById(R.id.edt_other_phone_item_tag);
                ImageView imgOtherPhoneItemDelete = (ImageView) ll.findViewById(R.id.img_other_phone_item_delete);
                imgOtherPhoneItemDelete.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        llEtLayout.removeView(ll);
                    }
                });
                valueEditText.setTag("other");
                valueEditText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                    @Override
                    public void onFocusChange(View v, boolean hasFocus) {
                        if (!hasFocus) {
                            String mobile = valueEditText.getText().toString().trim();
                            if (mobile != null && !"".equals(mobile)) {
//                                isHaveCustomer(mobile);
                            }
                        }
                    }
                });
                ll.findViewById(R.id.line);
                llEtLayout.addView(ll);
                break;

            case R.id.bt_getdata:
                stringList.clear();
                traversalView(llEtLayout);
                Toast.makeText(context, stringList.toString(), Toast.LENGTH_SHORT).show();
                break;
        }
    }

    /**
     * 遍历所有view
     *
     * @param viewGroup 其他号码
     */
    public void traversalView(ViewGroup viewGroup) {
        try {
            int count = viewGroup.getChildCount();
            Log.e("TAG", "长度----------" + count);

            for (int i = 0; i < count; i++) {
                View view = viewGroup.getChildAt(i);
                if (view instanceof ViewGroup) {
                    int id = view.getId();
                    Log.e("TAG", "----------" + id);
                    traversalView((ViewGroup) view);
                } else {
                    doView(view);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 处理view  其他号码
     *
     * @param view
     */
    private void doView(View view) {
        try {
            String tag = (String) view.getTag();
            if (tag != null) {
                if (tag.equals("other")) {
                    if (view instanceof EditText) {
                        String text = ((EditText) view).getText().toString();
                        stringList.add(replaceAll(text));
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * 去除所有空格
     *
     * @param s
     * @return
     */
    public static String replaceAll(String s) {
        String s1 = null;
        s1 = s.replaceAll(" ", "");
        return s1;
    }
}
