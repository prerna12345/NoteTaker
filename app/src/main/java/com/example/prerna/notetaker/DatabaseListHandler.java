package com.example.prerna.notetaker;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseListHandler extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "datainlist";
    private static final String TABLE_NAME = "listdata";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_CHECKBOX= "alwar";
    private static final String COLUMN_EDITTEXT= "jaipur";
    private static final String COLUMN_EDITTEXT_NUMBER= "list_number";

    public DatabaseListHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {

        String CREATE_ITEM_TABLE = "CREATE TABLE " + TABLE_NAME + "(" + COLUMN_ID + " INTEGER PRIMARY KEY ," + COLUMN_CHECKBOX + " INTEGER , "+ COLUMN_EDITTEXT_NUMBER + " INTEGER , "+ COLUMN_EDITTEXT + " TEXT"+ ")";
        db.execSQL(CREATE_ITEM_TABLE);
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);


        onCreate(db);
    }


    public void insertLabel(int set, String edit,int listid){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_CHECKBOX, set);//column name, column value
        values.put(COLUMN_EDITTEXT_NUMBER, listid);
        values.put(COLUMN_EDITTEXT, edit);

        db.insert(TABLE_NAME, null, values);
        db.close();
    }


    public Cursor getAllLabels(){


        String selectQuery = "SELECT  * FROM " + TABLE_NAME;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);





        return cursor;
    }



    }
