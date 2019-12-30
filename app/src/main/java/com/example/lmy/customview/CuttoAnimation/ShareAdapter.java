package com.example.lmy.customview.CuttoAnimation;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lmy.customview.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @功能:
 * @Creat 2019/12/19 11:04
 * @User Lmy
 * @Compony zaituvideo
 */
public class ShareAdapter extends RecyclerView.Adapter<ShareAdapter.ViewHolder> {

    private Activity context;

    public ShareAdapter(Activity context) {
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.share_recycler_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.ivImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (android.os.Build.VERSION.SDK_INT > 20) {
                    context.startActivity(new Intent(context, SharedElementsActivity2.class), ActivityOptions.makeSceneTransitionAnimation(context, null).toBundle());
                } else {
                    context.startActivity(new Intent(context, SharedElementsActivity2.class));
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return 10;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.iv_image)
        ImageView ivImage;
        @BindView(R.id.tv_name)
        TextView tvName;
        private Context context;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

}
