package com.seocoo.onlineshoping.base.presenter;

import com.seocoo.onlineshoping.base.view.BaseView;

import io.reactivex.disposables.Disposable;

/**
 * desc: Presenter 接口类
 *
 * @author Bian
 * create at 2018/11/9 15:22
 */
public interface AbstractPresenter<T extends BaseView> {

    /**
     * 注入View
     *
     * @param view view
     */
    void attachView(T view);

    /**
     * 回收View
     */
    void detachView();


    /**
     * Add rxBing subscribe manager
     *
     * @param disposable Disposable
     */
    void addRxBindingSubscribe(Disposable disposable);

}
