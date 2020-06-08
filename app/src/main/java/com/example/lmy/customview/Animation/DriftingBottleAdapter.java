package com.example.lmy.customview.Animation;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lmy.customview.R;

import de.hdodenhof.circleimageview.CircleImageView;


/**
 * Created by guohao on 2017/9/6.
 */

public class DriftingBottleAdapter extends RecyclerView.Adapter<DriftingBottleAdapter.ViewHolder> {
    private Context context;


    public DriftingBottleAdapter(Context context) {
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_drifitingboottle, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public int getItemCount() {
        return 6;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        holder.all.getBackground().setAlpha(190);//0~255透明度值 ，0为完全透明，255为不透明
        holder.all2.getBackground().setAlpha(190);//0~255透明度值 ，0为完全透明，255为不透明

    }


    public static class ViewHolder extends RecyclerView.ViewHolder {
        private CardView all;
        private ConstraintLayout all2;
        private CircleImageView image;
        public ViewHolder(View itemView) {
            super(itemView);
            all = itemView.findViewById(R.id.all);
            image = itemView.findViewById(R.id.image);
            all2 = itemView.findViewById(R.id.all2);

        }
    }
}
