package com.seocoo.onlineshoping.activity;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.flyco.tablayout.CommonTabLayout;
import com.flyco.tablayout.listener.CustomTabEntity;
import com.flyco.tablayout.listener.OnTabSelectListener;
import com.seocoo.onlineshoping.R;
import com.seocoo.onlineshoping.adapter.PagerAdapter;
import com.seocoo.onlineshoping.base.ui.BaseActivity;
import com.seocoo.onlineshoping.base.ui.BaseFragment;
import com.seocoo.onlineshoping.bean.ItemEntity;
import com.seocoo.onlineshoping.fragment.OrderListFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class OrderListActivity extends BaseActivity implements OnTabSelectListener, ViewPager.OnPageChangeListener {
    @BindView(R.id.tab_order)
    CommonTabLayout mTab;
    @BindView(R.id.viewpager)
    ViewPager mPager;
    private List<BaseFragment> mFragments = new ArrayList<>();

    @Override
    protected void activityInject() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_order_list;
    }

    @Override
    protected void initView() {

    }

    private ArrayList<CustomTabEntity> mTabEntities = new ArrayList<>();

    @Override
    protected void initEvent() {
        mTabEntities.add(new ItemEntity("aa"));
        mTabEntities.add(new ItemEntity("bb"));
        mTabEntities.add(new ItemEntity("cc"));
        mTab.setTabData(mTabEntities);

        mFragments.add(OrderListFragment.newInstance(0));
        mFragments.add(OrderListFragment.newInstance(1));
        mFragments.add(OrderListFragment.newInstance(2));
        PagerAdapter mAdapter = new PagerAdapter(getSupportFragmentManager(), mFragments);
        mPager.setAdapter(mAdapter);
        mPager.setCurrentItem(0);
        initListener();
    }

    private void initListener() {
        mTab.setOnTabSelectListener(this);
        mPager.addOnPageChangeListener(this);
    }

    @Override
    public void onTabSelect(int position) {
        mPager.setCurrentItem(position);
    }

    @Override
    public void onTabReselect(int position) {

    }

    @Override
    public void onPageScrolled(int i, float v, int i1) {

    }

    @Override
    public void onPageSelected(int i) {
        if (mTab.getCurrentTab() != i) {
            mTab.setCurrentTab(i);
        }
    }

    @Override
    public void onPageScrollStateChanged(int i) {

    }
}
