package com.example.lmy.customview.Utils;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.lmy.customview.R;

/**
 * 图片加载loader
 */
public class GlideImageLoader {
    private static GlideImageLoader glideImageLoader;
    private static RequestOptions options = new RequestOptions()
            .placeholder(R.mipmap.ic_load)//图片加载出来前，显示的图片
            .fallback(R.mipmap.ic_load_erro) //url为空的时候,显示的图片
            .error(R.mipmap.ic_load_erro);//图片加载失败后，显示的图片

    public static GlideImageLoader getInstance() {
        if (glideImageLoader == null) {
            glideImageLoader = new GlideImageLoader();
        }
        return glideImageLoader;
    }
    /**
     * 加载图片
     */
    public static void loader(Context context, String url, ImageView imageView) {
        Glide.with(context).load(url).apply(options).into(imageView);
    }

    /**
     * 加载图片
     */
    public static void loader(Context context, int url, ImageView imageView) {
        Glide.with(context).load(url).apply(options).into(imageView);
    }
}
