package com.here.timedtaxi.taxis;

import com.here.timedtaxi.BasePresenter;
import com.here.timedtaxi.BaseView;
import com.here.timedtaxi.data.TaxiModel;

import java.util.List;

/**
 * Created by Mickael on 21/03/2018.
 */

public class TaxisContract {

    interface View extends BaseView<Presenter> {
        void onEtaReceived(List<TaxiModel> taxiModels);

        void onETAError();
    }

    interface Presenter extends BasePresenter {
        void stopMonitoring();
    }

}
