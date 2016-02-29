package com.example.ianshinbro.trackerbat.UI;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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
    private void setOnClickListeners() {
        addatBat.setOnClickListener(addInning);
    }
    private void loadButtons() {
        addatBat = (Button) findViewById(R.id.addInning_AtBat_SetupPage);
        inningNumber = (TextView) findViewById(R.id.addInningText_AtBat_SetupPage);
    }
}
