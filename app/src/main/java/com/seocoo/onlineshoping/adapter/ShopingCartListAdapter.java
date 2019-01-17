package com.seocoo.onlineshoping.adapter;

import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.seocoo.onlineshoping.R;
import com.seocoo.onlineshoping.bean.ShopingCartEntity;
import com.seocoo.onlineshoping.bean.api.CommodityBean;

import java.util.List;

/**
 * desc   :
 * author : Jiang
 * date   : 2019/1/10
 */
public class ShopingCartListAdapter extends BaseQuickAdapter<ShopingCartEntity, BaseViewHolder> {

    public ShopingCartListAdapter(int layoutResId, @Nullable List<ShopingCartEntity> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, ShopingCartEntity item) {
        helper.setText(R.id.tv_store_name, item.getName() + "热销产品");
        helper.setChecked(R.id.cb_store_all, item.isCheck());
        helper.addOnClickListener(R.id.cb_store_all);
        RecyclerView view = helper.getView(R.id.rv_commodity_list);
        view.setLayoutManager(new LinearLayoutManager(mContext));
        Log.e("jfy", item.getCommodityEntities().size() + "==========");
        final ShopCartAdapter shopCartAdapter = new ShopCartAdapter(R.layout.layout_shoping_cart_commodity_item, item.getCommodityEntities());
        view.setAdapter(shopCartAdapter);
        shopCartAdapter.setOnItemChildClickListener((adapter, view1, position) -> {
            int itemViewId = view1.getId();
            switch (itemViewId) {
                case R.id.cb_commodity:
                    List<CommodityBean> commodityEntities = item.getCommodityEntities();
                    commodityEntities.get(position).setCheck(!item.getCommodityEntities().get(position).isCheck());
                    shopCartAdapter.notifyDataSetChanged();
                    for (CommodityBean commodityEntity : commodityEntities) {
                        if (!commodityEntity.isCheck()) {
                            item.setCheck(false);
                            ShopingCartListAdapter.this.notifyDataSetChanged();
                            return;
                        }
                    }
                    item.setCheck(true);
                    ShopingCartListAdapter.this.notifyDataSetChanged();
                    break;
                default:
                    break;
            }
        });

    }

}
