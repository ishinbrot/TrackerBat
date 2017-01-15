package com.example.ianshinbro.trackerbat.UI.Adapters;

import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.ianshinbro.trackerbat.R;
import com.example.ianshinbro.trackerbat.UI.Adapters.adapterHelpers.ItemTouchHelperViewHolder;

/**
 * Created by ianshinbro on 2/29/2016.
 */
public  class PlayerHolder extends RecyclerView.ViewHolder implements ItemTouchHelperViewHolder {
    protected TextView name;
    protected TextView number;
    protected ImageView updateView;
    private LinearLayout mainLayout;

    public PlayerHolder(View itemView) {
        super(itemView);
        this.name = (TextView) itemView.findViewById(R.id.PlayerName_PlayerListScreen);
        this.number = (TextView) itemView.findViewById(R.id.PlayerNumber_PlayerListScreen);
        this.mainLayout = (LinearLayout) itemView.findViewById(R.id.playerListLinearLayout);
        updateView = (ImageView) itemView.findViewById(R.id.handle_UpdatePlayerButton);
    }
        @Override
        public void onItemSelected() {
            itemView.setBackgroundColor(Color.LTGRAY);
        }
        @Override
        public void onItemClear() {
            itemView.setBackgroundColor(0);
    }
}