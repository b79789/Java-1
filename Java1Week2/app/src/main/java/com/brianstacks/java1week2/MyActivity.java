/*
    Brian Stacks
    Java19/13/2014
 */
package com.brianstacks.java1week2;

import android.app.Activity;

import android.content.res.Configuration;
import android.os.Bundle;

import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Surface;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;


public class MyActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);
        createHashSet();
        // get orientation
        int rotation = getWindowManager().getDefaultDisplay().getRotation();
        // set activity on view rotation
        if (rotation == Surface.ROTATION_0 || rotation == Surface.ROTATION_180) {
            //set spinner to view if vertical
            setSpinner();
            // Grab our Spinner by ID and assign it to a variable.
            final Spinner sp = (Spinner) findViewById(R.id.mySpinner);
            // set listener for when item is selected
            sp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                    // your code here
                    // get TextView by ID and assign it to variable
                    TextView tv = (TextView) findViewById(R.id.spinText);
                    tv.setText(sp.getItemAtPosition(sp.getSelectedItemPosition()).toString());
                }

                @Override
                public void onNothingSelected(AdapterView<?> parentView) {
                    // your code here
                }


            });
        } else {
            //set to listView to view if horizontal
            setList();
            // Grab our ListView by ID and assign it to a variable.
            final ListView lv = (ListView)findViewById(R.id.president_list);
            // listen for on click event
            lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                //on item click
                public void onItemClick(AdapterView<?> myAdapter, View myView, int myItemInt, long mylng) {
                    TextView tv2 = (TextView) findViewById(R.id.spinText);
                    tv2.setText(lv.getItemAtPosition(lv.getSelectedItemPosition()).toString());

                }

            });





        }

    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);

        switch(newConfig.orientation) {
            case Configuration.ORIENTATION_LANDSCAPE:
                Toast.makeText(getApplicationContext(), "Landscape!!! =)",
                        Toast.LENGTH_LONG).show();
                break;
            case Configuration.ORIENTATION_PORTRAIT:
                Toast.makeText(getApplicationContext(), "Portrait!!! =)",
                        Toast.LENGTH_LONG).show();
                break;
        }
    }

    public void setSpinner(){
        // Grab our Spinner by ID and assign it to a variable.
        Spinner sp = (Spinner) findViewById(R.id.mySpinner);
        // Create a new ArrayAdapter that takes in a context,
        // list item layout, and data collection.
        ArrayAdapter<CharSequence> myAdapter = ArrayAdapter.createFromResource(this,
                R.array.teams, android.R.layout.simple_spinner_item);
        myAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp.setAdapter(myAdapter);

    }

    public void setList(){

        this.setContentView(R.layout.extra);
        // Grab our ListView by ID and assign it to a variable.
        final ListView lv = (ListView)findViewById(R.id.president_list);
        // Get our data collection from the resource file.
        String[] myTeams = getResources().getStringArray(R.array.teams);
        // Create a new ArrayAdapter that takes in a context,
        // list item layout, and data collection.
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, myTeams);

        // Hook everything up by setting the adapter to the ListView.
        lv.setAdapter(adapter);

    }

    public void createHashSet() {
        // Empty set of String values.
        HashSet stringSet = new HashSet(Arrays.asList(getResources().getStringArray(R.array.teams)));
        // Get the iterator for this set
        // Iterator starts at the beginning of the set.
                Iterator<String> iter = stringSet.iterator();
        // String to hold values from the set
                String complete = "";
        // Run until we've retrieved all values in the set.
                while(iter.hasNext()) {
                    // Add the value from the set to the complete string
                    // and advance the iterator to the next position.
                    complete += iter.next();
                }
        Log.d("Set values",complete);
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
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
