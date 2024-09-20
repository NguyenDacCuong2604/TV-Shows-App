package com.example.tv_shows_app.models;

import com.google.gson.annotations.SerializedName;

public class Episode {
    @SerializedName("season")
    private String season;

    @SerializedName("episode")
    private String episode;

    @SerializedName("name")
    private String name;

    @SerializedName("air_date")
    private String airDate;

    public String getSeason() {
        return season;
    }

    public void setSeason(String season) {
        this.season = season;
    }

    public String getEpisode() {
        return episode;
    }

    public void setEpisode(String episode) {
        this.episode = episode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAirDate() {
        return airDate;
    }

    public void setAirDate(String airDate) {
        this.airDate = airDate;
    }
}
