package com.example.tv_shows_app.listeners;

import com.example.tv_shows_app.models.TVShow;

public interface WatchlistListener {
    void onTVShowClicked(TVShow tvShow);
    void removeTVShowFromWatchlist(TVShow tvShow, int position);
}
