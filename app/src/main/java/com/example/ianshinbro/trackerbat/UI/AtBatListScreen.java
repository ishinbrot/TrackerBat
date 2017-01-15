package com.example.ianshinbro.trackerbat.UI;

import android.content.Context;
import android.content.Intent;
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
import android.util.Log;
import android.widget.Toast;

import com.example.ianshinbro.trackerbat.Implentation.AtBat;
import com.example.ianshinbro.trackerbat.Implentation.Game;
import com.example.ianshinbro.trackerbat.Implentation.Game_Table;
import com.example.ianshinbro.trackerbat.R;
import com.example.ianshinbro.trackerbat.UI.Adapters.AtBatAdapter;
import com.example.ianshinbro.trackerbat.UI.Adapters.adapterHelpers.DividerItemDecoration;
import com.example.ianshinbro.trackerbat.UI.Adapters.adapterHelpers.ItemTouchHelperCallBack;
import com.example.ianshinbro.trackerbat.UI.Adapters.adapterHelpers.OnStartDragListener;
import com.example.ianshinbro.trackerbat.UI.popupScreens.GameEnd;
import com.example.ianshinbro.trackerbat.UI.popupScreens.atBatSetup;
import com.raizlabs.android.dbflow.sql.language.SQLite;


/**
 * Created by ianshinbrot on 4/30/15.
 */
public class AtBatListScreen extends AppCompatActivity {
    RecyclerView atBatList;
    FloatingActionButton addAtBat;
    Toolbar toolbar;
    Game game;
    AtBat atBat;
    private LinearLayoutManager linearLayoutManager;
    int totalinList = -1;
    private int selectedPosition = -1;
    private String tag = "AtBatListScreen";
    private Context context;
    private ItemTouchHelper mItemTouchHelper;
    private AtBatAdapter atBatAdapter;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_view);
        context = getApplicationContext();
        Intent intent = getIntent();

        game = (Game) intent.getExtras().getSerializable("game");
        game.updateAtBats(SQLite.select().from(AtBat.class).where(Game_Table.gameId.eq(game.getGameId())).queryList());
        Log.d(this.tag, "atbat load");
        this.loadFields();
        this.setOnClickListeners();
        this.setUpToolbar();
        this.loadList();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
           totalinList= atBatAdapter.getItemCount()-1;        // ensure the total is correct
        if (resultCode == 1) {        // return 1 is adding
            Log.d(this.tag, "Adding at atBat");
            atBat = (AtBat) data.getExtras().getSerializable("atBat");
            atBat.setGameId(game.getGameId());
            game.addAtBat(atBat);
            atBat.save();
            totalinList++;
            selectedPosition = totalinList;
            editAtBat();
        }
        if (resultCode == 2) {        // new at bat
            Log.d(this.tag, "Updating at Bat");
            Log.d(this.tag, atBat.getBaseStats());
            atBat = (AtBat) data.getExtras().getSerializable("atBat");
            atBat.save();
            game.updateAtBatatIndex(selectedPosition, atBat);
            atBatAdapter.updateAtBat(atBat, selectedPosition);
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

    private void endGame() {
            game.endGame();
            Intent intent = new Intent(AtBatListScreen.this, GameEnd.class);
            intent.putExtra("game", game);
            startActivityForResult(intent, 0);
        }


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
                Toast.makeText(getApplicationContext(), "Editing not implemented yet", Toast.LENGTH_SHORT);
            //    selectAtBat(position);
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
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        switch (item.getItemId()) {


            case R.id.saveAtBatListButton:

                endGame();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.atbat_menu_list, menu);
        return true;
    }
    private void setUpToolbar() {
        toolbar.setTitle(R.string.atbatTitleText);
        setSupportActionBar(toolbar);
    }

    private void setOnClickListeners() {
        addAtBat.setOnClickListener(newAtBat);
    }
    private void loadFields() {

        atBatList = (RecyclerView) findViewById(R.id.listView_listScreen);
        addAtBat = (FloatingActionButton) findViewById(R.id.addBTN_listScreen);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
    }

}