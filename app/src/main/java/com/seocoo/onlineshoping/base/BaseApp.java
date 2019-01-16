package com.seocoo.onlineshoping.base;

import android.app.Application;
import android.graphics.Typeface;
import android.support.v4.content.ContextCompat;

import com.scwang.smartrefresh.header.MaterialHeader;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.footer.ClassicsFooter;
import com.seocoo.onlineshoping.BuildConfig;
import com.seocoo.onlineshoping.R;
import com.seocoo.onlineshoping.dagger.component.ApplicationComponent;
import com.seocoo.onlineshoping.dagger.component.DaggerApplicationComponent;
import com.seocoo.onlineshoping.dagger.module.ApplicationModule;
import com.squareup.leakcanary.LeakCanary;
import com.squareup.leakcanary.RefWatcher;

import java.lang.reflect.Field;

/**
 * desc   :
 * author : Jiang
 * date   : 2019/1/7
 */
public class BaseApp extends Application {
    private ApplicationComponent applicationComponent;
    private static BaseApp baseApp;
    private RefWatcher refWatcher;

    @Override
    public void onCreate() {
        super.onCreate();
        baseApp = this;
        applicationComponent = DaggerApplicationComponent.builder().applicationModule(new ApplicationModule(this)).build();
        initTextStyle();
        if (!LeakCanary.isInAnalyzerProcess(this)) {
            refWatcher = BuildConfig.DEBUG ? LeakCanary.install(this) : RefWatcher.DISABLED;
        }
    }

    //static 代码段可以防止内存泄露
    static {
        //设置全局的Header构建器
        SmartRefreshLayout.setDefaultRefreshHeaderCreator((context, layout) -> {
            MaterialHeader header = new MaterialHeader(context);
            header.setColorSchemeColors(ContextCompat.getColor(context, R.color.transparent),
                    ContextCompat.getColor(context, R.color.transparent),
                    ContextCompat.getColor(context, R.color.transparent),
                    ContextCompat.getColor(context, R.color.transparent));
            header.setShowBezierWave(false);
            return header;
        });
        //设置全局的Footer构建器
        SmartRefreshLayout.setDefaultRefreshFooterCreator((context, layout) -> new ClassicsFooter(context));
    }

    /**
     * 修改字体样式
     */
    private void initTextStyle() {
        try {
            Typeface typeface = Typeface.createFromAsset(getAssets(), ".ttf");
            Field field = Typeface.class.getDeclaredField("MONOSPACE");
            field.setAccessible(true);
            field.set(null, typeface);
        } catch (NoSuchFieldException | IllegalAccessException | RuntimeException e) {
            e.printStackTrace();
        }
    }

    public ApplicationComponent getApplicationComponent() {
        return applicationComponent;
    }

    public static BaseApp getInstance() {
        return baseApp;
    }

    public static RefWatcher getRefWatcher() {
        return getInstance().refWatcher;
    }
}
