package com.prashant.demowipro.viewmodel;

import android.databinding.ObservableField;
import android.view.View;

import com.prashant.demowipro.R;
import com.prashant.demowipro.model.bean.Response;
import com.prashant.demowipro.model.data.RetrofitHelper;
import com.prashant.demowipro.view.AboutAdapter;
import com.prashant.demowipro.view.DemoApplication;
import com.prashant.demowipro.view.ICompletedListener;

import rx.Subscriber;

/**
 * The type Main view model.
 */
public class MainViewModel {
    /**
     * The Content view visibility.
     */
    public ObservableField<Integer> contentViewVisibility;
    /**
     * The Progress bar visibility.
     */
    public ObservableField<Integer> progressBarVisibility;
    /**
     * The Error info layout visibility.
     */
    public ObservableField<Integer> errorInfoLayoutVisibility;
    /**
     * The Exception.
     */
    public ObservableField<String> exception;
    private Subscriber<Response> subscriber;
    private AboutAdapter aboutAdapter;
    private String title;
    private ICompletedListener completedListener;

    /**
     * Instantiates a new Main view model.
     *
     * @param aboutAdapter      the about adapter
     * @param completedListener the completed listener
     */
    public MainViewModel(AboutAdapter aboutAdapter, ICompletedListener completedListener) {
        this.aboutAdapter = aboutAdapter;
        this.completedListener = completedListener;
        initData();
        getDetails();
    }

    private void getDetails() {
        subscriber = new Subscriber<Response>() {
            @Override
            public void onCompleted() {
                hideAll();
                contentViewVisibility.set(View.VISIBLE);
                completedListener.onCompleted();
                errorInfoLayoutVisibility.set(View.GONE);
            }

            @Override
            public void onError(Throwable e) {
                hideAll();
                e.printStackTrace();
                errorInfoLayoutVisibility.set(View.VISIBLE);

                exception.set(DemoApplication.getContext().getResources().getString(R.string
                        .no_internet));
            }

            @Override
            public void onNext(Response item) {
                aboutAdapter.addItem(item.getRows());
                setTitle(item.getTitle());
            }
        };
        RetrofitHelper.getInstance().getDetails(subscriber);
    }

    /**
     * Refresh data.
     */
    public void refreshData() {
        getDetails();
    }

    /**
     * Gets title.
     *
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * Sets title.
     *
     * @param title the title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Initialise  data.
     */
    private void initData() {
        contentViewVisibility = new ObservableField<>();
        progressBarVisibility = new ObservableField<>();
        errorInfoLayoutVisibility = new ObservableField<>();
        exception = new ObservableField<>();
        contentViewVisibility.set(View.GONE);
        errorInfoLayoutVisibility.set(View.GONE);
        progressBarVisibility.set(View.VISIBLE);
    }

    /**
     * hide all data on error.
     */
    private void hideAll() {
        contentViewVisibility.set(View.GONE);
        errorInfoLayoutVisibility.set(View.GONE);
        progressBarVisibility.set(View.GONE);
    }
}
