package com.here.timedtaxi.data;

import android.support.annotation.NonNull;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Mickael on 3/20/2018.
 */

public class TaxiModel implements Comparable<TaxiModel>{

    @SerializedName("id")
    private long taxiId;

    @SerializedName("name")
    private String taxiName;

    @SerializedName("image")
    private String taxiImageUrl;

    @SerializedName("eta")
    private long eta;

    private TaxiModel() {}

    public long getTaxiId() {
        return taxiId;
    }

    public String getTaxiName() {
        return taxiName;
    }

    public String getTaxiImageUrl() {
        return taxiImageUrl;
    }

    public Long getTaxiEta() {
        return eta;
    }

    public void setTaxiEta(int taxiEta) {
        this.eta = taxiEta;
    }

    @Override
    public int compareTo(@NonNull TaxiModel o) {
        return this.getTaxiEta().compareTo(o.getTaxiEta());
    }
}
