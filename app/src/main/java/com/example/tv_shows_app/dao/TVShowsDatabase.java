package com.example.tv_shows_app.dao;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.tv_shows_app.models.TVShow;

@Database(entities = TVShow.class, version = 1, exportSchema = false)
public abstract class TVShowsDatabase extends RoomDatabase {
    private static TVShowsDatabase tvShowsDatabase;

    public static synchronized TVShowsDatabase getTvShowsDatabase(Context context){
        if(tvShowsDatabase == null){
            tvShowsDatabase = Room.databaseBuilder(context, TVShowsDatabase.class, "tv_shows_db").build();
        }
        return tvShowsDatabase;
    }

    public abstract TVShowDao tvShowDao();
}
