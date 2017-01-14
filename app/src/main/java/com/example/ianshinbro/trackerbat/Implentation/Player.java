package com.example.ianshinbro.trackerbat.Implentation;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ianshinbrot on 4/30/15.
 * This is a player class
 * Serializable is used to navigate intents in android
 */
@Table(name= "Player")
public class Player extends Model implements Serializable{



    public Player( int number) {
        super();
        this.number_ = number;
        this.games_ = new ArrayList<>();
    }

    public Player() {
        super();
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
    public List<Game> getGames() {
        return getMany(Game.class, "Player");
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
    @Column(name = "firtName")
    private String firstName_;
    @Column(name = "lastName")
    private String lastName_;
    @Column(name = "nickName")
    private String nickName_;
    int id_;
    private Boolean hasNickName=false;
    @Column(name = "number", index = true)
    private int number_;

    public int getPlayerId() {
        return playerId_;
    }

    public void setPlayerId(int playerId) {
        this.playerId_ = playerId;
    }

    private int playerId_;
    private ArrayList<Game> games_;

    public static final String TABLE = "Player";
    public static final String KEY_FirstName = "First Name";
    public static final String KEY_LastName = "Last Name";
    public static final String KEY_NickName = "NickName";
    public static final String KEY_PlayerID = "Player_ID";
    public static final String KEY_PlayerNumber = "Player Number";
}
