package com.example.oldcastellovers.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.oldcastellovers.model.CastleModel;
import com.example.oldcastellovers.models.DiaryEntryModel;
import com.example.oldcastellovers.models.LikedCastleModel;

import java.util.ArrayList;
import java.util.List;

public class DataBaseHelper extends SQLiteOpenHelper {

    public static final String LIKED_CASTLE_TABLE = "LIKED_CASTLE_TABLE";
    public static final String COLUMN_CASTLE_NAME = "CASTLE_NAME";
    public static final String COLUMN_CASTLE_ADDRESS = "CASTLE_ADDRESS";
    public static final String COLUMN_CASTLE_RATING = "CASTLE_RATING";
    public static final String COLUMN_CASTLE_PICTURE = "CASTLE_PICTURE";
    public static final String COLUMN_ID = "ID";
    public static final String DIARY_ENTRY_TABLE = "DIARY_ENTRY_TABLE";
    public static final String COLUMN_DIARY_ENTRY_DATE = "DIARY_ENTRY_DATE";
    public static final String COLUMN_ENTRY_ID = "ENTRY_ID";
    public static final String COLUMN_DIARY_CASTLE_NAME = "DIARY_CASTLE_NAME";
    public static final String COLUMN_DIARY_CASTLE_LOCATION = "DIARY_CASTLE_LOCATION";
    public static final String COLUMN_DIARY_CASTLE_WEBSITE = "DIARY_CASTLE_WEBSITE";
    public static final String COLUMN_DIARY_NOTES = "DIARY_NOTES";
    public static final String COLUMN_DIARY_MEDIAPATH = "DIARY_MEDIAPATH";

    public DataBaseHelper(@Nullable Context context) {
        super(context, "oldCastle.db", null, 2);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String createCastleTable = "CREATE TABLE " + LIKED_CASTLE_TABLE + " (" + COLUMN_ID + " TEXT PRIMARY KEY, " + COLUMN_CASTLE_NAME + " TEXT, " + COLUMN_CASTLE_ADDRESS + " TEXT, " + COLUMN_CASTLE_RATING + " INTEGER, " + COLUMN_CASTLE_PICTURE + " STRING)";
        //String createEntryTable = "CREATE TABLE " + DIARY_ENTRY_TABLE + " (" + COLUMN_ENTRY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUMN_DIARY_ENTRY_DATE + " TEXT, " + COLUMN_DIARY_CASTLE_NAME + " TEXT, " + COLUMN_DIARY_CASTLE_LOCATION + " TEXT, " + COLUMN_DIARY_CASTLE_WEBSITE + " TEXT, " + COLUMN_DIARY_NOTES + " TEXT, " + COLUMN_DIARY_MEDIAPATH + " TEXT)";

        sqLiteDatabase.execSQL(createCastleTable);
        //sqLiteDatabase.execSQL(createEntryTable);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {

        if (oldVersion < 2) {
            String createEntryTable = "CREATE TABLE " + DIARY_ENTRY_TABLE + " (" + COLUMN_ENTRY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUMN_DIARY_ENTRY_DATE + " TEXT, " + COLUMN_DIARY_CASTLE_NAME + " TEXT, " + COLUMN_DIARY_CASTLE_LOCATION + " TEXT, " + COLUMN_DIARY_CASTLE_WEBSITE + " TEXT, " + COLUMN_DIARY_NOTES + " TEXT, " + COLUMN_DIARY_MEDIAPATH + " TEXT)";
            sqLiteDatabase.execSQL(createEntryTable);

            oldVersion = 2;
        }

        sqLiteDatabase.setVersion(newVersion);
    }

    public boolean addOne(LikedCastleModel likedCastleModel){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_ID, likedCastleModel.getPlaceId());
        cv.put(COLUMN_CASTLE_NAME, likedCastleModel.getCastleName());
        cv.put(COLUMN_CASTLE_ADDRESS, likedCastleModel.getAddress());
        cv.put(COLUMN_CASTLE_RATING, likedCastleModel.getRating());
        cv.put(COLUMN_CASTLE_PICTURE,likedCastleModel.getPhotoReference());

        long insert = db.insert(LIKED_CASTLE_TABLE,null,cv);
        if (insert == -1) {
            return false;
        }else {
            return true;
        }
    }

    public boolean deleteOne(LikedCastleModel likedCastleModel){
        SQLiteDatabase db = this.getWritableDatabase();
        String whereClause = COLUMN_ID + " = ?";
        String[] whereArgs = {likedCastleModel.getPlaceId()};

        // Use the delete method to remove the row
        int deletedRows = db.delete(LIKED_CASTLE_TABLE, whereClause, whereArgs);

        // Check if the deletion was successful
        return deletedRows > 0;
    }

    public List<LikedCastleModel> getAll(){
        List<LikedCastleModel> returnList = new ArrayList<>();

        String queryString = "SELECT * FROM " + LIKED_CASTLE_TABLE;
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery(queryString, null);

        if (cursor.moveToFirst()){
            do{
                String placeId = cursor.getString(0);
                String castleName = cursor.getString(1);
                String castleAddress = cursor.getString(2);
                Double castleRating = cursor.getDouble(3);
                String castlePhotoReference = cursor.getString(4);

                LikedCastleModel newlikedCastleModel = new LikedCastleModel(placeId, castleName,castleAddress, castleRating, castlePhotoReference);
                returnList.add(newlikedCastleModel);
            }while (cursor.moveToNext());
        }else {
            //Empty list
        }

        cursor.close();
        db.close();
        return returnList;
    }

    public boolean addDiaryEntry(DiaryEntryModel diaryEntryModel){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_DIARY_ENTRY_DATE, diaryEntryModel.getDate());
        cv.put(COLUMN_DIARY_CASTLE_NAME, diaryEntryModel.getCastleName());
        cv.put(COLUMN_DIARY_CASTLE_LOCATION, diaryEntryModel.getCastleLocation());
        cv.put(COLUMN_DIARY_CASTLE_WEBSITE, diaryEntryModel.getWebsite());
        cv.put(COLUMN_DIARY_NOTES, diaryEntryModel.getNotes());
        cv.put(COLUMN_DIARY_MEDIAPATH, diaryEntryModel.getMediaPath());

        long insert = db.insert(DIARY_ENTRY_TABLE, null, cv);
        if (insert == -1) {
            return false;
        }else {
            return true;
        }
    }
}