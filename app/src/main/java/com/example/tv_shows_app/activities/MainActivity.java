package com.example.tv_shows_app.activities;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import com.example.tv_shows_app.R;
import com.example.tv_shows_app.adapters.TVShowAdapter;
import com.example.tv_shows_app.databinding.ActivityMainBinding;
import com.example.tv_shows_app.models.TVShow;
import com.example.tv_shows_app.viewmodels.MostPopularTVShowsViewModel;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding activityMainBinding;
    private MostPopularTVShowsViewModel viewModel;
    private List<TVShow> tvShows = new ArrayList<>();
    private TVShowAdapter tvShowAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        doInitialization();
    }

    private void doInitialization(){
        activityMainBinding.tvShowsRecyclerView.setHasFixedSize(true);
        viewModel = new ViewModelProvider(this).get(MostPopularTVShowsViewModel.class);
        tvShowAdapter = new TVShowAdapter(tvShows);
        activityMainBinding.tvShowsRecyclerView.setAdapter(tvShowAdapter);
        getMostPopularTVShows();
    }

    private void getMostPopularTVShows() {
        activityMainBinding.setIsLoading(true);
        viewModel.getMostPopularTVShows(0).observe(this, mostPopularTVShowsResponse ->
        {
            activityMainBinding.setIsLoading(false);
            if(mostPopularTVShowsResponse != null){
                tvShows.addAll(mostPopularTVShowsResponse.getTvShows());
                tvShowAdapter.notifyDataSetChanged();
            }
        }

        );
    }
}