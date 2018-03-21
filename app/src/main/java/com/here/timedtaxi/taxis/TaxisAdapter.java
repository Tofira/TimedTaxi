package com.here.timedtaxi.taxis;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.here.timedtaxi.R;
import com.here.timedtaxi.data.TaxiModel;
import com.here.timedtaxi.utils.Utils;

import java.util.ArrayList;
import java.util.List;

public class TaxisAdapter extends RecyclerView.Adapter<TaxisAdapter.TaxiViewHolder> {

    private List<TaxiModel> taxisList;

    public TaxisAdapter() {
        this.taxisList = new ArrayList<>();
    }

    @Override
    public TaxiViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new TaxiViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.taxi_item_cardview, parent, false));
    }

    @Override
    public void onBindViewHolder(final TaxiViewHolder holder, int position) {
        TaxiModel taxiModel = taxisList.get(position);

        holder.tvTaxiETA.setText(
                Utils.parseETA(taxiModel.getTaxiEta())
                        + holder.tvTaxiName.getContext().getString(R.string.minute_suffix)
        );

        holder.tvTaxiName.setText(
                taxiModel.getTaxiName()
        );

        Glide.with(holder.ivTaxiImage.getContext())
                .load(taxiModel.getTaxiImageUrl())
                .apply(RequestOptions.circleCropTransform())
                .into(holder.ivTaxiImage);
    }



    @Override
    public int getItemCount() {
        return (taxisList == null) ? 0 : taxisList.size();
    }

    public void setList(List<TaxiModel> taxisList) {
        this.taxisList.clear();
        this.taxisList.addAll(taxisList);
        notifyDataSetChanged();
    }

    static class TaxiViewHolder extends RecyclerView.ViewHolder {
        private TextView tvTaxiName;
        private TextView tvTaxiETA;
        private ImageView ivTaxiImage;

        TaxiViewHolder(View view) {
            super(view);
            tvTaxiName = view.findViewById(R.id.tv_taxi_name);
            tvTaxiETA = view.findViewById(R.id.tv_taxi_eta);
            ivTaxiImage = view.findViewById(R.id.iv_taxi_image);
        }
    }
}