package com.example.ianshinbro.trackerbat.UI;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.util.Log;
import android.widget.Toast;

import com.example.ianshinbro.trackerbat.AppDataBase;
import com.example.ianshinbro.trackerbat.Implentation.Player;
import com.example.ianshinbro.trackerbat.Implentation.Player_Table;
import com.example.ianshinbro.trackerbat.R;
import com.example.ianshinbro.trackerbat.UI.Adapters.adapterHelpers.DividerItemDecoration;
import com.example.ianshinbro.trackerbat.UI.Adapters.adapterHelpers.ItemTouchHelperCallBack;
import com.example.ianshinbro.trackerbat.UI.Adapters.adapterHelpers.OnStartDragListener;
import com.example.ianshinbro.trackerbat.UI.Adapters.PlayerAdapter;
import com.example.ianshinbro.trackerbat.UI.popupScreens.AddPlayer;
import com.example.ianshinbro.trackerbat.UI.popupScreens.UpdatePlayer;
import com.raizlabs.android.dbflow.config.FlowConfig;
import com.raizlabs.android.dbflow.config.FlowManager;
import com.raizlabs.android.dbflow.sql.language.SQLite;
import com.raizlabs.android.dbflow.structure.database.DatabaseWrapper;
import com.raizlabs.android.dbflow.structure.database.transaction.ProcessModelTransaction;
import com.raizlabs.android.dbflow.structure.database.transaction.Transaction;

import java.util.ArrayList;
import java.util.Arrays;


/**
 * Created by ianshinbrot on 4/30/15.
 */
public class playerListScreen extends AppCompatActivity {
    RecyclerView playerList;
    FloatingActionButton addPlayerButton;
    int totalinList=0;
    Button helpButton;
    TextView titleView;
    private int selectedPosition=-1;
    private String tag="PlayerListScreen";
    private Context context;
    private ArrayList<Player> players;
    private PlayerAdapter playerAdapter;
    private LinearLayoutManager linearLayoutManager;
    private ItemTouchHelper mItemTouchHelper;
    private Toolbar toolbar;

    private boolean firstPlayer = false;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_view);
        // create the delegate
        context = getApplicationContext();

        players = new ArrayList();
        players.addAll(SQLite.select().from(Player.class).queryList());
        if (players == null)
            players = new ArrayList<>();
        Log.d(this.tag, "initialLoad");
            this.loadFields();
            this.setOnClickListeners();
        this.setUpToolbar();
        this.loadList();


    }
    @Override
    protected  void onActivityResult(int requestCode, int resultCode, Intent data) {
       super.onActivityResult(requestCode, resultCode, data);
        totalinList= playerAdapter.getItemCount()-1;        // ensure the total is correct
        // This is when adding a player
        if (resultCode==1) {        // return 1 is adding
            Log.d(this.tag, "Adding player");
            Player player = (Player) data.getExtras().getSerializable("player");

            //TODO Check if player number already exists in database

            if (SQLite.select().from(Player.class).where(Player_Table.number.is(player.getNumber())).queryList().size()>0)
            {
                Toast.makeText(this.context,"Please select a number not already taken", Toast.LENGTH_LONG).show();
            }
            else {
                totalinList++;
                selectedPosition = totalinList;
                player.setId(selectedPosition);
                player.save();
                players.add(player);
                Intent selectedPlayer = new Intent(playerListScreen.this, gameOverviewScreen.class);
                selectedPlayer.putExtra("player", player);
                startActivityForResult(selectedPlayer, 3);
            }
        }
        // This is when updating a player
        if (resultCode==2) {        // updates
            Log.d(this.tag,"Updating player");
            Player player = (Player) data.getExtras().getSerializable("player");
            playerAdapter.updatePlayer(player, selectedPosition);

        }
        if (resultCode==3) {
            Log.d(this.tag, "Updating player with game");
            Player player = (Player) data.getExtras().getSerializable("player");
            Log.d(this.tag, "games" + player.getGames().size());
            playerAdapter.updatePlayer(player, selectedPosition);
        }
    }
    private OnClickListener addPlayer = new OnClickListener() {
        public void onClick(View v) {
            // register selection

            Intent addPlayer = new Intent(playerListScreen.this, AddPlayer.class);

            startActivityForResult(addPlayer, 3);

        }
    };
    private void loadList() {

        // creates a new player adapter with a custom listener for selected, dragging, and updating
        playerAdapter = new PlayerAdapter(players, new OnStartDragListener() {
            @Override
            public void onStartDrag(RecyclerView.ViewHolder viewHolder) {
                mItemTouchHelper.startDrag(viewHolder);
            }

            @Override
            public void onUpdate(RecyclerView.ViewHolder viewHolder) {
                selectedPosition =  viewHolder.getAdapterPosition();
                updatePlayer(selectedPosition);

            }

            @Override
            public void onSelect(RecyclerView.ViewHolder viewHolder) {
                selectedPosition =  viewHolder.getAdapterPosition();
                selectPlayer(selectedPosition);
            }
        });
        playerList.setAdapter(playerAdapter);
        playerList.setHasFixedSize(true);
        linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        playerList.setLayoutManager(linearLayoutManager);

        RecyclerView.ItemDecoration itemDecoration =
                new DividerItemDecoration(this, LinearLayoutManager.VERTICAL);
        playerList.addItemDecoration(itemDecoration);
        ItemTouchHelper.Callback callback =
                new ItemTouchHelperCallBack(playerAdapter);
        mItemTouchHelper = new ItemTouchHelper(callback);
        mItemTouchHelper.attachToRecyclerView(playerList);
    }
    private void selectPlayer(int position) {
        Player player = players.get(position);

        selectedPosition = position;

        Intent selectedPlayer = new Intent(playerListScreen.this, gameOverviewScreen.class);

        selectedPlayer.putExtra("player", player);

        startActivityForResult(selectedPlayer, 3);

    }

        public void updatePlayer(int position) {
        Player player = players.get(position);
        selectedPosition=position;
        Intent updatePlayer = new Intent(playerListScreen.this, UpdatePlayer.class);

        updatePlayer.putExtra("player", player);

            startActivityForResult(updatePlayer, 3);
    }

    private void setOnClickListeners() {
        addPlayerButton.setOnClickListener(addPlayer);
        addPlayerButton.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                {
                    Log.d(tag, "Add Player");
                    return true;
                }
            }
        });
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        switch (item.getItemId()) {

            default:
                return super.onOptionsItemSelected(item);
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.playerlist_menu, menu);
        return true;
    }

    /**
     * This method is called to set up the toolbar
     */
    private void setUpToolbar() {
        toolbar.setTitle(R.string.playerListTitleText);

        setSupportActionBar(toolbar);
    }

    /**
     * This method loads the fields for the given page
     */
    private void loadFields() {

        playerList = (RecyclerView) findViewById(R.id.listView_listScreen);
        addPlayerButton = (FloatingActionButton) findViewById(R.id.addBTN_listScreen);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
    }

}