/**
 * Copyright (c) 2016, andjdk@163.com All Rights Reserved.
 * #                                                   #
 * #                       _oo0oo_                     #
 * #                      o8888888o                    #
 * #                      88" . "88                    #
 * #                      (| -_- |)                    #
 * #                      0\  =  /0                    #
 * #                    ___/`---'\___                  #
 * #                  .' \\|     |# '.                 #
 * #                 / \\|||  :  |||# \                #
 * #                / _||||| -:- |||||- \              #
 * #               |   | \\\  -  #/ |   |              #
 * #               | \_|  ''\---/''  |_/ |             #
 * #               \  .-\__  '-'  ___/-. /             #
 * #             ___'. .'  /--.--\  `. .'___           #
 * #          ."" '<  `.___\_<|>_/___.' >' "".         #
 * #         | | :  `- \`.;`\ _ /`;.`/ - ` : | |       #
 * #         \  \ `_.   \_ __\ /__ _/   .-` /  /       #
 * #     =====`-.____`.___ \_____/___.-`___.-'=====    #
 * #                       `=---='                     #
 * #     ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~   #
 * #                                                   #
 * #               佛祖保佑         永无BUG            #
 * #                                                   #
 */
package com.example.lmy.customview.ExcelListview;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.andjdk.hvscrollviewlibrary.CommonAdapter;
import com.andjdk.hvscrollviewlibrary.ViewHolder;
import com.example.lmy.customview.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by andjdk on 2016/10/13.
 */
public class StockListAdapter extends CommonAdapter<StockDataInfo> {
    private Context context;



    public StockListAdapter(Context mContext, List<StockDataInfo> mDatas, int layoutId) {
        super(mContext, mDatas, layoutId);
        this.context = mContext;
    }

    @Override
    public void convert(ViewHolder holder, final StockDataInfo stockDataInfo, int position, ArrayList<View> movableViewList) {
        holder.setText(R.id.text1, stockDataInfo.getStockName());
        holder.setText(R.id.text2, stockDataInfo.getPriceLastest());
        holder.setText(R.id.text3, stockDataInfo.getPriceOffsetRate());
        holder.setText(R.id.text4, stockDataInfo.getPriceHigh());
        holder.setText(R.id.text5, stockDataInfo.getPriceLow());
        holder.setText(R.id.text6, stockDataInfo.getPriceOpen());
        final TextView tv = (TextView) holder.getView(R.id.text1);
        if (stockDataInfo.isSelect()) {
            tv.setTextColor(context.getResources().getColor(R.color.colorAccent));
        } else {
            tv.setTextColor(context.getResources().getColor(R.color.colorPrimaryDark));
        }
        holder.setText(R.id.text7, "查看");
    }
}
