package com.brianstacks.java1week2;

import android.app.LauncherActivity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;

/**
 * Created by brianstacks on 9/17/14.
 */
public class CustomBaseClass extends BaseAdapter {
    private LayoutInflater inflater;
    // store the resource (typically list_item.xml)
    private int resource;
    // store (a reference to) the data
    private ArrayList<LauncherActivity.ListItem> data;


    public CustomBaseClass(Context context, int resource, ArrayList<LauncherActivity.ListItem> data){
        this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.resource = resource;
        this.data = data;
    }
    @Override
    public int getCount() {
        return this.data.size();
    }
    @Override
    public Object getItem(int _position) {
        return this.data.get(_position);
    }
    @Override
    public long getItemId(int _position) {
        return _position;
    }
    @Override
    public View getView(int _position, View _convertView, ViewGroup _parent) {
        return _convertView;
    }
}
