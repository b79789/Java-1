package com.brianstacks.java1week3ver2;

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
 * on 10/8/14
 * for FullSail.edu.
 */
public class CustomAdapter extends BaseAdapter {
    private int[] myImageList = new int[]{R.drawable.bc, R.drawable.canes, R.drawable.cards, R.drawable.cavs, R.drawable.clemson,R.drawable.duke,R.drawable.fsu,R.drawable.gators,R.drawable.gt,R.drawable.irish,
            R.drawable.ken,R.drawable.mich,R.drawable.ncstate,R.drawable.pitt,R.drawable.terps,R.drawable.uconn,R.drawable.unc,R.drawable.syracuse,R.drawable.vt,R.drawable.wake};

    private static final long ID_CONSTANT = 0x010101010L;
    private Context mContext;
    private ArrayList<Team> mTeam;
    // We take in a context and list of Team objects.
    // The list is our backing collection and the context is used
    // to create new views in our getView() method.
    public CustomAdapter(Context _context, ArrayList<Team> _teams) {
        mContext = _context;
        mTeam = _teams;
    }
    // Returning the number of objects in our collection.
    @Override
    public int getCount() {
        return mTeam.size();
    }
    // Returning team objects from our collection.
    @Override
    public Team getItem(int _position) {
        return mTeam.get(_position);
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
            _convertView = LayoutInflater.from(mContext).inflate(R.layout.customlayout, _parent, false);
        }
        // Get the object from the collection in an index-safe manner.
        Team team = getItem(_position);
        // Use the object from the collection to fill out our view.
        ImageView iv =(ImageView)_convertView.findViewById(R.id.teamImage);
        iv.setImageResource(myImageList[_position]);
        TextView tv = (TextView)_convertView.findViewById(R.id.teamName);
        tv.setText(team.getName());
        tv = (TextView)_convertView.findViewById(R.id.teamNick);
        tv.setText(team.getNick());

        // Returning our filled out view.
        return _convertView;
    }
}
