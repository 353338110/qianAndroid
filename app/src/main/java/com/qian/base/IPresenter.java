package com.qian.base;

/**
 * Created by master on 2017/9/5.
 */
public interface IPresenter<V extends IView> {

    /**
     * 绑定
     *
     * @param view
     */
    void attachView(V view);

    /**
     * 防止内存的泄漏,清楚presenter与activity之间的绑定
     */
    void detachView();

    /**
     * @return 获取View
     */
    IView getIView();
}
