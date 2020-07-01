package com.example.lmy.customview.Animation;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.animation.TypeEvaluator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.Intent;
import android.graphics.PointF;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.BounceInterpolator;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.lmy.customview.R;
import com.example.lmy.customview.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class ShuXingAnimActivity extends BaseActivity {

    @BindView(R.id.bt_01)
    Button bt01;
    @BindView(R.id.iv_image)
    ImageView ivImage;
    @BindView(R.id.tv_content)
    TextView tvContent;
    @BindView(R.id.tv_donghua)
    ScrollingDigitalAnimation tvDonghua;

    public static void show(Context context) {
        context.startActivity(new Intent(context, ShuXingAnimActivity.class));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shu_xing_anim);
        ButterKnife.bind(this);
        /**
         * 设置金钱的动画
         * */
        tvDonghua.setPrefixString("￥");
        tvDonghua.setNumberString("9", "999999999"); //设置起始于结束的数字
    }

    @OnClick({R.id.bt_01, R.id.bt_02, R.id.bt_03, R.id.iv_image})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.bt_01:

                startValueAnim(tvContent);


                // 载入XML动画
                Animator animator = AnimatorInflater.loadAnimator(ShuXingAnimActivity.this, R.animator.valueanim);
                // 设置动画对象
                animator.setTarget(tvContent);
                // 启动动画
                animator.start();


                break;
            case R.id.bt_02:
                //将TextView从常规变换成全透明，再从全透明变换成常规
                ObjectAnimator anim1 = ObjectAnimator.ofFloat(ivImage, "alpha", 1f, 0f, 1f);
                anim1.setDuration(3000);
                //将TextView进行一次360度的旋转
                ObjectAnimator anim2 = ObjectAnimator.ofFloat(ivImage, "rotation", 0f, 360f);
                anim2.setDuration(3000);
                //将TextView先向左移出屏幕，然后再移动回来
                float curTranslationX = ivImage.getTranslationX();
                ObjectAnimator anim3 = ObjectAnimator.ofFloat(ivImage, "translationX",
                        curTranslationX, -500f, curTranslationX);
                anim3.setDuration(3000);
                //将TextView先向右移出屏幕，然后再移动回来
                float curTranslationX2 = ivImage.getTranslationX();
                ObjectAnimator anim8 = ObjectAnimator.ofFloat(ivImage, "translationX",
                        curTranslationX, 1000f, curTranslationX);
                anim8.setDuration(3000);


                //将TextView在垂直方向上放大3倍再还原
                ObjectAnimator anim4 = ObjectAnimator.ofFloat(ivImage, "scaleY", 1f, 3f, 1f);
                anim4.setDuration(3000);
                //将TextView在水平方向上放大3倍再还原
                ObjectAnimator anim5 = ObjectAnimator.ofFloat(ivImage, "scaleX", 1f, 3f, 1f);
                anim5.setDuration(3000);

                //将TextView先向上移出屏幕，然后再移动回来
                float curTranslationY = ivImage.getTranslationY();
                ObjectAnimator anim6 = ObjectAnimator.ofFloat(ivImage, "translationY",
                        curTranslationY, -500f, curTranslationY);
                anim6.setDuration(3000);
                AnimatorSet animSet = new AnimatorSet();
                animSet.playSequentially(anim1, anim2, anim3, anim4, anim8, anim5, anim6);
                animSet.start();


                break;
            case R.id.bt_03:
                //让TextView先从屏幕外移动进屏幕，然后开始旋转360度，旋转的同时进行淡入淡出操作
                ObjectAnimator moveIn = ObjectAnimator.ofFloat(ivImage, "translationX", -500f, 0f);
                ObjectAnimator rotate = ObjectAnimator.ofFloat(ivImage, "rotation", 0f, 360f);
                ObjectAnimator fadeInOut = ObjectAnimator.ofFloat(ivImage, "alpha", 1f, 0f, 1f);
                AnimatorSet animSets = new AnimatorSet();
                //让TextView先从屏幕外移动进屏幕，然后开始旋转360度，旋转的同时进行淡入淡出操作
                animSets.play(rotate).with(fadeInOut).after(moveIn);
                //一起执行
//                animSets.playTogether(moveIn, rotate, fadeInOut);
                //顺序执行
//                animSets.playSequentially(moveIn, rotate, fadeInOut);
                animSets.setDuration(5000);
                animSets.start();
                break;
            case R.id.iv_image:
//                startAnimator();
//                startObjectAnimatorAnim(ivImage);
//                startValueAnimatorAnim(ivImage);
//                startPropertyValueHolderAnim(ivImage);
//                startAnimatorSet(ivImage);
//                startEvaluator(ivImage);
//                startInterpolatorApply(ivImage);
                break;
            default:
                break;
        }
    }


    /**
     * 数值变换的属性动画
     */
    public void startValueAnim(TextView view) {
        //初始化一个从0变化到100的动画
        ValueAnimator animator = ValueAnimator.ofInt(0, 100);
        //设置动画持续时间
        animator.setDuration(2000);
        //重放次数
        animator.setRepeatCount(0);
        animator.setRepeatMode(ValueAnimator.RESTART);
        //重放模式
        //ValueAnimator.START：正序
        //ValueAnimator.REVERSE：倒序
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                view.setText(valueAnimator.getAnimatedValue() + "");
            }
        });
        animator.start();
    }

    public void startAnimatorSet(View iv) {
        ObjectAnimator animator1 = ObjectAnimator.ofFloat(iv, "translationX", 0f, 500F);

        ObjectAnimator animator2 = ObjectAnimator.ofFloat(iv, "alpha", 0f, 1f);

        ObjectAnimator animator3 = ObjectAnimator.ofFloat(iv, "scaleX", 0f, 2f);
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.setDuration(500);

        //animatorSet.play(animator3).with(animator2).after(animator1);//链式调用顺序
        //animatorSet.play(animator3).with(animator2).before(animator1);//animator1
        //animatorSet.playTogether(animator1,animator2,animator3);//一起执行
        animatorSet.playSequentially(animator2, animator1, animator3);//顺序执行
        animatorSet.start();
    }


    /**
     * 插值器
     * 由API提供的一组算法，用来操作动画执行是的变换规则，省去了一些自己写算法的麻烦，大致分为九种
     */
    public void startInterpolatorApply(final View v) {
        ValueAnimator animator = new ValueAnimator();
        animator.setDuration(3000);
        animator.setObjectValues(new PointF(0, 0));
        final PointF point = new PointF();
        //估值

        animator.setEvaluator(new TypeEvaluator() {

            @Override
            public Object evaluate(float fraction, Object startValue, Object endValue) {
                point.x = 100f * (fraction * 5);
                // y=vt=1/2*g*t*t(重力计算)
                point.y = 0.5f * 98f * (fraction * 5) * (fraction * 5);
                return point;
            }
        });

        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                v.setX(point.x);
                v.setY(point.y);
            }
        });

        //详细算法见资料图片
//		加速插值器，公式： y=t^(2f) （加速度参数. f越大，起始速度越慢，但是速度越来越快）
//        animator.setInterpolator(new AccelerateInterpolator(10));

//      减速插值器公式: y=1-(1-t)^(2f) （描述: 加速度参数. f越大，起始速度越快，但是速度越来越慢）
//		animator.setInterpolator(new DecelerateInterpolator());

//      先加速后减速插值器 y=cos((t+1)π)/2+0.5
//		animator.setInterpolator(new AccelerateDecelerateInterpolator());

//      张力值, 默认为2，T越大，初始的偏移越大，而且速度越快 公式：y=(T+1)×t3–T×t2
//		animator.setInterpolator(new AnticipateInterpolator());

//      张力值tension，默认为2，张力越大，起始和结束时的偏移越大，
//      而且速度越快;额外张力值extraTension，默认为1.5。公式中T的值为tension*extraTension
//		animator.setInterpolator(new AnticipateOvershootInterpolator());
//      弹跳插值器
        animator.setInterpolator(new BounceInterpolator());
//      周期插值器 y=sin(2π×C×t)  周期值，默认为1；2表示动画会执行两次
//      周期插值器 y=sin(2π×C×t)  周期值，默认为1；2表示动画会执行两次
//		animator.setInterpolator(new CycleInterpolator(2));
//		线性插值器，匀速公式：Y=T
//		animator.setInterpolator(new LinearInterpolator());

//      公式: y=(T+1)x(t1)3+T×(t1)2 +1
//      描述: 张力值，默认为2，T越大，结束时的偏移越大，而且速度越快
//		animator.setInterpolator(new OvershootInterpolator());

        animator.start();

    }


    /**
     * 估值器
     * 自由落体效果实现
     * 核心目的：自定义变换规则
     */
    public void startEvaluator(final View v) {
        ValueAnimator animator = new ValueAnimator();
        animator.setDuration(3000);
        animator.setObjectValues(new PointF(0, 0));
        final PointF point = new PointF();
        //估值
        animator.setEvaluator(new TypeEvaluator() {
            @Override
            public Object evaluate(float fraction, Object startValue, Object endValue) {
                point.x = 100f * (fraction * 5);
                // y=vt=1/2*g*t*t(重力计算)
                point.y = 0.5f * 130f * (fraction * 5) * (fraction * 5);
                return point;
            }
        });

        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                PointF p = (PointF) animation.getAnimatedValue();
                Log.d("hongx", p.x + "===" + p.y);
                v.setX(p.x);
                v.setY(p.y);
            }
        });
        animator.start();
    }


    /**
     * 操作对象属性，不局限于对象
     * 完成透明动画
     */
    public void startObjectAnimatorAnim(View v) {
        //1.设置参数
        //2.执行动画
        ObjectAnimator alphaAnim = ObjectAnimator.ofFloat(v, "alpha", 1.0f, 0.3f, 1.0f);
        //执行时间
        alphaAnim.setDuration(1000);
        //延迟
        alphaAnim.setStartDelay(300);
        alphaAnim.start();

    }

    public void startValueAnimatorAnim(final View v) {
        //组合使用300
        ObjectAnimator animator = ObjectAnimator.ofFloat(v, "hehe", 0f, 100f, 50f);

        animator.setDuration(300);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
//				animation.getAnimatedFraction();//百分比
                float value = (float) animation.getAnimatedValue();//百分比的所对应的值
                Log.d("hongx", value + "");
                v.setScaleX(0.5f + value / 200);
                v.setScaleY(0.5f + value / 200);
            }
        });
        animator.start();
//		animator.setRepeatCount(2);//重复次数
        animator.setRepeatCount(ValueAnimator.INFINITE);//无限次数
    }


    public void startPropertyValueHolderAnim(View v) {
        PropertyValuesHolder holder1 = PropertyValuesHolder.ofFloat("alpha", 1f, 0.5f);
        PropertyValuesHolder holder2 = PropertyValuesHolder.ofFloat("scaleX", 1f, 0.5f);
        PropertyValuesHolder holder3 = PropertyValuesHolder.ofFloat("scaleY", 1f, 0.5f);
        ObjectAnimator animator = ObjectAnimator.ofPropertyValuesHolder(v, holder1, holder2, holder3);
        animator.setDuration(200);
        animator.start();
    }


    /**
     * ObjectAnimator
     * ObjectAnimator是属性动画框架中最重要的实现类，
     * 创建一个ObjectAnimator只需要通过它的静态工厂类直接返回一个ObjectAnimator对象。
     * 参数包括一个对象和对象的属性名字，但这个属性必须有get和set函数，内部会通过Java反射机制来调用set函数修改对象的属性值。
     */

    public String TAG = "HHH";

    private void startAnimator() {
        ObjectAnimator animator = ObjectAnimator.ofFloat(ivImage, "translationX", 300);
        animator.setDuration(5000);
        animator.start();
        animator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
                Log.e(TAG, "onAnimationStart");
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                Log.e(TAG, "onAnimationEnd");
            }

            @Override
            public void onAnimationCancel(Animator animation) {
                Log.e(TAG, "onAnimationCancel");
            }

            @Override
            public void onAnimationRepeat(Animator animation) {
                Log.e(TAG, "onAnimationRepeat");
            }
        });


    }
}
