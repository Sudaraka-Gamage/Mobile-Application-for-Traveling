package com.example.asus_pc.myproject;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.text.Editable;


public class DataBaseHelper extends SQLiteOpenHelper {


    public static final String DATABASE_NAME = "destinationold.db";
    public static final String TABLE_NAME = "destinationold_table";
    public static final String COL_1 = "DES_ID";
    public static final String COL_2 = "DES_NAME";
    public static final String COL_3 = "DES_DETAILS";
    public static final String COL_4 = "DES_DATE";
    public static final String COL_5 = "DES_SUMMARY";



    public DataBaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
        SQLiteDatabase db = this.getWritableDatabase();


        
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(" create table "+ TABLE_NAME +"(ID INTEGER PRIMARY KEY AUTOINCREMENT, DES_NAME TEXT, DETAILS TEXT,  DATE TEXT, SUMMARY TEXT)");

    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);


    }


    public boolean insertData(String des_name, String details, String date, String summary){

    SQLiteDatabase db = this.getWritableDatabase();
    ContentValues contentValues = new ContentValues();
    contentValues.put(COL_2, des_name);
    contentValues.put(COL_3, details);
    contentValues.put(COL_4, date);
    contentValues.put(COL_5, summary);
    long result = db.insert(TABLE_NAME, null, contentValues);

    if (result == 1)
        return false;
    else
        return true;
    }

    public  boolean updateData(String id,String des_name,String details,String date,String summary){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_1,id);
        contentValues.put(COL_2,des_name);
        contentValues.put(COL_3,details);
        contentValues.put(COL_4,date);
        contentValues.put(COL_5,summary);
        db.update(TABLE_NAME,contentValues,"DES_ID = ? ",new String[]{id});

        return true;

    }

    public Cursor getAllData(){

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from " + TABLE_NAME, null);
        return res;

    }

    public  Integer deleteData(String id){

        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_NAME, "DES_ID = ?", new String[] {id});


    }




}
