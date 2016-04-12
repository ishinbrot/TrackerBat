package com.example.ianshinbro.trackerbat.UI.Adapters;

import android.support.v4.view.MotionEventCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import com.example.ianshinbro.trackerbat.Implentation.Player;
import com.example.ianshinbro.trackerbat.data.SQLLiteSetup.DataBaseHelper;
import com.example.ianshinbro.trackerbat.R;
import com.example.ianshinbro.trackerbat.UI.Adapters.adapterHelpers.ItemTouchHelperAdapter;
import com.example.ianshinbro.trackerbat.UI.Adapters.adapterHelpers.OnStartDragListener;

import java.util.ArrayList;


/**
 * Created by ianshinbro on 2/20/2016.
 */
public class PlayerAdapter extends RecyclerView.Adapter<PlayerHolder> implements ItemTouchHelperAdapter {
    private ArrayList<Player> players = new ArrayList<>();
    private OnStartDragListener mDragStartListener;
    private DataBaseHelper dataBaseHelper;
    private static String Log="PlayerAdapter";

    public PlayerAdapter(ArrayList<Player> players) {
      this.players=players;

    }

    public PlayerAdapter(ArrayList<Player>players, DataBaseHelper helper, OnStartDragListener dragListener) {
        mDragStartListener = dragListener;
        dataBaseHelper = helper;
        this.players=players;
    }
    public interface OnDragStartListener {
        void onDragStarted(RecyclerView.ViewHolder viewHolder);
        void onUpdate(RecyclerView.ViewHolder viewHolder);

    }
    @Override
    public void onItemDismiss(int position) {
        players.remove(position);
        notifyItemRemoved(position);
    }
    @Override
    public void onItemMove(int fromPosition, int toPosition) {
        Player prev = players.remove(fromPosition);
        players.add(toPosition > fromPosition ? toPosition - 1 : toPosition, prev);
        notifyItemMoved(fromPosition, toPosition);
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
        viewHolder.number.setText(Integer.toString(player.getNumber()));

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
        notifyDataSetChanged();
    }
    public void addItem(int position, Player player) {
        players.add(player);
        dataBaseHelper.addPlayer(player);

        notifyItemInserted(position - 1);
    }
    public void updatePlayer(Player player, int position) {
        players.set(position, player);
        dataBaseHelper.updatePlayer(player);
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