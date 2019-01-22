package com.seocoo.onlineshoping.adapter;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.view.ViewGroup;


import com.seocoo.onlineshoping.base.ui.BaseFragment;

import java.util.List;

/**
 * desc:消息 适配器
 *
 * @author Bian
 * create at 2018/12/21
 */
public class PagerAdapter extends FragmentStatePagerAdapter {

    private List<BaseFragment> fragments;

    public PagerAdapter(FragmentManager fm, List<BaseFragment> fragments) {
        super(fm);
        this.fragments = fragments;
    }

    @Override
    public Fragment getItem(int i) {
        return fragments.get(i);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
//        super.destroyItem(container, position, object);
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return super.getPageTitle(position);
    }

    @Override
    public int getItemPosition(@NonNull Object object) {
        //每次都刷新都调用instantiateItem 和destroyItem方法
        return POSITION_NONE;
    }
}
