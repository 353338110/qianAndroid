package com.qian.contract;

import com.luck.picture.lib.entity.LocalMedia;
import com.qian.base.IView;
import com.qian.bean.ChoosePicBean;
import com.qian.bean.MoodLog;
import com.qian.bean.kaiyan.Replies;

import java.util.List;

/**
 * Created by master on 2017/9/21.
 */

public interface IUploadContract {
    interface View extends IView {
        MoodLog getMoodLog();
        List<ChoosePicBean> getPicList();
        void showSuccess();
        void showError();
    }

    interface Presenter {
        void upload();
    }
}
