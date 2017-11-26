package com.example.jb.firstnote;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Intent;
import android.support.annotation.NonNull;
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

import java.util.ArrayList;
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

    private void fillSongDatabase(List<Song> songs) {
        SongDatabase db = SongDatabase.getSongDatabase(this);
        db.songDao().insertAll(songs);
    }

    private void fillList(ListView songsList, List<Song> songs) {
        //fillSongDatabase(songs);
        ArrayAdapter<Song> songsAdapter = new ArrayAdapter<Song>(this, R.layout.support_simple_spinner_dropdown_item, songs);
        songsList.setAdapter(songsAdapter);
    }

    /** Called when the user adds a song
    public void addSong(View view) {
        Intent intent = new Intent(this, ShowSongActivity.class);
        EditText editText = (EditText) findViewById(R.id.newSongText);
        String message = editText.getText().toString();
        intent.putExtra(EXTRA_MESSAGE, message);
        startActivity(intent);
    } */

    /** Called when the user plays a note
    public void playNote(View view) {
        Intent intent = new Intent(this, PlaySoundActivity.class);
        startActivity(intent);
    }*/


}
