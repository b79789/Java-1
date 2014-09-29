package com.brianstacks.androiddemoapp;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ShareActionProvider;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;


public class MainActivity extends Activity implements View.OnClickListener, AdapterView.OnItemClickListener {

    TextView mainTextView;
    Button mainButton;
    EditText mainEditText;
    ListView mainListView;
    ArrayAdapter<String> mArrayAdapter;
    ArrayList<String> mNameList = new ArrayList<String>();
    ShareActionProvider mShareActionProvider;
    private static final String PREFS = "prefs";
    private static final String PREF_NAME = "name";
    SharedPreferences mSharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 1. Access the TextView defined in layout XML
        // and then set its text
        mainTextView = (TextView) findViewById(R.id.main_textview);
        mainTextView.setText("Set in Java!");
        mainButton = (Button) findViewById(R.id.main_button);
        mainButton.setOnClickListener(this);
        // 3. Access the EditText defined in layout XML
        mainEditText = (EditText) findViewById(R.id.main_edittext);
        // 4. Access the ListView
        mainListView = (ListView) findViewById(R.id.main_listview);

        mainEditText.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_FLAG_CAP_SENTENCES);
        mainEditText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    // do any thing here
                    // Take what was typed into the EditText
                    // and use in TextView
                    mainTextView.setText(mainEditText.getText().toString()
                            + " is learning Android development!");
                    // Also add that value to the list shown in the ListView

                    String input = mainEditText.getText().toString();

                    if (input == null | input.trim().equals("")) {

                        Toast.makeText(getApplicationContext(), "You must enter some text", Toast.LENGTH_LONG).show();
                    }else {

                        mNameList.add(mainEditText.getText().toString());
                        mArrayAdapter.notifyDataSetChanged();
                        // 6. The text you'd like to share has changed,
                        // and you need to update
                        setShareIntent();
                        mainEditText.setText("");
                        removeKeyboard();
                    }

                }
                return false;
            }
        });
        // Create an ArrayAdapter for the ListView
        mArrayAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1,
                mNameList);
        // Set the ListView to use the ArrayAdapter
        mainListView.setAdapter(mArrayAdapter);
        // 7. Greet the user, or ask for their name if new
        displayWelcome();
    }

    public void removeKeyboard(){
        InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE); imm.hideSoftInputFromWindow(mainEditText.getWindowToken(), 0);

    }

    private void displayWelcome() {
        // Access the device's key-value storage
        mSharedPreferences = getSharedPreferences(PREFS, MODE_PRIVATE);

        // Read the user's name,
        // or an empty string if nothing found
        String name = mSharedPreferences.getString(PREF_NAME, "");

        if (name.length() > 0) {

            // If the name is valid, display a Toast welcoming them
            Toast.makeText(this, "Welcome back, " + name + "!", Toast.LENGTH_LONG).show();
        } else {

        // otherwise, show a dialog to ask for their name
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setTitle("Hello!");
        alert.setMessage("What is your name?");

        // Create EditText for entry
        final EditText input = new EditText(this);
            input.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_FLAG_CAP_SENTENCES);
        alert.setView(input);

        // Make an "OK" button to save the name
        alert.setPositiveButton("OK", new DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialog, int whichButton) {

                // Grab the EditText's input
                String inputName = input.getText().toString();

                // Put it into memory (don't forget to commit!)
                SharedPreferences.Editor e = mSharedPreferences.edit();
                e.putString(PREF_NAME, inputName);
                e.apply();

                // Welcome the new user
                Toast.makeText(getApplicationContext(), "Welcome, " + inputName + "!", Toast.LENGTH_SHORT).show();
            }
        });

        // Make a "Cancel" button
        // that simply dismisses the alert
        alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialog, int whichButton) {}
        });

        alert.show();
       }

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        // Inflate the menu.
        // Adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);

        // Access the Share Item defined in menu XML
        MenuItem shareItem = menu.findItem(R.id.menu_item_share);

        // Access the object responsible for
        // putting together the sharing submenu
        if (shareItem != null) {
            mShareActionProvider = (ShareActionProvider)shareItem.getActionProvider();
        }

        // Create an Intent to share your content
        setShareIntent();

        return true;
    }

    private void setShareIntent() {

        if (mShareActionProvider != null) {

            // create an Intent with the contents of the TextView
            Intent shareIntent = new Intent(Intent.ACTION_SEND);
            shareIntent.setType("text/plain");
            shareIntent.putExtra(Intent.EXTRA_SUBJECT, "Android Development");
            shareIntent.putExtra(Intent.EXTRA_TEXT, mainTextView.getText());

            // Make sure the provider knows
            // it should work with that Intent
            mShareActionProvider.setShareIntent(shareIntent);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        return id == R.id.menu_item_share || super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {

        String input = mainEditText.getText().toString();

        if (input == null | input.trim().equals("")) {
            Toast.makeText(getApplicationContext(), "You must enter some text", Toast.LENGTH_SHORT).show();

        }else {
            // Take what was typed into the EditText
            // and use in TextView
            mainTextView.setText(mainEditText.getText().toString()
                    + " is learning Android development!");
            mNameList.add(mainEditText.getText().toString());
            mArrayAdapter.notifyDataSetChanged();
            // 6. The text you'd like to share has changed,
            // and you need to update
            setShareIntent();
            mainEditText.setText("");
            removeKeyboard();
            mainListView.setOnItemClickListener(this);
        }


    }


    @Override
    public void onItemClick(AdapterView parent, View view, int position, long id) {
        // Log the item's position and contents
        // to the console in Debug
        Log.d("omg android", position + ": " + mNameList.get(position));
    }



}
