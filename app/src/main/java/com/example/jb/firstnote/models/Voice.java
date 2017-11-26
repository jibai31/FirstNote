package com.example.jb.firstnote.models;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;

/**
 * Created by jb on 26/11/2017.
 */

@Entity(tableName = "voices",
        foreignKeys = @ForeignKey(entity = Song.class, parentColumns = "uid", childColumns = "song_id"))
public class Voice {
    @PrimaryKey(autoGenerate = true)
    private int uid;

    @ColumnInfo(name = "song_id")
    public int songId;

    private String name;
    private String note;

    public Voice(String name, String note) {
        this.name = name;
        this.note = note;
    }

    // Instance methods

    public String toString() {
        return name + " : " + getNoteName();
    }

    public double getFrequency() {
        if (note == null) { return 0; }
        int octave = getOctave();
        String noteName = getNoteName();

        double baseFrequency = getBaseFrequency(noteName);
        if (octave == 3) {
            return baseFrequency;
        }
        else {
            return baseFrequency * Math.pow(2, octave - 3);
        }
    }

    // Private methods

    private int getOctave() {
        return Integer.parseInt(note.substring(note.length() - 1));
    }

    private String getNoteName() {
        return note.substring(0, note.length() - 1);
    }

    // La440 = A3 -> base frequency = octave n°3
    private double getBaseFrequency(String noteName) {
        switch (noteName) {
            case "A":
                return 440;
            case "A#":
            case "Bb":
                return 466.16;
            case "B":
            case "Cb":
                return 493.88;
            case "B#":
            case "C":
                return 261.63;
            case "C#":
            case "Db":
                return 277.18;
            case "D":
                return 293.66;
            case "D#":
            case "Eb":
                return 311.13;
            case "E":
            case "Fb":
                return 329.63;
            case "E#":
            case "F":
                return 349.23;
            case "F#":
            case "Gb":
                return 369.99;
            case "G":
                return 392;
            case "G#":
            case "Ab":
                return 415.30;
            default:
                return 0;
        }
    }
}
