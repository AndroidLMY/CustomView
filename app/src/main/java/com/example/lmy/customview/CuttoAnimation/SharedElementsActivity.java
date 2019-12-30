package com.example.lmy.customview.CuttoAnimation;

import android.os.Bundle;
import android.transition.Slide;
import android.view.Gravity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.androidlmy.headcustomview.HeadCustomView;
import com.androidlmy.headcustomview.OnClickHeadView;
import com.example.lmy.customview.R;
import com.example.lmy.customview.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * 共享元素 效果
 */
public class SharedElementsActivity extends BaseActivity implements OnClickHeadView {

    @BindView(R.id.title)
    HeadCustomView title;
    @BindView(R.id.recyclerview)
    RecyclerView recyclerview;

    private ShareAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shared_elements);
        ButterKnife.bind(this);
        initTitle();
        initRecyclerview();
        setupWindowAnimations();

    }
    private void setupWindowAnimations() {
        // 侧滑动画
        Slide transition = new Slide();
        transition.setSlideEdge(Gravity.LEFT);
        transition.setDuration(300);

        // 爆炸效果的动画
        // Explode transition = new Explode();
        // transition.setDuration(getResources().getInteger(R.integer.anim_duration_long));

        // 渐变动画
        // Fade transition = new Fade();
        // transition.setDuration(getResources().getInteger(R.integer.anim_duration_long));

        // 这两个方法在 TransitionActivity1 详解
        getWindow().setReenterTransition(transition);
        getWindow().setExitTransition(transition);
    }

    private void initRecyclerview() {
        if (adapter == null) {
            adapter = new ShareAdapter(this);
            recyclerview.setLayoutManager(new LinearLayoutManager(this));
            recyclerview.setAdapter(adapter);
        } else {
            adapter.notifyDataSetChanged();
        }
    }


    public void initTitle() {
        title.setBackgroundColor(getResources().getColor(R.color.blue));
        title.setTitle("共享元素");
        title.setTitleTextColor(getResources().getColor(R.color.white));
        title.setClickCallBack(this);

    }


    @Override
    public void onBackClick() {
        finish();
    }

    @Override
    public void onBackTextClick() {

    }

    @Override
    public void onRightTextClick() {

    }

    @Override
    public void onRightImgClick() {

    }

}
