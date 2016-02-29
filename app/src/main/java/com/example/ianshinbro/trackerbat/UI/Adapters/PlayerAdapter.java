package com.example.ianshinbro.trackerbat.UI.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.ianshinbro.trackerbat.Implentation.Player;
import com.example.ianshinbro.trackerbat.R;

import java.util.ArrayList;


/**
 * Created by ianshinbro on 2/20/2016.
 */
public class PlayerAdapter extends ArrayAdapter<Player> {
    private ArrayList<Player> players;
    public PlayerAdapter(Context context, int textViewResourceId) {
        super(context, textViewResourceId);

    }

    public PlayerAdapter(Context context, int resource, ArrayList<Player> players) {
        super(context, resource, players);
        this.players=players;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View v = convertView;

        if (v == null) {
            LayoutInflater vi;
            vi = LayoutInflater.from(getContext());
            v = vi.inflate(R.layout.player_list, parent, false);
        }

        Player p = getItem(position);

        if (p != null) {
            TextView tt1 = (TextView) v.findViewById(R.id.PlayerName_PlayerListScreen);
            TextView tt2 = (TextView) v.findViewById(R.id.PlayerNumber_PlayerListScreen);

            if (tt1 != null) {

                    if (!p.nickNameExists()) {
                        tt1.setText(p.getFirstName() +" " + p.getLastName());
                        tt1.setTextSize(25);
                    } else {
                        tt1.setText(p.getNickName());
                        tt1.setTextSize(25);
                    }
                }

            if (tt2 != null) {
                tt2.setText("#" + Integer.toString(p.getNumber()));
                tt2.setTextSize(25);
            }

        }

        return v;
    }

}