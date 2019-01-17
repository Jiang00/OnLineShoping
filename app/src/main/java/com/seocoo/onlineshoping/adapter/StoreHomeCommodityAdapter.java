package com.seocoo.onlineshoping.adapter;

import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.seocoo.onlineshoping.R;
import com.seocoo.onlineshoping.bean.api.CommodityBean;

import java.util.List;

/**
 * desc   :
 * author : Jiang
 * date   : 2019/1/10
 */
public class StoreHomeCommodityAdapter extends BaseQuickAdapter<CommodityBean, BaseViewHolder> {
    public StoreHomeCommodityAdapter(int layoutResId, @Nullable List<CommodityBean> data) {
        super(layoutResId, data);
    }


    @Override
    protected void convert(BaseViewHolder helper, CommodityBean item) {

    }
}
