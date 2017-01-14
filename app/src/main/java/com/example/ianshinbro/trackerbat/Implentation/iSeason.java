package com.example.ianshinbro.trackerbat.Implentation;

import java.sql.Date;

/**
 * Created by iansh on 7/31/2016.
 */
public interface iSeason {
    Date getDate();
    void setDate(Date date);
    void addGame(Game game);
}
