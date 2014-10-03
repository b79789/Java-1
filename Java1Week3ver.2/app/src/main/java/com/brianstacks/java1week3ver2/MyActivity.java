package com.brianstacks.java1week3ver2;

import android.app.Activity;
import android.content.Context;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


public class MyActivity extends Activity {

    Spinner spin;
    ListView myList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);
        spin = (Spinner) findViewById(R.id.my_spinner);
        myList = (ListView) findViewById(R.id.my_list);
        if (getResources().getConfiguration().orientation ==
                Configuration.ORIENTATION_PORTRAIT) {
                setContentView(R.layout.activity_my);
            Toast.makeText(this, "Portrait Vertical", Toast.LENGTH_SHORT).show();

        } else if(getResources().getConfiguration().orientation ==
                Configuration.ORIENTATION_LANDSCAPE){
                setContentView(R.layout.verticallayout);

            Toast.makeText(this, "landscape horizontal", Toast.LENGTH_SHORT).show();
        }else {
            // do nothing
            Toast.makeText(this, "Weird View", Toast.LENGTH_SHORT).show();
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
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
