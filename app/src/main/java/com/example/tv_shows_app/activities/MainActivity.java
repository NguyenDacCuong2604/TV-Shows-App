package com.example.tv_shows_app.activities;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

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
    private int currentPage = 1;
    private int totalAvailablePages = 1;

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
        activityMainBinding.tvShowsRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if(!activityMainBinding.tvShowsRecyclerView.canScrollVertically(1)){
                    if(currentPage <= totalAvailablePages){
                        currentPage+=1;
                        getMostPopularTVShows();
                    }
                }
            }
        });
        getMostPopularTVShows();
    }

    private void getMostPopularTVShows() {
        toggleLoading();
        viewModel.getMostPopularTVShows(currentPage).observe(this, mostPopularTVShowsResponse ->
        {
            toggleLoading();
            if(mostPopularTVShowsResponse != null){
                totalAvailablePages = mostPopularTVShowsResponse.getTotalPages();
                if(mostPopularTVShowsResponse.getTvShows()!=null){
                    int oldCount = tvShows.size();
                    tvShows.addAll(mostPopularTVShowsResponse.getTvShows());
                    tvShowAdapter.notifyItemRangeInserted(oldCount, tvShows.size());
                }
            }
        }
        );
    }

    private void toggleLoading(){
        if(currentPage == 1){
            activityMainBinding.setIsLoading(activityMainBinding.getIsLoading() == null || !activityMainBinding.getIsLoading());
        }else {
            activityMainBinding.setIsLoadingMore(activityMainBinding.getIsLoadingMore() == null || !activityMainBinding.getIsLoadingMore());
        }
    }
}