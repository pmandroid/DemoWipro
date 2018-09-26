package com.prashant.demowipro.model.data;

import android.content.Context;

import com.prashant.demowipro.model.bean.Response;
import com.prashant.demowipro.utility.NetworkObserver;
import com.prashant.demowipro.view.DemoApplication;

import okhttp3.Cache;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * The type Retrofit helper.
 */
public class RetrofitHelper {

    private static final Interceptor REWRITE_RESPONSE_INTERCEPTOR = chain -> {
        okhttp3.Response originalResponse = chain.proceed(chain.request());
        String cacheControl = originalResponse.header("Cache-Control");

        if (cacheControl == null || cacheControl.contains("no-store") || cacheControl.contains
                ("no-cache") ||
                cacheControl.contains("must-revalidate") || cacheControl.contains("max-age=0")) {
            return originalResponse.newBuilder()
                    .header("Cache-Control", "public, max-age=" + 10)
                    .build();
        } else {
            return originalResponse;
        }
    };

    /**
     * The Builder.
     */
    private OkHttpClient.Builder builder;
    private IRetrofitService retrofitService;

    private RetrofitHelper() {


        int cacheSize = 20 * 1024 * 1024;
        Cache cache = new Cache(DemoApplication.getContext().getCacheDir(), cacheSize);
        builder = new OkHttpClient.Builder()
                .addNetworkInterceptor(REWRITE_RESPONSE_INTERCEPTOR)
                .cache(cache);
        Retrofit retrofit = new Retrofit.Builder()
                .client(builder.build())

                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .baseUrl(IRetrofitService.BASE_URL)
                .build();
        retrofitService = retrofit.create(IRetrofitService.class);
    }

    /**
     * Gets instance.
     *
     * @return the instance
     */
    public static RetrofitHelper getInstance() {
        return Singleton.INSTANCE;
    }

    /**
     * Gets details.
     *
     * @param subscriber the subscriber
     */
    public void getDetails(Subscriber<Response> subscriber){

        retrofitService.getDetails().map(new Func1<Response, Response>() {
            @Override
            public Response call(Response response) {
                return response;
            }
        })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);

    }

    private static class Singleton {
        private static final RetrofitHelper INSTANCE = new RetrofitHelper();
    }


}
