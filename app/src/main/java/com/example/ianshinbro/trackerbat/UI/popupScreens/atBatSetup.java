package com.example.ianshinbro.trackerbat.UI.popupScreens;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.ianshinbro.trackerbat.Implentation.AtBat;
import com.example.ianshinbro.trackerbat.Implentation.Base;
import com.example.ianshinbro.trackerbat.R;

import org.w3c.dom.Text;

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

    void checkFieldsForEmptyValues(){

        String s1 = inningNumber.getText().toString();

        if(s1.equals("")){
            addatBat.setEnabled(false);
        } else {
            addatBat.setEnabled(true);
        }
    }
    private void setOnClickListeners() {
        addatBat.setOnClickListener(addInning);
        addatBat.setEnabled(false);
        inningNumber.addTextChangedListener(addAtBatWatcher);
    }
    private void loadButtons() {
        addatBat = (Button) findViewById(R.id.addInning_AtBat_SetupPage);
        inningNumber = (TextView) findViewById(R.id.addInningText_AtBat_SetupPage);
    }
}
