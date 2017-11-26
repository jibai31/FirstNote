package com.example.jb.firstnote.db;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.example.jb.firstnote.models.Song;

/**
 * Created by jb on 26/11/2017.
 */

@Database(entities = {Song.class}, version = 1)
public abstract class SongDatabase extends RoomDatabase {

    private static SongDatabase INSTANCE;

    public abstract SongDao songDao();

    /** You shouldn't use allowMainThreadQueries in production as it might lock the UI
     *  Instead the DB access should be done in another thread.
     */
    public static SongDatabase getSongDatabase(Context context) {
        if (INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(), SongDatabase.class, "song-database")
                    .allowMainThreadQueries()
                    .build();
        }
        return INSTANCE;
    }

    public void destroyInstance() {
        INSTANCE = null;
    }
}
