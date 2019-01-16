package com.seocoo.onlineshoping.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.constraint.Group;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.seocoo.onlineshoping.R;
import com.seocoo.onlineshoping.base.ui.BaseActivity;

import butterknife.BindView;
import butterknife.OnClick;

public class ConfirmOrderActivity extends BaseActivity {
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

    }

    @OnClick({R.id.group_no_address})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.group_no_address:
                startActivity(new Intent(this, ChooseReceiveAddressActivity.class));
                break;
        }
    }

}
