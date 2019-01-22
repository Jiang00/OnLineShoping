package com.seocoo.onlineshoping.base.ui;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.gyf.barlibrary.ImmersionBar;
import com.seocoo.onlineshoping.base.BaseApp;
import com.seocoo.onlineshoping.base.presenter.BasePresenter;
import com.seocoo.onlineshoping.base.view.BaseView;
import com.seocoo.onlineshoping.dagger.component.DaggerFragmentComponent;
import com.seocoo.onlineshoping.dagger.component.FragmentComponent;
import com.squareup.leakcanary.RefWatcher;

import java.util.Objects;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import es.dmoral.toasty.Toasty;

/**
 * desc   :
 * author : Jiang
 * date   : 2019/1/7
 */
public abstract class BaseFragment<T extends BasePresenter> extends Fragment implements BaseView {
    protected FragmentComponent fragmentComponent;

    private Unbinder unBinder;
    /**
     * 是否与View建立起映射关系
     */
    private boolean isInitView = false;
    /**
     * 懒加载过
     */
    private boolean isLazyLoaded = false;
    @Inject
    protected T mPresenter;
    protected Context mContext;
    protected View cacheView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (cacheView == null) {
            cacheView = inflater.inflate(getLayoutId(), container, false);
        }
        ViewGroup parent = (ViewGroup) cacheView.getParent();
        if (parent != null) {
            parent.removeView(cacheView);
        }
        unBinder = ButterKnife.bind(this, cacheView);
        mContext = getContext();
        if (isImmersionBarEnabled()) {
            initImmersionBar();
        }
        fragmentComponent = DaggerFragmentComponent.builder()
                .applicationComponent(BaseApp.getInstance().getApplicationComponent())
                .build();
        fragmentInject();
        initView(cacheView);
        return cacheView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        isInitView = true;
        if (mPresenter != null) {
            mPresenter.attachView(this);
        }
        initEvent();
        if (getUserVisibleHint()) {
            lazyLoadData();
        }
    }

    /**
     * viewpager 嵌套懒加载有问题自己写
     */
    private void lazyLoadData() {
        if (getUserVisibleHint() && isInitView && !isLazyLoaded) {
            lazyEvent();
            isLazyLoaded = true;
        }
    }

    public void lazyEvent() {
        //懒加载
    }


    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        lazyLoadData();
    }

    /**
     * //状态栏沉浸式
     */
    protected void initImmersionBar() {

    }

    /**
     * 是否可以使用沉浸式
     * Is immersion bar enabled boolean.
     *
     * @return the boolean
     */
    protected boolean isImmersionBarEnabled() {
        return true;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (null != cacheView) {
            ((ViewGroup) cacheView.getParent()).removeView(cacheView);
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        BaseApp.getRefWatcher().watch(this);
        if (mPresenter != null) {
            mPresenter.detachView();
            mPresenter = null;
        }
        if (unBinder != null && unBinder != Unbinder.EMPTY) {
            unBinder.unbind();
            unBinder = null;
        }
    }

    @Override
    public void showToast(String message) {
        Toasty.info(BaseApp.getInstance().getApplicationContext(), message, Toast.LENGTH_SHORT, true).show();
    }

    @Override
    public void showLoadingDialog() {

    }

    @Override
    public void hideLoadingDialog() {

    }

    /**
     * 获取布局Id
     *
     * @return id
     */
    protected abstract int getLayoutId();

    /**
     * dagger2初始化
     */
    protected abstract void fragmentInject();

    /**
     * 初始化控件
     *
     * @param view
     */
    protected abstract void initView(View view);

    /**
     * 初始化时间
     */
    protected abstract void initEvent();
}
