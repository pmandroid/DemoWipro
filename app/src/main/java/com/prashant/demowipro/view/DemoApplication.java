package com.prashant.demowipro.view;

import android.app.Application;

/**
 * The type Demo application.
 */
public class DemoApplication extends Application {

    private static DemoApplication mContext;
    @Override
    public void onCreate() {
        super.onCreate();
        mContext = this;
    }

    /**
     * Gets context.
     *
     * @return the context
     */
    public static DemoApplication getContext() {
        return mContext;
    }




}
