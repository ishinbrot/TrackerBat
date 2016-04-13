package com.example.ianshinbro.trackerbat.UI.popupScreens;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.ianshinbro.trackerbat.data.model.Player;
import com.example.ianshinbro.trackerbat.R;

public class UpdatePlayer extends Activity {
    Button  savePlayer;
    EditText firstName;
    EditText lastName;
    EditText nickName;
    EditText playerNumber;
    Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_player);
        intent = getIntent();
        Player player = (Player)intent.getExtras().getSerializable("player");
        this.initializeButtons();
        this.setText(player);
        this.setOnClickListeners();
    }

    private void updatePlayer() {
        Player player = new Player();
        player.setFirstName(firstName.getText().toString());
        player.setLastName(lastName.getText().toString());
        if (nickName.getText().length() != 0) {
            player.setNickName(nickName.getText().toString());
        }
        if (playerNumber.getText().length() != 0) {
            player.setNumber(Integer.parseInt(playerNumber.getText().toString()));
        }
        Intent intent = new Intent();
        intent.putExtra("player", player);
        setResult(2, intent);

        finish();       // finishing activity
    }

    /**
     * This sets the onclick listeners
     */
    private void setOnClickListeners() {
        savePlayer.setOnClickListener(updatePlayer);
        firstName.addTextChangedListener(mTextWatcher);
        lastName.addTextChangedListener(mTextWatcher);
        playerNumber.addTextChangedListener(mTextWatcher);
        nickName.addTextChangedListener(mTextWatcher);

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

    private View.OnClickListener updatePlayer = new View.OnClickListener() {
        public void onClick(View v) {
            // register selection
            //TODO Retrieve fields and create and send a player object
            updatePlayer();

        }
    };

    void checkFieldsForEmptyValues(){

        String s1 = firstName.getText().toString();
        String s2 = lastName.getText().toString();
        String s3 = playerNumber.getText().toString();

        if(s1.equals("")|| s2.equals("") || s3.equals("")){
            savePlayer.setEnabled(false);
        } else {
            savePlayer.setEnabled(true);
        }
    }

    private void setText(Player player) {
        savePlayer.setText(R.string.SaveChanges_BTNText);
        firstName.setText(player.getFirstName());
        lastName.setText(player.getLastName());
        playerNumber.setText(Integer.toString(player.getNumber()));
        if (player.nickNameExists()) {
            nickName.setText(player.getNickName());
        }

    }

        private void initializeButtons() {
            savePlayer = (Button) findViewById(R.id.saveButton_Update_AddPage);
            firstName = (EditText) findViewById(R.id.FirstName_UpdatePlayerScreen);
            lastName = (EditText) findViewById(R.id.LastName_UpdatePlayerScreen);
            nickName = (EditText) findViewById(R.id.NickName_UpdatePlayerScreen);
            playerNumber = (EditText) findViewById(R.id.PlayerNumber_UpdatePlayerScreen);
            savePlayer.setEnabled(false);
        }
}