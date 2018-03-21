package com.here.timedtaxi.data.source.remote;

import android.content.Context;

import com.here.timedtaxi.data.TaxiETAModel;
import com.here.timedtaxi.data.TaxiModel;
import com.here.timedtaxi.data.source.TaxisDataSource;

import java.util.List;

import io.reactivex.Observable;


/**
 * Created by Mickael on 3/20/2018.
 */

public class TaxisRemoteDataSource implements TaxisDataSource {

    private TaxisAPI taxisAPI;

    public TaxisRemoteDataSource(Context context) {

        taxisAPI = new TaxisAPI(context);

    }

    @Override
    public Observable<List<TaxiModel>> fetchTaxis() {

        return taxisAPI.fetchTaxis();

    }

    @Override
    public Observable<List<TaxiETAModel>> fetchETAs(List<TaxiModel> taxiModels) {
        return taxisAPI.fetchETAs(taxiModels);
    }

    @Override
    public Observable<List<TaxiModel>> refreshTaxis() {
        //Not required
        return null;
    }


}
