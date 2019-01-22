package com.seocoo.onlineshoping.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.seocoo.onlineshoping.R;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;

import java.util.List;

/**
 * desc   :搜索流式布局适配器
 * author : Jiang
 * date   : 2019/1/16
 */
public class SearchFlowAdapter extends TagAdapter<String> {
    LayoutInflater mInflater;

    public SearchFlowAdapter(Context context, List<String> datas) {
        super(datas);
        mInflater = LayoutInflater.from(context);
    }

    @Override
    public View getView(FlowLayout parent, int position, String s) {
        //                将tv.xml文件填充到标签内.
        TextView tv = (TextView) mInflater.inflate(R.layout.layout_search_flow,
                parent, false);
//               为标签设置对应的内容
        tv.setText(s);
        return tv;
    }
}
