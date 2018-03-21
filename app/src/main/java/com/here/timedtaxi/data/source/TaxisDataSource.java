package com.here.timedtaxi.data.source;

import com.here.timedtaxi.data.TaxiETAModel;
import com.here.timedtaxi.data.TaxiModel;

import java.util.List;

import io.reactivex.Observable;

/**
 * Created by Mickael on 3/20/2018.
 */

public interface TaxisDataSource {

    Observable<List<TaxiModel>> fetchTaxis();

    Observable<List<TaxiETAModel>> fetchETAs(List<TaxiModel> taxiModels);

    Observable<List<TaxiModel>> refreshTaxis();

}
