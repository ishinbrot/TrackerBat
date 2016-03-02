package com.example.ianshinbro.trackerbat.UI.Adapters;

import android.support.v7.widget.RecyclerView;

/**
 * Created by ianshinbro on 3/1/2016.
 */
public interface OnStartDragListener {

    /**
     * Called when a view is requesting a start of a drag.
     *
     * @param viewHolder The holder of the view to drag.
     */
    void onStartDrag(RecyclerView.ViewHolder viewHolder);

    void onUpdate(RecyclerView.ViewHolder viewHolder);

    void onSelect(RecyclerView.ViewHolder viewHolder);
}