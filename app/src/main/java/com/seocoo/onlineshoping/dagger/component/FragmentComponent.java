package com.seocoo.onlineshoping.dagger.component;

import com.seocoo.onlineshoping.dagger.module.FragmentModule;
import com.seocoo.onlineshoping.dagger.scope.ActivityScope;
import com.seocoo.onlineshoping.dagger.scope.FragmentScope;
import com.seocoo.onlineshoping.fragment.main.StoreFragment;

import dagger.Component;

/**
 * desc   :
 * author : Jiang
 * date   : 2019/1/7
 */
@FragmentScope
@Component(dependencies = ApplicationComponent.class, modules = FragmentModule.class)
public interface FragmentComponent {
    void inject(StoreFragment storeFragment);
}
