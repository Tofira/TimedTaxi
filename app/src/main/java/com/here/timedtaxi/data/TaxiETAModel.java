package com.here.timedtaxi.data;


/**
 * Created by Mickael on 3/20/2018.
 */

public class TaxiETAModel {

    private long taxiId;

    private int taxiEta;

    public TaxiETAModel(long taxiId, int taxiEta) {
        this.taxiId = taxiId;
        this.taxiEta = taxiEta;
    }

    public long getTaxiId() {
        return taxiId;
    }

    public int getTaxiEta() {
        return taxiEta;
    }
}
