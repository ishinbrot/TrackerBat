package com.example.ianshinbro.trackerbat.data.repo;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;

import com.example.ianshinbro.trackerbat.data.DatabaseManager;
import com.example.ianshinbro.trackerbat.data.model.GameAtBat;

/**
 * Created by ianshinbro on 4/12/2016.
 */
public class GameAtBatRepo {


    private final String TAG = PlayerGameRepo.class.getSimpleName();

    public GameAtBatRepo() {

    }
    private GameAtBat gameAtBat;

    public static String createTable() {
        return "CREATE TABLE "+GameAtBat.TABLE_NAME + "("
                + GameAtBat.COLUMN_RELATIONID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + GameAtBat.COLUMN_GAMEID + " INTEGER,"
                + GameAtBat.COLUMN_ATBATID + " INTEGER )";
    }

    public void insert(GameAtBat gameAtBat) {
        SQLiteDatabase db = DatabaseManager.getInstance().openDatabase();
        ContentValues values = new ContentValues();
        values.put(gameAtBat.COLUMN_GAMEID, gameAtBat.getGameid_());
        values.put(gameAtBat.COLUMN_ATBATID, gameAtBat.getAtBatid());
        // Inserting Row
        db.insert(gameAtBat.TABLE_NAME, null, values);
        DatabaseManager.getInstance().closeDatabase();
    }
    public void delete( ) {
        SQLiteDatabase db = DatabaseManager.getInstance().openDatabase();
        db.delete(gameAtBat.TABLE_NAME, null, null);
        DatabaseManager.getInstance().closeDatabase();
    }
}
