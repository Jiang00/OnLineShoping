package com.seocoo.onlineshoping.fragment.main;

import android.content.Intent;
import android.net.Uri;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.seocoo.onlineshoping.R;
import com.seocoo.onlineshoping.activity.MainActivity;
import com.seocoo.onlineshoping.activity.ShopingCartActivity;
import com.seocoo.onlineshoping.activity.StoreDetailsActivity;
import com.seocoo.onlineshoping.base.ui.BaseFragment;
import com.seocoo.onlineshoping.bean.BGAEntity;
import com.seocoo.onlineshoping.contract.StoreContract;
import com.seocoo.onlineshoping.presenter.StorePresenter;
import com.seocoo.onlineshoping.widget.StoreToolbar;
import com.zaaach.citypicker.CityPicker;
import com.zaaach.citypicker.adapter.OnPickListener;
import com.zaaach.citypicker.db.DBManager;
import com.zaaach.citypicker.model.City;
import com.zaaach.citypicker.model.LocateState;
import com.zaaach.citypicker.model.LocatedCity;

import java.lang.reflect.Array;
import java.util.ArrayList;

import butterknife.BindView;
import cn.bingoogolapple.bgabanner.BGABanner;
import cn.bingoogolapple.bgabanner.BGALocalImageSize;

/**
 * desc   :
 * author : Jiang
 * date   : 2019/1/8
 */
public class StoreFragment extends BaseFragment<StorePresenter> implements StoreContract.View {
    @BindView(R.id.store_banner)
    BGABanner mStoreBanner;
    @BindView(R.id.store__tool_bar)
    StoreToolbar mStoreToolBar;
    @BindView(R.id.fab_shop_cart)
    FloatingActionButton mFABShopCart;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_store;
    }

    @Override
    protected void fragmentInject() {
        fragmentComponent.inject(this);
    }

    @Override
    protected void initView(View view) {
        initListener();
    }

    private void initListener() {
        mStoreToolBar.setLeftClickListener(() -> {
            CityPicker.from(getActivity())
                    .enableAnimation(true)
//                    .setAnimationStyle(anim)
                    .setLocatedCity(null)
                    .setHotCities(null)
                    .setAllCities(new DBManager(getActivity()).getAllCities())
                    .setOnPickListener(new OnPickListener() {
                        @Override
                        public void onPick(int position, City data) {
                            mStoreToolBar.setTvLeftText(String.format("当前城市：%s，%s", data.getName(), data.getCode()));
                        }

                        @Override
                        public void onCancel() {
                            showToast("取消选择");
                        }

                        @Override
                        public void onLocate() {
                            //开始定位，这里模拟一下定位
                        }
                    })
                    .show();
        });
        mStoreToolBar.setSearchClickListener(() -> {
        });
        mStoreToolBar.setTextChangeListener(s -> {
        });
        mFABShopCart.setOnClickListener(v -> startActivity(new Intent(getActivity(), ShopingCartActivity.class)));
    }

    @Override
    protected void initEvent() {
        mPresenter.getBannerData("1");
    }

    @Override
    public void setBannerData(BGAEntity bannerData) {
        mStoreBanner.setDelegate((banner, itemView, model, position) -> {
            showToast("点击" + position);
            startActivity(new Intent(getActivity(), StoreDetailsActivity.class));
        });
        mStoreBanner.setAutoPlayAble(bannerData.getImgs().size() > 1);
        mStoreBanner.setAdapter((BGABanner.Adapter<ImageView, String>) (banner, itemView, model, position) -> {
                    Glide.with(getContext()).load(Uri.parse(model))
                            .apply(new RequestOptions().placeholder(R.mipmap.ic_launcher).error(R.mipmap.ic_launcher))
                            .into(itemView);
                }
        );
        mStoreBanner.setData(bannerData.getImgs(), bannerData.getTips());

    }
}
