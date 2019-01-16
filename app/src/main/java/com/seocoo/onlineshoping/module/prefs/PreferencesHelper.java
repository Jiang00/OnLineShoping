package com.seocoo.onlineshoping.module.prefs;

import android.content.Context;
import android.content.SharedPreferences;

import com.seocoo.onlineshoping.base.BaseApp;
import com.seocoo.onlineshoping.constants.Constants;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * desc:
 *
 * @author Bian
 * create at 2018/12/14 15:35
 */
@Singleton
public class PreferencesHelper implements IPreferencesHelper {

    private SharedPreferences mPreferences;

    @Inject
    public PreferencesHelper(Context context) {
        mPreferences = BaseApp.getInstance().getSharedPreferences(Constants.SHARED_PREFERENCES, Context.MODE_PRIVATE);
    }

    /**
     * @param account 账号
     */
    @Override
    public void setLoginAccount(String account) {
        mPreferences.edit().putString(Constants.SP_ACCOUNT, account).apply();
    }

    /**
     * @param password 密码
     */
    @Override
    public void setLoginPassword(String password) {
        mPreferences.edit().putString(Constants.SP_PASSWORD, password).apply();
    }

    /**
     * @return
     */
    @Override
    public String getLoginAccount() {
        return mPreferences.getString(Constants.SP_ACCOUNT, "");
    }

    /**
     * @return
     */
    @Override
    public String getLoginPassword() {
        return mPreferences.getString(Constants.SP_PASSWORD, "");
    }

}
