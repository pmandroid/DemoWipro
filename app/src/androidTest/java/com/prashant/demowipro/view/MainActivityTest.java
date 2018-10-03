package com.prashant.demowipro.view;

import android.support.test.rule.ActivityTestRule;
import android.support.v7.widget.RecyclerView;
import android.widget.FrameLayout;

import com.prashant.demowipro.R;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static android.support.test.InstrumentationRegistry.getInstrumentation;
import static org.junit.Assert.assertNotNull;

public class MainActivityTest {

    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new
            ActivityTestRule<MainActivity>(MainActivity.class);
    private MainActivity mainActivity =null;

    @Before
    public void setUp() throws Exception {
        getInstrumentation().waitForIdleSync();
        mainActivity = mActivityTestRule.getActivity();

    }

    @Test
    public void testLaunch(){
        FrameLayout frameLayout =  (FrameLayout)mainActivity.findViewById(R.id.fl_container);
        assertNotNull(frameLayout);

        AboutListFragment aboutListFragment = AboutListFragment.getInstance();
        mainActivity.getSupportFragmentManager().beginTransaction().add(frameLayout.getId(),
                aboutListFragment).commit();
        getInstrumentation().waitForIdleSync();

        RecyclerView recyclerView = aboutListFragment.getView().findViewById(R.id.recycler_view);
        assertNotNull(recyclerView);


    }



    @After
    public void tearDown() throws Exception {
        mainActivity = null;
    }
}