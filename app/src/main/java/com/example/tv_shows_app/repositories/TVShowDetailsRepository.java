package com.example.tv_shows_app.repositories;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.tv_shows_app.network.APIService;
import com.example.tv_shows_app.network.ApiClient;
import com.example.tv_shows_app.responses.TVShowDetailsResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TVShowDetailsRepository {
    private APIService apiService;

    public TVShowDetailsRepository(){
        apiService = ApiClient.getRetrofit().create(APIService.class);
    }

    public LiveData<TVShowDetailsResponse> getTVShowDetails(String tvShowId){
        MutableLiveData<TVShowDetailsResponse> data = new MutableLiveData<>();
        apiService.getTVShowDetails(tvShowId).enqueue(new Callback<TVShowDetailsResponse>() {
            @Override
            public void onResponse(Call<TVShowDetailsResponse> call, Response<TVShowDetailsResponse> response) {
                data.setValue(response.body());
            }

            @Override
            public void onFailure(Call<TVShowDetailsResponse> call, Throwable t) {
                data.setValue(null);
            }
        });
        return data;
    }
}
