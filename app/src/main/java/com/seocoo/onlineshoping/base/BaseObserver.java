package com.seocoo.onlineshoping.base;


import com.seocoo.onlineshoping.base.view.BaseView;

import io.reactivex.observers.ResourceObserver;

/**
 * desc:
 *
 * @author Bian
 * create at 2018/12/17 17:18
 */
public class BaseObserver<T> extends ResourceObserver<T> {
    private BaseView mView;
    private boolean isRefresh = false;

    protected BaseObserver(BaseView mView) {
        this.mView = mView;
    }

    protected BaseObserver(BaseView mView, boolean isRefresh) {
        this.mView = mView;
        this.isRefresh = isRefresh;
    }

    @Override
    protected void onStart() {
        if (mView != null && !isRefresh) {
            mView.showLoadingDialog();
        }
        super.onStart();
    }

    @Override
    public void onNext(T t) {
    }

    @Override
    public void onError(Throwable t) {
        if (mView != null && !isRefresh) {
            mView.hideLoadingDialog();
            mView.showToast(t.getMessage());
        }
    }

    @Override
    public void onComplete() {
        if (mView != null && !isRefresh) {
            mView.hideLoadingDialog();
        }
    }
}
