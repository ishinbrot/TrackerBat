package com.example.ianshinbro.trackerbat.data.model;

/**
 * Created by ianshinbro on 4/12/2016.
 */
public class GameAtBat {

    public static final String TAB = GameAtBat.class.getSimpleName();
    public static final String TABLE_NAME = "GameAtBatRelation";

    public static final String COLUMN_RELATIONID="RelationID";
    public static String COLUMN_GAMEID = "GameID";
    public static final String COLUMN_ATBATID = "AtBatID";

    public int gameId_;
    public int atBatId_;
    public int getGameid_() {
        return gameId_;
    }
    public int getAtBatid() {
        return atBatId_;
    }
    public void setAtBatId(int atBatId) {
        this.atBatId_=atBatId;
    }
    public void setGameId(int gameId) {
        this.gameId_=gameId;
    }
}
