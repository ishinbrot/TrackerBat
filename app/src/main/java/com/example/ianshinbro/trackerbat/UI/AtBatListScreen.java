package com.example.ianshinbro.trackerbat.UI;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.util.Log;

import com.example.ianshinbro.trackerbat.Implentation.AtBat;
import com.example.ianshinbro.trackerbat.Implentation.Game;
import com.example.ianshinbro.trackerbat.R;
import com.example.ianshinbro.trackerbat.UI.Adapters.AtBatAdapter;

import java.util.ArrayList;


/**
 * Created by ianshinbrot on 4/30/15.
 */
public class AtBatListScreen extends Activity {
    ListView playerList;
    FloatingActionButton addAtBat;
    Button helpButton;
    TextView titleView;
    Button endGameButton;
    Game game;
    AtBat atBat;
    int totalinList=-1;
    private int selectedPosition=-1;
    private String tag="AtBatListScreen";
    private Context context;
    private ArrayList<AtBat> atbats = new ArrayList<>();
    private AtBatAdapter atBatAdapter;
    private boolean firstPlayer = false;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_view);
        context = getApplicationContext();
        Intent intent = getIntent();

       game = (Game) intent.getExtras().getSerializable("game");
        Log.d(this.tag, "atbat load");
        this.loadFields();
        this.setOnClickListeners();
        this.setText();
        this.loadList();


    }
    @Override
    protected  void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == 1) {        // return 1 is adding
            Log.d(this.tag, "Adding at atBat");
            atBat = (AtBat) data.getExtras().getSerializable("atBat");
            atbats.add(atBat);
            game.addAtBat(atBat);
            totalinList++;
            selectedPosition = totalinList;
            editAtBat();
        }
        if (resultCode == 2) {        // new at bat
            Log.d(this.tag, "Updating at Bat");
            atBat = (AtBat) data.getExtras().getSerializable("atBat");
            atbats.set(selectedPosition, atBat);
            game.updateGameAtIndex(selectedPosition, atBat);

            selectedPosition = -1;        // deselect current position
        }
        if (resultCode == 3) {
            Log.d(this.tag, "Ending Game");
            game = (Game) data.getExtras().getSerializable("game");
            Intent intent = new Intent();
            intent.putExtra("game", game);
            setResult(3, intent);
            finish();

        }
        this.loadList();
    }
    private OnClickListener newAtBat = new OnClickListener() {
        public void onClick(View v) {
            // register selection

            Intent addBat = new Intent(AtBatListScreen.this, atBatSetup.class);

            startActivityForResult(addBat, 0);

        }
    };
    private void editAtBat() {
        Intent intent = new Intent(AtBatListScreen.this, AtBatScreen.class);
        intent.putExtra("atBat", atBat);
        startActivityForResult(intent, 0);


    }
    private OnClickListener endGameListener = new OnClickListener() {
        @Override
        public void onClick(View v) {
            game.endGame();
            Intent intent = new Intent(AtBatListScreen.this,GameEnd.class);
            intent.putExtra("game", game);
            startActivityForResult(intent, 0);
        }
    };
    private void loadList() {

        atBatAdapter = new AtBatAdapter(this, R.layout.atbat_list, atbats);
        playerList.setAdapter(atBatAdapter);
        playerList.setDividerHeight(2);
        playerList.setClickable(true);
        playerList.setOnItemLongClickListener(updateAtBat);
        playerList.setOnItemClickListener(selectAtBat);
        if (atbats.size() == 0) {

            playerList.setVisibility(View.GONE);
            addAtBat.setVisibility(View.VISIBLE);
            firstPlayer = true;
        } else {
            playerList.setVisibility(View.VISIBLE);
            firstPlayer = false;
        }
    }

    private AdapterView.OnItemClickListener selectAtBat = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            AtBat atBat = atBatAdapter.getItem(position);
            selectedPosition = position;

            Intent selectedAtBat = new Intent(AtBatListScreen.this,AtBatScreen.class);

           selectedAtBat.putExtra("newAtBat",false);
            selectedAtBat.putExtra("atBat",atBat);

            startActivity(selectedAtBat);

        }
    };
    private  AdapterView.OnItemLongClickListener updateAtBat = new AdapterView.OnItemLongClickListener() {
        @Override
        public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
            AtBat atBat = atBatAdapter.getItem(position);
            selectedPosition=position;
            Intent attBat = new Intent(AtBatListScreen.this, AtBatScreen.class);

            attBat.putExtra("atBat", atBat);

            startActivityForResult(attBat, 1);
            return true;
        }
    };

    private void setText() {
        titleView.setText(R.string.atbatScreenTitleText);
        endGameButton.setVisibility(View.VISIBLE);
        endGameButton.setText(R.string.EndGameBtn);

    }

    private void setOnClickListeners() {
        addAtBat.setOnClickListener(newAtBat);
        endGameButton.setOnClickListener(endGameListener);

    }
    private void loadFields() {

        playerList = (ListView) findViewById(R.id.listView_listScreen);
        addAtBat = (FloatingActionButton) findViewById(R.id.addBTN_listScreen);
        helpButton = (Button) findViewById(R.id.helpButton_listScreen);
        endGameButton = (Button) findViewById(R.id.endGameBTN);
        titleView = (TextView) findViewById(R.id.ScreenTitle_listView);
    }

}