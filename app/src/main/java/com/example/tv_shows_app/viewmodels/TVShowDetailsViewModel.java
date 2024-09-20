package com.example.tv_shows_app.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.tv_shows_app.repositories.TVShowDetailsRepository;
import com.example.tv_shows_app.responses.TVShowDetailsResponse;

public class TVShowDetailsViewModel extends ViewModel {
    private TVShowDetailsRepository tvShowDetailsRepository;

    public TVShowDetailsViewModel(){
        tvShowDetailsRepository = new TVShowDetailsRepository();
    }

    public LiveData<TVShowDetailsResponse> getTVShowDetails(String tvShowId){
        return tvShowDetailsRepository.getTVShowDetails(tvShowId);
    }
}
