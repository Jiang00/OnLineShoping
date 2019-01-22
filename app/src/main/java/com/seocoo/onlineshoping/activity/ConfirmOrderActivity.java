package com.seocoo.onlineshoping.activity;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import com.seocoo.onlineshoping.R;
import com.seocoo.onlineshoping.adapter.ConfirmOrderAdapter;
import com.seocoo.onlineshoping.base.ui.BaseActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 确认订单
 */
public class ConfirmOrderActivity extends BaseActivity {
    @BindView(R.id.rv_commodity_list)
    RecyclerView mCommodity;

    @Override
    protected void activityInject() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_confirm_order;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initEvent() {
        List<String> shopingCartEntities = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            shopingCartEntities.add(i + "");
        }
        mCommodity.setLayoutManager(new LinearLayoutManager(this));
        ConfirmOrderAdapter adapter = new ConfirmOrderAdapter(R.layout.layout_confirm_commodity_item, shopingCartEntities);
        mCommodity.setAdapter(adapter);
    }

    @OnClick({R.id.group_no_address, R.id.bt_delivery_merchant, R.id.bt_delivery_personal, R.id.bt_submit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.group_no_address:
                startActivity(new Intent(this, ChooseReceiveAddressActivity.class));
                break;
            case R.id.bt_delivery_merchant:
                break;
            case R.id.bt_delivery_personal:
                break;
            case R.id.bt_submit:
                startActivity(new Intent(this, SuccessActivity.class));
                break;
            default:
                break;
        }
    }

}
