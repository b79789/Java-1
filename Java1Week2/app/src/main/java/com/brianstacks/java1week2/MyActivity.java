/*
    Brian Stacks
    Java19/13/2014
 */
package com.brianstacks.java1week2;

import android.app.Activity;

import android.content.res.Configuration;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Surface;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Set;


public class MyActivity extends Activity {
    // variables for key strings
    static private String teamname;
    static private String img;
    // ArrayList to hold items from team
    private ArrayList<TeamCustomClass> mTeams;
    Spinner sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);

        ArrayList<HashMap<String, Object>> newTeam = new ArrayList<HashMap<String, Object>>();
        String teamList[] = getResources().getStringArray(R.array.teams);
        int[] myImageList = new int[]{R.drawable.bc, R.drawable.canes, R.drawable.cards, R.drawable.cavs, R.drawable.clemson,R.drawable.duke,R.drawable.fsu,R.drawable.gators,R.drawable.gt,R.drawable.irish,R.drawable.ken,R.drawable.mich,R.drawable.ncstate,R.drawable.pitt,R.drawable.syracuse,R.drawable.terps,R.drawable.uconn,R.drawable.unc,R.drawable.vt,R.drawable.wake};
        HashMap<String,Object> myHash = new HashMap<String,Object>();
        //myImageView.setImageResource(myImageList[i]);
        for (int k =0; k< teamList.length;k++){
            myHash.put(teamname,teamList[k]);
            myHash.put(img,myImageList[k]);
            newTeam.add(myHash);
        }
        Log.v("My TEAMS:",newTeam.toString());

        // get orientation
        int rotation = getWindowManager().getDefaultDisplay().getRotation();
        // set activity on view rotation
        if (rotation == Surface.ROTATION_0 || rotation == Surface.ROTATION_180) {
            setSpinner();
            // Grab our Spinner by ID and assign it to a variable.
            sp = (Spinner) findViewById(R.id.mySpinner);
            // set listener for when item is selected
            //sp.setAdapter(new TeamAdapter(this,mTeams));

            sp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

                @Override
                public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {


                    // get TextView by ID and assign it to variable
                    TextView tv = (TextView) findViewById(R.id.spinText);
                    tv.setBackgroundColor(Color.BLACK);
                    tv.setTextColor(Color.WHITE);
                    tv.setText(sp.getItemAtPosition(sp.getSelectedItemPosition()).toString());
                    ImageView iv = (ImageView) findViewById(R.id.firstImageView);


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
                    TextView tv2 = (TextView) findViewById(R.id.listViewText);
                    tv2.setBackgroundColor(Color.BLACK);
                    tv2.setTextColor(Color.WHITE);
                    tv2.setText(lv.getItemAtPosition(myItemInt).toString());
                    ImageView iv = (ImageView) findViewById(R.id.secondImageView);
                }

            });

        }

    }

    public void setSpinner(){


        // Grab our Spinner by ID and assign it to a variable.
        Spinner sp = (Spinner) findViewById(R.id.mySpinner);
        // Arraylist that contains my hashmap
        ArrayList<HashMap<String, Object>> newTeam = new ArrayList<HashMap<String, Object>>();
        // stringArray of the 20 teams
        String teamList[] = getResources().getStringArray(R.array.teams);
        // int array of my image id's
        int[] myImageList = new int[]{R.drawable.bc, R.drawable.canes, R.drawable.cards, R.drawable.cavs, R.drawable.clemson,R.drawable.duke,R.drawable.fsu,R.drawable.gators,R.drawable.gt,R.drawable.irish,
                R.drawable.ken,R.drawable.mich,R.drawable.ncstate,R.drawable.pitt,R.drawable.syracuse,R.drawable.terps,R.drawable.uconn,R.drawable.unc,R.drawable.vt,R.drawable.wake};
        // create hashMap and add the arrays to the map
        HashMap<String,Object> myHash = new HashMap<String,Object>();
        for (int k =0; k< teamList.length;k++){
            myHash.put(teamname,teamList[k]);
            myHash.put(img,myImageList[k]);
        }
        newTeam.add(myHash);
        // Create a new ArrayAdapter that takes in a context,
        // list item layout, and data collection.
        ArrayAdapter<HashMap<String, Object>> arrayAdapter = new ArrayAdapter<HashMap<String, Object>>(
                this,
                android.R.layout.simple_spinner_item,
                newTeam );
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        /*
        ArrayAdapter<CharSequence> myAdapter = ArrayAdapter.createFromResource(this,
                R.array.teams, android.R.layout.simple_spinner_item);
        myAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        */
        sp.setAdapter(arrayAdapter);

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
