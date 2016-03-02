package com.example.ianshinbro.trackerbat.UI;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.util.Log;

import com.example.ianshinbro.trackerbat.Implentation.Game;
import com.example.ianshinbro.trackerbat.Implentation.Player;
import com.example.ianshinbro.trackerbat.R;
import com.example.ianshinbro.trackerbat.UI.Adapters.DividerItemDecoration;
import com.example.ianshinbro.trackerbat.UI.Adapters.ItemTouchHelperCallBack;
import com.example.ianshinbro.trackerbat.UI.Adapters.OnStartDragListener;
import com.example.ianshinbro.trackerbat.UI.Adapters.PlayerAdapter;
import com.example.ianshinbro.trackerbat.UI.Adapters.PlayerHolder;
import com.example.ianshinbro.trackerbat.UI.Adapters.listItemListener;

import java.util.ArrayList;


/**
 * Created by ianshinbrot on 4/30/15.
 */
public class playerListScreen extends Activity{
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
    private boolean firstPlayer = false;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_view);
        context = getApplicationContext();
        players = new ArrayList<>();
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
            Log.d(this.tag, "Adding player");
            Player player = (Player) data.getExtras().getSerializable("player");
            totalinList++;
            selectedPosition = totalinList;
            players.add(player);


            Intent selectedPlayer = new Intent(playerListScreen.this,gameOverviewScreen.class);

            selectedPlayer.putExtra("player", player);

         //   startActivityForResult(selectedPlayer,3);
        }
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
        ItemTouchHelper.SimpleCallback simpleItemTouchCallback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {

            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int swipeDir) {
                //Remove swiped item from list and notify the RecyclerView
            }

        };

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleItemTouchCallback);
    }
    private OnClickListener addPlayer = new OnClickListener() {
        public void onClick(View v) {
            // register selection

            Intent addPlayer = new Intent(playerListScreen.this, AddPlayer.class);

            startActivityForResult(addPlayer, 3);

        }
    };

    private void loadList() {


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

    private void setText() {
        titleView.setText(R.string.playerListTitleText);

    }

    private void setOnClickListeners() {
        addPlayerButton.setOnClickListener(addPlayer);

    }

    private void loadFields() {

        playerList = (RecyclerView) findViewById(R.id.listView_listScreen);
        addPlayerButton = (FloatingActionButton) findViewById(R.id.addBTN_listScreen);
        helpButton = (Button) findViewById(R.id.helpButton_listScreen);
        titleView = (TextView) findViewById(R.id.ScreenTitle_listView);
    }

}