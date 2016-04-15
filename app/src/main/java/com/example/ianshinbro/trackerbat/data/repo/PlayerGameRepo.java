package com.example.ianshinbro.trackerbat.data.repo;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;

import com.example.ianshinbro.trackerbat.data.DatabaseManager;
import com.example.ianshinbro.trackerbat.data.model.Game;
import com.example.ianshinbro.trackerbat.data.model.Player;
import com.example.ianshinbro.trackerbat.data.model.PlayerGame;


/**
 * Created by ianshinbro on 4/12/2016.
 */
public class PlayerGameRepo {

    private final String TAG =PlayerGameRepo.class.getSimpleName();


    private PlayerGame playerGame;

    public PlayerGameRepo() {
        playerGame = new PlayerGame();

    }

    public static String createTable() {
        return "CREATE TABLE " + PlayerGame.TABLE_NAME + "("
                + PlayerGame.COLUMN_RELATIONID + " INTEGER PRIMARY KEY,"
                + PlayerGame.COLUMN_PLAYERID + " INTEGER,"
                + PlayerGame.COLUMN_GAMEID + " INTEGER, "
                + "FOREIGN KEY (" + PlayerGame.COLUMN_PLAYERID + ")" + "REFERENCES " + Player.TABLE_NAME + "(" + Player.COLUMN_PLAYERID + ") ON DELETE CASCADE "
                + "FOREIGN KEY (" + Game.COLUMN_GAMEID + " )" + "REFERENCES " + Game.TABLE_NAME + " (" + Game.COLUMN_GAMEID + ") ON DELETE CASCADE "
                + ")";
    }

    public void insert(PlayerGame playerGame) {
        SQLiteDatabase db = DatabaseManager.getInstance().openDatabase();
        ContentValues values = new ContentValues();
        values.put(playerGame.COLUMN_PLAYERID, playerGame.getPlayerId());
        values.put(playerGame.COLUMN_GAMEID, playerGame.getGameId());
        // Inserting Row
        db.insert(playerGame.TABLE_NAME, null, values);
        DatabaseManager.getInstance().closeDatabase();
    }
    public void delete( ) {
        SQLiteDatabase db = DatabaseManager.getInstance().openDatabase();
        db.delete(playerGame.TABLE_NAME, null, null);
        DatabaseManager.getInstance().closeDatabase();
    }

}
