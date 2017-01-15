package com.example.ianshinbro.trackerbat.Implentation;

import com.example.ianshinbro.trackerbat.Implentation.Game;

/**
 * Created by ianshinbrot on 4/30/15.
 */
public interface iPlayer {


     String getFirstName();
    String getLastName();
    String getNickName();

     void setFirstName(String firstName);
    void setLastName(String lastName);
    void setNickName(String nickName);

     long getNumber();

     void setNumber(int number);

    void addGame(Game game);

    void removeGame(int index);

    Game getGame(int index);

    boolean nickNameExists();





}
