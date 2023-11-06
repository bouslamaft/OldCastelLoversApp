package com.example.oldcastellovers.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DataBaseHelper extends SQLiteOpenHelper {

    public static final String LIKED_CASTLE_TABLE = "LIKED_CASTLE_TABLE";
    public static final String COLUMN_CASTLE_NAME = "CASTLE_NAME";
    public static final String COLUMN_COUNTRY = "COUNTRY";
    public static final String COLUMN_REGION = "REGION";
    public static final String COLUMN_WEBSITE = "WEBSITE";
    public static final String COLUMN_ISLIKED = "ISLIKED";
    public static final String COLUMN_ID = "ID";

    public DataBaseHelper(@Nullable Context context) {
        super(context, "oldCastle.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String createTableStatement = "CREATE TABLE " + LIKED_CASTLE_TABLE + " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUMN_CASTLE_NAME + " TEXT, " + COLUMN_COUNTRY + " TEXT, " + COLUMN_REGION + " TEXT, " + COLUMN_WEBSITE + " TEXT, " + COLUMN_ISLIKED + " BOOLEAN)";

        sqLiteDatabase.execSQL(createTableStatement);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
