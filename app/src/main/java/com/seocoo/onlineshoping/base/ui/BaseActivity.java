package com.seocoo.onlineshoping.base.ui;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.gyf.barlibrary.ImmersionBar;
import com.seocoo.onlineshoping.R;
import com.seocoo.onlineshoping.base.BaseApp;
import com.seocoo.onlineshoping.base.presenter.BasePresenter;
import com.seocoo.onlineshoping.base.view.BaseView;
import com.seocoo.onlineshoping.dagger.component.ActivityComponent;
import com.seocoo.onlineshoping.dagger.component.DaggerActivityComponent;
import com.seocoo.onlineshoping.utils.ActivityUtils;
import com.tbruyelle.rxpermissions2.RxPermissions;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import es.dmoral.toasty.Toasty;

/**
 * desc   :
 * author : Jiang
 * date   : 2019/1/7
 */
public abstract class BaseActivity<V extends BasePresenter> extends AppCompatActivity implements BaseView {
    @Inject
    protected V mPresenter;
    private Unbinder unBinder;
    protected BaseActivity mActivity;
    protected ActivityComponent activityComponent;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        unBinder = ButterKnife.bind(this);
        mActivity = this;
        ActivityUtils.getInstance().addActivity(this);
        activityComponent = DaggerActivityComponent.builder().applicationComponent(((BaseApp) getApplication()).getApplicationComponent()).build();
        activityInject();
        if (isImmersionBarEnabled()) {
            initImmersionBar(R.color.transparent);
        }
        if (mPresenter != null) {
            mPresenter.attachView(this);
            mPresenter.addRxBindingSubscribe(new RxPermissions(this)
                    .request(Manifest.permission.WRITE_EXTERNAL_STORAGE,
                            Manifest.permission.READ_EXTERNAL_STORAGE)
                    .subscribe(aBoolean -> {
                        if (!aBoolean) {
                            showToast("请开放相关权限");
                        }
                    }));
        }
        initView();
        initEvent();
    }

    protected abstract void activityInject();

    /**
     * 布局文件
     *
     * @return
     */
    protected abstract int getLayoutId();

    /**
     * 初始化控件
     */
    protected abstract void initView();

    /**
     * 初始化事件
     */
    protected abstract void initEvent();

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivityUtils.getInstance().removeActivity(this);
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

    protected void readyGo(Class<?> clazz) {
        Intent intent = new Intent(this, clazz);
        startActivity(intent);
    }

    protected void readyGo(Class<?> clazz, Bundle bundle) {
        Intent intent = new Intent(this, clazz);
        if (null != bundle) {
            intent.putExtras(bundle);
        }
        startActivity(intent);
    }

    /**
     * //状态栏沉浸式
     *
     * @param colorId 颜色
     */
    protected void initImmersionBar(int colorId) {
        ImmersionBar.with(this).statusBarColor(colorId).init();
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

    /**
     * 吐司
     *
     * @param message 信息
     */
    @Override
    public void showToast(String message) {
        Toasty.info(BaseApp.getInstance().getApplicationContext(), message, Toast.LENGTH_SHORT, false).show();
    }

    @Override
    public Context getContext() {
        return this;
    }


    @Override
    public void showLoadingDialog() {

    }

    @Override
    public void hideLoadingDialog() {

    }
}
