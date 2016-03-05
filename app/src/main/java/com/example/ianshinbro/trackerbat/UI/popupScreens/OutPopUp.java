package com.example.ianshinbro.trackerbat.UI.popupScreens;

import android.app.*;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.*;

import com.example.ianshinbro.trackerbat.Implentation.AtBat;
import com.example.ianshinbro.trackerbat.Implentation.Base;
import com.example.ianshinbro.trackerbat.Implentation.OutField;
import com.example.ianshinbro.trackerbat.R;

public class OutPopUp extends Activity {

    Button catcher;
    Button first;
    Button second;
    Button third;
    Button shortStop;
    Button pitcher;
    Button leftField;
    Button rightField;
    Button centerField;
    OutField out1;
    AtBat atBat;
    Intent intent;
    private String tag = "OutPopUp";


    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(this.tag, "entering gameListScreen");
        Intent intent = getIntent();
        // retrieve the at bat object
        atBat = (AtBat) intent.getExtras().getSerializable("atBat");
        setContentView(R.layout.content_out_pop_up);
        this.loadFields();
        this.setOnClickListeners();
    }

    private void setOnClickListeners() {
        catcher.setOnClickListener(catcherBaseListener);
        first.setOnClickListener(firstBaseListener);
        second.setOnClickListener(secondBaseListener);
        third.setOnClickListener(thirdBaseListener);
        shortStop.setOnClickListener(shortStopListener);
        pitcher.setOnClickListener(pitcherListener);
        leftField.setOnClickListener(leftFieldListener);
        centerField.setOnClickListener(centerFieldListener);
        rightField.setOnClickListener(rightFieldListener);
    }

    /**
     * This ends the activity
     */
    private void finishActivity() {
        intent = new Intent();
        atBat.setOut(true);
        intent.putExtra("atBat", atBat);
        setResult(2, intent);
        finish();
    }

    /**
     * This is an onclick listener for hitting the right field
     */
    private OnClickListener rightFieldListener = new OnClickListener() {
        @Override
        public void onClick(View v) {
            out1 = OutField.RIGHT_FIELD;
            // check if the first out is recieved, else set final out
            if (!atBat.isFirstOutRecieved()) {
                atBat.setInitialCatch(out1);
                atBat.setFirstOutRecieved(true);
            } else {
                atBat.setFinalCatch(out1);
                finishActivity();

            }
        }
    };
    /**
     * This is an onclick listener for center field
     */
    private OnClickListener centerFieldListener = new OnClickListener() {
        @Override
        public void onClick(View v) {
            out1 = OutField.CENTER_FIELD;
            if (!atBat.isFirstOutRecieved()) {
                atBat.setInitialCatch(out1);
                atBat.setFirstOutRecieved(true);
            } else {
                atBat.setFinalCatch(out1);
                finishActivity();
            }
        }
    };
    /**
     * This is an onclick listener for left field
     */
    private OnClickListener leftFieldListener = new OnClickListener() {
        @Override
        public void onClick(View v) {
            out1 = OutField.LEFT_FIELD;
            if (!atBat.isFirstOutRecieved()) {
                atBat.setInitialCatch(out1);
                atBat.setFirstOutRecieved(true);
            } else {
                atBat.setFinalCatch(out1);
                finishActivity();
            }
        }
    };
    /**
     * This is an onclick listener for the pitcher
     */
    private OnClickListener pitcherListener = new OnClickListener() {
        @Override
        public void onClick(View v) {
            out1 = OutField.PITCHER;
            if (!atBat.isFirstOutRecieved()) {
                atBat.setInitialCatch(out1);
                atBat.setFirstOutRecieved(true);
            } else {
                atBat.setFinalCatch(out1);
                finishActivity();
            }
        }
    };
    /**
     * This is an onclick listener for short stop
     */
    private OnClickListener shortStopListener = new OnClickListener() {
        @Override
        public void onClick(View v) {
            out1 = OutField.SHORT_STOP;
            if (!atBat.isFirstOutRecieved()) {
                atBat.setInitialCatch(out1);
                atBat.setFirstOutRecieved(true);
            } else {
                atBat.setFinalCatch(out1);
                finishActivity();
            }
        }
    };
    /**
     * This is an onclick listener for catcher
     */
    private OnClickListener catcherBaseListener = new OnClickListener() {
        @Override
        public void onClick(View v) {
            out1 = OutField.CATCHER;
            if (!atBat.isFirstOutRecieved()) {
                atBat.setInitialCatch(out1);
                atBat.setFirstOutRecieved(true);
            } else {
                atBat.setFinalCatch(out1);

                finishActivity();
            }
        }
    };

    /**
     * This is an onclick listener for first base
     */
    private OnClickListener firstBaseListener = new OnClickListener() {
        @Override
        public void onClick(View v) {
            out1 = OutField.FIRST_BASE;
            if (!atBat.isFirstOutRecieved()) {
                atBat.setInitialCatch(out1);
                atBat.setFirstOutRecieved(true);
            } else {
                atBat.setFinalCatch(out1);
                finishActivity();
            }
        }
    };
    /**
     * This is an onclick listener for third base
     */
    private OnClickListener thirdBaseListener = new OnClickListener() {
        @Override
        public void onClick(View v) {
            out1 = OutField.THIRD_BASE;
            if (!atBat.isFirstOutRecieved()) {
                atBat.setInitialCatch(out1);
                atBat.setFirstOutRecieved(true);
            } else {
                atBat.setFinalCatch(out1);

                finishActivity();
            }
        }
    };
    private OnClickListener secondBaseListener = new OnClickListener() {
        @Override
        public void onClick(View v) {
            out1 = OutField.SECOND_BASE;
            if (!atBat.isFirstOutRecieved()) {
                atBat.setInitialCatch(out1);
                atBat.setFirstOutRecieved(true);
            } else {
                atBat.setFinalCatch(out1);

                finishActivity();
            }
        }
    };

    private void loadFields() {
        catcher = (Button) findViewById(R.id.CatcherOutPopUp);
        first = (Button) findViewById(R.id.FirstBaseOutPopUp);
        second = (Button) findViewById(R.id.SecondBaseOutPopUp);
        third = (Button) findViewById(R.id.ThirdBaseOutPopUp);
        shortStop = (Button) findViewById(R.id.ShortStopOutPopUp);
        pitcher = (Button) findViewById(R.id.PitcherOutPopUp);
        leftField = (Button) findViewById(R.id.LeftFieldOutPopUp);
        rightField = (Button) findViewById(R.id.RightFieldOutPopUp);
        centerField = (Button) findViewById(R.id.CenterFieldOutPopUp);
    }
}