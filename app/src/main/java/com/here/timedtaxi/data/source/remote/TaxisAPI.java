package com.here.timedtaxi.data.source.remote;

import android.content.Context;
import android.content.res.Resources;

import com.google.gson.Gson;
import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import com.here.timedtaxi.R;
import com.here.timedtaxi.data.TaxiETAModel;
import com.here.timedtaxi.data.TaxiModel;
import com.here.timedtaxi.utils.Utils;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.functions.Function;

/**
 * Created by Mickael on 21/03/2018.
 */

public class TaxisAPI {

    private Gson gson;

    private Context context;

    public TaxisAPI(Context context) {

        this.context = context;

        gson = new Gson();

    }

    public Observable<List<TaxiModel>> fetchTaxis()
    {
        return Observable.create((ObservableEmitter<List<TaxiModel>> emitter) -> {

            try {
                Resources res = context.getResources();
                InputStream inputStream = res.openRawResource(R.raw.taxis);

                BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

                List<TaxiModel> taxiModels = gson.fromJson(reader, new TypeToken<List<TaxiModel>>(){}.getType());

                emitter.onNext(taxiModels);

                emitter.onComplete();
            } catch (Exception e) {
                e.printStackTrace();

                emitter.onError(e);

            }

        });
    }

    public Observable<List<TaxiETAModel>> fetchETAs(List<TaxiModel> taxiModels)
    {
        return Observable.fromIterable(taxiModels)
            .map(taxiModel -> new TaxiETAModel(
                    taxiModel.getTaxiId(),
                    Utils.getRandomETA()
            ))
            .toList()
            .toObservable();
    }
}
