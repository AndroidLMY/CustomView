package com.example.lmy.customview.Utils;

import android.graphics.Color;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.widget.TextView;

import java.util.HashMap;
import java.util.Map;

/**
 * @功能:
 * String字符串处理
 * @Creat 2019/03/08 16:17
 * @User Lmy
 * @By Android Studio
 */
public class StringUtils {
    private void setTVContentColor(TextView textView, String string) {
        //设置textview中文字的颜色
        SpannableStringBuilder builder = new SpannableStringBuilder(string);
        //ForegroundColorSpan 为文字前景色，BackgroundColorSpan为文字背景色
        ForegroundColorSpan redSpan = new ForegroundColorSpan(Color.RED);
        builder.setSpan(redSpan, 0, 4, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        textView.setText(builder);
    }

    public static String getChoosable(String url, Map<String, String> map) {
        if (map.size() == 0) {
            return url;
        }
        for (String key : map.keySet()) {
            String value = map.get(key);
            if (value.isEmpty()) {
                url = url + "";
            } else {
                if (!url.contains("?")) {
                    url = url + "?" + key + "=" + value;
                } else {
                    url = url + "&" + key + "=" + value;
                }
            }
        }
        return url;
    }


    private static String getUrl(String url, HashMap<String, String> map) {
        if (map.size() == 0) {
            return url;
        } else {
            for (String key : map.keySet()) {
                String value = map.get(key);
                if (value.isEmpty()) {
                    url = url + "";
                } else {
                    if (!url.contains("?")) {
                        url = url + "?" + key + "=" + value;
                    } else {
                        url = url + "&" + key + "=" + value;
                    }
                }
            }

        }
        return url;
    }
}
