package com.example.ianshinbro.trackerbat.Implentation;

import com.example.ianshinbro.trackerbat.Implentation.AtBat;

/**
 * Created by ianshinbrot on 5/2/15.
 */
public interface iGame {

   void addAtBat(AtBat atBat);

    void removeAtBat(int index);

    AtBat getAtBat(int index);

    void setHomeScore(int score);

    boolean getStatus();

    int getHomeScore();

    void setAwayScore(int score);

    int getAwayScore();

    void setAwayTeam(String team);
    void setHomeTeam(String team);
    void setInningNumber(int number);

    String getAwayTeam();
    String getHomeTeam();
    int getInningTotal();

    String getScore();
}
