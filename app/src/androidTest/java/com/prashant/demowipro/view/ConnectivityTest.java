package com.prashant.demowipro.view;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.test.rule.ActivityTestRule;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static android.support.test.InstrumentationRegistry.getInstrumentation;
import static junit.framework.TestCase.assertTrue;

public class ConnectivityTest {

    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new
            ActivityTestRule<MainActivity>(MainActivity.class);
    private MainActivity mainActivity =null;

    @Before
    public void setUp(){
        getInstrumentation().waitForIdleSync();
        mainActivity = mActivityTestRule.getActivity();

    }

    @Test
    public void testLaunch(){
        assertTrue(isConnected(mainActivity));

    }

    public static boolean isConnected(Context context) {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    @After
    public void tearDown() throws Exception {
        mainActivity = null;
    }
}