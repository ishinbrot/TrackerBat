package com.example.ianshinbro.trackerbat.UI.Adapters;

import android.content.Context;
import android.support.v4.view.MotionEventCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.ianshinbro.trackerbat.Implentation.Game;
import com.example.ianshinbro.trackerbat.Implentation.Player;
import com.example.ianshinbro.trackerbat.R;

import java.util.ArrayList;


/**
 * Created by ianshinbro on 2/20/2016.
 */
public class GameAdapter extends RecyclerView.Adapter<GameHolder> implements ItemTouchHelperAdapter  {
    private ArrayList<Game> games;
    private  OnStartDragListener mDragStartListener;
    private static String Log="PlayerAdapter";

    public GameAdapter(ArrayList<Game> games) {
        this.games=games;

    }
    public GameAdapter(ArrayList<Game> games, OnStartDragListener dragListener) {
        this.games=games;
        this.mDragStartListener = dragListener;
    }
    public interface OnDragStartListener {
        void onDragStarted(RecyclerView.ViewHolder viewHolder);

    }
    @Override
    public GameHolder onCreateViewHolder(
            ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.game_list, parent, false);
        GameHolder gameHolder = new GameHolder(view);
        return  gameHolder;
    }
    @Override
    public void onItemDismiss(int position) {
        games.remove(position);
        notifyItemRemoved(position);
    }
    @Override
    public void onItemMove(int fromPosition, int toPosition) {
        Game prev = games.remove(fromPosition);
        games.add(toPosition > fromPosition ? toPosition - 1 : toPosition, prev);
        notifyItemMoved(fromPosition, toPosition);
    }
    @Override
    public void onBindViewHolder(final GameHolder viewHolder, int position) {
        Game game = games.get(position);

        if (game.getStatus()) {
            viewHolder.progress.setText("In Progress");
        }
        else if (!game.getStatus()) {
            viewHolder.progress.setText("Finished");
        }
        viewHolder.score.setText(game.getHomeScore() + " - " + game.getAwayScore());

        viewHolder.gameTeam.setText(game.getHomeTeam() + " vs " + game.getAwayTeam());


        viewHolder.updateView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (MotionEventCompat.getActionMasked(event) == MotionEvent.ACTION_DOWN) {
                    mDragStartListener.onUpdate(viewHolder);
                }

                return false;

            }
        });
        viewHolder.progress.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (MotionEventCompat.getActionMasked(event) == MotionEvent.ACTION_DOWN) {
                    mDragStartListener.onSelect(viewHolder);
                }

                return false;
            }
        });
        viewHolder.gameTeam.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (MotionEventCompat.getActionMasked(event) == MotionEvent.ACTION_DOWN) {
                    mDragStartListener.onSelect(viewHolder);
                }

                return false;
            }
        });
        viewHolder.score.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (MotionEventCompat.getActionMasked(event) == MotionEvent.ACTION_DOWN) {
                    mDragStartListener.onSelect(viewHolder);
                }

                return false;
            }
        });
    }

    public void updateData(ArrayList<Game> games) {
        this.games.clear();
        this.games.addAll(games);
        notifyDataSetChanged();
    }
    public void addItem(int position, Game game) {
        games.add(game);

        notifyItemInserted(position - 1);
    }
    public void updateGame(Game game, int position) {
        games.set(position, game);
        notifyItemChanged(position);
    }
    public void removeItem(int position) {
        games.remove(position);
        notifyItemRemoved(position);
    }
    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    @Override
    public int getItemCount() {

        return games.size();
    }

}