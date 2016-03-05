package com.example.ianshinbro.trackerbat.Implentation;

/**
 * Created by ianshinbro on 3/2/2016.
 */

/**
 * This is an enum for outfield
 * The numbers correspond to what is put in a score card
 */
public enum OutField{
    NONE(0), PITCHER(1), CATCHER(2), FIRST_BASE(3), SECOND_BASE(4),THIRD_BASE(5),SHORT_STOP(6),LEFT_FIELD(7),CENTER_FIELD(8),RIGHT_FIELD(9);
    private int value;
    private OutField(int value) {
        this.value=value;
    }
    public int getValue() {
        return value;
    }
}
