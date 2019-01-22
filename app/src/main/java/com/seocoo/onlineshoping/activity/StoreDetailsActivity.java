package com.seocoo.onlineshoping.activity;

import android.content.Intent;
import android.support.constraint.ConstraintLayout;
import android.support.constraint.Group;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.flipboard.bottomsheet.BottomSheetLayout;
import com.flyco.tablayout.SlidingTabLayout;
import com.seocoo.onlineshoping.R;
import com.seocoo.onlineshoping.adapter.GridCommodityAdapter;
import com.seocoo.onlineshoping.adapter.LineaaCommodityAdapter;
import com.seocoo.onlineshoping.base.ui.BaseActivity;
import com.seocoo.onlineshoping.base.ui.BaseFragment;
import com.seocoo.onlineshoping.fragment.CommodityListFragment;
import com.seocoo.onlineshoping.widget.Toolbar;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import jp.wasabeef.glide.transformations.BlurTransformation;

/**
 * 店铺详情
 */
public class StoreDetailsActivity extends BaseActivity {
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.iv_store_cart)
    ImageView mStoreCart;
    @BindView(R.id.nl_cart_list)
    BottomSheetLayout mCartList;
    @BindView(R.id.tv_store_score)
    TextView mStoreScore;
    @BindView(R.id.bt_store_order)
    Button mStoreOrder;
    @BindView(R.id.appbar_layout)
    AppBarLayout mAppbarLayout;
    @BindView(R.id.colltoolbar)
    CollapsingToolbarLayout mColltoolbar;
    @BindView(R.id.iv_title_backg)
    ImageView mTitle;
    @BindView(R.id.slid_tab)
    SlidingTabLayout slidingTabLayout;
    @BindView(R.id.viewpager)
    ViewPager mViewpager;
    @BindView(R.id.colltoolbar_parallax)
    ConstraintLayout mParallax;
    @BindView(R.id.recyclerView_search)
    RecyclerView mSearch;
    RecyclerView mCartCommodity;

    private ArrayList<Fragment> mFragments = new ArrayList<>();
    private View bottomView;

    @Override
    protected void activityInject() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_store_details;
    }

    @Override
    protected void initView() {
        bottomView = LayoutInflater.from(StoreDetailsActivity.this).inflate(R.layout.dialog_cart_list, mCartList, false);
        mCartCommodity = bottomView.findViewById(R.id.rv_dialog_list);
        mAppbarLayout.addOnOffsetChangedListener((appBarLayout, i) -> {
            mToolbar.setRootViewAlpha(((float) -i) / (mColltoolbar.getHeight() - mToolbar.getHeight()));
        });
        Glide.with(this).load(R.mipmap.cart_full)
                .apply(RequestOptions.bitmapTransform(new BlurTransformation(25, 5)))
                .into(mTitle);
        mToolbar.setRightClickListener(() -> {
            mParallax.setVisibility(View.VISIBLE);
            slidingTabLayout.setVisibility(View.VISIBLE);
            mSearch.setVisibility(View.GONE);
            mToolbar.clearEditFocus();
        });
        mToolbar.setEditTextListener(new Toolbar.ToolbarEditTextListener() {
            @Override
            public void clickSearch(String text) {
                List<String> list = new ArrayList<>();
                for (int i = 0; i < 10; i++) {
                    list.add(i + "");
                }
                mSearch.setLayoutManager(new LinearLayoutManager(StoreDetailsActivity.this));
                mSearch.setAdapter(new LineaaCommodityAdapter(R.layout.layout_linear_commodity_item, list));
            }

            @Override
            public void textChange(String text) {

            }

            @Override
            public void onFocusChange(boolean hasFocus, String string) {
                if (hasFocus) {
                    mParallax.setVisibility(View.GONE);
                    slidingTabLayout.setVisibility(View.GONE);
                    mSearch.setVisibility(View.VISIBLE);
                }
            }
        });
    }

    @OnClick({R.id.tv_store_score, R.id.bt_store_order, R.id.iv_store_cart})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_store_cart:
                if (!mCartList.isSheetShowing()) {
                    mCartList.showWithSheetView(bottomView);
                } else {
                    mCartList.dismissSheet();
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
        String[] mVals = new String[]
                {"有信用卡", "有微粒贷", "我有房", "我有车", "有社保", "有公积金",
                        "有人寿保险", "工资银行卡转账", "啥都没有"};
        mFragments.add(CommodityListFragment.newInstance());
        mFragments.add(CommodityListFragment.newInstance());
        mFragments.add(CommodityListFragment.newInstance());
        mFragments.add(CommodityListFragment.newInstance());
        mFragments.add(CommodityListFragment.newInstance());
        mFragments.add(CommodityListFragment.newInstance());
        mFragments.add(CommodityListFragment.newInstance());
        mFragments.add(CommodityListFragment.newInstance());
        mFragments.add(CommodityListFragment.newInstance());
        slidingTabLayout.setViewPager(mViewpager, mVals, this, mFragments);
        mCartCommodity.setLayoutManager(new LinearLayoutManager(StoreDetailsActivity.this));
        mCartCommodity.setAdapter(new GridCommodityAdapter(R.layout.layout_store_cart_commodity_item, list));
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
