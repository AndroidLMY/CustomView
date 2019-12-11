package com.example.lmy.customview.ExcelListview;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;

import com.androidlmy.headcustomview.HeadCustomView;
import com.androidlmy.headcustomview.OnClickHeadView;
import com.example.lmy.customview.R;
import com.example.lmy.customview.base.BaseActivity;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ExcelListView extends BaseActivity implements OnClickHeadView {
    @BindView(R.id.title)
    HeadCustomView title;
    @BindView(R.id.hv_scrollview)
    HVScrollViews hvScrollview;
    private StockListAdapter mAdapter;
    private ArrayList<StockDataInfo> stockDataInfoList;

    public static void show(Context context) {
        context.startActivity(new Intent(context, ExcelListView.class));
    }

    @Override
    public void initTitle() {
        title.setBackgroundColor(getResources().getColor(R.color.blue));
        title.setTitle("ExcelListView");
        title.setTitleTextColor(getResources().getColor(R.color.white));
        title.setClickCallBack(this);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_excel_list_view);
        ButterKnife.bind(this);
        initTitle();

        stockDataInfoList = new ArrayList<>();
        for (int i = 0; i < 1; i++) {
            StockDataInfo stockDataInfo = new StockDataInfo();
            stockDataInfo.setStockName("补");
            stockDataInfo.setStockCode("600000");
            stockDataInfo.setPriceLastest("补料单");
            stockDataInfo.setPriceOffsetRate("郑州中原国贸经销商");
            stockDataInfo.setPriceHigh("王大锤");
            stockDataInfo.setSelect(true);
            stockDataInfo.setPriceLow("橱柜");
            stockDataInfo.setPriceOpen("美克");
            stockDataInfoList.add(stockDataInfo);
        }
        //定义顶部栏
        hvScrollview.setHeaderListData(new String[]{"类型"}, new String[]{"类型", "经销商名称", "C端客户", "产品类型", "系列", "操作"});
        mAdapter = new StockListAdapter(this, stockDataInfoList, R.layout.item_layout);
        hvScrollview.setAdapter(mAdapter);
        //点击列表item
        hvScrollview.setOnItemClick(new HVScrollViews.OnItemClickedListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (stockDataInfoList.get(position).isSelect()) {
                    stockDataInfoList.get(position).setSelect(false);
                } else {
                    stockDataInfoList.get(position).setSelect(true);
                }
                mAdapter.notifyDataSetChanged();
                Toast.makeText(ExcelListView.this, position + "", Toast.LENGTH_SHORT).show();
            }
        });
        //点击头部按钮
        hvScrollview.setOnHeaderClickedListener(new HVScrollViews.OnHeaderClickedListener() {
            @Override
            public void onHeadViewClick(String string) {
                Toast.makeText(ExcelListView.this, string, Toast.LENGTH_SHORT).show();
            }
        });

        hvScrollview.setOnLoadMoreListener(new HVScrollViews.OnLoadMoreListener() {
            @Override
            public void onLoadingMore() {

                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            Thread.sleep(1000);
                            handler.sendEmptyMessage(5);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }).start();
            }
        });
    }


    Handler handler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            if (msg.what == 5) {
                for (int i = 0; i < 10; i++) {
                    StockDataInfo stockDataInfo = new StockDataInfo();
                    stockDataInfo.setStockName("补190407-001-01" + i + "A");
                    stockDataInfo.setSelect(false);
                    stockDataInfo.setStockCode("600000");
                    stockDataInfo.setPriceLastest("补料单");
                    stockDataInfo.setPriceOffsetRate("郑州中原国贸经销商");
                    stockDataInfo.setPriceHigh("王大锤");
                    stockDataInfo.setPriceLow("橱柜");
                    stockDataInfo.setPriceOpen("美克");
                    stockDataInfoList.add(stockDataInfo);
                    mAdapter.notifyDataSetChanged();
                }
                hvScrollview.onLoadingComplete();
            }
            return false;
        }
    });

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
