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
import android.widget.Toast;
import static java.lang.Math.*;


import java.util.ArrayList;


public class MyActivity extends Activity {
    // create array of strings
public static final ArrayList<String> stringList = new ArrayList<String>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);
    }

    public void onButtonClick(View v) {

        // initiate button control with the self view v
        Button myButton = (Button) v;

        // tell the editText named message to find view
        final EditText myEditText = (EditText) findViewById(R.id.myEditText);        //takes input and saves as string
        final String my_message = myEditText.getText().toString();

        if (my_message.matches("")) {
            Toast.makeText(this, "You must enter some text", Toast.LENGTH_SHORT).show();
            return;
            } else {
            //add string to array
            stringList.add(my_message);
            Toast.makeText(this, "Text saved!", Toast.LENGTH_SHORT).show();
            myEditText.setText(null);
            return;
        }
    }

    public void onButtonClick2(View v) {
        new AlertDialog.Builder(this)
                .setTitle("How many in Arraylist?")
                .setMessage(String.valueOf(stringList.size()))
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // continue with delete
                    }
                })
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();
    }


    public void mathFunction(View v) {
        // do something for on Click of Show length
            Toast.makeText(this, "Show length is running", Toast.LENGTH_SHORT).show();
            return;
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
