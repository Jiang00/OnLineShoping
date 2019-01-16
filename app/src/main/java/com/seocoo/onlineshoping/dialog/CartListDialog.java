package com.seocoo.onlineshoping.dialog;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetDialogFragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.seocoo.onlineshoping.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * desc:    支行弹窗
 *
 * @author Bian
 * create at 2018/12/20
 */
public class CartListDialog extends BottomSheetDialogFragment implements BaseQuickAdapter.OnItemClickListener {
    @BindView(R.id.rv_dialog_list)
    RecyclerView mRecView;
    @BindView(R.id.tv_dialog_list_title)
    TextView mTvTitle;
    Unbinder unbinder;

    public CartListDialog setCommonListener() {
        return this;
    }

    public static CartListDialog newInstance() {
        Bundle args = new Bundle();
        CartListDialog fragment = new CartListDialog();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_cart_list, container, false);
        unbinder = ButterKnife.bind(this, view);
        initView(view);
        return view;
    }

    private void initView(View view) {
        mRecView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initEvent();
    }

    private void initEvent() {
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick(R.id.iv_dialog_list_cancel)
    public void onViewClicked() {
        this.dismiss();
    }


    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {

    }
}
