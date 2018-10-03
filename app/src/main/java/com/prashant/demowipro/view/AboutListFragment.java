package com.prashant.demowipro.view;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        rxNetworkObservable();
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
        if (getActivity()!=null && isAdded() && viewModel != null) {
            getActivity().setTitle(viewModel.getTitle());
        }
    }

    @Override
    public void onResume() {
        super.onResume();

    }

    @Override
    public void onDetach() {
        super.onDetach();
        compositeDisposable.add(internetDisposable);

    }

    /**
     * Rx network observable.
     */
    public void rxNetworkObservable() {

        internetDisposable = ReactiveNetwork
                .observeNetworkConnectivity(DemoApplication.getContext())
                .flatMapSingle(connectivity -> ReactiveNetwork.checkInternetConnectivity())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(isConnected -> {
                    if (isConnected) {
                            initData();
                    }
                });


    }
}