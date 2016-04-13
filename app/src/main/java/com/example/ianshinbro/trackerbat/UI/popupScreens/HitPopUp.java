package com.example.ianshinbro.trackerbat.UI.popupScreens;

import android.app.*;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.*;

import com.example.ianshinbro.trackerbat.data.model.AtBat;
import com.example.ianshinbro.trackerbat.data.model.Base;
import com.example.ianshinbro.trackerbat.R;

public class HitPopUp extends Activity {

    Button firstBase;
    Button secondBase;
    Button thirdBase;
    Button HomeBase;
    Base base;
    AtBat atBat;
    Intent intent;
    Context ctx;
    private String tag="PopUpScreen";

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(this.tag, "entering gameListScreen");
        Intent intent = getIntent();
        atBat = (AtBat) intent.getExtras().getSerializable("atBat");
        setContentView(R.layout.bases_pop_up);
        this.loadFields();
        this.setOnClickListeners();
    }
    private void setOnClickListeners() {
        firstBase.setOnClickListener(FirstBaseListener);
        secondBase.setOnClickListener(SecondBaseListener);
        thirdBase.setOnClickListener(ThirdBaseListener);
        HomeBase.setOnClickListener(HomeBaseListener);
    }
    private void loadFields() {
        firstBase = (Button) findViewById(R.id.FirstdBaseButton_AtBat);
        secondBase = (Button) findViewById(R.id.SecondBaseButton_AtBat);
        thirdBase = (Button) findViewById(R.id.ThirdBaseButton_AtBat);
        HomeBase = (Button) findViewById(R.id.HomeBaseButton_AtBat);
    }
    private void finishActivity() {
        intent = new Intent();
        intent.putExtra("atBat", atBat);
        setResult(1, intent);
        finish();
    }

    /**
     * This is a listener for the first base button
     * Sets the base to first
     */
    private OnClickListener FirstBaseListener = new OnClickListener() {
        @Override
        public void onClick(View v) {
            base = Base.FIRST;
            atBat.setInitialBase(base);
            finishActivity();
        }
    };
    /**
     * This is a listener for the second base button
     * Sets the base to second
     */
    private OnClickListener SecondBaseListener = new OnClickListener() {
        @Override
        public void onClick(View v) {
            base = Base.SECOND;
            atBat.setInitialBase(base);
            finishActivity();
        }
    };
    /**
     * This is a listener for the third base button
     * Sets the base to third
     */
    private OnClickListener ThirdBaseListener = new OnClickListener() {
        @Override
        public void onClick(View v) {
            base = Base.THIRD;
            atBat.setInitialBase(base);
            finishActivity();
        }
    };
    /**
     * This is a listener for the home base button
     * Sets the home to first
     */
    private OnClickListener HomeBaseListener = new OnClickListener() {
        @Override
        public void onClick(View v) {
            base = Base.HOME;
            atBat.setInitialBase(base);
            atBat.setFinalBase(base);
            atBat.score();
            finishActivity();
        }
    };
}