package com.seocoo.onlineshoping.activity;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.seocoo.onlineshoping.R;
import com.seocoo.onlineshoping.adapter.ShopingCartListAdapter;
import com.seocoo.onlineshoping.base.ui.BaseActivity;
import com.seocoo.onlineshoping.bean.ShopingCartEntity;
import com.seocoo.onlineshoping.bean.api.CommodityBean;
import com.seocoo.onlineshoping.widget.LinearItemDecoration;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * 购物车
 */
public class ShopingCartActivity extends BaseActivity {
    @BindView(R.id.rl_shopcart)
    RecyclerView mShopCart;

    @Override
    protected void activityInject() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_shoping_cart;
    }

    @Override
    protected void initView() {
    }

    @Override
    protected void initEvent() {
        List<ShopingCartEntity> shopingCartEntities = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            ShopingCartEntity shopingCartEntity = new ShopingCartEntity();
            shopingCartEntity.setName(i + "aa");
            shopingCartEntities.add(shopingCartEntity);
        }
        LinearItemDecoration linearItemDecoration = new LinearItemDecoration.Builder(this)
                .setSpan(R.dimen.dp_10)
                .setColorResource(R.color.color_f2)
                .setShowLastLine(true)
                .build();
        mShopCart.addItemDecoration(linearItemDecoration);
        mShopCart.setLayoutManager(new LinearLayoutManager(this));
        ShopingCartListAdapter adapter = new ShopingCartListAdapter(R.layout.layout_shoping_cart_list_item, shopingCartEntities);
        mShopCart.setAdapter(adapter);
        adapter.setOnItemChildClickListener((adapter1, view, position) -> {
            shopingCartEntities.get(position).setCheck(!shopingCartEntities.get(position).isCheck());
            for (CommodityBean commodityEntity : shopingCartEntities.get(position).getCommodityEntities()) {
                commodityEntity.setCheck(shopingCartEntities.get(position).isCheck());
            }
            adapter.notifyDataSetChanged();
        });
    }
}
