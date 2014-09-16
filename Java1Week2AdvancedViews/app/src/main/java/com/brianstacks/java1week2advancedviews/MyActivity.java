/*
* Brian Stacks
* 9-12-2014
* */
package com.brianstacks.java1week2advancedviews;

import android.app.Activity;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import android.widget.Spinner;
import android.widget.TextView;


public class MyActivity extends Activity {
    final TextView txtView = (TextView) findViewById(R.id.spinText);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);

        setSpinner();

    }

    public void setSpinner() {
        // Grab our Spinner by ID and assign it to a variable.
        Spinner sp = (Spinner) findViewById(R.id.mySpinner);

        // Get our data collection from the resource file.
        String[] presidents = getResources().getStringArray(R.array.politicians);

        // Create a new ArrayAdapter that takes in a context,
        // list item layout, and data collection.
        ArrayAdapter<CharSequence> myAdapter = ArrayAdapter.createFromResource(this,
                R.array.politicians, android.R.layout.simple_spinner_item);
        myAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp.setAdapter(myAdapter);
        sp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
                Object item = parent.getItemAtPosition(pos);
            }
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });


    }

    public void setList(){
        // Grab our ListView by ID and assign it to a variable.
        ListView lv = (ListView)findViewById(R.id.president_list);
        // Get our data collection from the resource file.
        String[] presidents = getResources().getStringArray(R.array.politicians);

        // Create a new ArrayAdapter that takes in a context,
        // list item layout, and data collection.
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, presidents);

        // Hook everything up by setting the adapter to the ListView.
        lv.setAdapter(adapter);
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
