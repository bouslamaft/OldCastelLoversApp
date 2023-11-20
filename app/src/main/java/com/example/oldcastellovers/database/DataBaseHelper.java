package com.example.oldcastellovers.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.oldcastellovers.model.CastleModel;

import java.util.ArrayList;
import java.util.List;

public class DataBaseHelper extends SQLiteOpenHelper {


    public static final String LIKED_CASTLE_TABLE = "LIKED_CASTLE_TABLE";
    public static final String COLUMN_CASTLE_NAME = "CASTLE_NAME";
    public static final String COLUMN_CASTLE_ADDRESS = "CASTLE_ADDRESS";
    public static final String COLUMN_CASTLE_RATING = "CASTLE_RATING";
    public static final String COLUMN_CASTLE_PICTURE = "CASTLE_PICTURE";
    public static final String COLUMN_ID = "ID";

    public DataBaseHelper(@Nullable Context context) {
        super(context, "oldCastle.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        //String createTableStatement = "CREATE TABLE " + LIKED_CASTLE_TABLE + " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUMN_CASTLE_NAME + " TEXT, " + COLUMN_COUNTRY + " TEXT, " + COLUMN_REGION + " TEXT, " + COLUMN_WEBSITE + " TEXT, " + COLUMN_ISLIKED + " BOOLEAN)";

        //String createCastleTable = "CREATE TABLE " + LIKED_CASTLE_TABLE + " (" + COLUMN_ID + " STRING PRIMARY KEY, " + COLUMN_CASTLE_NAME + " TEXT, " + COLUMN_CASTLE_ADDRESS + " TEXT, " + COLUMN_CASTLE_WEBSITE + " TEXT, " + COLUMN_IS_LIKED + " BOOLEAN)";
        //String createCastleTable = "CREATE TABLE " + LIKED_CASTLE_TABLE + " (" + COLUMN_ID + " STRING PRIMARY KEY, " + COLUMN_CASTLE_NAME + " TEXT, " + COLUMN_CASTLE_ADDRESS + " TEXT, " + COLUMN_CASTLE_WEBSITE + " TEXT, " + COLUMN_IS_LIKED + " INTEGER, " + COLUMN_RATING + " DOUBLE, " + COLUMN_PHOTO_REFERENCE + " TEXT)";

        //String createDiaryTable = "CREATE TABLE " + DIARY_ENTRY_TABLE + " (" + COLUMN_DIARY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUMN_ENTRY_DATE + " DATE, " + COLUMN_NOTES + " TEXT, " + COLUMN_MEDIA_PATH + " TEXT, " + COLUMN_CASTLE_ID + " INTEGER, " + "FOREIGN KEY (" + COLUMN_CASTLE_ID + ") REFERENCES " + LIKED_CASTLE_TABLE + " (" +COLUMN_ID+"));";
        //String createCalendarTable = "CREATE TABLE CALENDAR_TABLE (ID)";
        String createCastleTable = "CREATE TABLE " + LIKED_CASTLE_TABLE + " (" + COLUMN_ID + " TEXT PRIMARY KEY, " + COLUMN_CASTLE_NAME + " TEXT, " + COLUMN_CASTLE_ADDRESS + " TEXT, " + COLUMN_CASTLE_RATING + " INTEGER, " + COLUMN_CASTLE_PICTURE + " STRING)";



        sqLiteDatabase.execSQL(createCastleTable);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public boolean addOne(CastleModel castleModel){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_ID, castleModel.getPlaceId());
        cv.put(COLUMN_CASTLE_NAME, castleModel.getName());
        cv.put(COLUMN_CASTLE_ADDRESS, castleModel.getFormattedAddress());
        cv.put(COLUMN_CASTLE_RATING, castleModel.getRating());

        long insert = db.insert(LIKED_CASTLE_TABLE,null,cv);
        if (insert == -1) {
            return false;
        }else {
            return true;
        }
    }

    public List<CastleModel> getAll(){
        List<CastleModel> returnList = new ArrayList<>();

        String queryString = "SELECT * FROM " + LIKED_CASTLE_TABLE;
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery(queryString, null);

        if (cursor.moveToFirst()){
            do{
                String Castle_ID = cursor.getString(0);
                String Castle_Name = cursor.getString(1);
                String Castle_Address = cursor.getString(2);
                Double Castle_Rating = cursor.getDouble(3);

                CastleModel newCastleModel = new CastleModel(Castle_ID, Castle_Name, Castle_Address, Castle_Rating, null);
                returnList.add(newCastleModel);
            }while (cursor.moveToNext());
        }else {
            //Empty list
        }

        cursor.close();
        db.close();
        return returnList;
    }
}
