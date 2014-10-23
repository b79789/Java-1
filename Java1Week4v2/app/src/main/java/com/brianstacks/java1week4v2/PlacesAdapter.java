package com.brianstacks.java1week4v2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Brian Stacks
 * on 10/23/14
 * for FullSail.edu.
 */
public class PlacesAdapter extends ArrayAdapter<Places> {

    private Context context;
    private List<Places> placeList;

    public PlacesAdapter(Context context, int resource, List<Places> objects){
        super(context,resource,objects);
        this.context = context;
        this.placeList =objects;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        LayoutInflater inflator = (LayoutInflater)context.getSystemService(MyActivity.LAYOUT_INFLATER_SERVICE);
        View view;
        view = inflator.inflate(R.layout.item_place,null);
        Places places = placeList.get(position);
        TextView tv = (TextView) view.findViewById(R.id.textView1);
        TextView tv2 = (TextView) view.findViewById(R.id.textView2);
        TextView tv3 = (TextView) view.findViewById(R.id.textView3);
        tv.setText(places.getName());
        tv2.setText(places.getFormatted_address());
        tv3.setText(places.getTypes());
        return  view;
    }

}
