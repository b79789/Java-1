package com.brianstacks.java1project1;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


import java.util.ArrayList;


public class MyActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);
    }

    public void onButtonClick(View v) {
        // grabs textview names it to textViewToChange finds by findViewById
        final TextView textViewToChange = (TextView) findViewById(R.id.entryText);
        final TextView textViewToChange2 = (TextView) findViewById(R.id.avgLengthText);
        // initiate button control with the self view v
        Button myButton =(Button)v;

        // create array of strings
        final ArrayList<String> stringList = new ArrayList<String>();
        //give the editText a name
        final EditText message;
        // tell the editText named message to find view
        message = (EditText) findViewById(R.id.myEditText);
        //takes input and saves as string
        final String my_message = message.getText().toString();
        // due something with string here

        new AlertDialog.Builder(this)
                .setTitle("Added Entry")
                .setMessage("Are you sure you want to Add this entry?")
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // continue with add
                        //add string to array
                        stringList.add(my_message);

                        // set textviews value
                        textViewToChange.setText(stringList.get(0));
                        textViewToChange2.setText(String.valueOf(stringList.size()));
                        //log values
                        Log.v("My value::", String.valueOf(stringList.size()));
                        message.setText(null);

                    }
                })
                .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // do nothing
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
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
