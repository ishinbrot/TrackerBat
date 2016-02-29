package com.example.ianshinbro.trackerbat.UI;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;
import android.widget.TextView;
import android.util.Log;

import com.example.ianshinbro.trackerbat.Implentation.AtBat;
import com.example.ianshinbro.trackerbat.Implentation.Base;
import com.example.ianshinbro.trackerbat.R;


/**
 * Created by ianshinbrot on 4/30/15.
 */
public class AtBatScreen extends Activity {
    Button EndAtBat;
    Button Hit;
    Button Walk;
    Button Out;
    Button AdvanceBase;
    Button Undo;
    TextView titleView;
    AtBat atBat;
    Button helpButton;
    private String tag="AtBatScreen";
    private Context context;
    private boolean newAtBat = false;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_at_bat_update_screen);
        context = getApplicationContext();
        Intent intent = getIntent();

            atBat = (AtBat) intent.getExtras().getSerializable("atBat");

        Log.d(this.tag, "atbat load");
        this.loadFields();
        this.setOnClickListeners();
    }
    @Override
    protected  void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == 1) {        // return 1 is adding
            Log.d(this.tag, "Hit Updated");
            atBat = (AtBat) data.getExtras().getSerializable("atBat");
            Out.setEnabled(false);
            Walk.setEnabled(false);
            Undo.setEnabled(true);
            AdvanceBase.setEnabled(true);
            EndAtBat.setEnabled(true);
        }
        Base initialBase = atBat.getInitialBaseNum();
        String message = "";
        switch (initialBase) {
            case FIRST:
                message = "HIT A SINGLE";
                break;
            case SECOND:
                message = "HIT A DOUBLE";
                break;
            case THIRD:
                message = "HIT A TRIPLE";
                break;
            case HOME:
                message = "HOME RUN";
        }
        if (message.equals("")) {

        }
        else {
            showToastMessage(message);
        }
    }

    private OnClickListener endAtBat = new OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent();
            atBat.setFinalBase(atBat.getCurrentBase());
            intent.putExtra("atBat", atBat);
            setResult(2, intent);
            finish();
        }
    };

    private OnClickListener HitListener = new OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(AtBatScreen.this, HitPopUp.class);

            intent.putExtra("atBat", atBat);
            startActivityForResult(intent, 0);


        }
    };
    private OnClickListener WalkListener = new OnClickListener() {
        @Override
        public void onClick(View v) {
            atBat.setInitialBase(Base.FIRST);
            atBat.setWalk();
            String message = "Walk to first";
            showToastMessage(message);
            Out.setEnabled(false);
            Walk.setEnabled(false);
            Undo.setEnabled(true);
            EndAtBat.setEnabled(true);
        }
    };
    private OnClickListener OutListener = new OnClickListener() {
        @Override
        public void onClick(View v) {

            String message = "Out";
            showToastMessage(message);

        }
    };
    private OnClickListener AdvanceBaseListener = new OnClickListener() {
        @Override
        public void onClick(View v) {
            atBat.nextBase();
            String message = "";
            switch (atBat.getCurrentBase()) {
                case FIRST:
                    message = "advanced to first";
                    break;
                case SECOND:
                    message = "Advanced to second";
                    break;
                case THIRD:
                    message = "Advanced to third";
                    break;
                case HOME:
                    message = "Made it Home";
                    break;
            }
            showToastMessage(message);
        }
    };
    private OnClickListener UndoListener = new OnClickListener() {
        @Override
        public void onClick(View v) {
            atBat.revertBase();
            String message="";
            Base currentBase = atBat.getCurrentBase();
            Base initialBase = atBat.getInitialBaseEnum();
            if (atBat.getCurrentBase()==Base.ATBAT) {
                Undo.setEnabled(false);
                Hit.setEnabled(true);
                Out.setEnabled(true);
                AdvanceBase.setEnabled(false);
                EndAtBat.setEnabled(false);
            }
             if (currentBase==initialBase) {
                message = "start from scratch";
                atBat.UndoInitialBase();
                 Undo.setEnabled(false);
                 Hit.setEnabled(true);
                 Out.setEnabled(true);
                 AdvanceBase.setEnabled(false);
                 EndAtBat.setEnabled(false);
            }

            else {
                switch (currentBase) {
                    case FIRST:
                        atBat.revertBase();
                        message =  "Back at bat";
                        Undo.setEnabled(false);
                        Hit.setEnabled(true);
                        Out.setEnabled(true);
                        AdvanceBase.setEnabled(false);
                        EndAtBat.setEnabled(false);
                        break;
                    case SECOND:
                        atBat.revertBase();
                        message = "Back at first";
                        break;
                    case THIRD:
                        message = "Back at second";
                        atBat.revertBase();
                        break;
                    case HOME:
                        message = "Back at third";
                        atBat.revertBase();
                        break;
                }
            }
            atBat.setWalk();
            showToastMessage(message);
        }
    };
    private void showToastMessage(String message) {
        Toast.makeText(AtBatScreen.this,message, Toast.LENGTH_SHORT).show();
    }


    private void setOnClickListeners() {
        EndAtBat.setOnClickListener(endAtBat);
        Walk.setOnClickListener(WalkListener);
        Hit.setOnClickListener(HitListener);
        AdvanceBase.setOnClickListener(AdvanceBaseListener);
        Undo.setOnClickListener(UndoListener);
        Out.setOnClickListener(OutListener);
        Undo.setEnabled(false);
        EndAtBat.setEnabled(false);
    }
    private void loadFields() {

        Hit = (Button) findViewById(R.id.HitButton_AtBatScreen);
        Walk = (Button) findViewById(R.id.WalkButton_AtBatScreen);
        Out = (Button) findViewById(R.id.OutButton_AtBatScreen);
        AdvanceBase = (Button) findViewById(R.id.AdvanceBaseButton_AtBatScreen);
        Undo = (Button) findViewById(R.id.UndoBat_AtBatScreen);
        helpButton = (Button) findViewById(R.id.helpButton_listScreen);
        EndAtBat = (Button) findViewById(R.id.EndButton_atBatScreen);
        titleView = (TextView) findViewById(R.id.ScreenTitle_listView);
    }
}