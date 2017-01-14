package com.example.ianshinbro.trackerbat.sqlLite;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.provider.ContactsContract;

import com.example.ianshinbro.trackerbat.Implentation.Game;

/**
 * Created by iansh on 12/29/2016.
 */

public class GameRepo {

    private Game game;

    public GameRepo(){
        game = new Game();
    }

    public static String createTable(){
        return "CREATE TABLE " + Game.TABLE + "("
                + Game.KEY_GameID + "TEXT PRIMARY KEY,"
                + Game.KEY_AwayTeam + "TEXT," +
                Game.KEY_HomeTeam + "TEXT," +
                Game.KEY_Innings + "INTEGER)";
    }
    public int insert(Game game) {
        int gameId;
        SQLiteDatabase db = DataBaseManager.getInstance().openDatabase();
        ContentValues values = new ContentValues();
        values.put(Game.KEY_AwayTeam, game.getAwayTeam());
        values.put(Game.KEY_HomeTeam, game.getHomeTeam());
        values.put(Game.KEY_Innings, game.getInningTotal());
        values.put(Game.KEY_Score, game.getScore());

        // Inserting Row
        gameId = (int) db.insert(Game.TABLE, null, values);
        DataBaseManager.getInstance().closeDatabase();
        return gameId;
    }
    public void delete( ) {
        SQLiteDatabase db = DataBaseManager.getInstance().openDatabase();
        db.delete(Game.TABLE, null,null);
        DataBaseManager.getInstance().closeDatabase();
    }
}
