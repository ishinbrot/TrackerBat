package com.example.ianshinbro.trackerbat.UI.Adapters;

import android.content.Context;
import android.support.v4.view.MotionEventCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import com.example.ianshinbro.trackerbat.Implentation.AtBat;
import com.example.ianshinbro.trackerbat.R;
import com.example.ianshinbro.trackerbat.UI.Adapters.adapterHelpers.ItemTouchHelperAdapter;
import com.example.ianshinbro.trackerbat.UI.Adapters.adapterHelpers.OnStartDragListener;

import java.util.ArrayList;


/**
 * Created by ianshinbro on 2/20/2016.
 */
public class AtBatAdapter extends RecyclerView.Adapter<AtBatHolder> implements ItemTouchHelperAdapter {
    private ArrayList<AtBat> atBats;
    private OnStartDragListener mDragStartListener;
    private static String Log="PlayerAdapter";

    public AtBatAdapter(ArrayList<AtBat> atBats, OnStartDragListener dragListener) {
        this.atBats=atBats;
        this.mDragStartListener=dragListener;

    }
    public AtBatAdapter(ArrayList<AtBat> atBats) {
        this.atBats=atBats;
    }

    @Override
    public AtBatHolder onCreateViewHolder(
            ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.atbat_list, parent, false);
        AtBatHolder atBatHolder = new AtBatHolder(view);
        return  atBatHolder;
    }
    @Override
    public void onItemDismiss(int position) {
        atBats.get(position).delete();
        atBats.remove(position);
        notifyItemRemoved(position);
    }

    /**
     * Moves the item when moving an item in the list
     * @param fromPosition
     * @param toPosition
     */
    @Override
    public void onItemMove(int fromPosition, int toPosition) {
   //     AtBat prev = atBats.remove(fromPosition);
    //    atBats.add(toPosition > fromPosition ? toPosition - 1 : toPosition, prev);
     //   notifyItemMoved(fromPosition, toPosition);
    }

    /**
     * Interface for the touch listener
     */
    public interface OnDragStartListener {
        void onDragStarted(RecyclerView.ViewHolder viewHolder);

    }
    @Override
    public void onBindViewHolder(final AtBatHolder viewHolder, int position) {
        AtBat atBat = atBats.get(position);

        viewHolder.inningNumber.setText("Inning " +atBat.getInningNumber());
        if (atBat.getBaseStats()!="") {
            viewHolder.inningStats.setText(atBat.getBaseStats());
        }
        /**
         * Sets the listener for the inning stats text and inning number
         */
        viewHolder.inningStats.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (MotionEventCompat.getActionMasked(event) == MotionEvent.ACTION_DOWN) {
                    mDragStartListener.onSelect(viewHolder);
                }

                return false;
            }
        });
        viewHolder.inningNumber.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (MotionEventCompat.getActionMasked(event) == MotionEvent.ACTION_DOWN) {
                    mDragStartListener.onSelect(viewHolder);
                }

                return false;
            }
        });
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    @Override
    public int getItemCount() {

        return atBats.size();
    }

}