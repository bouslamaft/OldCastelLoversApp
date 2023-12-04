package com.example.oldcastellovers.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import com.example.oldcastellovers.database.models.DiaryEntryModel;
import com.example.oldcastellovers.database.models.LikedCastleModel;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
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
        super(context, "oldCastle.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String createCastleTable = "CREATE TABLE " + LIKED_CASTLE_TABLE + " (" + COLUMN_ID + " TEXT PRIMARY KEY, " + COLUMN_CASTLE_NAME + " TEXT, " + COLUMN_CASTLE_ADDRESS + " TEXT, " + COLUMN_CASTLE_RATING + " INTEGER, " + COLUMN_CASTLE_PICTURE + " STRING)";
        //String createEntryTable = "CREATE TABLE " + DIARY_ENTRY_TABLE + " (" + COLUMN_ENTRY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUMN_DIARY_ENTRY_DATE + " TEXT, " + COLUMN_DIARY_CASTLE_NAME + " TEXT, " + COLUMN_DIARY_CASTLE_LOCATION + " TEXT, " + COLUMN_DIARY_CASTLE_WEBSITE + " TEXT, " + COLUMN_DIARY_NOTES + " TEXT, " + COLUMN_DIARY_MEDIAPATH + " TEXT)";
        String createEntryTable = "CREATE TABLE " + DIARY_ENTRY_TABLE + " (" + COLUMN_ENTRY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUMN_DIARY_ENTRY_DATE + " TEXT, " + COLUMN_DIARY_CASTLE_NAME + " TEXT, " + COLUMN_DIARY_CASTLE_LOCATION + " TEXT, " + COLUMN_DIARY_CASTLE_WEBSITE + " TEXT, " + COLUMN_DIARY_NOTES + " TEXT, " + COLUMN_DIARY_MEDIAPATH + " TEXT)";

        sqLiteDatabase.execSQL(createCastleTable);
        //sqLiteDatabase.execSQL(createEntryTable);
        sqLiteDatabase.execSQL(createEntryTable);


    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {

    }

    public boolean addOne(LikedCastleModel likedCastleModel) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_ID, likedCastleModel.getPlaceId());
        cv.put(COLUMN_CASTLE_NAME, likedCastleModel.getCastleName());
        cv.put(COLUMN_CASTLE_ADDRESS, likedCastleModel.getAddress());
        cv.put(COLUMN_CASTLE_RATING, likedCastleModel.getRating());
        cv.put(COLUMN_CASTLE_PICTURE, likedCastleModel.getPhotoReference());

        long insert = db.insert(LIKED_CASTLE_TABLE, null, cv);
        if (insert == -1) {
            return false;
        } else {
            return true;
        }
    }

    public boolean deleteOne(LikedCastleModel likedCastleModel) {
        SQLiteDatabase db = this.getWritableDatabase();
        String whereClause = COLUMN_ID + " = ?";
        String[] whereArgs = {likedCastleModel.getPlaceId()};

        // Use the delete method to remove the row
        int deletedRows = db.delete(LIKED_CASTLE_TABLE, whereClause, whereArgs);

        // Check if the deletion was successful
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
                Double castleRating = cursor.getDouble(3);
                String castlePhotoReference = cursor.getString(4);

                LikedCastleModel newlikedCastleModel = new LikedCastleModel(placeId, castleName, castleAddress, castleRating, castlePhotoReference);
                returnList.add(newlikedCastleModel);
            } while (cursor.moveToNext());
        } else {
            //Empty list
        }

        cursor.close();
        db.close();
        return returnList;
    }

    public boolean addDiaryEntry(DiaryEntryModel diaryEntryModel) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_DIARY_ENTRY_DATE, diaryEntryModel.getDate());
        cv.put(COLUMN_DIARY_CASTLE_NAME, diaryEntryModel.getCastleName());
        cv.put(COLUMN_DIARY_CASTLE_LOCATION, diaryEntryModel.getCastleLocation());
        cv.put(COLUMN_DIARY_CASTLE_WEBSITE, diaryEntryModel.getWebsite());
        cv.put(COLUMN_DIARY_NOTES, diaryEntryModel.getNotes());
        cv.put(COLUMN_DIARY_MEDIAPATH, String.join(",", diaryEntryModel.getMediaPath()));

        long insert = db.insert(DIARY_ENTRY_TABLE, null, cv);
        if (insert == -1) {
            return false;
        } else {
            return true;
        }
    }

    public List<DiaryEntryModel> getCastleDetailsWithMediaPath() {
        List<DiaryEntryModel> returnList = new ArrayList<>();

        SQLiteDatabase db = getReadableDatabase();

        // Define the columns you want to retrieve
        String[] columns = {
                COLUMN_ENTRY_ID,
                COLUMN_DIARY_CASTLE_NAME,
                COLUMN_DIARY_CASTLE_LOCATION,
                COLUMN_DIARY_ENTRY_DATE,
                COLUMN_DIARY_MEDIAPATH
        };

        Cursor cursor = db.query(
                DIARY_ENTRY_TABLE,
                columns,
                null,
                null,
                null,
                null,
                null
        );

        if (cursor.moveToFirst()) {
            do {
                int entryId = cursor.getInt(0);
                String castleName = cursor.getString(1);
                String castleLocation = cursor.getString(2);
                String entryDate = cursor.getString(3);
                String mediaPathString = cursor.getString(4);
                Log.d("abc", "castlename :"+castleName+" loc "+castleLocation+"entryDate"+entryDate+"mediaPathString"+mediaPathString);

                ArrayList<String> mediaPath = new ArrayList<>(Arrays.asList(mediaPathString.split(",")));
                Iterator<String> iterator = mediaPath.iterator();
                while (iterator.hasNext()) {
                    String path = iterator.next();
                    if (path.endsWith(".mp4")) {
                        iterator.remove();
                    }
                }
                // Keep only the first element, remove others
                if (mediaPath.size() > 1) {
                    mediaPath.subList(1, mediaPath.size()).clear();

                } else if (mediaPath.size()==0) {
                    mediaPath.add(" ");
                }
                for (int i = 0; i < mediaPath.size(); i++) {
                    Log.d("print list",mediaPath.get(i));
                }
                DiaryEntryModel entryDetails = new DiaryEntryModel(entryId, entryDate, castleName, castleLocation, null, null, mediaPath);
                returnList.add(entryDetails);
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return returnList;
    }

    public DiaryEntryModel getDiaryEntryDetails(int entryId) {
        SQLiteDatabase db = getReadableDatabase();


        // Define the columns you want to retrieve
        String[] columns = {
                COLUMN_DIARY_ENTRY_DATE,
                COLUMN_DIARY_CASTLE_NAME,
                COLUMN_DIARY_CASTLE_LOCATION,
                COLUMN_DIARY_CASTLE_WEBSITE,
                COLUMN_DIARY_NOTES,
                COLUMN_DIARY_MEDIAPATH
        };

        // Specify the selection criteria
        String selection = COLUMN_ENTRY_ID + " = ?";
        String[] selectionArgs = {String.valueOf(entryId)};

        Cursor cursor = db.query(
                DIARY_ENTRY_TABLE,
                columns,
                selection,
                selectionArgs,
                null,
                null,
                null
        );

        DiaryEntryModel diaryEntryDetails = null;

        // Add the elements to the ArrayList
        if (cursor.moveToFirst()) {
            // Retrieve values from the cursor
            String entryDate = cursor.getString(0);
            String castleName = cursor.getString(1);
            String castleLocation = cursor.getString(2);
            String castleWebsite = cursor.getString(3);
            String notes = cursor.getString(4);
            String mediaPathString = cursor.getString(5);

            ArrayList<String> mediapath = new ArrayList<>(Arrays.asList(mediaPathString.split(",")));
            // Add the media path string to the list
            addStringToList(mediaPathString, mediapath);





            // Create a DiaryEntryModel object
            diaryEntryDetails = new DiaryEntryModel(entryId, entryDate, castleName, castleLocation, castleWebsite, notes, mediapath);
        }

        cursor.close();
        db.close();

        return diaryEntryDetails;
    }

    private static void addStringToList(String inputString, List<String> list) {
        // Split the input string using commas as separators
        String[] items = inputString.split(",");

        // Add each item to the list
        list.addAll(Arrays.asList(items));
    }


}