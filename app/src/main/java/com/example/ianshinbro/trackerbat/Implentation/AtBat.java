package com.example.ianshinbro.trackerbat.Implentation;

import java.io.Serializable;

/**
 * Created by ianshinbro on 2/19/2016.
 */

enum OutField{PITCHER(1), CATCHER(2), FIRST_BASE(3), SECOND_BASE(4),THIRD_BASE(5),SHORT_STOP(6),LEFT_FIELD(7),CENTER_FIELD(8),RIGHT_FIELD(9);
    private int value;
    private OutField(int value) {
        this.value=value;
    }
    public int getValue() {
        return value;
    }
}

public class AtBat implements iAtBat, Serializable{

    public AtBat(int inningNumber) {
        this.hit=false;
        this.out=false;
        this.score=0;
        this.inningNumber=inningNumber;
    }
    public AtBat() {
        this.hit = false;
        this.out = false;
        this.score = 0;
    }
    public int getInningNumber() {
        return this.inningNumber;
    }
    public void nextBase() {
        switch (currentBase) {
            case FIRST:
                currentBase = Base.SECOND;
                break;
            case SECOND:
                currentBase = Base.THIRD;
                break;
            case THIRD:
                currentBase = Base.HOME;
                score();
                break;
        }
    }
    public void revertBase() {
        if (this.currentBase.getValue()==4) {
            this.revertScore();
        }
        switch (currentBase) {
            case FIRST:
                currentBase = Base.ATBAT;
                break;
            case SECOND:
                currentBase = Base.FIRST;
                break;
            case THIRD:
                currentBase = Base.SECOND;
            case HOME:
                currentBase = Base.THIRD;
                revertScore();
                break;
        }
    }

    public void setInitialBase(Base base) {
       this.initialBase=base;
        this.currentBase=base;
    }
    public void UndoInitialBase() {
        this.initialBase=Base.ATBAT;
    }
    public void setInningNumber(int inning) {
        this.inningNumber=inning;
    }
    public String getInitialBase() {
        switch (initialBase) {
            case FIRST:
                return "SINGLE";
            case SECOND:
                return "DOUBLE";
            case THIRD:
                return "TRIPLE";
            case HOME:
                return "HOME RUN";
        }
        return "";
    }
    public Base getInitialBaseNum() {
        return this.initialBase;
    }
    public Base getCurrentBase() {
        return this.currentBase;
    }
    public Base getInitialBaseEnum() {
        return initialBase;
    }
    public void setFinalBase(Base finalBase) {

        this.finalBase=finalBase;
    }
    public void setWalk() {
        this.walk=true;
    }
    public void undoWalk() {
        this.walk=false;
    }
    public String getFinalBase() {
        switch (finalBase) {
            case FIRST :
                return "FIRST";
            case SECOND:
                return "SECOND";
            case THIRD:
                return "THIRD";
            case HOME:
                return "HOME";
        }
        return "";
    }
    public void score() {
        this.score+=1;
    }
    public void revertScore() {
        this.score-=1;
    }
    public void setInitialCatch(OutField initialCatch) {
        this.initialCatch=initialCatch;
    }
    public void setFinalCatch(OutField finalCatch) {

        this.finalCatch=finalCatch;
    }
    public String getBaseStats() {

        // they never advanced a base
        if (this.initialBase == this.finalBase) {
            return this.getInitialBase();
        } else if (this.initialBase != this.finalBase) {
            return this.getInitialBase() + " to " + this.getFinalBase();
        }
        return "";
    }
    public OutField getFinalCatch() {
        return this.finalCatch;
    }
    public OutField getInitialCatch() {
        return this.initialCatch;
    }
    boolean hit;
    boolean out;
    boolean walk;
    int score;
    private Base initialBase = Base.ATBAT;
    private Base finalBase = Base.ATBAT;
    private Base currentBase;
    private OutField initialCatch;
    private OutField finalCatch;
    private int inningNumber;
    private String baseStats;
}
