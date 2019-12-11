package com.example.lmy.customview.MPChart.Utils;

/**
 * @功能:
 * @Creat 2019/03/29 16:46
 * @User Lmy
 * @By Android Studio
 */

import android.graphics.Color;

import java.text.NumberFormat;

/**
 * Created by Charlie on 2016/10/8.
 * 通用字符串管理类
 */
public class StringUtils {


    public static int[] colors = {
            Color.parseColor("#00FFFF"),
            Color.parseColor("#34A8FF"),
            Color.parseColor("#578EF0"),
            Color.parseColor("#8EDEFF"),
            Color.parseColor("#31C3BA"),
            Color.parseColor("#f49468"),
            Color.parseColor("#d5932c"),
            Color.parseColor("#34b5cc"),
            Color.parseColor("#8169c6"),
            Color.parseColor("#ca4561"),
            Color.parseColor("#fee335")};


    /**
     * 将double转为数值，并最多保留num位小数。例如当num为2时，1.268为1.27，1.2仍为1.2；1仍为1，而非1.00;100.00则返回100。
     *
     * @param d
     * @param num 小数位数
     * @return
     */
    public static String double2String(double d, int num) {
        NumberFormat nf = NumberFormat.getNumberInstance();
        nf.setMaximumFractionDigits(num);//保留两位小数
        nf.setGroupingUsed(false);//去掉数值中的千位分隔符

        String temp = nf.format(d);
        if (temp.contains(".")) {
            String s1 = temp.split("\\.")[0];
            String s2 = temp.split("\\.")[1];
            for (int i = s2.length(); i > 0; --i) {
                if (!s2.substring(i - 1, i).equals("0")) {
                    return s1 + "." + s2.substring(0, i);
                }
            }
            return s1;
        }
        return temp;
    }

    /**
     * 将double转为数值，并最多保留num位小数。
     *
     * @param d
     * @param num      小数个数
     * @param defValue 默认值。当d为null时，返回该值。
     * @return
     */
    public static String double2String(Double d, int num, String defValue) {
        if (d == null) {
            return defValue;
        }

        return double2String(d, num);
    }
}