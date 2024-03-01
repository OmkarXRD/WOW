package com.live.worldsocialintegrationapp.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Keep;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.live.worldsocialintegrationapp.ModelClasses.Music;

import java.util.ArrayList;
import java.util.List;
@Keep
public class DatabaseHandler extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "musicDatabase";
    private static final String TABLE_CONTACTS = "music";


    private static final String ID = "ID";
    private static final String TITLE = "title";
    private static final String ALBUM = "album";
    private static final String ARTIST = "artist";
    private static final String PATH = "path";
    private static final String DURATION = "duration";
    private static final String ARTURI = "artUri";
    private static final Boolean STATUS = true;

    public DatabaseHandler(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        String CREATE_CONTACTS_TABLE = "CREATE TABLE " + TABLE_CONTACTS + "("
                + ID + " INTEGER PRIMARY KEY,"
                + TITLE + " TEXT,"
                + ALBUM + " ALBUM,"
                + ARTIST + " ARTIST,"
                + PATH + " PATH,"
                + ARTURI + " ARTURI,"
                + DURATION + " DURATION" + ")";
        sqLiteDatabase.execSQL(CREATE_CONTACTS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }


    // code to add the new contact
   public void addMusic(Music contact) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(TITLE, contact.getTitle());
        values.put(ID, contact.getId());
        values.put(ALBUM, contact.getAlbum());
        values.put(ARTIST, contact.getArtist());
        values.put(PATH, contact.getPath());
        values.put(DURATION, contact.getDuration());
        values.put(ARTURI, contact.getArtUri());


        // Inserting Row
        db.insert(TABLE_CONTACTS, null, values);
        //2nd argument is String containing nullColumnHack
        db.close(); // Closing database connection
    }


    public List<Music> getMusicList() {
        List<Music> contactList = new ArrayList<Music>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_CONTACTS;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Music contact = new Music();
                contact.setId(cursor.getString(0));
                contact.setTitle(cursor.getString(1));
                contact.setAlbum(cursor.getString(2));
                contact.setPath(cursor.getString(3));
                contact.setDuration(cursor.getLong(4));
                contact.setArtUri(cursor.getString(5));

                // Adding contact to list
                contactList.add(contact);
            } while (cursor.moveToNext());
        }

        // return contact list
        return contactList;
    }

}
