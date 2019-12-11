package com.example.lmy.customview.RecyclerviewCheck;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.lmy.customview.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * Created by guohao on 2017/9/6.
 */

public class CheckRecyclerViewAdapter extends RecyclerView.Adapter<CheckRecyclerViewAdapter.ViewHolder> implements CheckActivity.EditLisner {
    private CheckActivity context;
    private List<MyLiveList> mMyLiveList;
    private OnItemClickListener mOnItemClickListener;
    private static boolean isEdit = false;

    public CheckRecyclerViewAdapter(CheckActivity context, List<MyLiveList> myLiveList) {
        this.context = context;
        this.mMyLiveList = myLiveList;
        context.setEditLisner(this);

    }

    public List<MyLiveList> getMyLiveList() {
        if (mMyLiveList == null) {
            mMyLiveList = new ArrayList<>();
        }
        return mMyLiveList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_my_live, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public int getItemCount() {
        return mMyLiveList.size();
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        final MyLiveList myLive = mMyLiveList.get(holder.getAdapterPosition());
        holder.tvSource.setText(myLive.getTitle());
        if (isEdit == false) {
            //不可编辑状态下不显示checkBox按钮
            holder.checkBox.setVisibility(View.GONE);
        } else {
            //可编辑的状态显示checkBox按钮
            holder.checkBox.setVisibility(View.VISIBLE);
            if (myLive.isSelect()) {
                //根据每条数据中的isSelect状态值谈定是否被选中 如果选中就设置选中状态的图标
                holder.checkBox.setImageResource(R.mipmap.cb_checked);
            } else {
                holder.checkBox.setImageResource(R.mipmap.cb_uncheck);
            }
        }
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //item的点击事件
                mOnItemClickListener.onItemClickListener(position, mMyLiveList);
            }
        });
    }

    @Override
    public void isClickEditLisner(boolean isEdits) {
        isEdit = isEdits;
        notifyDataSetChanged();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_source)
        TextView tvSource;
        @BindView(R.id.check_box)
        ImageView checkBox;
        @BindView(R.id.item)
        RelativeLayout item;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }


    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.mOnItemClickListener = onItemClickListener;
    }

    public interface OnItemClickListener {
        void onItemClickListener(int pos, List<MyLiveList> myLiveList);
    }
}
