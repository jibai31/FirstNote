package com.example.jb.firstnote;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.jb.firstnote.db.SongDatabase;
import com.example.jb.firstnote.db.SongFactory;
import com.example.jb.firstnote.models.Song;

import java.util.List;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    public static final String SONG_POSITION = "com.example.myfirstapp.SONG_POSITION";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Fill songs list
        ListView songsList = (ListView) findViewById(R.id.songsList);
        List<Song> songs = loadSongs();
        fillList(songsList, songs);

        songsList.setOnItemClickListener(this);
    }

    /** Called when the user clicks on a song */
    public void onItemClick(AdapterView<?> l, View view, int position, long id) {
        Log.i("SongsList", "You clicked item: " + id + " at position: " + position);
        Intent intent = new Intent(this, ShowSongActivity.class);
        intent.putExtra(SONG_POSITION, position);
        startActivity(intent);
    }

    private List<Song> loadSongs() {
        SongFactory factory = new SongFactory();
        List<Song> songs = factory.createList();
        return songs;
    }

    private void fillSongDatabase(final List<Song> songs) {
        final Context currentContext = this;
        // Use a new tread as this can take a while
        final Thread thread = new Thread(new Runnable() {
            public void run() {
                SongDatabase db = SongDatabase.getSongDatabase(currentContext);
                db.songDao().insertAll(songs);
            }
        });
        thread.start();
    }

    private void fillList(ListView songsList, List<Song> songs) {
        //fillSongDatabase(songs);
        ArrayAdapter<Song> songsAdapter = new ArrayAdapter<Song>(this, R.layout.support_simple_spinner_dropdown_item, songs);
        songsList.setAdapter(songsAdapter);
    }

}
