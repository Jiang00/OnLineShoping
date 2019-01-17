package com.seocoo.onlineshoping.contract;

import com.seocoo.onlineshoping.base.view.BaseView;

import java.util.List;

/**
 * desc   :
 * author : Jiang
 * date   : 2019/1/16
 */
public class SearchStoreContract {
    public interface View extends BaseView {
        /**
         * 显示推荐商店
         */
        void resetConstraintSet();

        /**
         * 显示查找商店
         */
        void searchFindVisible();

        /**
         * 显示查找商店结果
         */
        void searchResultVisible();

        /**
         * 设置历史记录数据
         *
         * @param data
         */
        void setSearchBeforeData(List<String> data);

        /**
         * 设置热门推荐数据
         *
         * @param data
         */
        void setSearchRecommendData(List<String> data);

        /**
         * 设置查找数据
         *
         * @param data
         */
        void setFinddata(List<String> data);

        /**
         * 设置结果数据
         *
         * @param data
         */
        void setResultData(List<String> data);
    }

    public interface Presenter {
        void getBeforeData();

        void getRecommendData();

        /**
         * 商店名称
         *
         * @param storeName
         */
        void getFindData(String storeName);

        /**
         * 商店名称
         *
         * @param storeName
         */
        void getResultData(String storeName);
    }
}
