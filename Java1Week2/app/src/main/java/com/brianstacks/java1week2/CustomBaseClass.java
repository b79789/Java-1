package com.brianstacks.java1week2;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

/**
 * Created by brianstacks on 9/17/14.
 */
public class CustomBaseClass extends BaseAdapter {
    public CustomBaseClass(){

    }
    @Override
    public int getCount() {
        return 0;
    }
    @Override
    public Object getItem(int _position) {
        return null;
    }
    @Override
    public long getItemId(int _position) {
        return 0;
    }
    @Override
    public View getView(int _position, View _convertView, ViewGroup _parent) {
        return _convertView;
    }
}
