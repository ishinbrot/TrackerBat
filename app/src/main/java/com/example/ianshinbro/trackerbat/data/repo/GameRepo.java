package com.example.ianshinbro.trackerbat.data.repo;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;

import com.example.ianshinbro.trackerbat.data.DatabaseManager;
import com.example.ianshinbro.trackerbat.data.model.Game;

/**
 * Created by ianshinbro on 4/12/2016.
 */
public class GameRepo {


    private Game game;

    public GameRepo() {
        game = new Game();
    }

    public int insert(Game game) {
        int gameId;
        SQLiteDatabase db = DatabaseManager.getInstance().openDatabase();
        ContentValues values = new ContentValues();
        values.put(Game.COLUMN_GAMEID, game.getID());
        values.put(Game.COLUMN_AWAYTEAM, game.getAwayTeam());
        values.put(Game.COLUMN_HOMETEAM, game.getHomeTeam());
        values.put(Game.COLUMN_HOMESCORE, game.getHomeScore());
        values.put(Game.COLUMN_AWAYSCORE, game.getAwayScore());
        values.put(Game.COLUMN_NUMOFINNINGS, game.getInningNumber());
        // insert row
        gameId = (int) db.insert(game.TABLE_NAME, null, values);
        DatabaseManager.getInstance().closeDatabase();

        return gameId;
    }


    public static final String createTable() {
        return "CREATE TABLE "
                + Game.TABLE_NAME + "("
                + Game.COLUMN_GAMEID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + Game.COLUMN_NUMOFINNINGS + " INTEGER,"
                + Game.COLUMN_HOMETEAM + " TEXT,"
                + Game.COLUMN_AWAYTEAM + " TEXT,"
                + Game.COLUMN_HOMESCORE + " INTEGER,"
                + Game.COLUMN_AWAYSCORE + " INTEGER" + ")";
    }

    public void delete() {
        SQLiteDatabase db = DatabaseManager.getInstance().openDatabase();
        db.delete(Game.TABLE_NAME,null,null);
        DatabaseManager.getInstance().closeDatabase();
    }
}
