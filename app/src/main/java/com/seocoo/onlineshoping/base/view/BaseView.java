package com.seocoo.onlineshoping.base.view;

import android.content.Context;

/**
 * desc   :
 * author : Jiang
 * date   : 2019/1/7
 */

public interface BaseView {
    /**
     * 吐司
     *
     * @param message 信息
     */
    void showToast(String message);

    /**
     * 上下文
     *
     * @return context
     */
    Context getContext();

    /**
     * 显示加载框
     */
    void showLoadingDialog();

    /**
     * 隐藏
     */
    void hideLoadingDialog();
}
