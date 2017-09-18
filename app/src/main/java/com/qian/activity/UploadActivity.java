package com.qian.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.SparseArray;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.config.PictureMimeType;
import com.luck.picture.lib.entity.LocalMedia;
import com.qian.R;
import com.qian.base.BaseActivity;
import com.qian.base.BasePresenter;
import com.qian.utils.DoubleClick;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by master on 2017/9/16.
 */

public class UploadActivity extends BaseActivity {
    @BindView(R.id.toolbar_title)
    TextView toolbarTitle;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.ll_mixed_up_content)
    LinearLayout llMixedUpContent;
    @BindView(R.id.fab_chose)
    FloatingActionButton fabChose;

    List<EditText> etArray = new ArrayList<>();
    List<ImageView> imgArray = new ArrayList();
    LinearLayout.LayoutParams etParams;
    LinearLayout.LayoutParams imgParams;

    List<LocalMedia> selectList;

    @Override
    public void initParms(Bundle parms) {

    }

    @Override
    public View bindView() {
        return null;
    }

    @Override
    public int bindLayout() {
        return R.layout.activtiy_upload;
    }

    @Override
    protected BasePresenter loadPresenter() {
        return null;
    }

    @Override
    public void initView(View view) {
        toolbarTitle.setText("UpLoadMix");
        etParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        imgParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        imgParams.gravity = Gravity.CENTER;
        EditText firstEt = new EditText(this);
        addEt(firstEt);
    }

    @Override
    public void doBusiness(Context mContext) {

    }

    @OnClick(R.id.fab_chose)
    public void onViewClicked() {
        if (DoubleClick.isFastClick()) {
            PictureSelector.create(UploadActivity.this)
                    .openGallery(PictureMimeType.ofImage())
                    .forResult(PictureConfig.CHOOSE_REQUEST);
        }
    }

    private void addEt(final EditText editText) {

        Iterator<EditText> it = etArray.iterator();
        while (it.hasNext()) {
            if ("".equals(it.next().getText().toString())) {
                llMixedUpContent.removeView(it.next());
                it.remove();
            }
        }

        editText.setBackground(null);
        llMixedUpContent.addView(editText, etParams);
        etArray.add(editText);
    }

    private void addImg(final ImageView imageView) {
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //双击删除图片
                llMixedUpContent.removeView(imageView);
                imgArray.remove(imageView);
            }
        });
        llMixedUpContent.addView(imageView, imgParams);
        imgArray.add(imageView);
    }

    private void afterSelect(List<LocalMedia> selectList) {
        if (null != selectList && selectList.size() > 0) {
            for (int i = 0; i < selectList.size(); i++) {
                ImageView imageView = new ImageView(UploadActivity.this);
                Bitmap bitmap = getLoacalBitmap(selectList.get(i).getPath());
                imageView.setImageBitmap(bitmap);
                addImg(imageView);
            }
            EditText editText = new EditText(UploadActivity.this);
            editText.hasFocus();
            addEt(editText);
        }
    }

    public static Bitmap getLoacalBitmap(String url) {
        try {
            FileInputStream fis = new FileInputStream(url);
            return BitmapFactory.decodeStream(fis);  ///把流转化为Bitmap图片

        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case PictureConfig.CHOOSE_REQUEST:
                    // 图片选择结果回调
                    selectList = PictureSelector.obtainMultipleResult(data);
                    // 例如 LocalMedia 里面返回三种path
                    // 1.media.getPath(); 为原图path
                    // 2.media.getCutPath();为裁剪后path，需判断media.isCut();是否为true
                    // 3.media.getCompressPath();为压缩后path，需判断media.isCompressed();是否为true
                    // 如果裁剪并压缩了，以取压缩路径为准，因为是先裁剪后压缩的
                    afterSelect(selectList);
                    break;
            }
        }
    }
}
