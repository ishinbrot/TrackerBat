package com.example.ianshinbro.trackerbat.Implentation;

import com.example.ianshinbro.trackerbat.AppDataBase;
import com.raizlabs.android.dbflow.annotation.Column;
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
        this.numberOfInnings_=9;
        this.atBats_ = new ArrayList<>();
        this.awayScore_=0;
        this.homeScore_=0;
        this.inProgress_=true;

    }

    /**
     * Alternate constructor for game object
     * @param numOfInnings - sets number of innings to passed in object
     */
    public Game(int numOfInnings) {
        super();
        this.numberOfInnings_=numOfInnings;
        this.atBats_ = new ArrayList<>();
        this.awayScore_=0;
        this.homeScore_=0;
    }

    /**
     * Adds an at bat to the game
     * @param atBat the current at bat that will be added to the game
     */
   public void addAtBat(AtBat atBat) {
        this.atBats_.add(atBat);
   }

    /**
     * This will end the game
     */
    public void endGame() {
        this.inProgress_=false;
    }

    /**
     * This removes the at bat
     * @param index - index to remove the at bat
     */
    public void removeAtBat(int index) {
        this.atBats_.remove(index);
    }

    /**
     * This retrieves the at bat
     * @param index - index to get the at bat at
     * @return - the at bat at the current index
     */
    public AtBat getAtBat(int index) {
       return this.atBats_.get(index);
    }

    /**
     * Gets all of the at bats in a game
     * @return
     */
    public ArrayList<AtBat> getAtBats() {
        return atBats_;
    }

    /**
     * Updates a specific at bat in the array
     * @param index - index to update teh at bat
     * @param atBat - new at bat object to be modified
     */
    public void updateGameAtIndex(int index, AtBat atBat) {
        this.atBats_.set(index,atBat);
    }

    /**
     * This method updates every atBat in the array
     * @param atBats
     */
    public void updateAtBats(ArrayList<AtBat> atBats) {
        this.atBats_=atBats;
    }

    /**
     * This sets the away team for a specific game
     * @param awayTeam - String for the away team
     */
    public void setAwayTeam(String awayTeam) {
        this.awayTeam_ = awayTeam;
    }

    /**
     * Sets the number of innings in a game
     * @param number - total number of innings in the game
     */
    public void setInningNumber(int number) {
        this.numberOfInnings_=number;
    }

    /**
     * Retrieves away team
     * @return
     */
    public String getAwayTeam() {
        return this.awayTeam_;
    }

    /**
     * Sets the home team
     * @param homeTeam - String containing the home team to be added
     */
    public void setHomeTeam(String homeTeam) {
        this.homeTeam_ = homeTeam;
    }

    /**
     * Gets the home team
     * @return
     */
    public String getHomeTeam() {
        return this.homeTeam_;
    }

    /**
     * Sets the away score
     * @param score - int containing the score of the away team
     */
    public void setAwayScore(int score) {
        this.awayScore_=score;
    }
    public int getAwayScore() {
        return this.awayScore_;
    }
    public void setHomeScore(int score) {
        this.homeScore_=score;
    }
    public int getHomeScore() {
        return this.homeScore_;
    }

    public boolean getStatus() {
        return this.inProgress_;
    }
    /**
     *
     * @return
     */
    public int getInningTotal() {
        return this.numberOfInnings_;
    }

    /**
     * Score of the current player for the game
     * @return
     */
    public String getScore() {

        return getHomeScore() +"-" +  getAwayScore();
    }
    public void setId(int id) {
        this.id_=id;
    }
    public int getID() {
        return this.id_;
    }

    private ArrayList<AtBat> atBats_;
    @Column
    private String homeTeam_;
    @Column
    private String awayTeam_;
    @Column
    @PrimaryKey
    private int id_;
    @Column
    private int awayScore_;
    @Column
    private int homeScore_;
    @Column
    private int numberOfInnings_;       // 9 is default number of innings
    @Column
    boolean inProgress_;

    public static final String TAG = Game.class.getSimpleName();




}
