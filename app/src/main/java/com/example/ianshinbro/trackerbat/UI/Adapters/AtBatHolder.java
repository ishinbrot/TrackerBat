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
    public  class AtBatHolder extends RecyclerView.ViewHolder implements ItemTouchHelperViewHolder {
    protected TextView inningNumber;
    protected TextView inningStats;
    protected ImageView handleView;
    protected ImageView updateView;
    private LinearLayout mainLayout;

    public AtBatHolder(View itemView) {
        super(itemView);
        this.inningNumber = (TextView) itemView.findViewById(R.id.InningNumber_AtBatScreen);
        this.inningStats = (TextView) itemView.findViewById(R.id.InningStats_AtBatScreen);
      //  this.handleView = (ImageView) itemView.findViewById(R.id.handle_atBatView);
       // this.updateView = (ImageView) itemView.findViewById(R.id.handle_UpdateAtBatButton);

        mainLayout = (LinearLayout) itemView.findViewById(R.id.atBatListlinearLayout);

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
