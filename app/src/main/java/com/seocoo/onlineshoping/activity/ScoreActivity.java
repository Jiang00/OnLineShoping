package com.seocoo.onlineshoping.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.seocoo.onlineshoping.R;
import com.seocoo.onlineshoping.adapter.ScoreAdapter;
import com.seocoo.onlineshoping.base.ui.BaseActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class ScoreActivity extends BaseActivity {
    @BindView(R.id.rv_score_list)
    RecyclerView mScoreList;

    @Override
    protected void activityInject() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_score;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initEvent() {
        List<String> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            list.add("a" + i);
        }
        mScoreList.setLayoutManager(new LinearLayoutManager(this));
        mScoreList.setAdapter(new ScoreAdapter(R.layout.layout_score_list_item, list));
    }
}
