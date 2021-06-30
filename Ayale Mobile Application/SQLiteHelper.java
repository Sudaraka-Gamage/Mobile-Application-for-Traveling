package com.example.savindu.ayalenew1;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;

public class SQLiteHelper extends SQLiteOpenHelper{

    SQLiteHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version){

        super( context, name, factory, version);
    }

    public void queryData(String sql){

        SQLiteDatabase database = getWritableDatabase();
        database.execSQL(sql);
    }

    //insert data
    public void insertData(String amount, String category, String description ){

        SQLiteDatabase database = getWritableDatabase();

        String sql = "INSERT INTO expenses VALUES( NULL, ?,?,? )";

        SQLiteStatement statement = database.compileStatement(sql);
        statement.clearBindings();

        statement.bindString(1, amount);
        statement.bindString(2, category);
        statement.bindString(3, description);

        statement.executeInsert();
    }

    //update data
    public void updateData(String amount, String category, String description, int id ){

        SQLiteDatabase database = getWritableDatabase();

        String sql = "UPDATE expenses SET amount=?, category=?, description=? WHERE id=?";

        SQLiteStatement statement = database.compileStatement(sql);
        statement.clearBindings();

        statement.bindString(1, amount);
        statement.bindString(2, category);
        statement.bindString(3, description);
        statement.bindDouble(4, (double)id);

        statement.execute();
        database.close();

    }

    //delete data
    public void deleteData(int id){

        SQLiteDatabase database = getWritableDatabase();

        String sql = "DELETE FROM expenses WHERE id=?";

        SQLiteStatement statement = database.compileStatement(sql);
        statement.clearBindings();

        statement.bindDouble(1, (double)id);

        statement.execute();
        database.close();
    }

    public Cursor getData( String sql ){

        SQLiteDatabase database = getWritableDatabase();
        return database.rawQuery(sql, null);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
