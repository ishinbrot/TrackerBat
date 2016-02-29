package com.example.ianshinbro.trackerbat.UI.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.ianshinbro.trackerbat.Implentation.AtBat;
import com.example.ianshinbro.trackerbat.R;

import java.util.ArrayList;


/**
 * Created by ianshinbro on 2/20/2016.
 */
public class AtBatAdapter extends ArrayAdapter<AtBat> {
    private ArrayList<AtBat> atBats;

    public AtBatAdapter(Context context, int textViewResourceId) {
        super(context, textViewResourceId);

    }

    public AtBatAdapter(Context context, int resource, ArrayList<AtBat> atBat) {
        super(context, resource, atBat);
        this.atBats = atBat;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View v = convertView;

        if (v == null) {
            LayoutInflater vi;
            vi = LayoutInflater.from(getContext());
            v = vi.inflate(R.layout.atbat_list, parent, false);
        }

        AtBat atbat = getItem(position);

        if (atbat != null) {
            TextView tt1 = (TextView) v.findViewById(R.id.InningNumber_AtBatScreen);
            TextView tt2 = (TextView) v.findViewById(R.id.InningStats_AtBatScreen);

            if (tt1 != null) {
               int inningNumber= atbat.getInningNumber();

                tt1.setText("Inning" + " " + inningNumber);
            }
            if (tt2 != null) {
                String baseStats = atbat.getBaseStats();
                tt2.setText(baseStats);
            }
        }

        return v;
    }
}