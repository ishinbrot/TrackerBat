package com.example.ianshinbro.trackerbat.UI.Adapters;

import android.support.v4.view.MotionEventCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import com.example.ianshinbro.trackerbat.AppDataBase;
import com.example.ianshinbro.trackerbat.Implentation.Player;
import com.example.ianshinbro.trackerbat.R;
import com.example.ianshinbro.trackerbat.UI.Adapters.adapterHelpers.ItemTouchHelperAdapter;
import com.example.ianshinbro.trackerbat.UI.Adapters.adapterHelpers.OnStartDragListener;
import com.raizlabs.android.dbflow.config.FlowManager;
import com.raizlabs.android.dbflow.structure.database.DatabaseWrapper;
import com.raizlabs.android.dbflow.structure.database.transaction.ProcessModelTransaction;
import com.raizlabs.android.dbflow.structure.database.transaction.Transaction;

import java.util.ArrayList;


/**
 * Created by ianshinbro on 2/20/2016.
 */
public class PlayerAdapter extends RecyclerView.Adapter<PlayerHolder> implements ItemTouchHelperAdapter {
    private ArrayList<Player> players = new ArrayList<>();
    private OnStartDragListener mDragStartListener;

    private static String Log="PlayerAdapter";

    public PlayerAdapter(ArrayList<Player> players) {
      this.players=players;

    }

    public PlayerAdapter(ArrayList<Player>players, OnStartDragListener dragListener) {
        mDragStartListener = dragListener;
        this.players=players;
    }
    public interface OnDragStartListener {
        void onDragStarted(RecyclerView.ViewHolder viewHolder);
        void onUpdate(RecyclerView.ViewHolder viewHolder);

    }
    @Override
    public void onItemDismiss(int position) {
        players.get(position).delete();
        players.remove(position);
        notifyItemRemoved(position);
    }
    @Override
    public void onItemMove(int fromPosition, int toPosition) {
    //    Player prev = players.remove(fromPosition);
     //   players.add(toPosition > fromPosition ? toPosition - 1 : toPosition, prev);
     //   notifyItemMoved(fromPosition, toPosition);
        //TODO :: Implement in database in the future
    }

   @Override
   public PlayerHolder onCreateViewHolder(
           ViewGroup parent, int viewType) {
       View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.player_list, parent, false);
       PlayerHolder playerHolder = new PlayerHolder(view);
       return  playerHolder;
   }

    @Override
    public void onBindViewHolder(final PlayerHolder viewHolder, int position) {
        Player player = players.get(position);

        if (player.nickNameExists()) {
            viewHolder.name.setText(player.getNickName());
        } else {
            viewHolder.name.setText(player.getFirstName() + " " + player.getLastName());
        }
        viewHolder.number.setText(Long.toString(player.getNumber()));

        viewHolder.updateView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (MotionEventCompat.getActionMasked(event) == MotionEvent.ACTION_DOWN) {
                    mDragStartListener.onUpdate(viewHolder);
                }

                return false;

            }
        });
        viewHolder.name.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (MotionEventCompat.getActionMasked(event) == MotionEvent.ACTION_DOWN) {
                    mDragStartListener.onSelect(viewHolder);
                }

                return false;
            }
        });
        viewHolder.number.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (MotionEventCompat.getActionMasked(event) == MotionEvent.ACTION_DOWN) {
                    mDragStartListener.onSelect(viewHolder);
                }

                return false;
            }
        });
    }

    public void updateData(ArrayList<Player> players) {
        players.clear();
        players.addAll(players);
        FlowManager.getDatabase(AppDataBase.class)
                .beginTransactionAsync(new ProcessModelTransaction.Builder<>(
                        new ProcessModelTransaction.ProcessModel<Player>() {
                            @Override
                            public void processModel(Player player, DatabaseWrapper wrapper) {
                                // do work here -- i.e. user.delete() or user.update()
                                player.save();
                            }
                        }).addAll(players).build())  // add elements (can also handle multiple)
                .error(new Transaction.Error() {
                    @Override
                    public void onError(Transaction transaction, Throwable error) {

                    }
                })
                .success(new Transaction.Success() {
                    @Override
                    public void onSuccess(Transaction transaction) {

                    }
                }).build().execute();
    }
    public void addItem(int position, Player player) {
        players.add(player);
        player.save();
        notifyItemInserted(position - 1);
    }
    public void updatePlayer(Player player, int position) {
        players.set(position, player);
        player.save();
        notifyItemChanged(position);
    }
    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    @Override
    public int getItemCount() {

        return players.size();
    }

}