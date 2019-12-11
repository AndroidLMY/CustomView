package com.example.lmy.customview.SecondaryList;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.ScrollView;
import android.widget.Toast;

import com.androidlmy.headcustomview.HeadCustomView;
import com.androidlmy.headcustomview.OnClickHeadView;
import com.example.lmy.customview.R;
import com.example.lmy.customview.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @功能: 二级列表
 * @Creat 2019/12/10 16:44
 * @User Lmy
 * @Compony zaituvideo
 */
public class SecondaryListActivity extends BaseActivity implements OnClickHeadView {
    @BindView(R.id.title)
    HeadCustomView title;
    @BindView(R.id.elv)
    ExpandableListViewForScrollView elv;
    @BindView(R.id.scrollview)
    ScrollView scrollview;
    //View
    private ExpandableAdapter adapter;
    //Model：定义的数据
    private String[] groups = {"客厅", "厨房", "卧室"};
    //注意，字符数组不要写成{{"A1,A2,A3,A4"}, {"B1,B2,B3,B4，B5"}, {"C1,C2,C3,C4"}}
    private Integer[][] childs = {{R.drawable.chess1,}, {R.drawable.chess1, R.drawable.chess1}, {R.drawable.chess1, R.drawable.chess1, R.drawable.chess1, R.drawable.chess1}};


    public static void show(Context context) {
        context.startActivity(new Intent(context, SecondaryListActivity.class));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_secondary_list);
        ButterKnife.bind(this);
        initTitle();

        scrollview.setFocusable(true);
        scrollview.setFocusableInTouchMode(true);
        scrollview.requestFocus();
        adapter = new ExpandableAdapter(this, groups, childs);
        elv.setAdapter(adapter);
        elv.setGroupIndicator(null);//去掉默认显示的箭头
        for (int i = 0; i < adapter.getGroupCount(); i++) {
            elv.expandGroup(i);
        }
        //组点击无效果
        elv.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {

            @Override
            public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
                return true;
            }
        });

        elv.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                Toast.makeText(SecondaryListActivity.this, childs[groupPosition][childPosition], Toast.LENGTH_SHORT).show();
                return false;
            }
        });
    }

    @Override
    public void initTitle() {
        title.setBackgroundColor(getResources().getColor(R.color.blue));
        title.setTitle("二级列表");
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
}
