package com.seocoo.onlineshoping.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.seocoo.onlineshoping.R;

import java.util.List;

/**
 * desc   :评价列表适配器
 * author : Jiang
 * date   : 2019/1/10
 */
public class ScoreAdapter extends BaseQuickAdapter<String, BaseViewHolder> {
    public ScoreAdapter(int layoutResId, @Nullable List<String> data) {
        super(layoutResId, data);
    }


    @Override
    protected void convert(BaseViewHolder helper, String item) {
        helper.setText(R.id.iv_score_item_name, item + "");
    }
}
