package com.brianstacks.java1week3ver2;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.HashMap;

public class MyActivity extends Activity {
    // give spinner and listview references
    Spinner spin;
    ListView myList;
    // Names for key tag
    final String TAG = "Spinner Test";
    final String Nickname = "Nickname";
    final String NAME = "NAME";
    final String IMAGES = "Images";
    // create custom array list
    final ArrayList<Team> customObjList = new ArrayList<Team>();
    // Create custom adapter
    final CustomAdapter cArrayAdapter = new CustomAdapter(this,customObjList);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // set the content view
        setContentView(R.layout.activity_my);
        spin = (Spinner) findViewById(R.id.my_spinner);
        myList = (ListView) findViewById(R.id.landList);
        // stringArray of the 20 teams
        String myTeamList[] = getResources().getStringArray(R.array.teams);
        // stringArray of the 20 nicknames
        String myNicknameList[] = getResources().getStringArray(R.array.nicks);
        // stringArray of the 20 images
        int[] myImageList = new int[]{R.drawable.bc, R.drawable.canes, R.drawable.cards, R.drawable.cavs, R.drawable.clemson,R.drawable.duke,R.drawable.fsu,R.drawable.gators,R.drawable.gt,R.drawable.irish,
                R.drawable.ken,R.drawable.mich,R.drawable.ncstate,R.drawable.pitt,R.drawable.terps,R.drawable.uconn,R.drawable.unc,R.drawable.syracuse,R.drawable.vt,R.drawable.wake};
        //loop through one of the arrays and add each array to the custom object
        for (int k =0; k< myTeamList.length;k++){
            // create hashMap and add the  String arrays to the map
            HashMap<String, String> teamNameString = new HashMap<String, String>();
            // create strings to catch string array data
            teamNameString.put(NAME,myTeamList[k]);
            teamNameString.put(Nickname,myNicknameList[k]);
            // create another hash map to add my images which are Integers
            HashMap<String, Integer> teamImages = new HashMap<String, Integer>();
            // create int to catch image(int) array data
            teamImages.put(IMAGES,myImageList[k]);
            // create values to put into the custom object parameters
            int value3 = teamImages.get(IMAGES);
            String value = teamNameString.get(NAME);
            String value2 = teamNameString.get(Nickname);
            //add data to the customObjList(custom object)
            customObjList.add(new Team(value,value2,value3));
        }

            spin.setAdapter(cArrayAdapter);
            //ADD SPINNER EVENT LISTENER
            spin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {

                    setDetails(position);
                }
                @Override
                public void onNothingSelected(AdapterView<?> adapterView) {
                    Log.i(TAG,"Nothing Selected");
                }
            });
            myList.setAdapter(cArrayAdapter);
            myList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                public void onItemClick(AdapterView<?> parent, View view, int position,long id){
                    setDetails(position);
                }
            });
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

    public void setDetails(int position){

        Team item = cArrayAdapter.getItem(position);
        TextView textLabel = (TextView) findViewById(R.id.teamNameMain);
        textLabel.setBackgroundColor(Color.GREEN);
        textLabel.setTextColor(Color.BLUE);
        textLabel.setText(item.getName());
        TextView textLabel2 = (TextView) findViewById(R.id.teamNickMain);
        textLabel2.setBackgroundColor(Color.GREEN);
        textLabel2.setTextColor(Color.BLUE);
        textLabel2.setText(item.getNick());
        ImageView iv = (ImageView) findViewById(R.id.teamImageMain);
        iv.setImageResource(item.getImages());

    }
}
