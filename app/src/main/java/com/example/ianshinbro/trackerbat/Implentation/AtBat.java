package com.example.ianshinbro.trackerbat.Implentation;

import java.io.Serializable;

/**
 * Created by ianshinbro on 2/19/2016.
 */
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
                this.currentBase = Base.ATBAT;
                break;
            case SECOND:
                this.currentBase = Base.FIRST;
                break;
            case THIRD:
                this.currentBase = Base.SECOND;
                break;
            case HOME:
                this.currentBase = Base.THIRD;
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
    public void undoOut() {
        this.finalCatch=OutField.NONE;
        this.initialCatch=OutField.NONE;
        this.firstOutRecieved=false;
        this.out=false;
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
        if (!out) {
            if (this.initialBase != Base.ATBAT) {
                if (this.initialBase == this.finalBase) {
                    return this.getInitialBase();
                } else if (this.initialBase != this.finalBase) {
                    return this.getInitialBase() + " to " + this.getFinalBase();
                }
            }
        }
        else {
            if (this.finalCatch == this.initialCatch) {
                return "Out via " + this.getCatchString(this.initialCatch);
            }
            else return "Out via " +  this.getCatchString(this.initialCatch) + " to "+ this.getCatchString(this.finalCatch);
        }
        return "";
    }
    public void undoStats() {
        if (out) {
            undoOut();
        }
        else {
            this.currentBase=Base.ATBAT;
            this.finalBase=Base.ATBAT;
            this.initialBase=Base.ATBAT;
        }
    }
    public String getCatchString(OutField field) {
        switch (field) {
            case PITCHER:
                return "Pitcher";

            case CENTER_FIELD:
                return "Center Field";

            case FIRST_BASE:
                return "First Base";

            case SECOND_BASE:
                return "Second Base";

            case THIRD_BASE:
                return "Third Base";

            case CATCHER:
                return "Catcher";

            case RIGHT_FIELD:
                return "Right Field";

            case LEFT_FIELD:
                return "Left Field";

            case SHORT_STOP:
                return "Short Stop";

        }
        return "";
    }
    public OutField getFinalCatch() {
        return this.finalCatch;
    }
    public OutField getInitialCatch() {
        return this.initialCatch;
    }
    public boolean isFirstOutRecieved() {
        return firstOutRecieved;
    }

    public void setFirstOutRecieved(boolean firstOutRecieved) {
        this.firstOutRecieved = firstOutRecieved;
    }
    public void setOut(boolean out) {
        this.out=out;
    }
    public boolean isOut() {
        return this.out;
    }
    boolean hit;
    boolean out=false;
    boolean walk;
    int score;
    private Base initialBase = Base.ATBAT;
    private Base finalBase = Base.ATBAT;
    private Base currentBase;
    private OutField initialCatch;
    private boolean firstOutRecieved;
    private OutField finalCatch;
    private int inningNumber;
    private String baseStats;
}
