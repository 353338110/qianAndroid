package com.qian.presenter;

import com.blankj.utilcode.util.LogUtils;
import com.qian.activity.UploadActivity;
import com.qian.base.BasePresenter;
import com.qian.bean.ChoosePicBean;
import com.qian.contract.IUploadContract;
import com.qian.utils.Constants;
import com.qian.utils.rxJaveRetrofitUtil.ProgressSubscriber;
import com.qian.utils.rxJaveRetrofitUtil.RetrofitHelper;
import com.qian.utils.rxJaveRetrofitUtil.SubscriberOnNextListener;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

/**
 * Created by master on 2017/9/21.
 */

public class UploadPresenter extends BasePresenter<UploadActivity> implements IUploadContract.Presenter{
    public  Map<String, RequestBody> filesToMap(List<ChoosePicBean>  files){
        List<ChoosePicBean>  temps  = new ArrayList<>();
        for (ChoosePicBean choosePicBean : files) {
            if (null != choosePicBean && null!=choosePicBean.getCompressPath() && !"".equals(choosePicBean.getCompressPath())){
                temps.add(choosePicBean);
            }
        }
        Map<String, RequestBody> parts = new HashMap<>();
        for (int i = 0; i < temps.size(); i++) {
            File file = new File(temps.get(i).getCompressPath());
            // TODO: 16-4-2  这里为了简单起见，没有判断file的类型
            RequestBody requestBody = RequestBody.create(MediaType.parse("image/png"), file);
            parts.put("files\"; filename=\"" + file.getName(), requestBody);
        }
        return parts;
    }
    @Override
    public void upload() {
        RetrofitHelper.getInstance().uploadMood(new ProgressSubscriber<String>(new SubscriberOnNextListener() {
            @Override
            public void onNext(Object o) {
                LogUtils.w(o);
            }

            @Override
            public void error(String error) {
                LogUtils.w(error);
            }
        }), Constants.uidUser.getUid(),getIView().getMoodLog().getTitle(),getIView().getMoodLog().getContent(),filesToMap(getIView().getPicList()));
    }
}
