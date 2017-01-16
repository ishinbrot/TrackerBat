package com.example.ianshinbro.trackerbat.Implentation;

import com.example.ianshinbro.trackerbat.AppDataBase;
import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.ColumnIgnore;
import com.raizlabs.android.dbflow.annotation.ForeignKey;
import com.raizlabs.android.dbflow.annotation.ForeignKeyAction;
import com.raizlabs.android.dbflow.annotation.ForeignKeyReference;
import com.raizlabs.android.dbflow.annotation.NotNull;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.structure.BaseModel;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ianshinbrot on 5/2/15.
 */
@Table(database = AppDataBase.class)
public class Game extends BaseModel implements Serializable{
    /**
     * Constructor for game object
     * Sets the score to zero, progress to true, and default number of innings to 9
     */
    public Game() {
        super();
        this.numberOfInnings =9;
        this.atBats = new ArrayList<>();
        this.awayScore =0;
        this.homeScore =0;
        this.inProgress =true;

    }

    /**
     * Alternate constructor for game object
     * @param numOfInnings - sets number of innings to passed in object
     */
    public Game(int numOfInnings) {
        super();
        this.numberOfInnings =numOfInnings;
        this.atBats = new ArrayList<>();
        this.awayScore =0;
        this.homeScore =0;
    }

    /**
     * Adds an at bat to the game
     * @param atBat the current at bat that will be added to the game
     */
   public void addAtBat(AtBat atBat) {
        this.atBats.add(atBat);
   }

    /**
     * This will end the game
     */
    public void endGame() {
        this.inProgress =false;
    }

    /**
     * This removes the at bat
     * @param index - index to remove the at bat
     */
    public void removeAtBat(int index) {
        this.atBats.remove(index);
    }

    /**
     * This retrieves the at bat
     * @param index - index to get the at bat at
     * @return - the at bat at the current index
     */
    public AtBat getAtBat(int index) {
       return this.atBats.get(index);
    }

    /**
     * Gets all of the at bats in a game
     * @return
     */
    public ArrayList<AtBat> getAtBats() {
        return atBats;
    }

    /**
     * Updates a specific at bat in the array
     * @param index - index to update teh at bat
     * @param atBat - new at bat object to be modified
     */
    public void updateAtBatatIndex(int index, AtBat atBat) {
        this.atBats.set(index,atBat);
    }

    /**
     * This method updates every atBat in the array
     * @param atBats
     */
    public void updateAtBats(List<AtBat> atBats) {
        this.atBats.addAll(atBats);
    }

    /**
     * This sets the away team for a specific game
     * @param awayTeam - String for the away team
     */
    public void setAwayTeam(String awayTeam) {
        this.awayTeam = awayTeam;
    }

    /**
     * Sets the number of innings in a game
     * @param number - total number of innings in the game
     */
    public void setInningNumber(int number) {
        this.numberOfInnings =number;
    }

    /**
     * Retrieves away team
     * @return
     */
    public String getAwayTeam() {
        return this.awayTeam;
    }

    /**
     * Sets the home team
     * @param homeTeam - String containing the home team to be added
     */
    public void setHomeTeam(String homeTeam) {
        this.homeTeam = homeTeam;
    }

    /**
     * Gets the home team
     * @return
     */
    public String getHomeTeam() {
        return this.homeTeam;
    }

    /**
     * Sets the away score
     * @param score - int containing the score of the away team
     */
    public void setAwayScore(int score) {
        this.awayScore =score;
    }
    public int getAwayScore() {
        return this.awayScore;
    }
    public void setHomeScore(int score) {
        this.homeScore =score;
    }
    public long getHomeScore() {
        return this.homeScore;
    }

    public boolean getStatus() {
        return this.inProgress;
    }
    /**
     *
     * @return
     */
    public long getInningTotal() {
        return this.numberOfInnings;
    }

    /**
     * Score of the current player for the game
     * @return
     */
    public String getScore() {

        return getHomeScore() +"-" +  getAwayScore();
    }

    public ArrayList<AtBat> atBats;
    @Column
    public String homeTeam;
    @Column
    public String awayTeam;

    @Column
    public int awayScore;
    @Column
    public long homeScore;
    @Column
    public long numberOfInnings;       // 9 is default number of innings
    @Column
    public boolean inProgress;

    public long getGameId() {
        return gameId;
    }

    public void setGameId(long gameId) {
        this.gameId = gameId;
    }


    @PrimaryKey
    @ForeignKey(references = { @ForeignKeyReference(foreignKeyColumnName = "gameId", columnName = "gameId")}, tableClass = Game.class)
    public long gameId;

    public long getPlayerId() {
        return playerId;
    }

    public void setPlayerId(long playerId) {
        this.playerId = playerId;
    }

    @Column
    @ForeignKey(references = { @ForeignKeyReference(foreignKeyColumnName = "playerId", columnName = "playerId")}, tableClass = Player.class, onDelete = ForeignKeyAction.CASCADE)
    @NotNull
    public long playerId;
@ColumnIgnore
    public static final String TAG = Game.class.getSimpleName();

}
