package com.seocoo.onlineshoping.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.seocoo.onlineshoping.R;
import com.seocoo.onlineshoping.adapter.OrderListAdapter;
import com.seocoo.onlineshoping.base.ui.BaseFragment;
import com.seocoo.onlineshoping.bean.api.OrderEvent;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;

/**
 * desc   :
 * author : Jiang
 * date   : 2019/1/22
 */
public class OrderListFragment extends BaseFragment {
    public static final int ALL = 0;
    private static final int COMMENT = 1;
    private static final int REFUND = 2;

    @BindView(R.id.recyclerView)
    RecyclerView mOrders;

    /**
     * 页面类型
     */
    private int pageType;

    public static OrderListFragment newInstance(int pageType) {
        OrderListFragment f = new OrderListFragment();
        Bundle b = new Bundle();
        b.putInt("type", pageType);
        f.setArguments(b);
        return f;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle args = getArguments();
        if (args != null) {
            pageType = args.getInt("pageType");

        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void eventMessage(OrderEvent orderEvent) {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.layout_recycleview;
    }

    @Override
    protected void fragmentInject() {

    }

    @Override
    protected void initView(View view) {
        EventBus.getDefault().register(this);
    }

    @Override
    protected void initEvent() {

    }

    @Override
    public void lazyEvent() {
        super.lazyEvent();
//        EventBus.getDefault().post(new OrderEvent());
        mOrders.setLayoutManager(new LinearLayoutManager(getActivity()));
        mOrders.setAdapter(new OrderListAdapter(R.layout.));

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        EventBus.getDefault().unregister(this);
    }
}
