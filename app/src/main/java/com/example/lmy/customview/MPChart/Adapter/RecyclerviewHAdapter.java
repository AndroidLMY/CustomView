package com.example.lmy.customview.MPChart.Adapter;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.lmy.customview.R;

import java.util.ArrayList;

/**
 * @功能:
 * @Creat 2019/05/20 10:59
 * @User Lmy
 * @By Android Studio
 */
public class RecyclerviewHAdapter extends RecyclerView.Adapter {
    private View view;
    private Context context;
    private ArrayList<String> list1, list2, list3;

    public RecyclerviewHAdapter(Context context, ArrayList<String> list1, ArrayList<String> list2, ArrayList<String> list3) {
        this.context = context;
        this.list1 = list1;
        this.list2 = list2;
        this.list3 = list3;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_heap_layout, viewGroup, false);
        return new FPurchaseViewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, final int i) {
        LinearLayout.LayoutParams weight01 = new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.MATCH_PARENT, Float.valueOf(list1.get(i)));
        LinearLayout.LayoutParams weight02 = new LinearLayout.LayoutParams(0,  LinearLayout.LayoutParams.MATCH_PARENT, Float.valueOf(list2.get(i)));
        LinearLayout.LayoutParams weight03 = new LinearLayout.LayoutParams(0,  LinearLayout.LayoutParams.MATCH_PARENT, Float.valueOf(list3.get(i)));
        ((FPurchaseViewholder) viewHolder).tv01.setLayoutParams(weight01);
        ((FPurchaseViewholder) viewHolder).tv02.setLayoutParams(weight02);
        ((FPurchaseViewholder) viewHolder).tv03.setLayoutParams(weight03);
    }

    @Override
    public int getItemCount() {
        return list1.size();
    }

    public class FPurchaseViewholder extends RecyclerView.ViewHolder {
        private TextView tv01;
        private TextView tv02;
        private TextView tv03;

        public FPurchaseViewholder(@NonNull View itemView) {
            super(itemView);
            tv01 = itemView.findViewById(R.id.tv_01);
            tv02 = itemView.findViewById(R.id.tv_02);
            tv03 = itemView.findViewById(R.id.tv_03);

        }
    }
}
