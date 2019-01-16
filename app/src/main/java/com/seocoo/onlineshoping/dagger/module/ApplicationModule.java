package com.seocoo.onlineshoping.dagger.module;

import android.content.Context;

import com.seocoo.onlineshoping.BuildConfig;
import com.seocoo.onlineshoping.base.BaseApp;
import com.seocoo.onlineshoping.constants.Constants;
import com.seocoo.onlineshoping.module.ApiService;

import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * desc   :
 * author : Jiang
 * date   : 2019/1/7
 */
@Module
public class ApplicationModule {
    private BaseApp application;

    public ApplicationModule(BaseApp application) {
        this.application = application;
    }

    @Singleton
    @Provides
    BaseApp provideApp() {
        return application;
    }

    @Provides
    @Singleton
    Context provideContext() {
        return application;
    }

    @Singleton
    @Provides
    Retrofit.Builder provideRetrofitBuilder() {
        return new Retrofit.Builder();
    }

    @Singleton
    @Provides
    OkHttpClient.Builder provideOkHttpBuilder() {
        return new OkHttpClient.Builder();
    }

    @Provides
    @Singleton
    OkHttpClient provideClient(OkHttpClient.Builder builder) {
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(BuildConfig.LOG_DEBUG ? HttpLoggingInterceptor.Level.BODY : HttpLoggingInterceptor.Level.NONE);
        return builder
                .connectTimeout(20, TimeUnit.SECONDS)
                .readTimeout(20, TimeUnit.SECONDS)
                .writeTimeout(20, TimeUnit.SECONDS)
                .addInterceptor(loggingInterceptor)
//                .addInterceptor(new Interceptor() {
//                    @NonNull
//                    @Override
//                    public Response intercept(@NonNull Chain chain) throws IOException {
//                        return chain.proceed(chain.request().newBuilder()
//                                .addHeader("token", Constants.getInstance().getUserToken()).build());
//                    }
//                })
                .build();
    }

    @Provides
    @Singleton
    Retrofit provideRetrofit(Retrofit.Builder builder, OkHttpClient okhttpClient) {
        return builder
                .baseUrl(Constants.BASE_URL)
                .client(okhttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }

    @Provides
    @Singleton
    ApiService provideApi(Retrofit retrofit) {
        return retrofit.create(ApiService.class);
    }
}
