package com.example.jb.firstnote;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.jb.firstnote.db.SongFactory;
import com.example.jb.firstnote.models.Song;
import com.example.jb.firstnote.models.Voice;

import java.util.List;


public class ShowSongActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    public static final String VOICE_FREQ = "com.example.myfirstapp.VOICE_FREQ";

    Song song;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_song);

        song = getSong();

        // Display song name
        TextView textView = (TextView) findViewById(R.id.songName);
        textView.setText(song.getName());

        // Display voices
        ListView voicesList = (ListView) findViewById(R.id.voicesList);
        ArrayAdapter<Voice> voicesAdapter = new ArrayAdapter<Voice>(this, R.layout.support_simple_spinner_dropdown_item, song.getVoices());
        voicesList.setAdapter(voicesAdapter);

        // Bind click event
        voicesList.setOnItemClickListener(this);
    }

    /** Called when the user clicks on a voice */
    public void onItemClick(AdapterView<?> l, View view, int position, long id) {
        Log.i("VoicesList", "You clicked item: " + id + " at position: " + position);
        Intent intent = new Intent(this, PlaySoundActivity.class);
        Voice voice = song.getVoice(position);
        intent.putExtra(VOICE_FREQ, voice.getFrequency());
        startActivity(intent);
    }

    private Song getSong() {
        // Get the Intent that started this activity and extract the string
        Intent intent = getIntent();
        int position = intent.getIntExtra(MainActivity.SONG_POSITION,0);

        List<Song> songs = loadSongs();
        return songs.get(position);
    }

    private List<Song> loadSongs() {
        SongFactory factory = new SongFactory();
        List<Song> songs = factory.createList();
        return songs;
    }
}
