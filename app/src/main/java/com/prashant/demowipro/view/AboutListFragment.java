package com.prashant.demowipro.view;


import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.pwittchen.reactivenetwork.library.rx2.ConnectivityPredicate;
import com.github.pwittchen.reactivenetwork.library.rx2.ReactiveNetwork;
import com.prashant.demowipro.R;
import com.prashant.demowipro.databinding.AboutListBinding;
import com.prashant.demowipro.viewmodel.MainViewModel;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * The type About list fragment.
 */
public class AboutListFragment extends Fragment implements ICompletedListener, SwipeRefreshLayout
        .OnRefreshListener {

    /**
     * The Internet disposable.
     */
    Disposable internetDisposable;
    /**
     * The Composite disposable.
     */
    CompositeDisposable compositeDisposable;
    private MainViewModel viewModel;
    private AboutListBinding aboutListFragmentBinding;
    private AboutAdapter aboutAdapter;

    /**
     * Gets instance.
     *
     * @return the instance
     */
    public static AboutListFragment getInstance() {
        return new AboutListFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle
            savedInstanceState) {
        View contentView = inflater.inflate(R.layout.about_list, container, false);

        compositeDisposable = new CompositeDisposable();
        aboutListFragmentBinding = AboutListBinding.bind(contentView);


        initData();
        return contentView;
    }

    private void initData() {
        aboutAdapter = new AboutAdapter();
        aboutListFragmentBinding.recyclerView.setLayoutManager(new LinearLayoutManager
                (getActivity(), LinearLayoutManager.VERTICAL, false));
        aboutListFragmentBinding.recyclerView.setItemAnimator(new DefaultItemAnimator());
        aboutListFragmentBinding.recyclerView.setAdapter(aboutAdapter);
        aboutListFragmentBinding.swipeRefreshLayout.setColorSchemeResources(R.color.colorAccent,
                R.color.colorPrimary, R.color.colorPrimaryDark);
        aboutListFragmentBinding.swipeRefreshLayout.setOnRefreshListener(this);
        viewModel = new MainViewModel(aboutAdapter, this);
        aboutListFragmentBinding.setViewModel(viewModel);

    }

    @Override
    public void onRefresh() {
        aboutAdapter.clearItems();
        viewModel.refreshData();
    }

    @Override
    public void onCompleted() {
        if (aboutListFragmentBinding.swipeRefreshLayout.isRefreshing()) {
            aboutListFragmentBinding.swipeRefreshLayout.setRefreshing(false);
        }
        if (viewModel != null) {
            getActivity().setTitle(viewModel.getTitle());
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        rxNetworkObservable(getActivity());
    }

    @Override
    public void onDetach() {
        super.onDetach();
        compositeDisposable.add(internetDisposable);
    }

    /**
     * Rx network observable.
     *
     * @param context the context
     */
    public void rxNetworkObservable(Context context) {

        internetDisposable = ReactiveNetwork.observeNetworkConnectivity(getActivity())
                .subscribeOn(Schedulers.io())
                .filter(ConnectivityPredicate.hasState(NetworkInfo.State.CONNECTED))
                .filter(ConnectivityPredicate.hasType(ConnectivityManager.TYPE_WIFI))
                .filter(ConnectivityPredicate.hasType(ConnectivityManager.TYPE_MOBILE))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(connectivity -> {
                    if (connectivity.state().equals(NetworkInfo.State.CONNECTED)) {
                        initData();
                    }

                });
    }
}