package com.example.ianshinbro.trackerbat.data.repo;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.ianshinbro.trackerbat.data.DatabaseManager;
import com.example.ianshinbro.trackerbat.data.model.Game;

import java.util.ArrayList;

/**
 * Created by ianshinbro on 4/12/2016.
 */
public class GameRepo {

    private final String TAG = GameRepo.class.getSimpleName();
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

    public ArrayList<Game> getGamesById(int id) {
        String query = "SELECT * FROM " + Game.TABLE_NAME;
        SQLiteDatabase db = DatabaseManager.getInstance().openDatabase();
        Cursor res = db.rawQuery(query, null);
        ArrayList<Game> list=new ArrayList<Game>();

        while(res.moveToNext()){

            Game row=new Game();
            row.setId(Integer.parseInt(res.getString(0)));
            row.setInningNumber(Integer.parseInt(res.getString(1)));
            row.setHomeTeam(res.getString(2));
                row.setAwayTeam(res.getString(3));
            row.setHomeScore(Integer.parseInt(res.getString(4)));
            row.setAwayScore(Integer.parseInt(res.getString(5)));
            Log.d(TAG, "Retrieving player at " + row.getID());
            list.add(row);
        }

        if (list.size()==0) {
            Log.d(TAG, "Empty list");
        }

        res.close();
        DatabaseManager.getInstance().closeDatabase();
        return list;

    }
    public void delete() {
        SQLiteDatabase db = DatabaseManager.getInstance().openDatabase();
        db.delete(Game.TABLE_NAME,null,null);
        DatabaseManager.getInstance().closeDatabase();
    }
}
