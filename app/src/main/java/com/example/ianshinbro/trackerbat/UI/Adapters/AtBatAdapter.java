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

import com.example.ianshinbro.trackerbat.Implentation.AtBat;
import com.example.ianshinbro.trackerbat.Implentation.Game;
import com.example.ianshinbro.trackerbat.R;

import java.util.ArrayList;


/**
 * Created by ianshinbro on 2/20/2016.
 */
public class AtBatAdapter extends RecyclerView.Adapter<AtBatHolder> implements ItemTouchHelperAdapter {
    private ArrayList<AtBat> atBats;
    private Context mContext;
    private  OnStartDragListener mDragStartListener;
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
        atBats.remove(position);
        notifyItemRemoved(position);
    }
    @Override
    public void onItemMove(int fromPosition, int toPosition) {
        AtBat prev = atBats.remove(fromPosition);
        atBats.add(toPosition > fromPosition ? toPosition - 1 : toPosition, prev);
        notifyItemMoved(fromPosition, toPosition);
    }
    public interface OnDragStartListener {
        void onDragStarted(RecyclerView.ViewHolder viewHolder);

    }
    @Override
    public void onBindViewHolder(final AtBatHolder viewHolder, int position) {
        AtBat atBat = atBats.get(position);

        viewHolder.inningNumber.setText("Inning " +atBat.getInningNumber());
        viewHolder.inningStats.setText(atBat.getBaseStats());


        viewHolder.updateView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (MotionEventCompat.getActionMasked(event) == MotionEvent.ACTION_DOWN) {
                    mDragStartListener.onUpdate(viewHolder);
                }
                return false;
            }
        });
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

    public void updateData(ArrayList<AtBat> atBats) {
        this.atBats.clear();
        this.atBats.addAll(atBats);
        notifyDataSetChanged();
    }
    public void addItem(int position, AtBat atBat) {
        atBats.add(atBat);

        notifyItemInserted(position - 1);
    }
    public void updatePlayer(AtBat atBat, int position) {
        atBats.set(position, atBat);
        notifyItemChanged(position);
    }
    public void removeItem(int position) {
        atBats.remove(position);
        notifyItemRemoved(position);
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