package com.seocoo.onlineshoping.contract;

import com.seocoo.onlineshoping.base.view.BaseView;
import com.seocoo.onlineshoping.bean.BGAEntity;

/**
 * desc   :
 * author : Jiang
 * date   : 2019/1/8
 */
public class StoreContract {
    public interface View extends BaseView {
        /**
         * 设置banner数据
         *
         * @param bannerData banner数据
         */
        void setBannerData(BGAEntity bannerData);

    }

    public interface Presenter {
        /**
         * 获取banner数据
         *
         * @param itemCount
         */
        void getBannerData(String itemCount);
    }

}
