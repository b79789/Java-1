package com.brianstacks.java1week3ver2;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
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
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;

import static android.content.res.Configuration.*;

public class MyActivity extends Activity {
    Spinner spin;
    ListView myList;
    final String TAG = "Spinner Test";
    final String Nickname = "Nickname";
    final String NAME = "NAME";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Array list that contains my hash map
        final ArrayList<HashMap<String, String>> newTeam = new ArrayList<HashMap<String, String>>();
        // stringArray of the 20 teams
        String myTeamList[] = getResources().getStringArray(R.array.teams);
        // stringArray of the 20 nicknames
        String myNicknameList[] = getResources().getStringArray(R.array.nicks);
        //Array of images
        int[] myImageList = new int[]{R.drawable.bc, R.drawable.canes, R.drawable.cards, R.drawable.cavs, R.drawable.clemson,R.drawable.duke,R.drawable.fsu,R.drawable.gators,R.drawable.gt,R.drawable.irish,
                R.drawable.ken,R.drawable.mich,R.drawable.ncstate,R.drawable.pitt,R.drawable.syracuse,R.drawable.terps,R.drawable.uconn,R.drawable.unc,R.drawable.vt,R.drawable.wake};

        // create custom array list
        ArrayList<Team> customObjList = new ArrayList<Team>();
        for (int k =0; k< myTeamList.length;k++){
            // create hashMap and add the arrays to the map
            HashMap<String, String> teamNameString = new HashMap<String, String>();
            teamNameString.put(NAME,myTeamList[k]);
            teamNameString.put(Nickname,myNicknameList[k]);

            newTeam.add(teamNameString);
            String value = teamNameString.get(NAME);
            String value2 = teamNameString.get(Nickname);
            customObjList.add(new Team(value,value2,0));



        }

        Log.v("custom object list",customObjList.toString());

        if (getResources().getConfiguration().orientation == ORIENTATION_PORTRAIT) {
            setContentView(R.layout.activity_my);
            spin = (Spinner) findViewById(R.id.my_spinner);
            ArrayAdapter<HashMap<String, String>> arrayAdapter = new ArrayAdapter<HashMap<String, String>>(this,android.R.layout.simple_spinner_dropdown_item,newTeam);
            CustomAdapter cArrayAdapter = new CustomAdapter(this,customObjList);
            spin.setAdapter(cArrayAdapter);
            //ADD SPINNER EVENT LISTENER
            spin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {




                }

                @Override
                public void onNothingSelected(AdapterView<?> adapterView) {
                    Log.i(TAG,"Nothing Selected");
                }
            });
        }else {
            setContentView(R.layout.verticallayout);
            myList = (ListView) findViewById(R.id.my_list);
            final ArrayList<String> teamList = new ArrayList<String>(Arrays.asList(getResources().getStringArray(R.array.teams)));
            ArrayAdapter<HashMap<String, String>> arrayAdapter = new ArrayAdapter<HashMap<String, String>>(this, android.R.layout.simple_list_item_1, newTeam);
            myList.setAdapter(arrayAdapter);
            myList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                public void onItemClick(AdapterView<?> parent, View view, int position,long id){
                    Object obj = myList.getItemAtPosition(position);
                    String val = myList.getItemAtPosition(position).toString();
                    TextView textLabel = (TextView) findViewById(R.id.teamName2);
                    TextView textLabe2 = (TextView) findViewById(R.id.teamNick2);
                    textLabel.setText(obj.toString());
                    textLabe2.setText(val);

                }
            });
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

    public void setMyView(){


    }
}
