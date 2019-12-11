package com.example.lmy.customview.SecondaryList;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.lmy.customview.R;

/**
 * @功能:
 * @Creat 2019/03/16 13:48
 * @User Lmy
 * @By Android Studio
 */
public class ExpandableAdapter extends BaseExpandableListAdapter {
    //Model：定义的数据
    private String[] groups;
    //注意，字符数组不要写成{{"A1,A2,A3,A4"}, {"B1,B2,B3,B4，B5"}, {"C1,C2,C3,C4"}}
    private Integer[][] childs;
    private Context context;

    public ExpandableAdapter(Context context, String[] groups, Integer[][] childs) {
        this.context = context;
        this.childs = childs;
        this.groups = groups;
    }

    //返回一级列表的个数
    @Override
    public int getGroupCount() {
        return groups.length;
    }

    //返回每个二级列表的个数
    @Override
    public int getChildrenCount(int groupPosition) { //参数groupPosition表示第几个一级列表
        Log.d("smyhvae", "-->" + groupPosition);
        return childs[groupPosition].length;
    }

    //返回一级列表的单个item（返回的是对象）
    @Override
    public Object getGroup(int groupPosition) {
        return groups[groupPosition];
    }

    //返回二级列表中的单个item（返回的是对象）
    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return childs[groupPosition][childPosition];  //不要误写成groups[groupPosition][childPosition]
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    //每个item的id是否是固定？一般为true
    @Override
    public boolean hasStableIds() {
        return true;
    }

    //【重要】填充一级列表
    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {

        if (convertView == null) {
            convertView = View.inflate(context, R.layout.item_group_name, null);
        } else {

        }
        TextView tv_group = (TextView) convertView.findViewById(R.id.tv_group);
        tv_group.setText(groups[groupPosition]);
        return convertView;
    }

    //【重要】填充二级列表
    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {

        if (convertView == null) {
            convertView = View.inflate(context, R.layout.item_child_case, null);
        }

        ImageView iv_child = (ImageView) convertView.findViewById(R.id.iv_child);
        //iv_child.setImageResource(resId);
        // tv_child.setText(childs[groupPosition][childPosition]);
        iv_child.setImageResource(childs[groupPosition][childPosition]);
        return convertView;
    }

    //二级列表中的item是否能够被选中？可以改为true
    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}
