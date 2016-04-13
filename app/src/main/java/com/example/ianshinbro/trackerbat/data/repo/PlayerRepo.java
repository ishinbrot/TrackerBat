package com.example.ianshinbro.trackerbat.data.repo;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;

import com.example.ianshinbro.trackerbat.data.DatabaseManager;
import com.example.ianshinbro.trackerbat.data.model.Player;

/**
 * Created by ianshinbro on 4/12/2016.
 */
public class PlayerRepo {

    private Player player;

    public PlayerRepo() {
        player = new Player();
    }

    public static final String createTable() {
        return "CREATE TABLE"
                + Player.TABLE_NAME + "("
                + Player.COLUMN_PLAYERID + "INTEGER PRIMARY KEY AUTOINCREMENT,"
                + Player.COLUMN_FIRST_NAME + "TEXT,"
                + Player.COLUMN_LAST_NAME + "TEXT,"
                + Player.COLUMN_NICKNAME + "TEXT,"
                + Player.COLUMN_NUMBER + "INTEGER," + ")";
    }

    public int insert(Player player) {
        int playerId;
        SQLiteDatabase db = DatabaseManager.getInstance().openDatabase();
        ContentValues values = new ContentValues();
        values.put(player.COLUMN_FIRST_NAME, player.getFirstName());
        values.put(player.COLUMN_LAST_NAME, player.getLastName());
        values.put(player.COLUMN_NICKNAME, player.getNickName());
        values.put(player.COLUMN_NUMBER, player.getNumber());
        // insert row
        playerId = (int)db .insert(player.TABLE_NAME,null,values);
        DatabaseManager.getInstance().closeDatabase();

        return playerId;
    }

    // Deleting a shop
    public void remove(Player player) {
        SQLiteDatabase db = DatabaseManager.getInstance().openDatabase();
        db.delete(Player.TABLE_NAME, Player.COLUMN_PLAYERID + " = ?",
                new String[]{String.valueOf(player.getID())});
        db.close();
    }
    // Updating a shop
    public int updatePlayer(Player player) {
        SQLiteDatabase db = DatabaseManager.getInstance().openDatabase();
        ContentValues values = new ContentValues();
        values.put(player.COLUMN_FIRST_NAME, player.getFirstName());
        values.put(player.COLUMN_LAST_NAME, player.getLastName());
        values.put(player.COLUMN_NICKNAME, player.getNickName());
        values.put(player.COLUMN_NUMBER, player.getNumber());
// updating row
        return db.update(player.TABLE_NAME, values, "PlayerID" + " = ?",
                new String[]{String.valueOf(player.getID())});
    }
    public void delete() {
        SQLiteDatabase db = DatabaseManager.getInstance().openDatabase();
        db.delete(Player.TABLE_NAME,null,null);
        DatabaseManager.getInstance().closeDatabase();
    }
}
