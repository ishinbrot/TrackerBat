package com.example.ianshinbro.trackerbat.Implentation;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by ianshinbrot on 4/30/15.
 * This is a player class
 * Serializable is used to navigate intents in android
 */
public class Player implements Serializable{
    public static final String TAG = Player.class.getSimpleName();

    public Player( int number) {
        this.number_ = number;
        this.games_ = new ArrayList<>();
    }

    public Player() {
        this.games_=new ArrayList<>();
    }

    public Player(String firstName, String lastName, int number) {
        this.firstName_ = firstName;
        this.lastName_ = lastName;
        this.number_ = number;
        this.games_ = new ArrayList<>();

    }

    /**
     * This updates a specific game by player
     * @param index - the index of the game to be updated
     * @param game - the modified game object
     */
    public void updateGame(int index, Game game) {
        this.games_.set(index, game);
    }
    public ArrayList<Game> getGames() {
        return this.games_;
    }
    public void setId(int id) {
        this.id_=id;
    }
    public int getID() {
        return this.id_;
    }
    public String getFirstName() {
        return this.firstName_;
    }
    public String getLastName() {
        return this.lastName_;
    }
    public String getNickName() {
        return this.nickName_;
    }
    public void updateGames(ArrayList<Game> games) {
        this.games_=games;
    }
    public void setFirstName(String firstName) {
        this.firstName_ = firstName;
    }
    public void setLastName(String lastName) {
        this.lastName_ = lastName;
    }
    public void setNickName(String nickName) {
        this.nickName_ = nickName; this.hasNickName=true;
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
    public int getNumber() {
        return number_;
    }

    public void setNumber(int number) {
        this.number_ = number;
    }
    public void addGame(Game game) {
        this.games_.add(game);
    }
    public void removeGame(int index) {
        this.games_.remove(index);
    }
    public Game getGame(int index) {
        return this.games_.get(index);
    }

    public String toString() {
        return this.firstName_ + " " + this.lastName_ + " " + this.number_;
    }
    private String firstName_;
    private String lastName_;
    private String nickName_;
    int id_;
    private Boolean hasNickName=false;
    private int number_;
    private ArrayList<Game> games_;
    public static final String TABLE_NAME="Players";
    public static final String COLUMN_PLAYERID = "PlayerID";
    public static final String COLUMN_FIRST_NAME = "firstName";
    public static final String COLUMN_LAST_NAME = "lastName";
    public static final String COLUMN_NUMBER = "playerNumber";
    public static final String COLUMN_NICKNAME = "playerNickName";
}
