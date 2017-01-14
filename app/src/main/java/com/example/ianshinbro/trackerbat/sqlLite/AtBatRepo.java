package com.example.ianshinbro.trackerbat.sqlLite;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;

import com.example.ianshinbro.trackerbat.Implentation.AtBat;

/**
 * Created by iansh on 12/29/2016.
 */

public class AtBatRepo {

    private AtBat atBat;

    public AtBatRepo(){
        atBat = new AtBat();
    }
    public static String createTable(){
        return "CREATE TABLE" + AtBat.TABLE + "("
                + AtBat.KEY_AtBatID + "TEXT PRIMARY KEY,"
                + AtBat.KEY_InningNumber + "TEXT,"
                + AtBat.KEY_BaseStats + "TEXT)";
    }
    public int insert(AtBat atBat) {
        int atBatId;
        SQLiteDatabase db = DataBaseManager.getInstance().openDatabase();
        ContentValues values = new ContentValues();
        values.put(AtBat.KEY_AtBatID, atBat.getAtBatId());
        values.put(AtBat.KEY_BaseStats, atBat.getBaseStats());
        values.put(AtBat.KEY_InningNumber, atBat.getInningNumber());
        atBatId = (int) db.insert(AtBat.TABLE, null, values);
        DataBaseManager.getInstance().closeDatabase();
        return atBatId;
    }
    public void delete( ) {
        SQLiteDatabase db = DataBaseManager.getInstance().openDatabase();
        db.delete(AtBat.TABLE, null,null);
        DataBaseManager.getInstance().closeDatabase();
    }
}
