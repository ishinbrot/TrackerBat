package com.example.ianshinbro.trackerbat.UI.Adapters;

import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.ianshinbro.trackerbat.R;


/**
 * Created by ianshinbro on 2/29/2016.
 */
public  class GameHolder extends RecyclerView.ViewHolder implements ItemTouchHelperViewHolder {
    protected TextView score;
    protected TextView progress;
    protected TextView gameTeam;
    protected  ImageView handleView;
    protected final ImageView updateView;
    private LinearLayout mainLayout;

    public GameHolder(View itemView) {
        super(itemView);
        this.score = (TextView) itemView.findViewById(R.id.GameScore_GameListScreen);
        this.progress = (TextView) itemView.findViewById(R.id.GameProgress_GameListScreen);
        this.gameTeam = (TextView) itemView.findViewById(R.id.GameTeam_GameListScreen);
  //      handleView = (ImageView) itemView.findViewById(R.id.handle_gameView);
        updateView = (ImageView) itemView.findViewById(R.id.handle_UpdateGameButton);
        mainLayout = (LinearLayout) itemView.findViewById(R.id.gameListLinearLayout);
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