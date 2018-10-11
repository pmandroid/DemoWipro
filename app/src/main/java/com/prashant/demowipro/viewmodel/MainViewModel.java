package com.prashant.demowipro.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.databinding.ObservableField;
import android.view.View;

import com.prashant.demowipro.R;
import com.prashant.demowipro.model.bean.Response;
import com.prashant.demowipro.model.data.RetrofitHelper;
import com.prashant.demowipro.view.DemoApplication;

/**
 * The type Main view model.
 */
public class MainViewModel extends AndroidViewModel {
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
    private MutableLiveData<Response> responseMutableLiveData;

    /**
     * Instantiates a new Main view model.
     *
     * @param application the application
     */
    public MainViewModel(Application application) {
        super(application);
        initData();
        getDetails();
    }

    /**
     * Gets details.
     */
    public void getDetails() {
        responseMutableLiveData = RetrofitHelper.getInstance().getDetails();

    }


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
     * Gets progress bar visibility.
     *
     * @return the progress bar visibility
     */
    public ObservableField<Integer> getProgressBarVisibility() {
        return progressBarVisibility;
    }

    /**
     * Gets response observable.
     *
     * @return the response observable
     */
    public LiveData<Response> getResponseObservable() {
        return responseMutableLiveData;
    }

    /**
     * Hide error message.
     */
    public void hideErrorMessage() {
        errorInfoLayoutVisibility.set(View.GONE);
        contentViewVisibility.set(View.VISIBLE);

    }

    /**
     * Show error message.
     */
    public void showErrorMessage() {
        errorInfoLayoutVisibility.set(View.VISIBLE);
        contentViewVisibility.set(View.GONE);
        exception.set(DemoApplication.getContext().getString(R.string
                .no_internet));
    }

}
