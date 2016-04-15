package com.example.ianshinbro.trackerbat.data.repo;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.ianshinbro.trackerbat.data.DatabaseManager;
import com.example.ianshinbro.trackerbat.data.model.Game;
import com.example.ianshinbro.trackerbat.data.model.PlayerGame;

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

    public static final String createTable() {
        return "CREATE TABLE "
                + Game.TABLE_NAME + "("
                + Game.COLUMN_GAMEID + " INTEGER PRIMARY KEY,"
                + Game.COLUMN_NUMOFINNINGS + " INTEGER,"
                + Game.COLUMN_HOMETEAM + " TEXT,"
                + Game.COLUMN_AWAYTEAM + " TEXT,"
                + Game.COLUMN_HOMESCORE + " INTEGER,"
                + Game.COLUMN_AWAYSCORE + " INTEGER" + ")";
    }
    public int insert(Game game, int playerId) {
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
        // create a player game object
        insertPlayerGame(db, playerId);

        db.insert(Game.TABLE_NAME, null, values);
        DatabaseManager.getInstance().closeDatabase();

        return gameId;
    }

    /**
     * This function creates the player g ame relation in the database
     * @param db - database object
     * @param playerId - player id
     */
    private void insertPlayerGame(SQLiteDatabase db, int playerId) {
        PlayerGame playerGame = new PlayerGame();
        playerGame.setGameId(game.getID());
        playerGame.setPlayerId(playerId);
        ContentValues values = new ContentValues();
        values.put(PlayerGame.COLUMN_GAMEID, playerGame.getGameId());
        values.put(PlayerGame.COLUMN_PLAYERID,playerGame.getPlayerId());

        db.insert(playerGame.TABLE_NAME,null,values);
    }
    public void remove(int id) {
        SQLiteDatabase db = DatabaseManager.getInstance().openDatabase();
        Log.d(TAG, "Remove player at "+ id);
        db.delete(Game.TABLE_NAME, Game.COLUMN_GAMEID + " = ?",
                new String[]{String.valueOf(id)});
        DatabaseManager.getInstance().closeDatabase();
    }
    public int updateGame(Game game) {
        SQLiteDatabase db = DatabaseManager.getInstance().openDatabase();
        ContentValues values = new ContentValues();
        values.put(Game.COLUMN_GAMEID, game.getID());
        values.put(Game.COLUMN_AWAYTEAM, game.getAwayTeam());
        values.put(Game.COLUMN_HOMETEAM, game.getHomeTeam());
        values.put(Game.COLUMN_HOMESCORE, game.getHomeScore());
        values.put(Game.COLUMN_AWAYSCORE, game.getAwayScore());
        values.put(Game.COLUMN_NUMOFINNINGS, game.getInningNumber());
// updating row
        return db.update(Game.TABLE_NAME, values, Game.COLUMN_GAMEID + " = ?",
                new String[]{String.valueOf(game.getID())});
    }

    /**
     * Thisi retrieves games by id from the player game table
     * @param id - id of the game
     * @return
     */
    public ArrayList<Game> getGamesById(int id) {
        String query = "SELECT  *" + " FROM " + PlayerGame.TABLE_NAME +
                " WHERE " + PlayerGame.COLUMN_PLAYERID + " = " + id;

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
