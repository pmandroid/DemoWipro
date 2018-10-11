package com.prashant.demowipro.model.data;

import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;

import com.prashant.demowipro.model.bean.Response;
import com.prashant.demowipro.view.DemoApplication;

import okhttp3.Cache;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

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
                    .header("Cache-Control", "public, max-age=" + 60)
                    .build();
        } else {
            return originalResponse;
        }
    };

    private IRetrofitService retrofitService;

    private RetrofitHelper() {


        int cacheSize = 20 * 1024 * 1024;
        Cache cache = new Cache(DemoApplication.getContext().getCacheDir(), cacheSize);
        OkHttpClient.Builder builder = new OkHttpClient.Builder()
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
     * @return the details
     */
    public MutableLiveData<Response> getDetails() {
        {
            final MutableLiveData<Response> responseMutableLiveData = new MutableLiveData<>();
            retrofitService.getDetails()
                    .enqueue(new Callback<Response>() {

                        @Override
                        public void onResponse(@NonNull Call<Response> call, @NonNull retrofit2.Response<Response>
                                response) {
                            responseMutableLiveData.setValue(response.body());

                        }

                        @Override
                        public void onFailure(@NonNull Call<Response> call, @NonNull Throwable t) {
                            responseMutableLiveData.setValue(null);
                        }
                    });
            return responseMutableLiveData;
        }
    }

    private static class Singleton {
        private static final RetrofitHelper INSTANCE = new RetrofitHelper();
    }
}





