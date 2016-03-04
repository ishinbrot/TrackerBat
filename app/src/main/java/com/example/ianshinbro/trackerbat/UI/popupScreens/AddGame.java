package com.example.ianshinbro.trackerbat.UI.popupScreens;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.ianshinbro.trackerbat.Implentation.Game;
import com.example.ianshinbro.trackerbat.R;

public class AddGame extends Activity {
    Button  addGame;
    EditText homeTeam;
    EditText awayTeam;
    EditText inningTotal;
    private Context applicationContext;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_game);
        applicationContext = getApplicationContext();
        this.initializeButtons();
        this.setOnClickListeners();

        this.setText();
    }

    private View.OnClickListener newGame = new View.OnClickListener() {
        public void onClick(View v) {
            // register selection
            //TODO Retrieve fields and create and send a player object
            saveGame();

        }
    };

    private void saveGame() {
        Game game = new Game();
        game.setHomeTeam(homeTeam.getText().toString());
        game.setAwayTeam(awayTeam.getText().toString());
        if (inningTotal.getText().length()!=0) {
            game.setInningNumber(Integer.parseInt(inningTotal.getText().toString()));
        }
        Intent intent = new Intent();
        intent.putExtra("game", game);
        setResult(1,intent);     // result is 1 for add player

        finish();       // finishing activity
    }
    private void setText() {
        addGame.setText(R.string.SaveChanges_BTNText);

    }

    private void setOnClickListeners() {
        addGame.setOnClickListener(newGame);
        homeTeam.addTextChangedListener(gameTextWatcher);
        awayTeam.addTextChangedListener(gameTextWatcher);


    }
    private TextWatcher gameTextWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        }

        @Override
        public void afterTextChanged(Editable editable) {
            // check Fields For Empty Values
            checkFieldsForEmptyValues();
        }
    };

    void checkFieldsForEmptyValues(){

        String s1 = homeTeam.getText().toString();
        String s2 = awayTeam.getText().toString();

        if(s1.equals("")|| s2.equals("")){
            addGame.setEnabled(false);
        } else {
            addGame.setEnabled(true);
        }
    }

    private void initializeButtons() {
        addGame = (Button) findViewById(R.id.addGame_AddGameScreen);
        homeTeam = (EditText) findViewById(R.id.HomeTeam_AddGameScreen);
        awayTeam = (EditText) findViewById(R.id.AwayTeam_AddGameScreen);
        inningTotal = (EditText) findViewById(R.id.InningNumber_AddGameScreen);

        addGame.setEnabled(false);
    }
}
