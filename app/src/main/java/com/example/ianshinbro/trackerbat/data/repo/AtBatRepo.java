package com.example.ianshinbro.trackerbat.data.repo;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;

import com.example.ianshinbro.trackerbat.data.DatabaseManager;
import com.example.ianshinbro.trackerbat.data.model.AtBat;

/**
 * Created by ianshinbro on 4/12/2016.
 */
public class AtBatRepo {

    private AtBat atBat;

    public AtBatRepo() {
        atBat = new AtBat();
    }

    public static final String createTable() {
        return "CREATE TABLE "
                + AtBat.TABLE_NAME + "("
                + AtBat.COLUMN_ATBATID + " INTEGER PRIMARY KEY,"
                + AtBat.COLUMN_INNINGNUMBER + " INTEGER,"
                + AtBat.COLUMN_BASESTATS + " TEXT)";
    }

    public int insert(AtBat atBat) {
        int atBatId;
        SQLiteDatabase db = DatabaseManager.getInstance().openDatabase();
        ContentValues values = new ContentValues();
        values.put(AtBat.COLUMN_INNINGNUMBER, atBat.getInningNumber());
        values.put(AtBat.COLUMN_BASESTATS, atBat.getBaseStats());
        // insert row
        atBatId = (int)db .insert(atBat.TABLE_NAME,null,values);
        DatabaseManager.getInstance().closeDatabase();

        return atBatId;
    }

    public void delete() {
        SQLiteDatabase db = DatabaseManager.getInstance().openDatabase();
        db.delete(AtBat.TABLE_NAME,null,null);
        DatabaseManager.getInstance().closeDatabase();
    }
}
