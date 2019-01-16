package com.seocoo.onlineshoping.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.seocoo.onlineshoping.R;
import com.seocoo.onlineshoping.bean.ShopingCartEntity;

import java.util.List;

/**
 * desc   :
 * author : Jiang
 * date   : 2019/1/10
 */
public class ShopCartAdapter extends BaseQuickAdapter<ShopingCartEntity.CommodityEntity, BaseViewHolder> {
    public ShopCartAdapter(int layoutResId, @Nullable List<ShopingCartEntity.CommodityEntity> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, ShopingCartEntity.CommodityEntity item) {
        helper.setText(R.id.tv_commodity_name, item.getName() + "");
        helper.setChecked(R.id.cb_commodity, item.isCheck());
        helper.addOnClickListener(R.id.cb_commodity);
    }
}
