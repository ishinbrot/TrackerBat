package com.example.ianshinbro.trackerbat.Implentation;

/**
 * Created by ianshinbro on 2/27/2016.
 */
 public enum Base {
    ATBAT(0), FIRST(1), SECOND(2), THIRD(3), HOME(4);

     Base(int value) {
        this.value = value;
    }

    public int getValue() {
        return this.value;
    }
    public void setValue(int value) {this.value=value;
    }

    private int value;
}
