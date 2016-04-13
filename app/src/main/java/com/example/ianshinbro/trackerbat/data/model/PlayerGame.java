package com.example.ianshinbro.trackerbat.data.model;

/**
 * Created by ianshinbro on 4/12/2016.
 */
public class PlayerGame {

    public static final String TAB = PlayerGame.class.getSimpleName();
    public static final String TABLE_NAME = "PlayerGameRelation";

    public static final String COLUMN_RELATIONID="RelationID";
    public static String COLUMN_PLAYERID = "PlayerID";
    public static final String COLUMN_GAMEID = "GameID";

    public int playerId_;
    public int gameId_;
    public int getPlayerId() {
        return playerId_;
    }
    public int getGameId() {
        return gameId_;
    }
    public void setPlayerId(int playerId) {
        this.playerId_=playerId;
    }
    public void setGameId(int gameId) {
        this.gameId_=gameId;
    }
}
