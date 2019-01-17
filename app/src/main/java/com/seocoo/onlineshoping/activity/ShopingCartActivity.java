package com.seocoo.onlineshoping.activity;

import android.support.design.widget.AppBarLayout;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.seocoo.onlineshoping.R;
import com.seocoo.onlineshoping.adapter.ShopingCartListAdapter;
import com.seocoo.onlineshoping.base.ui.BaseActivity;
import com.seocoo.onlineshoping.bean.ShopingCartEntity;
import com.seocoo.onlineshoping.bean.api.CommodityBean;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

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
