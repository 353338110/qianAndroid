package com.qian.presenter;

import com.blankj.utilcode.util.LogUtils;
import com.qian.activity.UploadActivity;
import com.qian.base.BasePresenter;
import com.qian.bean.ChoosePicBean;
import com.qian.contract.IUploadContract;
import com.qian.utils.rxJaveRetrofitUtil.ProgressSubscriber;
import com.qian.utils.rxJaveRetrofitUtil.RetrofitHelper;
import com.qian.utils.rxJaveRetrofitUtil.SubscriberOnNextListener;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.MediaType;
import okhttp3.RequestBody;

/**
 * Created by master on 2017/9/21.
 */

public class UploadPresenter extends BasePresenter<UploadActivity> implements IUploadContract.Presenter{
    @Override
    public void upload() {
        /*Map<String, RequestBody> bodyMap = new HashMap<>();
        if(getIView().getPicList().size() > 0) {
            for (int i = 0; i < getIView().getPicList().size(); i++) {
                if (null!=getIView().getPicList().get(i).getCompressPath()){
                    File file = new File(getIView().getPicList().get(i).getCompressPath());
                    bodyMap.put("file"+i+"\"; filename=\""+file.getName(), RequestBody.create(MediaType.parse("image/png"),file));
                }

            }
        }*/
        List<ChoosePicBean> list = new ArrayList<>();
        for (int i = 0; i < getIView().getPicList().size(); i++) {
            if (null!=getIView().getPicList().get(i).getCompressPath()){
                list.add(getIView().getPicList().get(i));
            }
        }
        File[] files = new File[list.size()];
        for (int i = 0; i < list.size(); i++) {
            File file = new File(list.get(i).getCompressPath());
            files[i] = file;
        }
        RetrofitHelper.getInstance().uploadMood(new ProgressSubscriber<String>(new SubscriberOnNextListener() {
            @Override
            public void onNext(Object o) {
                LogUtils.w(o);
            }

            @Override
            public void error(String error) {
                LogUtils.w(error);
            }
        }),getIView().getMoodLog().getTitle(),getIView().getMoodLog().getContent(),files);
    }
}
