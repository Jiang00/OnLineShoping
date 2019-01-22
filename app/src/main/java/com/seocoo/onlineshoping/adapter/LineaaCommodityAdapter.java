package com.seocoo.onlineshoping.adapter;

import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.seocoo.onlineshoping.R;

import java.util.List;

/**
 * desc   :线性商品列表适配器
 * author : Jiang
 * date   : 2019/1/17
 */
public class LineaaCommodityAdapter extends BaseQuickAdapter<String, BaseViewHolder> {
    public LineaaCommodityAdapter(int layoutResId, @Nullable List<String> data) {
        super(layoutResId, data);
    }


    @Override
    protected void convert(BaseViewHolder helper, String item) {
        helper.setImageResource(R.id.iv_commodity_icon, R.mipmap.ic_launcher);
        TextView textView = helper.getView(R.id.tv_commodity_money_before);
        textView.getPaint().setAntiAlias(true);
        // 设置中划线并加清晰
        textView.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG | Paint.ANTI_ALIAS_FLAG);
        helper.addOnClickListener(R.id.iv_add_cart);
    }
}
