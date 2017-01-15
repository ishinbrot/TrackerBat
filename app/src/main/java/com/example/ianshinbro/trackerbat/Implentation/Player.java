package com.example.ianshinbro.trackerbat.Implentation;
import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.ColumnIgnore;
import com.raizlabs.android.dbflow.annotation.ForeignKey;
import com.raizlabs.android.dbflow.annotation.ForeignKeyReference;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.annotation.Unique;
import com.raizlabs.android.dbflow.structure.BaseModel;

import com.example.ianshinbro.trackerbat.AppDataBase;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ianshinbrot on 4/30/15.
 * This is a player class
 * Serializable is used to navigate intents in android
 */
@Table(database = AppDataBase.class)
public class Player extends BaseModel implements Serializable{



    public Player( int number) {
        super();
        this.number = number;
        this.games = new ArrayList<>();
    }

    public Player() {
        super();
        this.games=new ArrayList<>();
    }

    public Player(String firstName, String lastName, long number) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.number = number;
        this.games = new ArrayList<>();


    }

    /**
     * This updates a specific game by player
     * @param index - the index of the game to be updated
     * @param game - the modified game object
     */
    public void updateGame(int index, Game game) {
        this.games.set(index, game);
    }
    public ArrayList<Game> getGames() {
        return games;
    }
    public void setId(long id) {
        this.playerId=id;
    }
    public void addGames(List<Game> games) {this.games.addAll(games);}

    public String getFirstName() {
        return this.firstName;
    }
    public String getLastName() {
        return this.lastName;
    }
    public String getNickName() {
        return this.nickName;
    }
    public void updateGames(ArrayList<Game> games) {
        this.games=games;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public void setNickName(String nickName) {
        this.nickName = nickName; this.hasNickName=true;
    }

    /**
     * Determine if nickname exists
     * @return
     */
    public boolean nickNameExists() {
        return this.hasNickName;
    }

    /**
     * This retrieves the number of the player
     * @return - number of the player
     */
    public long getNumber() {
        return number;
    }

    public void setNumber(long number) {
        this.number = number;
    }
    public void addGame(Game game) {
        this.games.add(game);
    }
    public void removeGame(int index) {
        this.games.remove(index);
    }
    public Game getGame(int index) {
        return this.games.get(index);
    }

    public String toString() {
        return this.firstName + " " + this.lastName + " " + this.number;
    }

    @Column
    public String firstName;
    @Column
    public String lastName;

    @Column
    public String nickName;
    @Column
    public Boolean hasNickName=false;

    @Column
    @Unique
    private long number;

    public long getPlayerId() {
        return playerId;
    }

    public void setPlayerId(long playerId) {
        this.playerId = playerId;
    }

    @ForeignKey(references = { @ForeignKeyReference(foreignKeyColumnName = "playerId", columnName = "playerId")}, tableClass = Game.class)
    @PrimaryKey
    public long playerId;

    @ColumnIgnore
    private ArrayList<Game> games;
}
