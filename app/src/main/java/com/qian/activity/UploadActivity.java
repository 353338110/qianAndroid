package com.qian.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TextInputEditText;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.config.PictureMimeType;
import com.luck.picture.lib.entity.LocalMedia;
import com.qian.R;
import com.qian.adapter.ChoosePicAdapter;
import com.qian.base.BaseActivity;
import com.qian.bean.ChoosePicBean;
import com.qian.bean.MoodLog;
import com.qian.contract.IUploadContract;
import com.qian.presenter.UploadPresenter;
import com.qian.utils.DoubleClick;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by master on 2017/9/16.
 */

public class UploadActivity extends BaseActivity<UploadPresenter> implements IUploadContract.View{
    @BindView(R.id.toolbar_title)
    TextView toolbarTitle;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.ll_mixed_up_content)
    LinearLayout llMixedUpContent;
    @BindView(R.id.fab_chose)
    FloatingActionButton fabChose;

    List<LocalMedia> selectList = new ArrayList<>();
    @BindView(R.id.rcv_pic)
    RecyclerView rcvPic;
    @BindView(R.id.et_content)
    TextInputEditText etContent;

    ChoosePicAdapter multiItemQuickAdapter;
    List<ChoosePicBean> choosePicBeanList = new ArrayList<>();

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
    protected UploadPresenter loadPresenter() {
        return new UploadPresenter();
    }

    @Override
    public void initView(View view) {
        toolbarTitle.setText("UpLoadMix");
    }

    @Override
    public void doBusiness(Context mContext) {
        choosePicBeanList.add(new ChoosePicBean(ChoosePicAdapter.TYPEADD));
        multiItemQuickAdapter = new ChoosePicAdapter(choosePicBeanList);
        rcvPic.setAdapter(multiItemQuickAdapter);
        rcvPic.setLayoutManager(new GridLayoutManager(mContext,3));

        multiItemQuickAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                if (choosePicBeanList.get(position).getItemType()==ChoosePicAdapter.TYPEADD){
                    PictureSelector.create(UploadActivity.this)
                            .openGallery(PictureMimeType.ofImage())
                            .maxSelectNum(6)
                            .compress(true)
                            .selectionMedia(selectList)
                            .forResult(PictureConfig.CHOOSE_REQUEST);
                }else {
                    choosePicBeanList.remove(position);
                    boolean has = false;
                    for (int i = 0; i < choosePicBeanList.size(); i++) {
                        if (choosePicBeanList.get(i).getItemType()==ChoosePicAdapter.TYPEADD){
                            has = true;
                        }
                    }
                    if (!has){
                        selectList.remove(position);
                        choosePicBeanList.add(0,new ChoosePicBean(ChoosePicAdapter.TYPEADD));
                    }else {
                        selectList.remove(position-1);
                    }
                    multiItemQuickAdapter.notifyDataSetChanged();
                }
            }
        });
    }

    @OnClick(R.id.fab_chose)
    public void onViewClicked() {
        if (DoubleClick.isFastClick()) {
            mPresenter.upload();
        }
    }



    private void afterSelect(List<LocalMedia> selectList) {
        choosePicBeanList.clear();
        if (null != selectList && selectList.size() > 0) {
            for (int i = 0; i < selectList.size(); i++) {
                ChoosePicBean choosePicBean = new ChoosePicBean(ChoosePicAdapter.TYPENORMAL);
                choosePicBean.setCompressPath(selectList.get(i).getCompressPath());
                choosePicBean.setPath(selectList.get(i).getPath());
                choosePicBeanList.add(choosePicBean);
            }
            if (selectList.size()<6){
                choosePicBeanList.add(0,new ChoosePicBean(ChoosePicAdapter.TYPEADD));
            }
            multiItemQuickAdapter.notifyDataSetChanged();

        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case PictureConfig.CHOOSE_REQUEST:
                    // 图片选择结果回调
                    //selectList.addAll(PictureSelector.obtainMultipleResult(data));
                    selectList.clear();
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


    @Override
    public MoodLog getMoodLog() {
        MoodLog moodLog = new MoodLog();
        moodLog.setContent(etContent.getText().toString());
        moodLog.setTitle("这是title");
        return moodLog;
    }

    @Override
    public List<ChoosePicBean> getPicList() {
        return choosePicBeanList;
    }

    @Override
    public void showSuccess() {

    }

    @Override
    public void showError() {

    }
}
