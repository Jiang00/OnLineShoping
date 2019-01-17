package com.seocoo.onlineshoping.adapter;

import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.seocoo.onlineshoping.R;
import com.seocoo.onlineshoping.bean.NearStoreEntity;

import java.util.List;

/**
 * desc   :
 * author : Jiang
 * date   : 2019/1/10
 */
public class StoreAdapter extends BaseQuickAdapter<NearStoreEntity, BaseViewHolder> {
    public StoreAdapter(int layoutResId, @Nullable List<NearStoreEntity> data) {
        super(layoutResId, data);
    }


    @Override
    protected void convert(BaseViewHolder helper, NearStoreEntity item) {

        RecyclerView view = helper.getView(R.id.rv_store_home_commodity);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(mContext, 3);
        view.setLayoutManager(gridLayoutManager);
        StoreHomeCommodityAdapter storeHomeCommodityAdapter = new StoreHomeCommodityAdapter(R.layout.layout_store_item_home_commodity_item, item.getCommodities());
        view.setAdapter(storeHomeCommodityAdapter);
    }
}
