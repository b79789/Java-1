package com.brianstacks.java1week2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Brian Stacks
  on 9/18/14
  for FullSail.edu.
 */
public class TeamAdapter extends BaseAdapter {
    private static final long ID_CONSTANT = 0x010101010L;
    private Context mContext;
    private ArrayList<TeamCustomClass> mTeams;
    // We take in a context and list of Team objects.
    // The list is our backing collection and the context is used
    // to create new views in our getView() method.
    public TeamAdapter(Context _context, ArrayList<TeamCustomClass> _teams) {
        mContext = _context;
        mTeams = _teams;
    }
    // Returning the number of objects in our collection.
    @Override
    public int getCount() {
        return mTeams.size();
    }
    // Returning Team objects from our collection.
    @Override
    public TeamCustomClass getItem(int _position) {
        return mTeams.get(_position);
    }
    // Adding our constant and position to create unique ID values.
    @Override
    public long getItemId(int _position) {
        return ID_CONSTANT + _position;
    }
    @Override
    public View getView(int _position, View _convertView, ViewGroup _parent) {
        // If we don't have a recycled view, create a new view.
        if(_convertView == null) {
            // Creating the new view.
            _convertView = LayoutInflater.from(mContext).inflate(R.layout.activity_my, _parent, false);
        }

        // Get the object from the collection in an index-safe manner.
        TeamCustomClass team = getItem(_position);

        // Use the object from the collection to fill out our view.
        TextView tv = (TextView)_convertView.findViewById(R.id.spinText);
        if (tv != null) {
            tv.setText(team.getmTeamName());
        }else {
            tv = (TextView) _convertView.findViewById(R.id.listViewText);
            tv.setText(team.getmTeamName());
        }
        ImageView iv = (ImageView)_convertView.findViewById(R.id.firstImageView);

        iv =(ImageView)_convertView.findViewById(R.id.secondImageView);
        // Returning our filled out view.
        return _convertView;
    }

}
