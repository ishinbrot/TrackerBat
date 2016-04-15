package com.example.ianshinbro.trackerbat.data;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.ianshinbro.trackerbat.data.repo.AtBatRepo;
import com.example.ianshinbro.trackerbat.data.repo.GameAtBatRepo;
import com.example.ianshinbro.trackerbat.data.repo.GameRepo;
import com.example.ianshinbro.trackerbat.data.repo.PlayerGameRepo;
import com.example.ianshinbro.trackerbat.data.repo.PlayerRepo;
import com.example.ianshinbro.trackerbat.data.model.AtBat;
import com.example.ianshinbro.trackerbat.data.model.Game;
import com.example.ianshinbro.trackerbat.data.model.GameAtBat;
import com.example.ianshinbro.trackerbat.data.model.Player;
import com.example.ianshinbro.trackerbat.data.model.PlayerGame;
import com.example.ianshinbro.trackerbat.app.App;

/**
 * Created by ianshinbro on 4/12/2016.
 */
public class DatabaseHelper extends SQLiteOpenHelper {

    //version number to upgrade database version
    //each time if you Add, Edit table, you need to change the
    //version number.
    private static final int DATABASE_VERSION =2;
    // Database Name
    public static final String DATABASE_NAME = "trackerBat.db";
    private static final String TAG = DatabaseHelper.class.getSimpleName().toString();

    public DatabaseHelper( ) {
        super(App.getContext(), DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onOpen(SQLiteDatabase db) {

        super.onOpen(db);
        if (!db.isReadOnly()) {
            //Enable foreign key constraints
            db.setForeignKeyConstraintsEnabled(true);
        }
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        //All necessary tables you like to create will create here
        db.execSQL(PlayerRepo.createTable());
        db.execSQL(GameRepo.createTable());
        db.execSQL(PlayerGameRepo.createTable());
        db.execSQL(AtBatRepo.createTable());
        db.execSQL(GameAtBatRepo.createTable());
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.d(TAG, String.format("SQLiteDatabase.onUpgrade(%d -> %d)", oldVersion, newVersion));

        // Drop table if existed, all data will be gone!!!
        db.execSQL("DROP TABLE IF EXISTS " + Player.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + Game.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + AtBat.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + PlayerGame.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + GameAtBat.TABLE_NAME);

        onCreate(db);
    }

}
