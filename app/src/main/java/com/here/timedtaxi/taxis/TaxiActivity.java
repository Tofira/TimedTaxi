package com.here.timedtaxi.taxis;

import android.content.ComponentName;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.here.timedtaxi.R;
import com.here.timedtaxi.data.TaxiModel;
import com.here.timedtaxi.data.source.TaxisRepository;
import com.here.timedtaxi.utils.Utils;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import it.gmariotti.recyclerview.adapter.SlideInBottomAnimatorAdapter;

public class TaxiActivity extends AppCompatActivity implements TaxisContract.View {

    //Elements
    private RecyclerView rvTaxis;
    private TaxisAdapter taxisAdapter;

    //Presenter
    private TaxisContract.Presenter taxisPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_taxi);

        setupToolbar();

        setupRecyclerView();

        taxisPresenter = new TaxisPresenter(
                TaxisRepository.getInstance(getBaseContext()),
                this
        );

    }

    private void setupRecyclerView()
    {
        rvTaxis = findViewById(R.id.rv_taxis);


        //Adapters
        taxisAdapter = new TaxisAdapter();
        SlideInBottomAnimatorAdapter animatorAdapter = new SlideInBottomAnimatorAdapter(taxisAdapter, rvTaxis);

        rvTaxis.setLayoutManager(new LinearLayoutManager(this));

        rvTaxis.setAdapter(animatorAdapter);

    }

    private void setupToolbar()
    {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    @Override
    protected void onStart() {
        super.onStart();

        taxisPresenter.start();

    }

    @Override
    protected void onStop() {
        super.onStop();

        taxisPresenter.stopMonitoring();

    }


    @Override
    public void setPresenter(TaxisContract.Presenter presenter) {
        //Not required
    }

    @Override
    public synchronized void onEtaReceived(List<TaxiModel> taxiModels) {
        taxisAdapter.setList(taxiModels);
    }

    @Override
    public void onETAError() {
        Toast.makeText(this, R.string.eta_error_message, Toast.LENGTH_LONG).show();
    }

}
