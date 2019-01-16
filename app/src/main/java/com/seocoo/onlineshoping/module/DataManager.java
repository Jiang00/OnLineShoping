package com.seocoo.onlineshoping.module;

import com.seocoo.onlineshoping.bean.BGAEntity;
import com.seocoo.onlineshoping.module.http.HttpHelper;
import com.seocoo.onlineshoping.module.http.IHttpHelper;
import com.seocoo.onlineshoping.module.prefs.IPreferencesHelper;
import com.seocoo.onlineshoping.module.prefs.PreferencesHelper;

import javax.inject.Inject;

import io.reactivex.Observable;

/**
 * desc   :
 * author : Jiang
 * date   : 2019/1/7
 */
public class DataManager implements IHttpHelper, IPreferencesHelper {
    private HttpHelper mHttp;
    private PreferencesHelper mPreferences;

    @Inject
    public DataManager(HttpHelper mHttp, PreferencesHelper preferencesHelper) {
        this.mHttp = mHttp;
        this.mPreferences = preferencesHelper;
    }

    /**
     * 保存登录账户
     *
     * @param account 账号
     */
    @Override
    public void setLoginAccount(String account) {
        mPreferences.setLoginAccount(account);
    }

    /**
     * 保存登录密码
     *
     * @param password 密码
     */
    @Override
    public void setLoginPassword(String password) {
        mPreferences.setLoginPassword(password);
    }

    /**
     * 获取登录账号
     *
     * @return
     */
    @Override
    public String getLoginAccount() {
        return mPreferences.getLoginAccount();
    }

    /**
     * 获取登录密码
     *
     * @return
     */
    @Override
    public String getLoginPassword() {
        return mPreferences.getLoginPassword();
    }

    /**
     * 超市便利banner
     *
     * @param itemCount
     * @return
     */
    @Override
    public Observable<BGAEntity> getStoreBanner(String itemCount) {
        return mHttp.getStoreBanner(itemCount);
    }
}
