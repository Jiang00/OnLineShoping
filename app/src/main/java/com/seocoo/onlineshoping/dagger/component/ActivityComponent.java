package com.seocoo.onlineshoping.dagger.component;

import com.seocoo.onlineshoping.activity.MainActivity;
import com.seocoo.onlineshoping.dagger.module.ActivityModule;
import com.seocoo.onlineshoping.dagger.module.FragmentModule;
import com.seocoo.onlineshoping.dagger.scope.ActivityScope;
import com.seocoo.onlineshoping.fragment.main.StoreFragment;

import dagger.Component;

/**
 * desc   :
 * author : Jiang
 * date   : 2019/1/7
 */
@ActivityScope
@Component(dependencies = ApplicationComponent.class, modules = ActivityModule.class)
public interface ActivityComponent {


}
