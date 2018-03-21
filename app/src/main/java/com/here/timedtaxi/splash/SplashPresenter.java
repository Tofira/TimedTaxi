package com.here.timedtaxi.splash;

import io.reactivex.android.schedulers.AndroidSchedulers;

/**
 * Created by Mickael on 3/21/2018.
 */

public class SplashPresenter implements SplashContract.Presenter {

    private boolean isFirstLoad = true;

    private final SplashContract.View splashView;

    public SplashPresenter(SplashContract.View splashView) {
        this.splashView = splashView;

        splashView.setPresenter(this);

    }

    @Override
    public void start() {
        if(!isFirstLoad)
            return;

        isFirstLoad = false;

        splashView.getAnimation()
                .subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnComplete(splashView::onTasksCompleted)
                .subscribe();


    }
}
