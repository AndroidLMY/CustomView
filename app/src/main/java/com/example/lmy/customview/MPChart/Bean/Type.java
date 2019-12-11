package com.example.lmy.customview.MPChart.Bean;

import java.io.Serializable;

/**
 * @功能:
 * @Creat 2019/05/13 17:33
 * @User Lmy
 * @By Android Studio
 */
public class Type implements Serializable {
    private String typeName;//类型名称
    private int sale;//销量
    private double typeScale;//类型占比

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public int getSale() {
        return sale;
    }

    public void setSale(int sale) {
        this.sale = sale;
    }

    public double getTypeScale() {
        return typeScale;
    }

    public void setTypeScale(double typeScale) {
        this.typeScale = typeScale;
    }
}
