package com.example.lmy.customview.RecyclerViewAddTitle.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.example.lmy.customview.R;

import java.util.ArrayList;

/**
 * @功能:
 * @Creat 2019/03/01 17:01
 * @User Lmy
 * @By Android Studio
 */
public class RecyclerAdapter extends RecyclerView.Adapter {

    private final static int HEAD_COUNT = 1;
    private final static int FOOT_COUNT = 1;

    private final static int TYPE_HEAD = 0;
    private final static int TYPE_CONTENT = 1;
    private final static int TYPE_FOOTER = 2;

    private Context context;
    private ArrayList<String> list;

    public RecyclerAdapter(Context context, ArrayList<String> dataBeanList) {
        this.context = context;
        this.list = dataBeanList;
    }


    public int getContentSize() {
        return list.size();
    }

    public boolean isHead(int position) {
        return HEAD_COUNT != 0 && position == 0;
    }

    public boolean isFoot(int position) {
        return FOOT_COUNT != 0 && position == getContentSize() + HEAD_COUNT;
    }


    @Override
    public int getItemViewType(int position) {
        int contentSize = getContentSize();
        if (HEAD_COUNT != 0 && position == 0) { // 头部
            return TYPE_HEAD;
        } else if (FOOT_COUNT != 0 && position == HEAD_COUNT + contentSize) { // 尾部
            return TYPE_FOOTER;
        } else {
            return TYPE_CONTENT;//内容
        }


    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == TYPE_HEAD) {
            //头部
            View itemView = LayoutInflater.from(context).inflate(R.layout.head_layout, parent, false);
            return new RecyclerAdapter.HeadHolder(itemView);
        } else if (viewType == TYPE_CONTENT) {
            //内容
            View itemView = LayoutInflater.from(context).inflate(R.layout.content_layout, parent, false);
            return new RecyclerAdapter.ContentHolder(itemView);
        } else {
            //尾部
            View itemView = LayoutInflater.from(context).inflate(R.layout.head_layout, parent, false);
            return new RecyclerAdapter.FootHolder(itemView);
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        if (holder instanceof RecyclerAdapter.HeadHolder) { // 头部

        } else if (holder instanceof RecyclerAdapter.ContentHolder) { // 内容
            RecyclerAdapter.ContentHolder myHolder = (RecyclerAdapter.ContentHolder) holder;
            myHolder.itemText.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    itemOnClistener.itemClick(list.get(position - 1), position);
                }
            });
            myHolder.itemText.setText(list.get(position - 1));
        } else {
            RecyclerAdapter.FootHolder te = (RecyclerAdapter.FootHolder) holder;
            te.linearLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(context, "1111", Toast.LENGTH_SHORT).show();
                }
            });
//            // 尾部
//            myHolder.itemText.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    Toast.makeText(context, "111", Toast.LENGTH_SHORT).show();
//                }
//            });
        }
    }

    @Override
    public int getItemCount() {
        return list.size() + HEAD_COUNT + FOOT_COUNT;
    }

    // 头部
    private class HeadHolder extends RecyclerView.ViewHolder {
        public HeadHolder(View itemView) {
            super(itemView);
        }
    }

    // 尾部
    private class FootHolder extends RecyclerView.ViewHolder {
        LinearLayout linearLayout;
        public FootHolder(View itemView) {
            super(itemView);
            linearLayout =  itemView.findViewById(R.id.ll_test);

        }
    }

    // 内容
    private class ContentHolder extends RecyclerView.ViewHolder {
        TextView itemText;

        public ContentHolder(View itemView) {
            super(itemView);
            itemText = (TextView) itemView.findViewById(R.id.content_tv);
        }
    }

    private ItemOnClistener itemOnClistener;

    public void setItemOnClistener(ItemOnClistener itemOnClistener) {
        this.itemOnClistener = itemOnClistener;
    }

    public interface ItemOnClistener {
        public void itemClick(String s, int p);
    }

}
