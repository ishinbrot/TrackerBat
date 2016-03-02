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
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.util.Log;

import com.example.ianshinbro.trackerbat.Implentation.AtBat;
import com.example.ianshinbro.trackerbat.Implentation.Game;
import com.example.ianshinbro.trackerbat.R;
import com.example.ianshinbro.trackerbat.UI.Adapters.AtBatAdapter;
import com.example.ianshinbro.trackerbat.UI.Adapters.DividerItemDecoration;
import com.example.ianshinbro.trackerbat.UI.Adapters.ItemTouchHelperCallBack;
import com.example.ianshinbro.trackerbat.UI.Adapters.OnStartDragListener;
import com.example.ianshinbro.trackerbat.UI.Adapters.PlayerHolder;
import com.example.ianshinbro.trackerbat.UI.Adapters.listItemListener;

import java.util.ArrayList;


/**
 * Created by ianshinbrot on 4/30/15.
 */
public class AtBatListScreen extends Activity {
    RecyclerView atBatList;
    FloatingActionButton addAtBat;
    Button helpButton;
    TextView titleView;
    Button endGameButton;
    Game game;
    AtBat atBat;
    private LinearLayoutManager linearLayoutManager;
    int totalinList = -1;
    private int selectedPosition = -1;
    private String tag = "AtBatListScreen";
    private Context context;
    private ItemTouchHelper mItemTouchHelper;
    private AtBatAdapter atBatAdapter;
    private boolean firstPlayer = false;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_view);
        context = getApplicationContext();
        Intent intent = getIntent();

        game = (Game) intent.getExtras().getSerializable("game");
        game.updateAtBats(game.getAtBats());
        Log.d(this.tag, "atbat load");
        this.loadFields();
        this.setOnClickListeners();
        this.setText();
        this.loadList();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == 1) {        // return 1 is adding
            Log.d(this.tag, "Adding at atBat");
            atBat = (AtBat) data.getExtras().getSerializable("atBat");
            game.addAtBat(atBat);
            totalinList++;
            selectedPosition = totalinList;
            editAtBat();
        }
        if (resultCode == 2) {        // new at bat
            Log.d(this.tag, "Updating at Bat");
            atBat = (AtBat) data.getExtras().getSerializable("atBat");
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
        startActivityForResult(intent, 3);


    }

    private OnClickListener endGameListener = new OnClickListener() {
        @Override
        public void onClick(View v) {
            game.endGame();
            Intent intent = new Intent(AtBatListScreen.this, GameEnd.class);
            intent.putExtra("game", game);
            startActivityForResult(intent, 0);
        }
    };

    private void loadList() {

        atBatAdapter = new AtBatAdapter(game.getAtBats(),new OnStartDragListener() {
            @Override
            public void onStartDrag(RecyclerView.ViewHolder viewHolder) {
                mItemTouchHelper.startDrag(viewHolder);
            }

            @Override
            public void onUpdate(RecyclerView.ViewHolder viewHolder) {
                int position = viewHolder.getAdapterPosition();
                updateAtBat(position);
            }

            @Override
            public void onSelect(RecyclerView.ViewHolder viewHolder) {
                int position = viewHolder.getAdapterPosition();
                selectAtBat(position);
            }

        });
        atBatList.setAdapter(atBatAdapter);
        atBatList.setHasFixedSize(true);
        linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        atBatList.setLayoutManager(linearLayoutManager);

        RecyclerView.ItemDecoration itemDecoration =
                new DividerItemDecoration(this, LinearLayoutManager.VERTICAL);
        atBatList.addItemDecoration(itemDecoration);

        ItemTouchHelper.Callback callback =
                new ItemTouchHelperCallBack(atBatAdapter);
        mItemTouchHelper = new ItemTouchHelper(callback);
        mItemTouchHelper.attachToRecyclerView(atBatList);
    }

    public void selectAtBat(int position) {
        AtBat atBat = game.getAtBats().get(position);
        selectedPosition = position;

        Intent selectedAtBat = new Intent(AtBatListScreen.this, AtBatScreen.class);

        selectedAtBat.putExtra("newAtBat", false);
        Log.d(tag, "size " + game.getAtBats().size());
        selectedAtBat.putExtra("atBat", atBat);

        startActivityForResult(selectedAtBat, 3);

    }

    public void updateAtBat(int position) {

        AtBat atBat = game.getAtBats().get(position);
        selectedPosition = position;
        Intent attBat = new Intent(AtBatListScreen.this, AtBatScreen.class);

        attBat.putExtra("atBat", atBat);

        startActivityForResult(attBat, 1);
    }

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

        atBatList = (RecyclerView) findViewById(R.id.listView_listScreen);
        addAtBat = (FloatingActionButton) findViewById(R.id.addBTN_listScreen);
        helpButton = (Button) findViewById(R.id.helpButton_listScreen);
        endGameButton = (Button) findViewById(R.id.endListButton);
        titleView = (TextView) findViewById(R.id.ScreenTitle_listView);
    }

}