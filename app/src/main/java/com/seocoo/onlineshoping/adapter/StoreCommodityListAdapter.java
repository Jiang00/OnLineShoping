package com.seocoo.onlineshoping.adapter;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.listener.OnItemChildClickListener;
import com.seocoo.onlineshoping.R;

import java.util.List;

/**
 * desc   :
 * author : Jiang
 * date   : 2019/1/10
 */
public class StoreCommodityListAdapter extends BaseQuickAdapter<List<String>, BaseViewHolder> {
    private CommodityClickListener commodityClickListener;

    public StoreCommodityListAdapter(int layoutResId, @Nullable List<List<String>> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, List<String> item) {
        helper.setText(R.id.tv_title_name, item.get(0) + "热销产品");

        RecyclerView view = helper.getView(R.id.rv_commodity_list);
        view.setLayoutManager(new LinearLayoutManager(mContext));
        StoreCommodityAdapter storeCommodityAdapter = new StoreCommodityAdapter(R.layout.layout_commodity_detail_item, item);
        view.setAdapter(storeCommodityAdapter);
        view.setNestedScrollingEnabled(false);
        storeCommodityAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                if (commodityClickListener != null) {
                    commodityClickListener.commodityListener();
                }
            }
        });
        storeCommodityAdapter.setOnItemChildClickListener(new OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                int itemViewId = view.getId();
                switch (itemViewId) {
                    case R.id.iv_add_cart:
                        if (commodityClickListener != null) {
                            commodityClickListener.addCatrtListener((ImageView) view);
                        }
                        break;
                    default:
                        break;
                }
            }
        });
    }

    public void setCommodityClickListener(CommodityClickListener commodityClickListener) {
        this.commodityClickListener = commodityClickListener;
    }

    public interface CommodityClickListener {
        void addCatrtListener(ImageView imageView);

        void commodityListener();
    }
}
