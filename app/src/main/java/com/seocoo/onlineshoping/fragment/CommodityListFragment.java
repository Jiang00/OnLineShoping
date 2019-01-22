package com.seocoo.onlineshoping.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.seocoo.onlineshoping.R;
import com.seocoo.onlineshoping.adapter.GridCommodityAdapter;
import com.seocoo.onlineshoping.base.ui.BaseFragment;
import com.seocoo.onlineshoping.utils.AddCartAnimation;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * desc   :
 * author : Jiang
 * date   : 2019/1/17
 */
public class CommodityListFragment extends BaseFragment {
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    RelativeLayout relativelayout;
    View endview;
    private GridCommodityAdapter gridCommodityAdapter;

    public static CommodityListFragment newInstance() {

        CommodityListFragment fragment = new CommodityListFragment();

        return fragment;
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
        endview = view.findViewById(R.id.endview);
        relativelayout = view.findViewById(R.id.relativelayout);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 3));
        gridCommodityAdapter = new GridCommodityAdapter(R.layout.layout_commodity_detail_item, null);
        recyclerView.setAdapter(gridCommodityAdapter);
        gridCommodityAdapter.setOnItemChildClickListener((adapter, view1, position) -> {
            if (view1.getId() == R.id.iv_add_cart) {
                AddCartAnimation.AddToCart((ImageView) view1, endview, getActivity(), relativelayout, 0.5f);
            }
        });
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void lazyEvent() {
        super.lazyEvent();
        List<String> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            list.add(i + "");
        }
        gridCommodityAdapter.setNewData(list);
        gridCommodityAdapter.notifyDataSetChanged();
    }

    @Override
    protected void initEvent() {
    }
}
