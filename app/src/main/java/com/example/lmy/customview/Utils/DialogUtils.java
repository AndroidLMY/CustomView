package com.example.lmy.customview.Utils;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;



/**
 * @功能:
 * Dialog辅助类
 * @Creat 2019/05/06 10:44
 * @User Lmy
 * @By Android Studio
 */
public class DialogUtils {
    private static DialogUtils dialogUtils;

    public static DialogUtils getInstance() {
        if (dialogUtils == null) {
            dialogUtils = new DialogUtils();
        }
        return dialogUtils;
    }
    //----------------------------------------------------------Dialog控件----------------------------------------------------------------------


    /**
     * 默认的弹出框
     *
     * @param activity
     * @param onDialogListener 实现接口
     */
    public void displayDialog(Activity activity, final OnDialogListener onDialogListener) {
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        onDialogListener.onBuilder(builder);
        builder.create().show();
    }

    public interface OnDialogListener {
        void onBuilder(AlertDialog.Builder builder);
    }

    /**
     * 弹出对话框
     *
     * @param activity
     * @param arr             内容 String[]{"启动照相机", "打开手机相册", "取消选择"}
     * @param onClickListener 实现接口
     */
    public void displayListDialog(Activity activity, final String[] arr, final DialogInterface.OnClickListener onClickListener) {
        displayDialog(activity, new OnDialogListener() {
            @Override
            public void onBuilder(AlertDialog.Builder builder) {
                builder.setItems(arr, onClickListener);
            }
        });
    }


    /**
     * 自定义的Dialog View 页面
     *
     * @param context
     * @param resource                    xml布局
     * @param onCustomAlertDialogListener 接口
     */
    public void displayAlertDialog(Context context, int resource, OnCustomAlertDialogListener onCustomAlertDialogListener) {
        final View view = LayoutInflater.from(context).inflate(resource, null); //    通过LayoutInflater来加载一个xml的布局文件作为一个View对象
        onCustomAlertDialogListener.OnHandleEvent(view, new AlertDialog.Builder(context));
    }

    //---------------------------------------------------------- 全屏对话框----------------------------------------------------------------------

    /**
     * 自定义的Dialog View 页面
     *
     * @param context
     * @param resource               xml布局
     * @param onCustomDialogListener 接口
     */
    public void displayCustomDialog(Context context, int resource, OnCustomDialogListener onCustomDialogListener) {
        View view = LayoutInflater.from(context).inflate(resource, null); //    通过LayoutInflater来加载一个xml的布局文件作为一个View对象
        Dialog dialog = new Dialog(context, resource);
        dialog.setContentView(view);
        onCustomDialogListener.OnHandleEvent(view, dialog);
    }

    /**
     * @param context
     * @param resource               xml布局
     * @param themeResId             样式Style
     * @param onCustomDialogListener
     */
    public void displayCustomDialog(Context context, int resource, int themeResId, OnCustomDialogListener onCustomDialogListener) {
        //    通过LayoutInflater来加载一个xml的布局文件作为一个View对象
        View view = LayoutInflater.from(context).inflate(resource, null);
        Dialog dialog = new Dialog(context, themeResId);
        dialog.setContentView(view);
        onCustomDialogListener.OnHandleEvent(view, dialog);
    }

    /**
     * 全屏对话框
     *
     * @param context
     * @param resource               xml布局
     * @param themeResId             样式 Style
     * @param gravity                位置 Gravity.FILL
     * @param WindowHeight           窗口高度 WindowManager.LayoutParams.FILL_PARENT
     * @param onCustomDialogListener
     */
    public void displayFillDialog(Context context, int resource, int themeResId, int gravity, int WindowHeight, OnCustomDialogListener onCustomDialogListener) {
        View view = LayoutInflater.from(context).inflate(resource, null);  //    通过LayoutInflater来加载一个xml的布局文件作为一个View对象
        Dialog dialog = new Dialog(context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        Window window = dialog.getWindow();
        window.getDecorView().setPadding(0, 0, 0, 0); //获得window窗口的属性
        window.setGravity(gravity);//window.setGravity(Gravity.FILL);
        window.setWindowAnimations(themeResId); //设置dialog弹出时的动画效果，从屏幕底部向上弹出ActionSheetDialogAnimation
        window.setContentView(view);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT)); //dialog.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);//去掉这句话，背景会变暗
        WindowManager.LayoutParams lp = window.getAttributes();
        lp.width = WindowManager.LayoutParams.FILL_PARENT; //设置窗口宽度为充满全屏
        lp.height = WindowHeight; //设置窗口高度为包裹内容
        window.setAttributes(lp); //将设置好的属性set回去
        onCustomDialogListener.OnHandleEvent(view, dialog);
    }


    //----------------------------------------------------------自适应对话框----------------------------------------------------------------------

    public interface OnCustomAlertDialogListener {
        void OnHandleEvent(View view, final AlertDialog.Builder builder);
    }

    public interface OnCustomDialogListener {
        void OnHandleEvent(View view, final Dialog dialog);
    }


}
