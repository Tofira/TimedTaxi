package com.here.timedtaxi.taxis;

import android.support.annotation.NonNull;

import com.here.timedtaxi.data.TaxiModel;
import com.here.timedtaxi.data.source.TaxisRepository;

import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Mickael on 21/03/2018.
 */

public class TaxisPresenter implements TaxisContract.Presenter {

    private static final long REFRESH_INTERVAL_SECONDS = 5;

    private final TaxisRepository taxisRepository;

    private final TaxisContract.View taxisView;

    private Disposable monitoringDisposable;

    public TaxisPresenter(@NonNull TaxisRepository taxisRepository,
                          @NonNull TaxisContract.View taxisView) {
        this.taxisRepository = taxisRepository;
        this.taxisView = taxisView;

        taxisView.setPresenter(this);
    }

    @Override
    public void start() {

        startMonitoring();
    }

    private void startMonitoring()
    {
        Observable<List<TaxiModel>> observable = taxisRepository.fetchTaxis()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .flatMap(taxiModels -> {
                taxisView.onEtaReceived(taxiModels);

                return Observable.interval(REFRESH_INTERVAL_SECONDS, TimeUnit.SECONDS)
                    .flatMap(aLong -> taxisRepository.refreshTaxis());
            });

        monitoringDisposable = observable.subscribe(taxisView::onEtaReceived);
    }

    @Override
    public void stopMonitoring() {
        monitoringDisposable.dispose();
    }
}
