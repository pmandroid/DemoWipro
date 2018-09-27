package com.prashant.demowipro.view;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.prashant.demowipro.R;
import com.prashant.demowipro.utility.NetworkObserver;
import com.prashant.demowipro.utility.Utils;

/**
 * The type Main activity.
 */
public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
//Removed dialogue to test data caching.
    //    if (NetworkObserver.isNetworkAvailable(MainActivity.this))
            getFragmentManager().beginTransaction().add(R.id.fl_container, AboutListFragment
                    .getInstance())
                    .commit();
//        else
//            Utils.networkDialog(MainActivity.this);
    }
}
