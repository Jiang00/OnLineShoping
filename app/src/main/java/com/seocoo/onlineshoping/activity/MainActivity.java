package com.seocoo.onlineshoping.activity;

import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import com.liulishuo.okdownload.DownloadListener;
import com.liulishuo.okdownload.DownloadSerialQueue;
import com.liulishuo.okdownload.DownloadTask;
import com.liulishuo.okdownload.SpeedCalculator;
import com.liulishuo.okdownload.core.breakpoint.BlockInfo;
import com.liulishuo.okdownload.core.breakpoint.BreakpointInfo;
import com.liulishuo.okdownload.core.cause.EndCause;
import com.liulishuo.okdownload.core.cause.ResumeFailedCause;
import com.liulishuo.okdownload.core.listener.DownloadListener4WithSpeed;
import com.liulishuo.okdownload.core.listener.assist.Listener4SpeedAssistExtend;
import com.seocoo.onlineshoping.R;
import com.seocoo.onlineshoping.base.ui.BaseActivity;
import com.seocoo.onlineshoping.fragment.main.StoreFragment;

import java.io.File;
import java.util.List;
import java.util.Map;

import butterknife.BindView;

/**
 * 主页
 */
public class MainActivity extends BaseActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    @BindView(R.id.nv_main_bottom)
    BottomNavigationView mBottomNavigation;
    /**
     * 当前页
     */
    private int currentIndex;
    private FragmentManager mFragmentManager;

    @Override
    protected void activityInject() {
//        activityComponent.inject(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
        mFragmentManager = getSupportFragmentManager();
        mFragmentManager.getFragments().clear();
        mFragmentManager.beginTransaction().add(R.id.a, new StoreFragment()).commit();
    }

    @Override
    protected void initImmersionBar(int colorId) {
        super.initImmersionBar(colorId);
    }

    @Override
    protected void initEvent() {
        mBottomNavigation.setOnNavigationItemSelectedListener(this);
    }

    private void showFragment(int index) {
        if (currentIndex == index) {
            return;
        }
//        mFragmentManager.beginTransaction().hide(mFragmentManager.get(currentIndex)).commit();
//        currentIndex = index;
//        if (!mFragmentManager.get(currentIndex).isAdded()) {
//            mFragmentManager.beginTransaction().add(R.id.fl_main_container, mFragmentManager.get(currentIndex)).commit();
//        } else {
//            //实时获取状态
//            mPresenter.getUserStatus();
//        }
//        mFragmentManager.beginTransaction().show(mFragments.get(currentIndex)).commit();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.navigation_home:
                showFragment(0);
                return true;
            case R.id.navigation_store:
                showFragment(1);
                return true;
            case R.id.navigation_rebate:
                showFragment(2);
                return true;
            case R.id.navigation_integral:
//                showFragment(2);
                return true;
            case R.id.navigation_mine:
                showFragment(2);
                return true;
            default:
                break;
        }
        return false;
    }
}
