package com.seocoo.onlineshoping.module.http;

import com.seocoo.onlineshoping.bean.BGAEntity;

import io.reactivex.Observable;

/**
 * desc   :
 * author : Jiang
 * date   : 2019/1/7
 */
public interface IHttpHelper {
    /**
     * 超市便利banner
     *
     * @param itemCount
     * @return
     */
    Observable<BGAEntity> getStoreBanner(String itemCount);
}
