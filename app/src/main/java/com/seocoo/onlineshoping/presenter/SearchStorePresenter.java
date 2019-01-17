package com.seocoo.onlineshoping.presenter;

import com.seocoo.onlineshoping.base.presenter.BasePresenter;
import com.seocoo.onlineshoping.contract.SearchStoreContract;
import com.seocoo.onlineshoping.module.DataManager;

import java.util.Arrays;

import javax.inject.Inject;

/**
 * desc   :
 * author : Jiang
 * date   : 2019/1/16
 */
public class SearchStorePresenter extends BasePresenter<SearchStoreContract.View> implements SearchStoreContract.Presenter {
    @Inject
    public SearchStorePresenter(DataManager dataManager) {
        super(dataManager);
    }

    @Override
    public void getBeforeData() {
        String[] mVals = new String[]
                {"有信用卡", "有微粒贷", "我有房", "我有车", "有社保", "有公积金",
                        "有人寿保险", "工资银行卡转账", "啥都没有"};
        mView.setSearchBeforeData(Arrays.asList(mVals));
    }

    @Override
    public void getRecommendData() {
        String[] mVals = new String[]
                {"有信用卡", "有微粒贷", "我有房", "我有车", "有社保", "有公积金",
                        "有人寿保险", "工资银行卡转账", "啥都没有"};
        mView.setSearchRecommendData(Arrays.asList(mVals));
    }

    @Override
    public void getFindData(String storeName) {
        mView.searchFindVisible();
        //获取数据
        String[] mVals = new String[]
                {"有信用卡", "有微粒贷", "我有房", "我有车", "有社保", "有公积金",
                        "有人寿保险", "工资银行卡转账", "啥都没有"};
        mView.setFinddata(Arrays.asList(mVals));
    }

    @Override
    public void getResultData(String storeName) {
        mView.searchResultVisible();
        //获取结果
        String[] mVals = new String[]
                {"有信用卡", "有微粒贷", "我有房", "我有车", "有社保", "有公积金",
                        "有人寿保险", "工资银行卡转账", "啥都没有"};
        mView.setResultData(Arrays.asList(mVals));
    }

}
