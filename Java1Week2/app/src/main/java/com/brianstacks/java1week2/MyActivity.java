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
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;


public class MyActivity extends Activity {
    // variables for key strings
    static private String teamname;
    static private String img;
    // ArrayList to hold items from team
    private ArrayList<TeamCustomClass> mTeams;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);
        setSpinner();
        mTeams = new ArrayList<TeamCustomClass>();
        mTeams.add(TeamCustomClass.newTeamClass("Boston College",R.drawable.bc));
        mTeams.add(TeamCustomClass.newTeamClass("Miami",R.drawable.canes));
        mTeams.add(TeamCustomClass.newTeamClass("Louisville",R.drawable.cards));
        mTeams.add(TeamCustomClass.newTeamClass("Virginia",R.drawable.cavs));
        mTeams.add(TeamCustomClass.newTeamClass("Clemson",R.drawable.clemson));
        mTeams.add(TeamCustomClass.newTeamClass("Dooke",R.drawable.duke));
        mTeams.add(TeamCustomClass.newTeamClass("Florida State",R.drawable.fsu));
        mTeams.add(TeamCustomClass.newTeamClass("Florida",R.drawable.gators));
        mTeams.add(TeamCustomClass.newTeamClass("Georgia Tech",R.drawable.gt));
        mTeams.add(TeamCustomClass.newTeamClass("Notre Dame",R.drawable.irish));
        mTeams.add(TeamCustomClass.newTeamClass("Kentucky",R.drawable.ken));
        mTeams.add(TeamCustomClass.newTeamClass("Michigan",R.drawable.mich));
        mTeams.add(TeamCustomClass.newTeamClass("NC State",R.drawable.ncstate));
        mTeams.add(TeamCustomClass.newTeamClass("Pittsburgh",R.drawable.pitt));
        mTeams.add(TeamCustomClass.newTeamClass("Syracuse",R.drawable.syracuse));
        mTeams.add(TeamCustomClass.newTeamClass("Maryland",R.drawable.terps));
        mTeams.add(TeamCustomClass.newTeamClass("UConn",R.drawable.uconn));
        mTeams.add(TeamCustomClass.newTeamClass("North Carolina",R.drawable.unc));
        mTeams.add(TeamCustomClass.newTeamClass("Virginia Tech",R.drawable.vt));
        mTeams.add(TeamCustomClass.newTeamClass("Wake Forest",R.drawable.wake));


        ArrayList<HashMap<String, Object>> newTeam = new ArrayList<HashMap<String,Object>>();
        for (TeamCustomClass teamCustomClass : mTeams){
            HashMap<String, Object> meMap=new HashMap<String, Object>();
            meMap.put(teamname,teamCustomClass.getmTeamName());
            meMap.put(img,teamCustomClass.getmImage());
            newTeam.add(meMap);
        }



        // get orientation
        int rotation = getWindowManager().getDefaultDisplay().getRotation();
        // set activity on view rotation
        if (rotation == Surface.ROTATION_0 || rotation == Surface.ROTATION_180) {

            // Grab our Spinner by ID and assign it to a variable.
            final Spinner sp = (Spinner) findViewById(R.id.mySpinner);
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
                }

            });

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
