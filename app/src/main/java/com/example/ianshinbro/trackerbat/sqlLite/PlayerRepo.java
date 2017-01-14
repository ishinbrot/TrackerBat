package com.example.ianshinbro.trackerbat.sqlLite;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;

import com.example.ianshinbro.trackerbat.Implentation.Player;

import java.util.FormatFlagsConversionMismatchException;

/**
 * Created by iansh on 12/29/2016.
 */

public class PlayerRepo {

    private Player player;

    public PlayerRepo() {
        player = new Player();
    }

    public static String createTable() {
    return  "CREATE TABLE" + Player.TABLE + "("
            + Player.KEY_FirstName + "TEXT" + "," + Player.KEY_LastName + "TEXT,"
            + Player.KEY_NickName + "TEXT " + Player.KEY_PlayerNumber + "NUMBER" + Player.KEY_PlayerID + "NUMBER Primary KEY)";
    }

    public int insert(Player player) {
        int playerID;
        SQLiteDatabase db = DataBaseManager.getInstance().openDatabase();
        ContentValues values = new ContentValues();
        values.put(Player.KEY_FirstName, player.getFirstName());
        values.put(Player.KEY_LastName, player.getLastName());
        values.put(Player.KEY_NickName, player.getNickName());
        values.put(Player.KEY_PlayerID, player.getNumber());

        // Inserting row

        playerID = (int)db.insert(Player.TABLE, null, values);
        DataBaseManager.getInstance().closeDatabase();
        return playerID;
    }

    public void delete() {
        SQLiteDatabase db = DataBaseManager.getInstance().openDatabase();
        db.delete(Player.TABLE,null,null);
        DataBaseManager.getInstance().closeDatabase();
    }

}
