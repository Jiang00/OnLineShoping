package com.seocoo.onlineshoping.presenter;

import com.seocoo.onlineshoping.base.BaseObserver;
import com.seocoo.onlineshoping.base.presenter.BasePresenter;
import com.seocoo.onlineshoping.bean.BGAEntity;
import com.seocoo.onlineshoping.contract.StoreContract;
import com.seocoo.onlineshoping.module.DataManager;
import com.seocoo.onlineshoping.utils.RxUtils;

import javax.inject.Inject;

/**
 * desc   :
 * author : Jiang
 * date   : 2019/1/8
 */
public class StorePresenter extends BasePresenter<StoreContract.View> implements StoreContract.Presenter {
    @Inject
    public StorePresenter(DataManager dataManager) {
        super(dataManager);
    }

    @Override
    public void getBannerData(String itemCount) {
        addSubscribe(mDataManager.getStoreBanner(itemCount)
                .compose(RxUtils.rxScheduler())
                .subscribeWith(new BaseObserver<BGAEntity>(mView) {
                    @Override
                    public void onNext(BGAEntity bgaEntity) {
                        super.onNext(bgaEntity);
                        mView.setBannerData(bgaEntity);
                    }
                }));
    }
}
