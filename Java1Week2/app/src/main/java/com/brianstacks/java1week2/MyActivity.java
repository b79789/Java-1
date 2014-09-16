/*
    Brian Stacks
    Java19/13/2014
 */
package com.brianstacks.java1week2;

import android.app.Activity;

import android.content.res.Configuration;
import android.os.Bundle;

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


public class MyActivity extends Activity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);
        setSpinner();
        int rotation = getWindowManager().getDefaultDisplay().getRotation();
        if (rotation == Surface.ROTATION_0 || rotation == Surface.ROTATION_180) {
            // Grab our Spinner by ID and assign it to a variable.
            final Spinner sp = (Spinner) findViewById(R.id.mySpinner);
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
            this.setContentView(R.layout.extra);
            // Grab our ListView by ID and assign it to a variable.
            final ListView lv = (ListView)findViewById(R.id.president_list);
            // Get our data collection from the resource file.
            String[] presidents = getResources().getStringArray(R.array.politicians);
            lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> myAdapter, View myView, int myItemInt, long mylng) {
                    TextView tv2 = (TextView) findViewById(R.id.spinText);
                    String selectedFromList = (String) (lv.getItemAtPosition(myItemInt));
                    tv2.setText(selectedFromList);

                }

            });

            // Create a new ArrayAdapter that takes in a context,
            // list item layout, and data collection.
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                    android.R.layout.simple_list_item_1, presidents);

            // Hook everything up by setting the adapter to the ListView.
            lv.setAdapter(adapter);



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
                R.array.politicians, android.R.layout.simple_spinner_item);
        myAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp.setAdapter(myAdapter);
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
