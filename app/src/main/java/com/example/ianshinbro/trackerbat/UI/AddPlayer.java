package com.example.ianshinbro.trackerbat.UI;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.ianshinbro.trackerbat.Implentation.Player;
import com.example.ianshinbro.trackerbat.R;

public class AddPlayer extends Activity {
    Button  savePlayer;
    TextInputLayout firstName;
    TextInputLayout lastName;
    TextInputLayout nickName;
    TextInputLayout playerNumber;
    private Context applicationContext;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_player);
        applicationContext = getApplicationContext();
        this.initializeButtons();
        this.setOnClickListeners();

        this.setText();
    }

    private View.OnClickListener addPlayer = new View.OnClickListener() {
        public void onClick(View v) {
            // register selection
            //TODO Retrieve fields and create and send a player object
            savePlayer();

        }
    };

    private void savePlayer() {
        Player player = new Player();
        player.setFirstName(firstName.getEditText().getText().toString());
        player.setLastName(lastName.getEditText().getText().toString());
        if (nickName.getEditText().getText().length()!=0) {
            player.setNickName(nickName.getEditText().getText().toString());
        }
        if (playerNumber.getEditText().getText().length()!=0) {
            player.setNumber(Integer.parseInt(playerNumber.getEditText().getText().toString()));
        }
        Intent intent = new Intent();
        intent.putExtra("player", player);
       setResult(1,intent);     // result is 1 for add player

        finish();       // finishing activity
    }
    private void setText() {
        savePlayer.setText(R.string.AddPlayer_BtnText);

    }

    private void setOnClickListeners() {
        savePlayer.setOnClickListener(addPlayer);
        firstName.getEditText().addTextChangedListener(mTextWatcher);
        lastName.getEditText().addTextChangedListener(mTextWatcher);
        playerNumber.getEditText().addTextChangedListener(mTextWatcher);

    }
    private TextWatcher mTextWatcher = new TextWatcher() {
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

        String s1 = firstName.getEditText().getText().toString();
        String s2 = lastName.getEditText().getText().toString();
        String s3 = playerNumber.getEditText().getText().toString();

        if(s1.equals("")|| s2.equals("") || s3.equals("")){
            savePlayer.setEnabled(false);
        } else {
            savePlayer.setEnabled(true);
        }
    }

    private void initializeButtons() {
        savePlayer = (Button) findViewById(R.id.saveButton_Update_AddPage);
        firstName = (TextInputLayout) findViewById(R.id.layout_FirstName_UpdatePlayerScreen);
        lastName = (TextInputLayout) findViewById(R.id.layout_LastName_UpdatePlayerScreen);
        nickName = (TextInputLayout) findViewById(R.id.layout_NickName_UpdatePlayerScreen);
        playerNumber = (TextInputLayout) findViewById(R.id.layout_PlayerNumber_UpdatePlayerScreen);
        savePlayer.setEnabled(false);
    }
}
