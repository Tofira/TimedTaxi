package com.here.timedtaxi.splash;

import com.here.timedtaxi.BasePresenter;
import com.here.timedtaxi.BaseView;

import io.reactivex.Completable;

/**
 * Created by Mickael on 3/21/2018.
 */

public class SplashContract {

    interface View extends BaseView<Presenter> {
        Completable getAnimation();
        void onTasksCompleted();
    }

    interface Presenter extends BasePresenter {
    }

}
