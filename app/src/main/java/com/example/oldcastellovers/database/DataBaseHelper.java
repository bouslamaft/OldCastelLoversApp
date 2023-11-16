package com.example.oldcastellovers.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DataBaseHelper extends SQLiteOpenHelper {


    public static final String LIKED_CASTLE_TABLE = "LIKED_CASTLE_TABLE";
    public static final String COLUMN_CASTLE_NAME = "CASTLE_NAME";
    public static final String COLUMN_CASTLE_ADDRESS = "CASTLE_ADDRESS";
    public static final String COLUMN_CASTLE_WEBSITE = "CASTLE_WEBSITE";
    public static final String COLUMN_IS_LIKED = "IS_LIKED";
    public static final String COLUMN_ID = "ID";
    public static final String DIARY_ENTRY_TABLE = "DIARY_ENTRY_TABLE";
    public static final String COLUMN_DIARY_ID = "DIARY_ID";
    public static final String COLUMN_ENTRY_DATE = "ENTRY_DATE";
    public static final String COLUMN_NOTES = "NOTES";
    public static final String COLUMN_MEDIA_PATH = "MEDIA_PATH";
    public static final String COLUMN_CASTLE_ID = "DIARY_CASTLE_ID";
    public static final String COLUMN_RATING = "RATING";
    public static final String COLUMN_PHOTO_REFERENCE = "PHOTO_REFERENCE";

    public DataBaseHelper(@Nullable Context context) {
        super(context, "oldCastle.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        //String createTableStatement = "CREATE TABLE " + LIKED_CASTLE_TABLE + " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUMN_CASTLE_NAME + " TEXT, " + COLUMN_COUNTRY + " TEXT, " + COLUMN_REGION + " TEXT, " + COLUMN_WEBSITE + " TEXT, " + COLUMN_ISLIKED + " BOOLEAN)";

        //String createCastleTable = "CREATE TABLE " + LIKED_CASTLE_TABLE + " (" + COLUMN_ID + " STRING PRIMARY KEY, " + COLUMN_CASTLE_NAME + " TEXT, " + COLUMN_CASTLE_ADDRESS + " TEXT, " + COLUMN_CASTLE_WEBSITE + " TEXT, " + COLUMN_IS_LIKED + " BOOLEAN)";
        String createCastleTable = "CREATE TABLE " + LIKED_CASTLE_TABLE + " (" + COLUMN_ID + " STRING PRIMARY KEY, " + COLUMN_CASTLE_NAME + " TEXT, " + COLUMN_CASTLE_ADDRESS + " TEXT, " + COLUMN_CASTLE_WEBSITE + " TEXT, " + COLUMN_IS_LIKED + " INTEGER, " + COLUMN_RATING + " DOUBLE, " + COLUMN_PHOTO_REFERENCE + " TEXT)";

        //String createDiaryTable = "CREATE TABLE " + DIARY_ENTRY_TABLE + " (" + COLUMN_DIARY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUMN_ENTRY_DATE + " DATE, " + COLUMN_NOTES + " TEXT, " + COLUMN_MEDIA_PATH + " TEXT, " + COLUMN_CASTLE_ID + " INTEGER, " + "FOREIGN KEY (" + COLUMN_CASTLE_ID + ") REFERENCES " + LIKED_CASTLE_TABLE + " (" +COLUMN_ID+"));";
        //String createCalendarTable = "CREATE TABLE CALENDAR_TABLE (ID)";



        //sqLiteDatabase.execSQL(createDiaryTable);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
