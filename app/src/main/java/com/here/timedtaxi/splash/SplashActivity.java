package com.here.timedtaxi.splash;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.here.timedtaxi.R;
import com.here.timedtaxi.taxis.TaxiActivity;

import io.reactivex.Completable;

public class SplashActivity extends AppCompatActivity implements SplashContract.View{

    private SplashContract.Presenter splashPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        //Create the presenter
        splashPresenter = new SplashPresenter(
                this
        );

        splashPresenter.start();

    }

    @Override
    public void setPresenter(SplashContract.Presenter presenter) {

    }

    @Override
    public Completable getAnimation() {
        return SplashAnimationsUtils.startSplashAnimation (
                findViewById(R.id.tv_logo_1),
                findViewById(R.id.tv_logo_2)
                );
    }

    @Override
    public void onTasksCompleted() {

        //Start the taxi activity
        Intent taxisActivityIntent = new Intent(this, TaxiActivity.class);
        startActivity(taxisActivityIntent);

        //Finish the current activity
        finish();
    }
}
