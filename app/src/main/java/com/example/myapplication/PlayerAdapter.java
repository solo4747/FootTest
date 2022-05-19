package com.example.myapplication;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class PlayerAdapter extends ArrayAdapter<Player> {

    private int resourceLayout;
    private Context mContext;

    public PlayerAdapter(Context context, int resource, ArrayList<Player> players) {
        super(context, resource, players);
        this.resourceLayout = resource;
        this.mContext = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View v = convertView;

        if (v == null) {
            LayoutInflater vi;
            vi = LayoutInflater.from(mContext);
            v = vi.inflate(resourceLayout, null);
        }

        Player p = getItem(position);

        if (p != null) {
            TextView tt1 = (TextView) v.findViewById(R.id.toptext);
            TextView tt2 = (TextView) v.findViewById(R.id.toptextdata);
            TextView tt3 = (TextView) v.findViewById(R.id.topId);


            if (tt1 != null) {
                tt1.setText(p.getName());
            }

            if (tt2 != null) {
                tt2.setText(p.getSpeed());
            }

            if (tt2 != null) {
                tt3.setText("Player nr " + p.getId()+".");
            }
        }

        return v;
    }

//        // Lookup view for data population
//        TextView name = (TextView) convertView.findViewById(R.id.toptext);
//        TextView speed = (TextView) convertView.findViewById(R.id.toptextdata);
//        // Populate the data into the template view using the data object
//        name.setText(players.get(position).name);
//        speed.setText(players.get(position).speed);
//        // Return the completed view to render on screen
//        return convertView;
    }



