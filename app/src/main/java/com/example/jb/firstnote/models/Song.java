package com.example.jb.firstnote.models;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;

/**
 * Created by jb on 26/11/2017.
 */

@Entity(tableName = "songs")
public class Song {
    @PrimaryKey(autoGenerate = true)
    private int uid;
    private String name;

    @Ignore
    private Voice[] voices;

    public Song(String name) {
        this.name = name;
    }

    public Song(String name, Voice[] voices) {
        this(name);
        this.voices = voices;
    }

    // Getters / Setters
    public int getUid() { return uid; }
    public void setUid(int uid) { this.uid = uid; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public Voice[] getVoices() { return voices; }
    public void setVoices(Voice[] voices) { this.voices = voices; }

    // Instance methods

    public Voice getVoice(int position) {
        return voices[position];
    }

    public String toString() { return name; }
}
