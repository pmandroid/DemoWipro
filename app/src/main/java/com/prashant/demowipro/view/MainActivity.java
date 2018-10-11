package com.prashant.demowipro.view;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.prashant.demowipro.R;

/**
 * The type Main activity.
 */
public class MainActivity extends BaseActivity {


    /**
     * The constant IS_FIRST_TIME.
     */
    public static final String IS_FIRST_TIME = "is_first_time";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().add(R.id.fl_container, AboutListFragment
                    .getInstance())
                    .commit();
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putBoolean(IS_FIRST_TIME, false);
    }
}
