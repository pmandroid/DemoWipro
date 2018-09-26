package com.prashant.demowipro.model.data;

import com.prashant.demowipro.model.bean.Response;

import retrofit2.http.GET;
import rx.Observable;

/**
 * The interface Retrofit service.
 */
public interface IRetrofitService {

    /**
     * The constant BASE_URL.
     */
    String BASE_URL = "https://dl.dropboxusercontent.com/s/2iodh4vg0eortkl/";

    /**
     * Gets details.
     *
     * @return the details
     */
    @GET("facts.json")
    Observable<Response> getDetails();
}
