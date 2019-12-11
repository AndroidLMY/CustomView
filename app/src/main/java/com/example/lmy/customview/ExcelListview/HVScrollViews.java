package com.example.lmy.customview.ExcelListview;

//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//


import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.RelativeLayout.LayoutParams;

import com.andjdk.hvscrollviewlibrary.CommonAdapter;
import com.andjdk.hvscrollviewlibrary.DisplayUtil;
import com.example.lmy.customview.R;

import java.util.ArrayList;

public class HVScrollViews extends RelativeLayout {
    private LinearLayout mLayoutTitleMovable;
    private float mStartX;
    private int mMoveOffsetX;
    private int mFixX;
    private String[] mFixLeftListColumnsText;
    private int[] mFixLeftListColumnsWidth;
    private String[] mMovableListColumnsText;
    private int[] mMovableListColumnsWidth;
    private ListView mStockListView;
    private Object mAdapter;
    private ArrayList<View> mMovableViewList;
    private Context context;
    private int mMovableTotalWidth;
    private int mMoveViewWidth;
    private int mFixViewWidth;
    private int mItemViewHeight;
    private LinearLayout footerLayout;
    private boolean isLoading;
    private int mLastVisibleItem;
    private int mTotalItemCount;
    private int mVisibleItemCount;
    private int mFirstVisibleItem;
    private OnItemClickListener mOnItemClickListener;
    private OnItemLongClickListener mOnItemLongClickListener;
    private HVScrollViews.OnItemClickedListener onItemClickedListener;
    private HVScrollViews.OnItemLongClickedListener onItemLongClickedListener;
    private HVScrollViews.OnHeaderClickedListener onHeaderClickedListener;
    private HVScrollViews.OnLoadMoreListener onLoadMoreListener;

    public HVScrollViews(Context context) {
        this(context, (AttributeSet) null);
    }

    public HVScrollViews(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public HVScrollViews(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mStartX = 0.0F;
        this.mMoveOffsetX = 0;
        this.mFixX = 0;
        this.mMovableListColumnsText = new String[0];
        this.mMovableListColumnsWidth = null;
        this.mMovableViewList = new ArrayList();
        this.mMovableTotalWidth = 0;
        this.mMoveViewWidth = 100;
        this.mFixViewWidth = 100;
        this.mItemViewHeight = 40;
        this.mOnItemClickListener = new OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (null != HVScrollViews.this.onItemClickedListener) {
                    HVScrollViews.this.onItemClickedListener.onItemClick(parent, view, position, id);
                }

            }
        };
        this.mOnItemLongClickListener = new OnItemLongClickListener() {
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                if (null != HVScrollViews.this.onItemLongClickedListener) {
                    HVScrollViews.this.onItemLongClickedListener.onItemLongClick(parent, view, position, id);
                }

                return false;
            }
        };
        this.onHeaderClickedListener = null;
        this.context = context;
    }

    private void initView() {
        LinearLayout linearLayout = new LinearLayout(this.getContext());
        linearLayout.setOrientation(LinearLayout.VERTICAL);
        linearLayout.addView(this.buildHeadLayout());
        linearLayout.addView(this.buildMoveableListView());
        this.addView(linearLayout, new LayoutParams(-1, -1));
    }

    private View buildHeadLayout() {
        LinearLayout headLayout = new LinearLayout(this.getContext());
        headLayout.setBackgroundColor(0xFF33A8FF);
        headLayout.setGravity(17);
        LinearLayout fixHeadLayout = new LinearLayout(this.getContext());
        this.addListHeaderTextView(this.mFixLeftListColumnsText[0], this.mFixLeftListColumnsWidth[0], fixHeadLayout);
        fixHeadLayout.setGravity(17);
        headLayout.addView(fixHeadLayout, 0, new android.view.ViewGroup.LayoutParams(DisplayUtil.dip2px(this.context, (float) this.mFixViewWidth), DisplayUtil.dip2px(this.context, (float) this.mItemViewHeight)));

        this.mLayoutTitleMovable = new LinearLayout(this.getContext());

        for (int i = 0; i < this.mMovableListColumnsText.length; ++i) {
            TextView textView = this.addListHeaderTextView(this.mMovableListColumnsText[i], this.mMovableListColumnsWidth[i], this.mLayoutTitleMovable);
            textView.setOnClickListener(new OnClickListener() {
                public void onClick(View v) {
                    if (HVScrollViews.this.onHeaderClickedListener != null) {
                        HVScrollViews.this.onHeaderClickedListener.onHeadViewClick(((TextView) v).getText().toString());
                    }

                }
            });
        }

        headLayout.addView(this.mLayoutTitleMovable);
        return headLayout;
    }

    private View buildMoveableListView() {
        RelativeLayout linearLayout = new RelativeLayout(this.getContext());
        this.mStockListView = new ListView(this.getContext());
        if (null != this.mAdapter && this.mAdapter instanceof CommonAdapter) {
            this.mStockListView.setAdapter((CommonAdapter) this.mAdapter);
            this.mMovableViewList = ((CommonAdapter) this.mAdapter).getMovableViewList();
        }

        this.footerLayout = new LinearLayout(this.getContext());
        this.footerLayout.setGravity(17);
        ProgressBar progressBar = new ProgressBar(this.getContext());
        this.footerLayout.addView(progressBar);
        this.footerLayout.setVisibility(View.GONE);
        this.mStockListView.setOnScrollListener(new OnScrollListener() {
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                if (HVScrollViews.this.mTotalItemCount == HVScrollViews.this.mLastVisibleItem && scrollState == 0 && !HVScrollViews.this.isLoading) {
                    HVScrollViews.this.isLoading = true;
                    if (null != HVScrollViews.this.onLoadMoreListener) {
                        HVScrollViews.this.onLoadMoreListener.onLoadingMore();
                        HVScrollViews.this.footerLayout.setVisibility(View.VISIBLE);
                    }
                }

            }

            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                HVScrollViews.this.mFirstVisibleItem = firstVisibleItem;
                HVScrollViews.this.mTotalItemCount = totalItemCount;
                HVScrollViews.this.mLastVisibleItem = firstVisibleItem + visibleItemCount;
                HVScrollViews.this.mVisibleItemCount = visibleItemCount;
            }
        });
        this.mStockListView.setOnItemClickListener(this.mOnItemClickListener);
        this.mStockListView.setOnItemLongClickListener(this.mOnItemLongClickListener);
        linearLayout.addView(this.footerLayout, -1, -1);
        linearLayout.addView(this.mStockListView, new LayoutParams(-1, -1));
        ListViewUtiils.getInstance().processListViewHeight(mStockListView);
        //第一次进来的时候需要计算listview的高
        return linearLayout;
    }

    public void onLoadingComplete() {
        if (null != this.footerLayout) {
            ((Activity) this.context).runOnUiThread(new Runnable() {
                public void run() {
                    HVScrollViews.this.footerLayout.setVisibility(View.GONE);
                    HVScrollViews.this.isLoading = false;
                    HVScrollViews.this.mStockListView.setSelection(HVScrollViews.this.mLastVisibleItem - HVScrollViews.this.mVisibleItemCount + 1);
                    ListViewUtiils.getInstance().processListViewHeight(mStockListView);//
                    // 解决高度问题  需要在设置完值之后在计算listview的高
                }
            });
        }
    }

    public void setAdapter(Object adapter) {
        this.mAdapter = adapter;
        this.initView();
    }

    public void setOnItemClick(HVScrollViews.OnItemClickedListener onItemClickedListener) {
        this.onItemClickedListener = onItemClickedListener;
    }

    public void setOnItemLongClick(HVScrollViews.OnItemLongClickedListener onItemLongClickedListener) {
        this.onItemLongClickedListener = onItemLongClickedListener;
    }

    private TextView addListHeaderTextView(String headerName, int AWidth, LinearLayout fixHeadLayout) {
        TextView textView = new TextView(this.getContext());
        textView.setText(headerName);
        textView.setGravity(17);
        textView.setBackgroundColor(getResources().getColor(R.color.danlan));

        fixHeadLayout.addView(textView, AWidth, DisplayUtil.dip2px(this.context, 40.0F));
        return textView;
    }

    public boolean onInterceptTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case 0:
                this.mStartX = ev.getX();
                break;
            case 1:
                this.actionUP();
                break;
            case 2:
                int offsetX = (int) Math.abs(ev.getX() - this.mStartX);
                if (offsetX > 30) {
                    return true;
                }

                return false;
        }

        return super.onInterceptTouchEvent(ev);
    }

    private void actionUP() {
        int i;
        if (this.mFixX < 0) {
            this.mFixX = 0;
            this.mLayoutTitleMovable.scrollTo(0, 0);
            if (null != this.mMovableViewList) {
                for (i = 0; i < this.mMovableViewList.size(); ++i) {
                    ((View) this.mMovableViewList.get(i)).scrollTo(0, 0);
                }
            }
        } else if (this.mLayoutTitleMovable.getWidth() + Math.abs(this.mFixX) > this.MovableTotalWidth()) {
            this.mLayoutTitleMovable.scrollTo(this.MovableTotalWidth() - this.mLayoutTitleMovable.getWidth(), 0);
            if (null != this.mMovableViewList) {
                for (i = 0; i < this.mMovableViewList.size(); ++i) {
                    ((View) this.mMovableViewList.get(i)).scrollTo(this.MovableTotalWidth() - this.mLayoutTitleMovable.getWidth(), 0);
                }
            }
        }

    }

    private int MovableTotalWidth() {
        if (0 == this.mMovableTotalWidth) {
            for (int i = 0; i < this.mMovableListColumnsWidth.length; ++i) {
                this.mMovableTotalWidth += this.mMovableListColumnsWidth[i];
            }
        }
        return this.mMovableTotalWidth;
    }

    private int downY = 0;//初始化按下时Y坐标变量



    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case 0:
                this.mStartX = event.getX();
                return true;
            case 1:
                this.mFixX = this.mMoveOffsetX;
                this.actionUP();
                break;
            case 2:
                int offsetX = (int) Math.abs(event.getX() - this.mStartX);
                if (offsetX > 30) {
                    this.mMoveOffsetX = (int) (this.mStartX - event.getX() + (float) this.mFixX);
                    if (0 > this.mMoveOffsetX) {
                        this.mMoveOffsetX = 0;
                    } else if (this.mLayoutTitleMovable.getWidth() + this.mMoveOffsetX > this.MovableTotalWidth()) {
                        this.mMoveOffsetX = this.MovableTotalWidth() - this.mLayoutTitleMovable.getWidth();
                    }

                    this.mLayoutTitleMovable.scrollTo(this.mMoveOffsetX, 0);
                    if (null != this.mMovableViewList) {
                        for (int i = 0; i < this.mMovableViewList.size(); ++i) {
                            ((View) this.mMovableViewList.get(i)).scrollTo(this.mMoveOffsetX, 0);
                        }
                    }
                }
                break;
        }

        return super.onTouchEvent(event);
    }

    public void setHeaderListData(String[] firsts, String[] headerListData) {
        if (headerListData != null) {
            this.mMovableListColumnsText = headerListData;
            this.mMovableListColumnsWidth = new int[headerListData.length];

            for (int i = 0; i < headerListData.length; ++i) {
                this.mMovableListColumnsWidth[i] = DisplayUtil.dip2px(this.context, (float) this.mMoveViewWidth);
            }

            this.mFixLeftListColumnsWidth = new int[]{DisplayUtil.dip2px(this.context, (float) this.mFixViewWidth)};
            this.mFixLeftListColumnsText = firsts;
        }
    }

    public HVScrollViews.OnHeaderClickedListener getOnHeaderClickedListener() {
        return this.onHeaderClickedListener;
    }

    public void setOnHeaderClickedListener(HVScrollViews.OnHeaderClickedListener onHeaderClickedListener) {
        this.onHeaderClickedListener = onHeaderClickedListener;
    }

    public HVScrollViews.OnLoadMoreListener getOnLoadMoreListener() {
        return this.onLoadMoreListener;
    }

    public void setOnLoadMoreListener(HVScrollViews.OnLoadMoreListener onLoadMoreListener) {
        this.onLoadMoreListener = onLoadMoreListener;
    }

    public interface OnItemLongClickedListener {
        void onItemLongClick(AdapterView<?> var1, View var2, int var3, long var4);
    }

    public interface OnItemClickedListener {
        void onItemClick(AdapterView<?> var1, View var2, int var3, long var4);
    }

    public interface OnLoadMoreListener {
        void onLoadingMore();
    }

    public interface OnHeaderClickedListener {
        void onHeadViewClick(String var1);
    }
}

