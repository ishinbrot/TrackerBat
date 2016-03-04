package com.example.ianshinbro.trackerbat.UI.Adapters.adapterHelpers;

/**
 * Created by ianshinbro on 2/29/2016.
 */
public interface ItemTouchHelperAdapter {

    void onItemMove(int fromPosition, int toPosition);

    void onItemDismiss(int position);
}