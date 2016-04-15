package com.example.ianshinbro.trackerbat.data.repo;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.ianshinbro.trackerbat.data.DatabaseManager;
import com.example.ianshinbro.trackerbat.data.model.Player;

import java.util.ArrayList;

/**
 * Created by ianshinbro on 4/12/2016.
 */
public class PlayerRepo {

    private Player player;

    public PlayerRepo() {
        player = new Player();
    }
    private final String TAG = PlayerRepo.class.getSimpleName();

    public static final String createTable() {
        return "CREATE TABLE "
                + Player.TABLE_NAME + "("
                + Player.COLUMN_PLAYERID + " INTEGER PRIMARY KEY,"
                + Player.COLUMN_FIRST_NAME + " TEXT NOT NULL,"
                + Player.COLUMN_LAST_NAME + " TEXT NOT NULL,"
                + Player.COLUMN_NICKNAME + " TEXT,"
                + Player.COLUMN_NUMBER + " INTEGER NOT NULL" + ")";
    }

    public int insert(Player player) {
        int playerId;
        SQLiteDatabase db = DatabaseManager.getInstance().openDatabase();
        ContentValues values = new ContentValues();
        values.put(player.COLUMN_FIRST_NAME, player.getFirstName());
        values.put(player.COLUMN_LAST_NAME, player.getLastName());
        if (player.nickNameExists()) {
            values.put(player.COLUMN_NICKNAME, player.getNickName());
        }
        values.put(player.COLUMN_NUMBER, player.getNumber());
        // insert row
        playerId = (int)db .insert(player.TABLE_NAME,null,values);
        DatabaseManager.getInstance().closeDatabase();
        Log.d(TAG, "inserting player at id " +player.getID());

        return playerId;
    }

    public ArrayList<Player> getAllPlayers() {
        String query = "SELECT * FROM " + Player.TABLE_NAME;
        SQLiteDatabase db = DatabaseManager.getInstance().openDatabase();
        Cursor res = db.rawQuery(query, null);
        ArrayList<Player> list=new ArrayList<Player>();

        while(res.moveToNext()){

            Player row=new Player();
            row.setId(Integer.parseInt(res.getString(0)));
            row.setFirstName(res.getString(1));
            row.setLastName(res.getString(2));
            if (res.getString(3)!=null) {
                row.setNickName(res.getString(3));
            }
            row.setNumber(Integer.parseInt(res.getString(4)));
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

    // Deleting a player
    public void remove(int id) {
        SQLiteDatabase db = DatabaseManager.getInstance().openDatabase();
        Log.d(TAG, "Remove player at "+ id);
        db.delete(Player.TABLE_NAME, Player.COLUMN_PLAYERID + " = ?",
                new String[]{String.valueOf(id)});
        DatabaseManager.getInstance().closeDatabase();
    }
    // Updating a plalyer
    public void updatePlayer(Player player) {
        SQLiteDatabase db = DatabaseManager.getInstance().openDatabase();
        ContentValues values = new ContentValues();
        values.put(player.COLUMN_FIRST_NAME, player.getFirstName());
        values.put(player.COLUMN_LAST_NAME, player.getLastName());
        values.put(player.COLUMN_NICKNAME, player.getNickName());
        values.put(player.COLUMN_NUMBER, player.getNumber());
// updating row
         db.update(player.TABLE_NAME, values, Player.COLUMN_PLAYERID + " = ?",
                new String[]{String.valueOf(player.getID())});
        Log.d(TAG,"Updating player at " + player.getID());
        DatabaseManager.getInstance().closeDatabase();
    }
    public void delete() {
        SQLiteDatabase db = DatabaseManager.getInstance().openDatabase();
        db.delete(Player.TABLE_NAME,null,null);
        DatabaseManager.getInstance().closeDatabase();
    }
}
