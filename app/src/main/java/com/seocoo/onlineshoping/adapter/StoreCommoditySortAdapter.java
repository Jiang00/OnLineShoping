package com.seocoo.onlineshoping.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.seocoo.onlineshoping.R;

import java.util.List;

/**
 * desc   :
 * author : Jiang
 * date   : 2019/1/10
 */
public class StoreCommoditySortAdapter extends BaseQuickAdapter<String, BaseViewHolder> {
    public StoreCommoditySortAdapter(int layoutResId, @Nullable List<String> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {
        helper.setText(R.id.tv_title_name, "aaaa" + item);
    }
}
