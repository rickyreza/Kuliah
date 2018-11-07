package com.kelompok25modul2.ui.detailCar;

import android.util.Log;

import com.google.gson.JsonObject;
import com.kelompok25modul2.data.model.DataCar;
import com.kelompok25modul2.data.network.RetrofitClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailDeletePresenter {

    private DetailView detailView;

    public DetailDeletePresenter(DetailView detailView){
        this.detailView = detailView;
    }


    public void deleteCar(DataCar car){
        final String tag = "Delete Car";

        RetrofitClient.getInstance()
                .getApi()
                .deleteCar(car.getId())
                .enqueue(new Callback<JsonObject>() {
                    @Override
                    public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                        if(response.isSuccessful()){
                            detailView.successDelete();
                            Log.e(tag, response.body().toString());
                        } else {
                            detailView.failedDelete();
                            Log.e(tag, response.body().toString());
                        }
                    }

                    @Override
                    public void onFailure(Call<JsonObject> call, Throwable t) {
                        detailView.failedDelete();
                        Log.e(tag, t.getMessage());
                    }
                });
    }
}
