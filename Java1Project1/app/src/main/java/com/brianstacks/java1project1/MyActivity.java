/*Brian Stacks
 9-9-2014
 Java-1 Mr.Story
*/
package com.brianstacks.java1project1;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;



public class MyActivity extends Activity {
    // create array of strings
public static final ArrayList<String> stringList = new ArrayList<String>();
public static final List<String> newList = new ArrayList<String>(new HashSet<String>(stringList));

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);
        final TextView txtView = (TextView) findViewById(R.id.myButton);
        final TextView txtView2 = (TextView) findViewById(R.id.button);
        final TextView txtView3 = (TextView) findViewById(R.id.ShowButton2);
        final TextView txtView4 = (TextView) findViewById(R.id.button2);
        txtView.setBackgroundResource(R.color.white);
        txtView2.setBackgroundResource(R.color.white);
        txtView3.setBackgroundResource(R.color.white);
        txtView4.setBackgroundResource(R.color.white);


    }





    // function to get the user input with no duplicates or empty strings
    public void onButtonClick(View v) {
        // tell the editText named message to find view
        final EditText myEditText = (EditText) findViewById(R.id.myEditText);        //takes input and saves as string
        final String my_message = myEditText.getText().toString();

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
    // function to show how many in the array
    public void onButtonClick2(View v) {
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


    public void mathFunction(View v) {
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

    // custom function to reload the view
    public void reload() {

        Intent intent = getIntent();
        overridePendingTransition(0, 0);
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        finish();
        overridePendingTransition(0, 0);
        startActivity(intent);
    }
    // function for showing the elements in array
    public void onButtonClick3(View v) {
        // create alert  to show value
        new AlertDialog.Builder(this)
                .setTitle("The elements!")
                .setMessage(newList.toString())
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .setNegativeButton("Remove Last", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // what to do when cancel is hit
                        if (newList.size() == 0) {
                            //do something here
                            reload();

                        } else {
                            newList.remove((newList.size() - 1));
                        }

                    }
                })
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();


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
        return id == R.id.action_settings||super.onOptionsItemSelected(item);
    }
}
