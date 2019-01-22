package com.seocoo.onlineshoping.activity;

import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.seocoo.onlineshoping.R;
import com.seocoo.onlineshoping.adapter.GridCommodityAdapter;
import com.seocoo.onlineshoping.base.ui.BaseActivity;
import com.seocoo.onlineshoping.widget.GridItemDecoration;
import com.seocoo.onlineshoping.widget.Toolbar;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * 商品详情
 */
public class CommodityDetailsActivity extends BaseActivity {
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.nv_commodity)
    NestedScrollView mNestedScrollView;
    @BindView(R.id.rv_other_commodity)
    RecyclerView mOtherCommodity;

    @Override
    protected void activityInject() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_commodity_details;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initEvent() {
        List<String> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            list.add(i + "");
        }
        GridItemDecoration gridItemDecoration = new GridItemDecoration.Builder(this)
                .setColorResource(R.color.color_f2)
                .setHorizontalSpan(R.dimen.dp_3)
                .setVerticalSpan(R.dimen.dp_3)
                .setShowLastLine(false)
                .setHasHeader(true)
                .build();
        mOtherCommodity.addItemDecoration(gridItemDecoration);
        mOtherCommodity.setLayoutManager(new GridLayoutManager(this, 2));
        GridCommodityAdapter gridCommodityAdapter = new GridCommodityAdapter(R.layout.layout_other_commodity_item, list);
        mOtherCommodity.setAdapter(gridCommodityAdapter);
        gridCommodityAdapter.addHeaderView(View.inflate(this, R.layout.layout_recyc_header, null));
        initListener();

    }

    private void initListener() {
        mNestedScrollView.setOnScrollChangeListener((NestedScrollView.OnScrollChangeListener) (nestedScrollView, x, y, oldx, oldy) -> {
            float a = (float) y / 1080;
            mToolbar.setRootViewAlpha(a);
        });
    }
}
