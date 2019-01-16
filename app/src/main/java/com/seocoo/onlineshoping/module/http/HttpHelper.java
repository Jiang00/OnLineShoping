package com.seocoo.onlineshoping.module.http;

import com.seocoo.onlineshoping.bean.BGAEntity;
import com.seocoo.onlineshoping.module.ApiService;

import javax.inject.Inject;

import io.reactivex.Observable;

/**
 * desc   :
 * author : Jiang
 * date   : 2019/1/7
 */
public class HttpHelper implements IHttpHelper {
    private ApiService mApis;
    private PacketsHelper mPackets;

    @Inject
    HttpHelper(ApiService apiService, PacketsHelper packetsHelper) {
        mApis = apiService;
        mPackets = packetsHelper;
    }

    /**
     * 超市便利banner
     *
     * @param itemCount
     * @return
     */
    @Override
    public Observable<BGAEntity> getStoreBanner(String itemCount) {
        return mApis.getStoreBanner(itemCount);
    }
}
