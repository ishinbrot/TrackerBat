package com.example.ianshinbro.trackerbat.Implentation;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by ianshinbrot on 5/2/15.
 */
public class Game implements iGame, Serializable{

    public Game() {
        this.numberOfInnings=9;
        this.atBats = new ArrayList<>();
        this.awayScore=0;
        this.homeScore=0;
        this.inProgress=true;
    }
    public Game(int numOfInnings) {
        this.numberOfInnings=numOfInnings;
        this.atBats = new ArrayList<>();
        this.awayScore=0;
        this.homeScore=0;
    }
   public void addAtBat(AtBat atBat) {
        this.atBats.add(atBat);
   }

    public void endGame() {
        inProgress=false;
    }
    public void removeAtBat(int index) {
        this.atBats.remove(index);
    }
    public AtBat getAtBat(int index) {
       return this.atBats.get(index);
    }
    public ArrayList<AtBat> getAtBats() {
        return this.atBats;
    }
    public void updateGameAtIndex(int index, AtBat atBat) {
        this.atBats.set(index,atBat);
    }
    public void updateAtBats(ArrayList<AtBat> atBats) {
        this.atBats=atBats;
    }
    public void setAwayTeam(String awayTeam) {
        this.awayTeam = awayTeam;
    }
    public void setInningNumber(int number) {
        this.numberOfInnings=number;
    }
    public String getAwayTeam() {
        return this.awayTeam;
    }
    public void setHomeTeam(String homeTeam) {
        this.homeTeam = homeTeam;
    }
    public String getHomeTeam() {
        return this.homeTeam;
    }
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
