package com.seocoo.onlineshoping.module;

import com.seocoo.onlineshoping.bean.BGAEntity;


import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * desc   :
 * author : Jiang
 * date   : 2019/1/7
 */
public interface ApiService {

    @GET("{itemCount}item.json")
    Observable<BGAEntity> getStoreBanner(@Path("itemCount") String itemCount);
}
