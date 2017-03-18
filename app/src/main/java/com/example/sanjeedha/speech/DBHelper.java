package com.example.sanjeedha.speech;

/**
 * Created by sanjeedha on 11/14/16.
 */

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.Cursor;
import android.content.Context;
import android.content.ContentValues;

public class DBHelper extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 3;
    private static final String DATABASE_NAME = "buildings.db";
    public static final String TABLE_BUILDINGS = "buildings";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_BUILDINGNAME = "buildingname";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + TABLE_BUILDINGS + "(" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_BUILDINGNAME + " TEXT," +
                "CONSTRAINT building_unique UNIQUE (" + COLUMN_BUILDINGNAME + ")" +
                ");";

        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_BUILDINGS);
        onCreate(db);
    }

    // Add a new row to the database
    public void addBuilding(Buildings building){
        ContentValues values = new ContentValues();
        values.put(COLUMN_BUILDINGNAME, building.get_buildingname());
        SQLiteDatabase db = this.getWritableDatabase();
        db.insertWithOnConflict(TABLE_BUILDINGS, null, values, 4);
        db.close();
    }

    // Delete a building from the database
    public void deleteBuilding(String buildingName){
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("DELETE FROM " + TABLE_BUILDINGS + " WHERE " + COLUMN_BUILDINGNAME + "=\"" + buildingName + "\";");
    }

    // this is going in record_TextView in the Main activity.
    public String databaseToString1() {
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_BUILDINGS;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        String dbString = "";

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                if (cursor.getString(cursor.getColumnIndex("buildingname")) != null) {
                    dbString += cursor.getString(cursor.getColumnIndex("buildingname"));
                    dbString += "\n";
                }
            } while (cursor.moveToNext());
        }

        // return contact list
        return dbString;
    }

    public boolean checkIfBuildingExists(String buildingName) {
        if (buildingName.isEmpty()) {
            return false;
        }
        SQLiteDatabase db = this.getReadableDatabase();
        String Query = "Select * from " + TABLE_BUILDINGS + " where " + COLUMN_BUILDINGNAME + " = '" + buildingName + "'";
        Cursor cursor = db.rawQuery(Query, null);
        if(cursor.getCount() <= 0){
            cursor.close();
            return false;
        }
        cursor.close();
        return true;
    }
}
