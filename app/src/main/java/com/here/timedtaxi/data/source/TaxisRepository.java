package com.here.timedtaxi.data.source;

import android.content.Context;
import android.support.annotation.NonNull;

import com.here.timedtaxi.data.TaxiETAModel;
import com.here.timedtaxi.data.TaxiModel;
import com.here.timedtaxi.data.source.remote.TaxisRemoteDataSource;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;


/**
 * Created by Mickael on 3/20/2018.
 */

public class TaxisRepository implements TaxisDataSource {

    //Singleton
    private static TaxisRepository instance;

    //DataSources
    private TaxisDataSource taxisRemoteDataSource;

    //Current Taxis
    private Map<Long, TaxiModel> taxisMap;

    private List<TaxiModel> taxisList;

    private TaxisRepository(Context context) {
        taxisRemoteDataSource = new TaxisRemoteDataSource(context);

        taxisMap = new LinkedHashMap<>();

        taxisList = new ArrayList<>();

    }

    public static TaxisRepository getInstance(Context context)
    {
        if(instance == null)
        {
            synchronized (TaxisRepository.class)
            {
                if(instance == null)
                    instance = new TaxisRepository(context);
            }
        }

        return  instance;
    }


    @Override
    public Observable<List<TaxiModel>> fetchTaxis() {
        return taxisRemoteDataSource.fetchTaxis()
            .map(taxiModels -> {
                updateTaxis(taxiModels);
                return taxiModels;
            })
            .flatMap(Observable::fromIterable)
            .toSortedList()
            .toObservable();

    }

    @Override
    public Observable<List<TaxiETAModel>> fetchETAs(List<TaxiModel> taxiModels) {
        //Not required
        return null;
    }

    @Override
    public Observable<List<TaxiModel>> refreshTaxis() {

        return taxisRemoteDataSource.fetchETAs(taxisList)
            .flatMap(Observable::fromIterable)
            .map(taxiETAModel -> {
                TaxiModel taxiModel = taxisMap.get(taxiETAModel.getTaxiId());

                if(taxiModel == null)
                    return null;

                taxiModel.setTaxiEta(taxiETAModel.getTaxiEta());

                return taxiModel;
            })
            .filter(taxiModel -> taxiModel != null)
            .toSortedList()
            .toObservable()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread());

    }


    private void updateTaxis(@NonNull List<TaxiModel> newTaxis)
    {
        for(TaxiModel taxi : newTaxis)
            taxisMap.put(taxi.getTaxiId(), taxi);

        taxisList = newTaxis;

    }

}
