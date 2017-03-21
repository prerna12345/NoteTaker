package com.example.prerna.notetaker;

import java.util.ArrayList;
import java.util.List;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHandler extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 2;
    private static final String DATABASE_NAME = "Notetaker2123";
    private static final String TABLE_NAME = "notes123";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_DESCRIPTION= "desc";

    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {

        String CREATE_ITEM_TABLE = "CREATE TABLE " + TABLE_NAME + "(" + COLUMN_ID + " INTEGER PRIMARY KEY," + COLUMN_DESCRIPTION + "  TEXT)";
        db.execSQL(CREATE_ITEM_TABLE);
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);


        onCreate(db);
    }


    public void insertLabel(String label){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_DESCRIPTION, label);


        db.insert(TABLE_NAME, null, values);
        db.close();
    }


    public  ArrayList<String> getAllLabels(){
        ArrayList<String> list = new ArrayList<String>();

        String selectQuery = "SELECT  * FROM " + TABLE_NAME;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                list.add(cursor.getString(1));
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();

        return list;
    }


    public void deleteNote(String notes){
SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME,COLUMN_DESCRIPTION+ " = ?", new String[] { notes });
    }
}
