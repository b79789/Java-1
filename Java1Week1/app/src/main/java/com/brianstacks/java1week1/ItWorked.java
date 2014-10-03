/*Brian Stacks
 9-29-2014
 Java-1 Mr.Story
*/
package com.brianstacks.java1week1;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;


public class ItWorked extends Activity {
    // create array of strings
    public static final ArrayList<String> stringList = new ArrayList<String>();
    public static final List<String> newList = new ArrayList<String>(new HashSet<String>(stringList));
    EditText enterText;
    TextView collectionText;
    TextView collectionAverageText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);
        collectionText = (TextView) findViewById(R.id.collection_text);
        collectionAverageText = (TextView) findViewById(R.id.average_text);
        // removes object characters like [] and ,'s and gives me just letters inputted
        StringBuilder builder = new StringBuilder();
        for (String value : newList) {
            builder.append(value);
        }
        //put all builder object to string
        String allCharactersInArray = builder.toString();
        // do the math for average here
        float characterCount = allCharactersInArray.length();
        float newAverageLength = characterCount/newList.size();
        collectionText.setText(newList.toString());
        collectionAverageText.setText(String.valueOf(newAverageLength));


    }

    public void onClick1(View view) {

        enterText = (EditText) findViewById(R.id.enterText);
        //takes input and saves as string
        final String my_message = enterText.getText().toString();
        if (my_message.matches("")) {
            Toast.makeText(this, "You must enter some text", Toast.LENGTH_SHORT).show();
        } else if(newList.contains(my_message)) {
            Toast.makeText(this, "No duplicates!", Toast.LENGTH_SHORT).show();
        }else {
            //add string to array if it has not been added
            if (!newList.contains(my_message)) {newList.add(my_message);}
            Toast.makeText(this, "Text saved!", Toast.LENGTH_SHORT).show();
            reload();
        }
    }

    public void onClick2(View view){
        // create alert  to show value
        new AlertDialog.Builder(this)
                .setTitle("How many in Arraylist?")
                .setMessage(String.valueOf(newList.size()))
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // continue with delete
                    }
                })
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();
    }

    public void onClick3(View view){
        // do something for on Click of Show avg length
        // removes object characters like [] and ,'s and gives me just letters inputted
        StringBuilder builder = new StringBuilder();
        for (String value : newList) {
            builder.append(value);
        }
        //put all builder object to string
        String allCharactersInArray = builder.toString();
        // do the math for average here
        float characterCount = allCharactersInArray.length();
        float newAverageLength = characterCount/newList.size();
        // create alert  to show value
        new AlertDialog.Builder(this)
                .setTitle("Average Length?")
                .setMessage(String.valueOf(newAverageLength))
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // continue with delete
                    }
                })
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();
    }

    public void onClick4(View view){
        final AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        View convertView = inflater.inflate(R.layout.customxml, null);
        alertDialog.setView(convertView);
        alertDialog.setTitle("List");
        ListView lv = (ListView) convertView.findViewById(R.id.listView1);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,newList);
        lv.setAdapter(adapter);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Toast.makeText(getApplicationContext(), "the text index clicked is :: " + Integer.toString(position),
                        Toast.LENGTH_LONG).show();
            }
        });

        alertDialog.setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        alertDialog.setNegativeButton("Remove ", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                //User clicked remove so remove it
                if (newList.size() == 0) {
                    //do something here
                    reload();

                } else {
                    newList.remove((newList.size() - 1));
                }
            }
        });
        alertDialog.show();

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

    // custom function to reload the view
    public void reload() {

        Intent intent = getIntent();
        overridePendingTransition(0, 0);
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        finish();
        overridePendingTransition(0, 0);
        startActivity(intent);
    }

}
