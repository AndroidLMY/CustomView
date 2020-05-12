package com.example.lmy.customview.MMKV;

import android.content.Context;

import com.tencent.mmkv.MMKV;

/**
 * @author Lmy
 * @功能:
 * @Creat 2020/5/12 9:49
 * @Compony 永远相信美好的事物即将发生
 */
public class MmkvUtils {
    private static MmkvUtils mmkvUtils;

    public static MmkvUtils getInstance() {
        if (mmkvUtils == null) {
            mmkvUtils = new MmkvUtils();
        }
        return mmkvUtils;
    }

    public void init(Context context) {
        String rootDir = MMKV.initialize(context);
        System.out.println("mmkv root: " + rootDir);
    }

    public MMKV getdefaultMmkv() {
        return MMKV.defaultMMKV();
    }

    public MMKV getMmkv(String kvname) {
        return MMKV.mmkvWithID(kvname);
    }
}
