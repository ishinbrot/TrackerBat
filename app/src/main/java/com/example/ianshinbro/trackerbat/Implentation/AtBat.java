package com.example.ianshinbro.trackerbat.Implentation;

import java.io.Serializable;

/**
 * Created by ianshinbro on 2/19/2016.
 */

/**
 * This method stores atBat information
 * It implements serializable to be utilized by Android
 */
public class AtBat implements  Serializable{

    public AtBat(int inningNumber) {
        this.hit=false;
        this.out=false;
        this.score=0;
        this.inningNumber=inningNumber;
    }

    /**
     * Constructor for class. Defaults all values to 0
     */
    public AtBat() {
        this.hit = false;
        this.out = false;
        this.score = 0;
    }

    /**
     *
     * @return - retrieves the inning number
     */
    public int getInningNumber() {
        return this.inningNumber;
    }

    /**
     * this moves to the next base for the atbat class
     */
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

    /**
     * This reverts the base for the atBat class
     */
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

    /**
     * Sets the initial base as the initial and current base for the class
     * @param base - base object that is assigned
     */
    public void setInitialBase(Base base) {
       this.initialBase=base;
        this.currentBase=base;
    }

    /**
     * This makes the initial basae as an at bat
     */
    public void UndoInitialBase() {
        this.initialBase=Base.ATBAT;
    }

    /**
     * This sets the inning number for hte AtBat
     * @param inning - inning number of the AtBat
     */
    public void setInningNumber(int inning) {
        this.inningNumber=inning;
    }

    /**
     * retrieves the initial bats
     * @return - a string containing what the initial base would be equal to
     */
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

    /**
     * This retrieves the initial base
     * @return - the initial bases enum value
     */
    public Base getInitialBaseNum() {
        return this.initialBase;
    }

    /**
     * Retrieves the current base
     * @return - current bases enum value
     */
    public Base getCurrentBase() {
        return this.currentBase;
    }

    /**
     * retrieves the initial base
     * @return - initial base enum value
     */
    public Base getInitialBaseEnum() {
        return initialBase;
    }

    /**
     * Sets the final base
     * @param finalBase - base object that will be the final base
     */
    public void setFinalBase(Base finalBase) {

        this.finalBase=finalBase;
    }

    /**
     * Informs the AtBat class that the user walked
     * TODO currently not utilzed to display walk on list screen, as scorecard doesn't inform walk
     */
    public void setWalk() {
        this.walk=true;
    }
    public void undoWalk() {
        this.walk=false;
    }

    /**
     * Informs that an out is to be removed
     * Defaults all related values
     */
    public void undoOut() {
        this.finalCatch=OutField.NONE;
        this.initialCatch=OutField.NONE;
        this.firstOutRecieved=false;
        this.out=false;
    }

    /**
     * Retrieves the final base as a string value
     * @return - string value of the final base
     */
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

    /**
     * Reverts the score of the atBat object
     */
    public void revertScore() {
        this.score-=1;
    }

    /**
     * Sets the initial catche used only on outs
     * @param initialCatch - initial person catching the ball
     */
    public void setInitialCatch(OutField initialCatch) {
        this.initialCatch=initialCatch;
    }
    /**
     * Sets the initial catche used only on outs
     * @param finalCatch - initial person catching the ball
     */
    public void setFinalCatch(OutField finalCatch) {

        this.finalCatch=finalCatch;
    }

    /**
     * Confirms the base stats to a string value
     * @return the value of the atBat
     */
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

    /**
     * Retrieves the current catcher as a string value
     * @param field - the outfielder who caught the ball
     * @return - string value of the person who caught the ball
     */
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
}
