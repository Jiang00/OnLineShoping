package com.seocoo.onlineshoping.activity;

import android.support.constraint.ConstraintLayout;
import android.support.constraint.ConstraintSet;
import android.support.constraint.Group;
import android.support.transition.TransitionManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SimpleItemAnimator;
import android.util.Log;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.seocoo.onlineshoping.R;
import com.seocoo.onlineshoping.adapter.SearchFindAdapter;
import com.seocoo.onlineshoping.adapter.SearchFlowAdapter;
import com.seocoo.onlineshoping.adapter.SearchResultAdapter;
import com.seocoo.onlineshoping.base.ui.BaseActivity;
import com.seocoo.onlineshoping.contract.SearchStoreContract;
import com.seocoo.onlineshoping.presenter.SearchStorePresenter;
import com.seocoo.onlineshoping.utils.StatusBarUtils;
import com.seocoo.onlineshoping.utils.StringUtils;
import com.seocoo.onlineshoping.widget.AndroidBug5497Workaround;
import com.seocoo.onlineshoping.widget.Toolbar;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagFlowLayout;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

import butterknife.BindView;

public class SearchStoreActivity extends BaseActivity<SearchStorePresenter> implements SearchStoreContract.View, Toolbar.ToolbarEditTextListener {
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.cl_group)
    ConstraintLayout mclGroup;
    @BindView(R.id.group_before_recommend)
    Group mBoforeAndRecommend;
    @BindView(R.id.rv_search_before)
    TagFlowLayout mSearchBofore;
    @BindView(R.id.rv_search_recommend)
    TagFlowLayout mSearchRecommend;
    @BindView(R.id.rv_search_find)
    RecyclerView mSearchFind;
    @BindView(R.id.rv_search_result)
    RecyclerView mSearchResult;

    private ConstraintSet applyConstraintSet = new ConstraintSet();
    private ConstraintSet resetConstraintSet = new ConstraintSet();

    private List<String> findData = new ArrayList<>();
    private List<String> resultData = new ArrayList<>();
    private SearchFlowAdapter searchFlowAdapter;
    private SearchFlowAdapter searchFlowAdapterRecommend;
    private SearchFindAdapter searchFindAdapter;
    private SearchResultAdapter searchResultAdapter;

    @Override
    protected void activityInject() {
        activityComponent.inject(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_search_store;
    }

    @Override
    protected void initImmersionBar(int colorId) {
        super.initImmersionBar(colorId);
        StatusBarUtils.setAndroidNativeLightStatusBar(this, true);
    }

    @Override
    protected void initView() {
        AndroidBug5497Workaround.assistActivity(this);
        applyConstraintSet.clone(mclGroup);
        resetConstraintSet.clone(mclGroup);
        initListener();
    }

    private void initListener() {
        mToolbar.setLeftClickListener(() -> onBackPressed());
        mToolbar.setEditTextListener(this);
        mToolbar.clickEditText();
    }


    @Override
    protected void initEvent() {


        mSearchFind.setLayoutManager(new LinearLayoutManager(this));
        searchFindAdapter = new SearchFindAdapter(R.layout.layout_search_find_item, findData);
        mSearchFind.setAdapter(searchFindAdapter);
        searchFindAdapter.setOnItemClickListener((adapter, view, position) -> mPresenter.getResultData(findData.get(position)));

        mSearchResult.setLayoutManager(new LinearLayoutManager(this));
        searchResultAdapter = new SearchResultAdapter(R.layout.layout_search_result_item, resultData);
        mSearchResult.setAdapter(searchResultAdapter);

        mPresenter.getBeforeData();
        mPresenter.getRecommendData();
    }


    @Override
    public void clickSearch(String text) {
        mPresenter.getResultData(text);

    }

    @Override
    public void textChange(String text) {
        searchTextChenge(text);
    }

    @Override
    public void onFocusChange(boolean hasFocus, String string) {
        Log.e("jfy", hasFocus + "====" + string);
        if (hasFocus) {
            searchTextChenge(string);
        }
    }

    /**
     * 搜索条件改变
     *
     * @param text
     */
    private void searchTextChenge(String text) {
        TransitionManager.beginDelayedTransition(mclGroup);
        if (StringUtils.isEmpty(text)) {
            resetConstraintSet();
        } else {
            mPresenter.getFindData(text);
        }
    }

    @Override
    public void resetConstraintSet() {
        if (mBoforeAndRecommend.getVisibility() == View.VISIBLE) {
            return;
        }
        TransitionManager.beginDelayedTransition(mclGroup);
        resetConstraintSet.applyTo(mclGroup);
    }

    @Override
    public void searchFindVisible() {
        if (mSearchFind.getVisibility() == View.VISIBLE) {
            return;
        }
        TransitionManager.beginDelayedTransition(mclGroup);
        applyConstraintSet.setVisibility(R.id.group_before_recommend, ConstraintSet.GONE);
        applyConstraintSet.setVisibility(R.id.rv_search_find, ConstraintSet.VISIBLE);
        applyConstraintSet.setVisibility(R.id.rv_search_result, ConstraintSet.GONE);
        applyConstraintSet.applyTo(mclGroup);
    }

    @Override
    public void searchResultVisible() {

        if (mSearchResult.getVisibility() == View.VISIBLE) {
            return;
        }
        TransitionManager.beginDelayedTransition(mclGroup);
        applyConstraintSet.setVisibility(R.id.group_before_recommend, ConstraintSet.GONE);
        applyConstraintSet.setVisibility(R.id.rv_search_find, ConstraintSet.GONE);
        applyConstraintSet.setVisibility(R.id.rv_search_result, ConstraintSet.VISIBLE);
        applyConstraintSet.applyTo(mclGroup);
    }

    @Override
    public void setSearchBeforeData(List<String> data) {
        searchFlowAdapter = new SearchFlowAdapter(this, data);
        mSearchBofore.setAdapter(searchFlowAdapter);
        mSearchBofore.setOnTagClickListener((view, position, parent) -> {
            mPresenter.getResultData(data.get(position));
            return false;
        });
    }

    @Override
    public void setSearchRecommendData(List<String> data) {
        searchFlowAdapterRecommend = new SearchFlowAdapter(this, data);
        mSearchRecommend.setAdapter(searchFlowAdapterRecommend);
        mSearchRecommend.setOnTagClickListener((view, position, parent) -> {
            mPresenter.getResultData(data.get(position));
            return false;
        });
    }


    @Override
    public void setFinddata(List<String> data) {
        findData = data;
        searchFindAdapter.setNewData(data);
        mSearchFind.setAdapter(searchFindAdapter);
        searchResultAdapter.setNewData(null);
        searchResultAdapter.notifyDataSetChanged();
    }

    @Override
    public void setResultData(List<String> data) {
        resultData = data;
        searchResultAdapter.setNewData(data);
        mSearchResult.setAdapter(searchResultAdapter);
        searchFindAdapter.setNewData(null);
        searchFindAdapter.notifyDataSetChanged();
    }

    @Override
    protected void onDestroy() {
        mToolbar.destory();
        super.onDestroy();
    }
}
