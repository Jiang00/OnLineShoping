package com.seocoo.onlineshoping.module.prefs;

/**
 * desc:
 *
 * @author Bian
 * create at 2018/12/14 15:35
 */
public interface IPreferencesHelper {
    /**
     * 保存用户名
     *
     * @param account 账号
     */
    void setLoginAccount(String account);

    /**
     * 保存密码
     *
     * @param password 密码
     */
    void setLoginPassword(String password);


    /**
     * 获取用户名
     *
     * @return 账号
     */
    String getLoginAccount();

    /**
     * 获取登录密码
     *
     * @return 密码
     */
    String getLoginPassword();

}
