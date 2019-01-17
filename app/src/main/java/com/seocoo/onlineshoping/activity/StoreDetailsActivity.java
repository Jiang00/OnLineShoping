package com.seocoo.onlineshoping.activity;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.seocoo.onlineshoping.R;
import com.seocoo.onlineshoping.adapter.StoreCommodityListAdapter;
import com.seocoo.onlineshoping.adapter.StoreCommoditySortAdapter;
import com.seocoo.onlineshoping.base.ui.BaseActivity;
import com.seocoo.onlineshoping.utils.AddCartAnimation;
import com.seocoo.onlineshoping.widget.Toolbar;

import org.androidannotations.annotations.Click;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import jp.wasabeef.glide.transformations.BlurTransformation;


public class StoreDetailsActivity extends BaseActivity {
    @BindView(R.id.rv_ani)
    RelativeLayout mRl;
    @BindView(R.id.rv_store_commodity)
    RecyclerView mCommodity;
    @BindView(R.id.rv_store_commodity_sort)
    RecyclerView mCommoditySort;
    @BindView(R.id.iv_store_cart)
    ImageView mStoreCart;
    @BindView(R.id.nl_cart_list)
    NestedScrollView mCartList;
    @BindView(R.id.tv_store_score)
    TextView mStoreScore;
    @BindView(R.id.bt_store_order)
    Button mStoreOrder;
    @BindView(R.id.toolbar)
    Toolbar mToolber;
    @BindView(R.id.appbar_layout)
    AppBarLayout mAppbarLayout;
    @BindView(R.id.iv_title_backg)
    ImageView mTitle;
    private StoreCommoditySortAdapter storeDetailSortAdapter;
    private StoreCommodityListAdapter storeDetailAdapter;
    private BottomSheetBehavior bottomSheetBehavior;

    @Override
    protected void activityInject() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_store_details;
    }

    @Override
    protected void initView() {
        bottomSheetBehavior = BottomSheetBehavior.from(mCartList);
        bottomSheetBehavior.setSkipCollapsed(true);
        mAppbarLayout.addOnOffsetChangedListener((appBarLayout, i) -> {
            Log.e("jfy", mAppbarLayout.getHeight() - mToolber.getHeight() + "aaa===" + i);
            mToolber.setRootViewAlpha(((float) -i) / (mAppbarLayout.getHeight() - mToolber.getHeight()));
        });
        Glide.with(this).load(R.mipmap.cart_full)
                .apply(RequestOptions.bitmapTransform(new BlurTransformation(25, 5)))
                .into(mTitle);
    }

    @OnClick({R.id.tv_store_score, R.id.bt_store_order, R.id.iv_store_cart})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_store_cart:
                if (bottomSheetBehavior.getState() != BottomSheetBehavior.STATE_HIDDEN) {
                    bottomSheetBehavior.setState(BottomSheetBehavior.STATE_HIDDEN);
                } else {
                    bottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
                }
                break;
            case R.id.tv_store_score:
                startActivity(new Intent(this, ScoreActivity.class));
                break;
            case R.id.bt_store_order:
                startActivity(new Intent(this, ConfirmOrderActivity.class));
                break;
            default:
                break;
        }

    }

    @Override
    protected void initEvent() {
        List<String> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            list.add(i + "");
        }
        mCommoditySort.setLayoutManager(new LinearLayoutManager(this));

        storeDetailSortAdapter = new StoreCommoditySortAdapter(R.layout.layout_commodity_sort_item, list);
        mCommoditySort.setAdapter(storeDetailSortAdapter);
        storeDetailSortAdapter.setOnItemClickListener((adapter, view, position) -> smoothMoveToPosition(mCommodity, position));
        LinearLayoutManager linearLayoutManager2 = new LinearLayoutManager(getContext());
        mCommodity.setLayoutManager(linearLayoutManager2);
        List<List<String>> list2 = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            List<String> list3 = new ArrayList<>();
            for (int j = 0; j < 10; j++) {
                list3.add(j + "");
            }
            list2.add(list3);
        }
        storeDetailAdapter = new StoreCommodityListAdapter(R.layout.layout_commodity_item, list2);
        mCommodity.setAdapter(storeDetailAdapter);
        mCommodity.addOnScrollListener(new RecyclerView.OnScrollListener() {
            int position;

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (position == linearLayoutManager2.findFirstVisibleItemPosition()) {
                    return;
                }
                position = linearLayoutManager2.findFirstVisibleItemPosition();
                smoothMoveToPosition(mCommoditySort, linearLayoutManager2.findFirstVisibleItemPosition());
            }
        });

        storeDetailAdapter.setCommodityClickListener(new StoreCommodityListAdapter.CommodityClickListener() {
            @Override
            public void addCatrtListener(ImageView imageView) {
                AddCartAnimation.AddToCart(imageView, mStoreCart, StoreDetailsActivity.this, mRl, 0.5f);
            }

            @Override
            public void commodityListener() {
                readyGo(CommodityDetailsActivity.class);
            }
        });

    }

    private void smoothMoveToPosition(RecyclerView mRecyclerView, final int position) {
        int firstItem = mRecyclerView.getChildLayoutPosition(mRecyclerView.getChildAt(0));
        int lastItem = mRecyclerView.getChildLayoutPosition(mRecyclerView.getChildAt(mRecyclerView.getChildCount() - 1));

        if (position < firstItem) {
            mRecyclerView.scrollToPosition(position);
        } else if (position <= lastItem) {
            int movePosition = position - firstItem;
            if (movePosition >= 0 && movePosition < mRecyclerView.getChildCount()) {
                int top = mRecyclerView.getChildAt(movePosition).getTop();
                mRecyclerView.smoothScrollBy(0, top);
            }
        } else {
            mRecyclerView.scrollToPosition(position);
        }
    }

    @Override
    protected void onDestroy() {
        if (storeDetailAdapter != null) {
            storeDetailAdapter.setCommodityClickListener(null);
        }
        if (mCommodity != null) {
            mCommodity.clearOnScrollListeners();
        }
        super.onDestroy();
    }
}
