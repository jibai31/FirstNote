package com.example.jb.firstnote.db;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.example.jb.firstnote.models.Song;

import java.util.List;

/**
 * Created by jb on 26/11/2017.
 */

@Dao
public interface SongDao {

    @Query("SELECT * FROM songs")
    public List<Song> findAll();

    @Query("SELECT * FROM songs WHERE name LIKE :name LIMIT 1")
    Song findByName(String name);

    @Query("SELECT * FROM songs WHERE uid = :uid LIMIT 1")
    Song findByUid(int uid);

    @Insert
    void insertAll(List<Song> songs);

}
