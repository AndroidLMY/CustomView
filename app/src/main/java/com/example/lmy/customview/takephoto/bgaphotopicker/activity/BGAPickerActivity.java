package com.example.lmy.customview.takephoto.bgaphotopicker.activity;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.androidlmy.headcustomview.HeadCustomView;
import com.androidlmy.headcustomview.OnClickHeadView;
import com.bumptech.glide.Glide;
import com.example.lmy.customview.takephoto.bgaphotopicker.utils.BGAPhotoPickerUtils;
import com.example.lmy.customview.R;
import com.example.lmy.customview.base.BaseActivity;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.bingoogolapple.photopicker.activity.BGAPhotoPickerActivity;
import cn.bingoogolapple.photopicker.activity.BGAPhotoPickerPreviewActivity;
import cn.bingoogolapple.photopicker.widget.BGASortableNinePhotoLayout;


/**
 * 你自己项目里「可以不继承 BGAPPToolbarActivity」，我在这里继承 BGAPPToolbarActivity 只是为了方便写 Demo
 */
public class BGAPickerActivity extends BaseActivity implements OnClickHeadView,View.OnClickListener {
    @BindView(R.id.title)
    HeadCustomView title;
    @BindView(R.id.tv_moment_add_choice_photo)
    TextView tvMomentAddChoicePhoto;
    @BindView(R.id.tv_to)
    TextView tvTo;
    @BindView(R.id.tv_getfile)
    TextView tvGetfile;
    @BindView(R.id.iv_show)
    ImageView ivShow;
    @BindView(R.id.snpl_moment_add_photos)
    BGASortableNinePhotoLayout snplMomentAddPhotos;
    ArrayList<String> mPicList = new ArrayList<>();


    public static void show(Context context) {
        context.startActivity(new Intent(context, BGAPickerActivity.class));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bgapicker);
        ButterKnife.bind(this);
        initTitle();
        BGAPhotoPickerUtils.getInstance().init(BGAPickerActivity.this, snplMomentAddPhotos, 9, 3, BGAPhotoPickerUtils.RC_CHOOSE_PHOTO);

    }

    @Override
    public void initTitle() {
        title.setBackgroundColor(getResources().getColor(R.color.blue));
        title.setTitle("BGA九宫格图片选择");
        title.setTitleTextColor(getResources().getColor(R.color.white));
        title.setClickCallBack(this);

    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_moment_add_choice_photo:
                BGAPhotoPickerUtils.getInstance().MultipleChoice(BGAPickerActivity.this);
                break;
            case R.id.tv_to:
                BGANinePhotoActivity.show(BGAPickerActivity.this);
                break;
            case R.id.tv_getfile:
                Log.d("GetData imageFile", BGAPhotoPickerUtils.getInstance().getFile().toString());
                String push = "content://";

                break;
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == BGAPhotoPickerUtils.RC_CHOOSE_PHOTO) {
            snplMomentAddPhotos.addMoreData(BGAPhotoPickerActivity.getSelectedPhotos(data));//设置多选数据
        } else if (requestCode == BGAPhotoPickerUtils.RC_PHOTO_PREVIEW) {
            //拍照返回的数据 也属于单选
            snplMomentAddPhotos.setData(BGAPhotoPickerPreviewActivity.getSelectedPhotos(data));//设置单选数据
        } else if (requestCode == BGAPhotoPickerUtils.MULTIPLECHOICE) {
            //单选时返回的数据
            if (data == null) {
                Toast.makeText(this, "未选择图片", Toast.LENGTH_SHORT).show();
            } else {
                Glide.with(this).load(BGAPhotoPickerActivity.getSelectedPhotos(data).get(0)).into(ivShow);
            }
        }
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


