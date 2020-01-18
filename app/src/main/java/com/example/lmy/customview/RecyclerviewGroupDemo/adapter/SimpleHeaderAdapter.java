package com.example.lmy.customview.RecyclerviewGroupDemo.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.lmy.customview.R;
import com.example.lmy.customview.RecyclerviewGroupDemo.MyBean;
import com.truizlop.sectionedrecyclerview.SimpleSectionedAdapter;

import java.util.List;

/**
 * @Description: 简单用法 没有footer
 * @author: ZhangYW
 * @time: 2019/1/24 9:38
 */
public class SimpleHeaderAdapter extends SimpleSectionedAdapter<SimpleHeaderAdapter.MyItemViewHolder> {

    private Context mContext;
    private List<MyBean> mList;

    public SimpleHeaderAdapter(Context context, List<MyBean> list) {
        this.mContext = context;
        this.mList = list;
    }

    @Override
    protected String getSectionHeaderTitle(int section) {
        return mList.get(section).getHeader();
    }
    /**
     * header或者footer的个数
     *
     * @return
     */
    @Override
    protected int getSectionCount() {
        return mList.size();
    }

    /**
     * 每个header或者footer中包含具体的内容个数
     *
     * @return
     */
    @Override
    protected int getItemCountForSection(int section) {
        return mList.get(section).getList().size();
    }

    @Override
    protected MyItemViewHolder onCreateItemViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(mContext).inflate(R.layout.item_rv_content, parent, false);
        return new SimpleHeaderAdapter.MyItemViewHolder(itemView);
    }

    @Override
    protected void onBindItemViewHolder(MyItemViewHolder holder, int section, int position) {
        MyBean bean = mList.get(section);
        if (null == bean)
            return;
        Glide.with(mContext).load("http://47.100.250.181:8080/images/37WKKVZF.jpg").into(holder.iv_head);
    }

    /**
     * ItemViewHolder
     */
    public class MyItemViewHolder extends RecyclerView.ViewHolder {
        private ImageView iv_head;

        public MyItemViewHolder(View itemView) {
            super(itemView);
            iv_head = (ImageView) itemView.findViewById(R.id.item_iv);
        }
    }
}
