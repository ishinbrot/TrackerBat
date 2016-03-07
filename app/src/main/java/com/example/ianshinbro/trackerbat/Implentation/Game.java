package com.example.ianshinbro.trackerbat.Implentation;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by ianshinbrot on 5/2/15.
 */
public class Game implements iGame, Serializable{
    /**
     * Constructor for game object
     * Sets the score to zero, progress to true, and default number of innings to 9
     */
    public Game() {
        this.numberOfInnings=9;
        this.atBats = new ArrayList<>();
        this.awayScore=0;
        this.homeScore=0;
        this.inProgress=true;
    }

    /**
     * Alternate constructor for game object
     * @param numOfInnings - sets number of innings to passed in object
     */
    public Game(int numOfInnings) {
        this.numberOfInnings=numOfInnings;
        this.atBats = new ArrayList<>();
        this.awayScore=0;
        this.homeScore=0;
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
        this.inProgress=false;
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
        return this.atBats;
    }

    /**
     * Updates a specific at bat in the array
     * @param index - index to update teh at bat
     * @param atBat - new at bat object to be modified
     */
    public void updateGameAtIndex(int index, AtBat atBat) {
        this.atBats.set(index,atBat);
    }

    /**
     * This method updates every atBat in the array
     * @param atBats
     */
    public void updateAtBats(ArrayList<AtBat> atBats) {
        this.atBats=atBats;
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
        this.numberOfInnings=number;
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
        this.awayScore=score;
    }
    public int getAwayScore() {
        return this.awayScore;
    }
    public void setHomeScore(int score) {
        this.homeScore=score;
    }
    public int getHomeScore() {
        return this.homeScore;
    }

    public boolean getStatus() {
        return this.inProgress;
    }
    /**
     *
     * @return
     */
    public int getInningNumber() {
        return this.numberOfInnings;
    }

    /**
     * Score of the current player for the game
     * @return
     */
    public String getScore() {

    return null;
    }

    private ArrayList<AtBat> atBats;
    private String homeTeam;
    private String awayTeam;
    private int awayScore;
    private int homeScore;
    private int numberOfInnings;       // 9 is default number of innings
    boolean inProgress;




}
