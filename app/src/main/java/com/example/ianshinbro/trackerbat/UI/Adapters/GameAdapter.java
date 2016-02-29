package com.example.ianshinbro.trackerbat.UI.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.ianshinbro.trackerbat.Implentation.Game;
import com.example.ianshinbro.trackerbat.R;

import java.util.ArrayList;


/**
 * Created by ianshinbro on 2/20/2016.
 */
public class GameAdapter extends ArrayAdapter<Game> {
    private ArrayList<Game> games;

    public GameAdapter(Context context, int textViewResourceId) {
        super(context, textViewResourceId);

    }

    public GameAdapter(Context context, int resource, ArrayList<Game> games) {
        super(context, resource, games);
        this.games = games;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View v = convertView;

        if (v == null) {
            LayoutInflater vi;
            vi = LayoutInflater.from(getContext());
            v = vi.inflate(R.layout.game_list, parent, false);
        }

        Game game = getItem(position);

        if (game != null) {
            TextView tt1 = (TextView) v.findViewById(R.id.GameTeam_GameListScreen);
            TextView tt2 = (TextView) v.findViewById(R.id.GameScore_GameListScreen);
            TextView tt3 = (TextView) v.findViewById(R.id.GameProgress_GameListScreen);

            if (tt1 != null) {

                String awayTeam = game.getHomeTeam();
                String homeTeam = game.getAwayTeam();
                tt1.setText(homeTeam + " vs " + awayTeam);
            }
            if (tt2 != null) {
                int homeScore = game.getHomeScore();
                int awayScore = game.getAwayScore();
                tt2.setText(Integer.toString(homeScore) + "-" + Integer.toString(awayScore));
            }
            if (tt3 != null) {
                boolean gameStatus = game.getStatus();

                if (gameStatus) {
                    tt3.setText("In progress");
                } else {
                    tt3.setText(game.getInningNumber() + " innings");
                }

            }
        }

        return v;
    }
}