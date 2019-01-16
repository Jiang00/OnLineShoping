package com.seocoo.onlineshoping.dagger.component;

import com.seocoo.onlineshoping.dagger.module.ApplicationModule;
import com.seocoo.onlineshoping.module.DataManager;

import javax.inject.Singleton;

import dagger.Component;

/**
 * desc   :
 * author : Jiang
 * date   : 2019/1/7
 */
@Singleton
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {
    DataManager getData();
}
