package com.example.ianshinbro.trackerbat.UI;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.ianshinbro.trackerbat.Implentation.Game;
import com.example.ianshinbro.trackerbat.Implentation.Player;
import com.example.ianshinbro.trackerbat.R;

public class UpdateGame extends Activity {
    Button  updateGame;
    EditText homeTeam;
    EditText awayTeam;
    EditText inningTotal;
    Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_game);
        intent = getIntent();
        Game game = (Game)intent.getExtras().getSerializable("game");
        this.initializeButtons();
        this.setText(game);
        this.setOnClickListeners();
    }

    private void setOnClickListeners() {
        updateGame.setOnClickListener(saveGameListener);
        homeTeam.addTextChangedListener(gameTextWatcher);
        awayTeam.addTextChangedListener(gameTextWatcher);

    }
    private View.OnClickListener saveGameListener = new View.OnClickListener() {
        public void onClick(View v) {
            // register selection
            //TODO Retrieve fields and create and send a player object
            updateGame();

        }
    };
    private void updateGame() {
        Game game = new Game();
        game.setHomeTeam(homeTeam.getText().toString());
        game.setAwayTeam(awayTeam.getText().toString());
        if (inningTotal.getText().length()!=0) {
            game.setInningNumber(Integer.parseInt(inningTotal.getText().toString()));
        }
        Intent intent = new Intent();
        intent.putExtra("game", game);
        setResult(2,intent);     // result is 1 for add player

        finish();       // finishing activity
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
        String s3 = inningTotal.getText().toString();

        if(s1.equals("")|| s2.equals("") || s3.equals("")){
            updateGame.setEnabled(false);
        } else {
            updateGame.setEnabled(true);
        }
    }

    private void setText(Game game) {
        updateGame.setText(R.string.SaveChanges_BTNText);
        homeTeam.setText(game.getHomeTeam());
        awayTeam.setText(game.getAwayTeam());
        inningTotal.setText(Integer.toString(game.getInningNumber()));

    }

    private void initializeButtons() {
        updateGame = (Button) findViewById(R.id.addGame_AddGameScreen);
        homeTeam = (EditText) findViewById(R.id.HomeTeam_AddGameScreen);
        awayTeam = (EditText) findViewById(R.id.AwayTeam_AddGameScreen);
        inningTotal = (EditText) findViewById(R.id.InningNumber_AddGameScreen);

        updateGame.setEnabled(false);
    }
}