package com.seocoo.onlineshoping.activity;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.flyco.tablayout.CommonTabLayout;
import com.flyco.tablayout.listener.CustomTabEntity;
import com.seocoo.onlineshoping.R;
import com.seocoo.onlineshoping.adapter.AddressAdapter;
import com.seocoo.onlineshoping.base.ui.BaseActivity;
import com.seocoo.onlineshoping.bean.ItemEntity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * 选择收货地址
 */
public class ChooseReceiveAddressActivity extends BaseActivity {
    @BindView(R.id.rv_address_list)
    RecyclerView mAddressList;
    @BindView(R.id.bt_add_adress)
    Button mAddAddress;

    @Override
    protected void activityInject() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_choose_receive_address;
    }

    @Override
    protected void initView() {
        mAddAddress.setOnClickListener(v -> startActivity(new Intent(this, EditReceiveAddressActivity.class)));
    }

    @Override
    protected void initEvent() {
        List<String> list = new ArrayList<>();
//        for (int i = 0; i < 10; i++) {
//            list.add("a" + i);
//        }
        mAddressList.setLayoutManager(new LinearLayoutManager(this));
        AddressAdapter addressAdapter = new AddressAdapter(R.layout.layout_address_list_item, list);
        mAddressList.setAdapter(addressAdapter);
        addressAdapter.setEmptyView(R.layout.layout_empty_address, mAddressList);
        addressAdapter.setOnItemChildClickListener((adapter, view, position) -> {
            if (view.getId() == R.id.iv_address_edit) {
                startActivity(new Intent(this, EditReceiveAddressActivity.class));
            }
        });

    }
}
