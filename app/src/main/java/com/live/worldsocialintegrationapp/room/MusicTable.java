package com.live.worldsocialintegrationapp.room;

import androidx.annotation.Keep;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;
import androidx.text.emoji.flatbuffer.Table;
@Keep
@Entity(tableName = "music")
public class MusicTable {


    @PrimaryKey
    int  id;
    @ColumnInfo(name="title")
    String title;
    @ColumnInfo(name="album")
    String album;

    @ColumnInfo(name="status")
    boolean status;
    @ColumnInfo(name="path")
    String path;
    @ColumnInfo(name="duration")
    Long duration;
    @ColumnInfo(name="artUri")
    String artUri;
    @ColumnInfo(name="artist")
    String artist;

    public MusicTable(int id, String title, String album, boolean status, String path, Long duration, String artUri, String artist) {
        this.id = id;
        this.title = title;
        this.album = album;
        this.status = status;
        this.path = path;
        this.duration = duration;
        this.artUri = artUri;
        this.artist = artist;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAlbum() {
        return album;
    }

    public void setAlbum(String album) {
        this.album = album;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Long getDuration() {
        return duration;
    }

    public void setDuration(Long duration) {
        this.duration = duration;
    }

    public String getArtUri() {
        return artUri;
    }

    public void setArtUri(String artUri) {
        this.artUri = artUri;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }
}
