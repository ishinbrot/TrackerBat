package com.example.ianshinbro.trackerbat.UI.Adapters;

import android.graphics.Color;
import android.media.Image;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.ianshinbro.trackerbat.Implentation.Player;
import com.example.ianshinbro.trackerbat.R;

/**
 * Created by ianshinbro on 2/29/2016.
 */
public  class PlayerHolder extends RecyclerView.ViewHolder implements ItemTouchHelperViewHolder {
    protected TextView name;
    protected TextView number;
    protected  ImageView handleView;
    protected ImageView updateView;
    private Player player;
    private LinearLayout mainLayout;

    public PlayerHolder(View itemView) {
        super(itemView);
        this.name = (TextView) itemView.findViewById(R.id.PlayerName_PlayerListScreen);
        this.number = (TextView) itemView.findViewById(R.id.PlayerNumber_PlayerListScreen);
        mainLayout = (LinearLayout) itemView.findViewById(R.id.playerListLinearLayout);
    //    handleView = (ImageView) itemView.findViewById(R.id.handle_playerView);
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