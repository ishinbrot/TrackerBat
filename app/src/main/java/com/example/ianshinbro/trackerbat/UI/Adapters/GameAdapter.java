package com.example.ianshinbro.trackerbat.UI.Adapters;

import android.support.v4.view.MotionEventCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import com.example.ianshinbro.trackerbat.data.model.Game;
import com.example.ianshinbro.trackerbat.R;
import com.example.ianshinbro.trackerbat.UI.Adapters.adapterHelpers.ItemTouchHelperAdapter;
import com.example.ianshinbro.trackerbat.UI.Adapters.adapterHelpers.OnStartDragListener;
import com.example.ianshinbro.trackerbat.data.repo.GameRepo;

import java.util.ArrayList;


/**
 * Created by ianshinbro on 2/20/2016.
 */
public class GameAdapter extends RecyclerView.Adapter<GameHolder> implements ItemTouchHelperAdapter {
    private ArrayList<Game> games;
    private GameRepo gameRepo;
    private OnStartDragListener mDragStartListener;
    private int playerId_;
    private static String TAG="GameAdapter";

    public GameAdapter(ArrayList<Game> games) {
        this.games=games;

    }
    public GameAdapter(int playerId, OnStartDragListener dragListener) {
        gameRepo = new GameRepo();
        this.playerId_=playerId;
        this.games=gameRepo.getGamesById(this.playerId_);
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
        Game game = games.remove(position);
        gameRepo.remove(game.getID());
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
    public void addGame(Game game, int position) {
        games.add(game);
        gameRepo.insert(game, this.playerId_);

        notifyItemInserted(position - 1);
    }
    public Game getGame(int position) {
        return games.get(position);
    }
    public void updateGame(Game game, int position) {
        games.set(position, game);
        gameRepo.updateGame(game);
        notifyItemChanged(position);
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