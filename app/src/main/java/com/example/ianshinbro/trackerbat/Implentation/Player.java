package com.example.ianshinbro.trackerbat.Implentation;

import com.example.ianshinbro.trackerbat.Implentation.Game;
import com.example.ianshinbro.trackerbat.Implentation.iPlayer;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by ianshinbrot on 4/30/15.
 */
public class Player implements iPlayer, Serializable{


    public Player( int number) {
        this.number = number;
        this.games = new ArrayList<>();
    }

    public Player() {
        this.games=new ArrayList<>();
    }

    public Player(String firstName, String lastName, int number) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.number = number;
        this.games = new ArrayList<>();

    }
    public void updateGame(int index, Game game) {
        this.games.set(index, game);
    }
    public ArrayList<Game> getGames() {
        return this.games;
    }
    public String getFirstName() {
        return this.firstName;
    }
    public String getLastName() {
        return this.lastName;
    }
    public String getNickName() {
        return this.nickName;
    }
    public void updateGames(ArrayList<Game> games) {
        this.games=games;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public void setNickName(String nickName) {
        this.nickName = nickName; this.hasNickName=true;
    }
    public boolean nickNameExists() {
        return this.hasNickName;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }
    public void addGame(Game game) {
        this.games.add(game);
    }
    public void removeGame(int index) {
        this.games.remove(index);
    }
    public Game getGame(int index) {
        return this.games.get(index);
    }

    public String toString() {
        return this.firstName + " " + this.lastName + " " + this.number;
    }
    private String firstName;
    private String lastName;
    private String nickName;
    private Boolean hasNickName=false;
    private int number;
    private ArrayList<Game> games;
}
