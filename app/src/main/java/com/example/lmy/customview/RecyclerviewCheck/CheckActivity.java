package com.example.lmy.customview.RecyclerviewCheck;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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
 * @功能: Recyclerview的多选操作
 * @Creat 2019/12/10 14:59
 * @User Lmy
 * @Compony zaituvideo
 */
public class CheckActivity extends BaseActivity implements OnClickHeadView {

    @BindView(R.id.title)
    HeadCustomView title;
    @BindView(R.id.recyclerview)
    RecyclerView recyclerview;
    @BindView(R.id.btn_delete)
    Button btnDelete;
    @BindView(R.id.ll_mycollection_bottom_dialog)
    LinearLayout llMycollectionBottomDialog;
    @BindView(R.id.iv_select_all)
    ImageView ivSelectAll;
    @BindView(R.id.ll_select_all)
    LinearLayout llSelectAll;
    @BindView(R.id.tv_isAllSelect)
    TextView tvIsAllSelect;
    private List<MyLiveList> mList = new ArrayList<>();
    private CheckRecyclerViewAdapter mRadioAdapter = null;
    private LinearLayoutManager mLinearLayoutManager = new LinearLayoutManager(this);
    private boolean isShowDelete = false;
    private boolean isAllSelect = false;
    private List<String> deleteID = new ArrayList<>();//需要删除的条目的标识

    public static void show(Context context) {
        context.startActivity(new Intent(context, CheckActivity.class));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check);
        ButterKnife.bind(this);
        initTitle();
        initData();
    }

    @Override
    public void initTitle() {
        title.setBackgroundColor(getResources().getColor(R.color.blue));
        title.setTitle("Recyclerview的多选操作");
        title.setTitleTextColor(getResources().getColor(R.color.white));
        title.setClickCallBack(this);
        title.setFinishText("编辑");
        title.setFinishColor(getResources().getColor(R.color.white));
    }

    public void initData() {
        init();//初始化数据
        mRadioAdapter = new CheckRecyclerViewAdapter(this, mList);
        editLisner.isClickEditLisner(false);

        mLinearLayoutManager = new LinearLayoutManager(this);
        recyclerview.setLayoutManager(mLinearLayoutManager);
        mRadioAdapter.setOnItemClickListener(new CheckRecyclerViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClickListener(int pos, List<MyLiveList> myLiveList) {
                if (mList.get(pos).isSelect) {
                    mList.get(pos).setSelect(false);
                } else {
                    mList.get(pos).setSelect(true);
                }
                deleteID.clear();
                for (int i = 0; i < mList.size(); i++) {
                    if (mList.get(i).isSelect) {
                        deleteID.add(mList.get(i).getId());
                    }
                }
                mRadioAdapter.notifyDataSetChanged();
            }
        });
        recyclerview.setAdapter(mRadioAdapter);//设置adapter
    }

    public void init() {
        for (int i = 0; i < 30; i++) {
            MyLiveList myLiveList = new MyLiveList();
            myLiveList.setTitle("这是第" + i + "个条目");
            myLiveList.setId(i + "");
            mList.add(myLiveList);
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
        if (isShowDelete) {
            isShowDelete = false;
            title.setFinishText("编辑");
            llMycollectionBottomDialog.setVisibility(View.GONE);
            editLisner.isClickEditLisner(false);
        } else {
            title.setFinishText("完成");
            isShowDelete = true;
            editLisner.isClickEditLisner(true);
            llMycollectionBottomDialog.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onRightImgClick() {

    }


    public EditLisner editLisner;

    public void setEditLisner(EditLisner editLisner) {
        this.editLisner = editLisner;
    }

    @OnClick({R.id.btn_delete, R.id.ll_select_all})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_delete:
                for (int i = 0; i < deleteID.size(); i++) {
                    for (int k = 0; k < mList.size(); k++) {
                        if (deleteID.get(i).equals(mList.get(k).getId())) {
                            mList.remove(mList.get(k));
                        }
                    }
                }
                mRadioAdapter.notifyDataSetChanged();

                llMycollectionBottomDialog.setVisibility(View.VISIBLE);
                break;
            case R.id.ll_select_all:
                deleteID.clear();
                if (isAllSelect) {
                    for (int i = 0; i < mList.size(); i++) {
                        mList.get(i).setSelect(false);
                    }
                    tvIsAllSelect.setText("全选");
                    isAllSelect = false;
                    ivSelectAll.setImageResource(R.mipmap.cb_uncheck);
                } else {
                    for (int i = 0; i < mList.size(); i++) {
                        mList.get(i).setSelect(true);
                        deleteID.add(mList.get(i).getId());
                    }
                    tvIsAllSelect.setText("全不选");
                    isAllSelect = true;
                    ivSelectAll.setImageResource(R.mipmap.cb_checked);
                }
                mRadioAdapter.notifyDataSetChanged();
                break;
        }
    }

    public interface EditLisner {
        void isClickEditLisner(boolean isEdit);
    }

}
