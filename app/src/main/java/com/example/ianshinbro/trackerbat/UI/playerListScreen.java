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

import com.example.ianshinbro.trackerbat.Implentation.Player;
import com.example.ianshinbro.trackerbat.R;
import com.example.ianshinbro.trackerbat.UI.Adapters.PlayerAdapter;

import java.util.ArrayList;


/**
 * Created by ianshinbrot on 4/30/15.
 */
public class playerListScreen extends Activity {
    ListView playerList;
    FloatingActionButton addPlayerButton;
    Button helpButton;
    TextView titleView;
    private int selectedPosition=-1;
    private String tag="PlayerListScreen";
   private Context context;
    private ArrayList<Player> players = new ArrayList<>();
    private PlayerAdapter playerAdapter;
    private boolean firstPlayer = false;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_view);
        context = getApplicationContext();
        Log.d(this.tag, "initialLoad");
            this.loadFields();
            this.setOnClickListeners();
        this.setText();
        this.loadList();


    }
    @Override
    protected  void onActivityResult(int requestCode, int resultCode, Intent data) {
       super.onActivityResult(requestCode, resultCode, data);

        if (resultCode==1) {        // return 1 is adding
            Log.d(this.tag,"Adding player");
            Player player = (Player) data.getExtras().getSerializable("player");

            players.add(player);
        }
        if (resultCode==2) {        // updates
            Log.d(this.tag,"Updating player");
            Player player = (Player) data.getExtras().getSerializable("player");
            players.set(selectedPosition, player);
        }
        this.loadList();
        selectedPosition=-1;
    }
    private OnClickListener addPlayer = new OnClickListener() {
        public void onClick(View v) {
            // register selection

            Intent addPlayer = new Intent(playerListScreen.this, AddPlayer.class);

            startActivityForResult(addPlayer, 0);

        }
    };

    private void loadList() {

        playerAdapter = new PlayerAdapter(this, R.layout.player_list, players);
        playerList.setAdapter(playerAdapter);
        playerList.setClickable(true);
        playerList.setOnItemLongClickListener(updatePlayer);
        playerList.setOnItemClickListener(selectPlayer);
        if (players.size() == 0) {

            playerList.setVisibility(View.GONE);
            addPlayerButton.setVisibility(View.VISIBLE);
            firstPlayer = true;
        } else {
            playerList.setVisibility(View.VISIBLE);
            firstPlayer = false;
        }
    }

    private AdapterView.OnItemClickListener selectPlayer = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            Player player = playerAdapter.getItem(position);
            selectedPosition = position;

            Intent selectedPlayer = new Intent(playerListScreen.this,gameOverviewScreen.class);

            selectedPlayer.putExtra("player",player);

            startActivity(selectedPlayer);

        }
    };
private  AdapterView.OnItemLongClickListener updatePlayer = new AdapterView.OnItemLongClickListener() {
    @Override
    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
        Player player = playerAdapter.getItem(position);
        selectedPosition=position;
        Intent updatePlayer = new Intent(playerListScreen.this, UpdatePlayer.class);

        updatePlayer.putExtra("player", player);

        startActivityForResult(updatePlayer, 1);
        return true;
    }
};

    private void setText() {
        titleView.setText(R.string.playerListTitleText);

    }

    private void setOnClickListeners() {
        addPlayerButton.setOnClickListener(addPlayer);


    }
    private void loadFields() {

        playerList = (ListView) findViewById(R.id.listView_listScreen);
        addPlayerButton = (FloatingActionButton) findViewById(R.id.addBTN_listScreen);
        helpButton = (Button) findViewById(R.id.helpButton_listScreen);
        titleView = (TextView) findViewById(R.id.ScreenTitle_listView);
    }

}