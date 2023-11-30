package com.example.oldcastellovers.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.oldcastellovers.models.DiaryEntryModel;
import com.example.oldcastellovers.models.LikedCastleModel;

import java.util.ArrayList;
import java.util.List;

public class DataBaseHelper extends SQLiteOpenHelper {

    // Constants for table names and columns
    private static final String LIKED_CASTLE_TABLE = "LIKED_CASTLE_TABLE";
    private static final String COLUMN_ID = "ID";
    private static final String COLUMN_CASTLE_NAME = "CASTLE_NAME";
    private static final String COLUMN_CASTLE_ADDRESS = "CASTLE_ADDRESS";
    private static final String COLUMN_CASTLE_RATING = "CASTLE_RATING";
    private static final String COLUMN_CASTLE_PICTURE = "CASTLE_PICTURE";

    private static final String DIARY_ENTRY_TABLE = "DIARY_ENTRY_TABLE";
    private static final String COLUMN_ENTRY_ID = "ENTRY_ID";
    private static final String COLUMN_DIARY_ENTRY_DATE = "DIARY_ENTRY_DATE";
    private static final String COLUMN_DIARY_CASTLE_NAME = "DIARY_CASTLE_NAME";
    private static final String COLUMN_DIARY_CASTLE_LOCATION = "DIARY_CASTLE_LOCATION";
    private static final String COLUMN_DIARY_CASTLE_WEBSITE = "DIARY_CASTLE_WEBSITE";
    private static final String COLUMN_DIARY_NOTES = "DIARY_NOTES";
    private static final String COLUMN_DIARY_MEDIAPATH = "DIARY_MEDIAPATH";

    public DataBaseHelper(Context context) {
        super(context, "oldCastle.db", null, 2);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String createCastleTable = "CREATE TABLE " + LIKED_CASTLE_TABLE + " (" +
                COLUMN_ID + " TEXT PRIMARY KEY, " +
                COLUMN_CASTLE_NAME + " TEXT, " +
                COLUMN_CASTLE_ADDRESS + " TEXT, " +
                COLUMN_CASTLE_RATING + " INTEGER, " +
                COLUMN_CASTLE_PICTURE + " STRING)";
        sqLiteDatabase.execSQL(createCastleTable);

        String createEntryTable = "CREATE TABLE " + DIARY_ENTRY_TABLE + " (" +
                COLUMN_ENTRY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_DIARY_ENTRY_DATE + " TEXT, " +
                COLUMN_DIARY_CASTLE_NAME + " TEXT, " +
                COLUMN_DIARY_CASTLE_LOCATION + " TEXT, " +
                COLUMN_DIARY_CASTLE_WEBSITE + " TEXT, " +
                COLUMN_DIARY_NOTES + " TEXT, " +
                COLUMN_DIARY_MEDIAPATH + " TEXT)";
        sqLiteDatabase.execSQL(createEntryTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        // Handle database schema upgrades
    }

    // LikedCastleModel operations

    public boolean addOne(LikedCastleModel likedCastleModel) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_ID, likedCastleModel.getPlaceId());
        cv.put(COLUMN_CASTLE_NAME, likedCastleModel.getCastleName());
        cv.put(COLUMN_CASTLE_ADDRESS, likedCastleModel.getAddress());
        cv.put(COLUMN_CASTLE_RATING, likedCastleModel.getRating());
        cv.put(COLUMN_CASTLE_PICTURE, likedCastleModel.getPhotoReference());

        long insert = db.insert(LIKED_CASTLE_TABLE, null, cv);
        return insert != -1;
    }

    public boolean deleteOne(LikedCastleModel likedCastleModel) {
        SQLiteDatabase db = this.getWritableDatabase();
        String whereClause = COLUMN_ID + " = ?";
        String[] whereArgs = {likedCastleModel.getPlaceId()};

        int deletedRows = db.delete(LIKED_CASTLE_TABLE, whereClause, whereArgs);
        return deletedRows > 0;
    }

    public List<LikedCastleModel> getAll() {
        List<LikedCastleModel> returnList = new ArrayList<>();

        String queryString = "SELECT * FROM " + LIKED_CASTLE_TABLE;
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery(queryString, null);

        if (cursor.moveToFirst()) {
            do {
                String placeId = cursor.getString(0);
                String castleName = cursor.getString(1);
                String castleAddress = cursor.getString(2);
                double castleRating = cursor.getDouble(3);
                String castlePhotoReference = cursor.getString(4);

                LikedCastleModel newlikedCastleModel = new LikedCastleModel(placeId, castleName, castleAddress, castleRating, castlePhotoReference);
                returnList.add(newlikedCastleModel);
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return returnList;
    }

    // DiaryEntryModel operations

    public boolean addDiaryEntry(DiaryEntryModel diaryEntryModel) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_DIARY_ENTRY_DATE, diaryEntryModel.getDate());
        cv.put(COLUMN_DIARY_CASTLE_NAME, diaryEntryModel.getCastleName());
        cv.put(COLUMN_DIARY_CASTLE_LOCATION, diaryEntryModel.getCastleLocation());
        cv.put(COLUMN_DIARY_CASTLE_WEBSITE, diaryEntryModel.getWebsite());
        cv.put(COLUMN_DIARY_NOTES, diaryEntryModel.getNotes());
        cv.put(COLUMN_DIARY_MEDIAPATH, diaryEntryModel.getMediaPath());

        long insert = db.insert(DIARY_ENTRY_TABLE, null, cv);
        return insert != -1;
    }
}
