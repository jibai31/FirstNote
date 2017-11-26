package com.example.jb.firstnote.db;

import com.example.jb.firstnote.models.Song;
import com.example.jb.firstnote.models.Voice;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by jb on 26/11/2017.
 */

public class SongFactory {

    public List<Song> createList() {
        List<Song> songs = new ArrayList<>();

        songs.add(new Song("Asimbonanga", new Voice[]{
            new Voice("Solo Ténor", "C#4")
        }));
        songs.add(new Song("Total Praise", new Voice[]{
                new Voice("Sopranes", "Db3"),
                new Voice("Altos", "Ab2"),
                new Voice("Ténors", "F3"),
                new Voice("Basses", "Db2")
        }));

        return songs;
    }
}
