package com.example.ianshinbro.trackerbat.sqlLite;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.ianshinbro.trackerbat.Implentation.AtBat;
import com.example.ianshinbro.trackerbat.Implentation.Game;
import com.example.ianshinbro.trackerbat.Implentation.Player;


/**
 * Created by iansh on 12/29/2016.
 */

public class ExampleDBHelper extends SQLiteOpenHelper {


    public static final String DATABASE_NAME = "AtBat.db";
    private static final String TAG = ExampleDBHelper.class.getSimpleName().toString();
    private static final int DATABASE_VERSION = 1;


@Override
public void onCreate(SQLiteDatabase db) {
    db.execSQL(PlayerRepo.createTable());
    db.execSQL(AtBatRepo.createTable());
    db.execSQL(GameRepo.createTable());
    db.execSQL(PlayerGameRepo.createTable());
}

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        Log.d(TAG, String.format("SQLLiteDatase.onUpgrade(%d -> %d)", oldVersion, newVersion));
        // Drop table if existed, all data will be gone!!!
        db.execSQL("DROP TABLE IF EXISTS " + Player.TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + Game.TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + AtBat.TABLE);
        onCreate(db);
    }
}

