package com.prashant.demowipro.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;

import com.prashant.demowipro.R;
import com.prashant.demowipro.utility.NetworkObserver;

/**
 * The type Base activity.
 */
public class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (!NetworkObserver.isNetworkAvailable(BaseActivity.this)) {
            Snackbar snackbar = Snackbar.make(findViewById(android.R.id.content), getString(R
                            .string.network_unavailable),
                    Snackbar
                            .LENGTH_SHORT);
            snackbar.show();

        }
    }
}
