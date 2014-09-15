package com.brianstacks.listviewtest;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;



// The rest of our MainActivity class.
public class MyActivity extends Activity {
    // Our image IDs
    final int[] mImages = new int[] {
            R.drawable.acrobat, R.drawable.aftereffects, R.drawable.bridge,
            R.drawable.chrome, R.drawable.dreamweaver, R.drawable.earth,
            R.drawable.fireworks, R.drawable.flash, R.drawable.illustrator,
            R.drawable.indesign, R.drawable.photoshop, R.drawable.reader,
            R.drawable.steam
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);
        // Get the GridView by ID from the layout.
        GridView gv = (GridView)findViewById(R.id.image_grid);
        // Set an adapter for our grid.
        gv.setAdapter(new ImageAdapter(mImages));
    }
    private class ImageAdapter extends BaseAdapter {
        // The collection of image IDs that we'll be using in this adapter.
        private int[] mImageIds;
        public ImageAdapter(int[] _imageIds) {
            mImageIds = _imageIds;
        }
        // This method returns how many items are contained in the adapter collection.
        @Override
        public int getCount() {
            return mImageIds.length;
        }
        // This method returns a specific item from the adapter collection.
        @Override
        public Integer getItem(int _position) {
            return mImageIds[_position];
        }
        // This method specifies the ID of each view in the attached AdapterView.
        @Override
        public long getItemId(int _position) {
            return _position;
        }
        // This method returns the view that represents each item in the collection.
        @Override
        public View getView(int _position, View _convertView, ViewGroup _parent) {
            // If the passed in view is null, create a new one.
            // This view is typically null to start but will contain
            // a recycled view later on.
            if(_convertView == null) {
                _convertView = getLayoutInflater().inflate(
                        R.layout.grid_item, null);
            }
            // Set our image to be the image ID at the specified position.
            ImageView iv = (ImageView) _convertView;
            iv.setImageResource(getItem(_position));
            // Return our filled out grid item view.
            return _convertView;
        }
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.my, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        return id == R.id.action_settings || super.onOptionsItemSelected(item);
    }
}
