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

import com.example.ianshinbro.trackerbat.Implentation.Game;
import com.example.ianshinbro.trackerbat.R;
import com.example.ianshinbro.trackerbat.UI.Adapters.GameAdapter;

import java.util.ArrayList;


/**
 * Created by ianshinbrot on 4/30/15.
 */
public class gameOverviewScreen extends Activity {
    ListView gameList;
    FloatingActionButton addGameButton;
    Button helpButton;
    TextView titleView;
    int totalinList=-1;
    private String tag="gameOverviewScreen";
    private int selectedPosition=-1;
    private Context context;
    private ArrayList<Game> games = new ArrayList<>();
    private GameAdapter gameAdapter;
    private boolean firstPlayer = false;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_view);
        context = getApplicationContext();
        Log.d(this.tag, "entering gameListScreen");
        this.loadFields();
        this.setOnClickListeners();
        this.setText();
        this.loadList();
    }
    @Override
    protected  void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode==1) {        // return 1 is adding
            totalinList++;
            Game game = (Game) data.getExtras().getSerializable("game");
            games.add(game);
            selectedPosition=totalinList;
        }
        if (resultCode==2) {        // updates
            Game game = (Game) data.getExtras().getSerializable("game");
            games.set(selectedPosition, game);
            selectedPosition=-1;        // deselect current position
        }
        if (resultCode==3) {        // end game
            Game game = (Game) data.getExtras().getSerializable("game");
            games.set(selectedPosition,game);
            selectedPosition=-1;        // deselect current position
        }

        this.loadList();
    }
    private OnClickListener addGameListener = new OnClickListener() {
        public void onClick(View v) {
            // register selection

            Intent addGame = new Intent(gameOverviewScreen.this, AddGame.class);

            startActivityForResult(addGame, 0);

        }
    };

    private void loadList() {

        gameAdapter = new GameAdapter(this, R.layout.game_list, games);
        gameList.setAdapter(gameAdapter);
        gameList.setDividerHeight(2);
        gameList.setClickable(true);
       // gameList.setOnItemLongClickListener(updatePlayer);
        gameList.setOnItemClickListener(selectGame);
        if (games.size() == 0) {

            gameList.setVisibility(View.GONE);
            addGameButton.setVisibility(View.VISIBLE);
            firstPlayer = true;
        } else {
            gameList.setVisibility(View.VISIBLE);
            firstPlayer = false;
        }
    }

    private AdapterView.OnItemClickListener selectGame = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            Game game = gameAdapter.getItem(position);
            selectedPosition = position;

            Intent selectedGame = new Intent(gameOverviewScreen.this,AtBatListScreen.class);

            selectedGame.putExtra("game",game);

            startActivityForResult(selectedGame,3);
        }
    };

    private void setText() {
        titleView.setText(R.string.gameListScreenTitleText);

    }

    private void setOnClickListeners() {
        addGameButton.setOnClickListener(addGameListener);

    }
    private void loadFields() {
        gameList = (ListView) findViewById(R.id.listView_listScreen);
        addGameButton = (FloatingActionButton) findViewById(R.id.addBTN_listScreen);
        helpButton = (Button) findViewById(R.id.helpButton_listScreen);
        titleView = (TextView) findViewById(R.id.ScreenTitle_listView);
    }

}