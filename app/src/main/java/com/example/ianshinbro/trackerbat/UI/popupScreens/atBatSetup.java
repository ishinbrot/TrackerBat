package com.example.ianshinbro.trackerbat.UI.popupScreens;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.ianshinbro.trackerbat.data.model.AtBat;
import com.example.ianshinbro.trackerbat.R;

public class atBatSetup extends Activity {

    Button addatBat;
    TextView inningNumber;
    AtBat atBat;
    private String tag="AtBatSetup";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(this.tag, "entering At BatSet Up Screen");
        atBat = new AtBat();

        setContentView(R.layout.activity_at_bat_setup);
        this.loadButtons();
        this.setOnClickListeners();

    }

    /**
     * This adds an onclick listener to the add inning button
     */
    private View.OnClickListener addInning = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent();
            atBat.setInningNumber(Integer.parseInt(inningNumber.getText().toString()));
            intent.putExtra("atBat", atBat);
            setResult(1, intent);
            finish();

        }
    };
    /**
     * This adds a text watcher to the text fields to enable to the button
     */
    private TextWatcher addAtBatWatcher = new TextWatcher() {
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

    /**
     * This checks the fields for empty values
     */
    void checkFieldsForEmptyValues(){

        String s1 = inningNumber.getText().toString();

        if(s1.equals("")){
            addatBat.setEnabled(false);
        } else {
            addatBat.setEnabled(true);
        }
    }

    /**
     * This sets the on click listeners for the page
     */
    private void setOnClickListeners() {
        addatBat.setOnClickListener(addInning);
        addatBat.setEnabled(false);
        inningNumber.addTextChangedListener(addAtBatWatcher);
    }

    /**
     * This loads the buttons on the page
     */
    private void loadButtons() {
        addatBat = (Button) findViewById(R.id.addInning_AtBat_SetupPage);
        inningNumber = (TextView) findViewById(R.id.addInningText_AtBat_SetupPage);
    }
}
