package com.brianstacks.java1week4v2;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.List;


public class MyActivity extends Activity {
    // create some references for ui elements
    EditText urlText;
    TextView textView;
    ProgressBar pb;
    // create a reference to the list's needed for data
    List<MyTask> tasks;
    List<Places>  placeList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);
        // get my ui elements by id
        urlText = (EditText) findViewById(R.id.myUrl);
        textView = (TextView) findViewById(R.id.myText);
        pb = (ProgressBar) findViewById(R.id.progressBar);
        // set the progress bar to invisible
        pb.setVisibility(View.INVISIBLE);
        //initiate my tasks
        tasks = new ArrayList<>();
        // make text view scrollable
        textView.setMovementMethod(new ScrollingMovementMethod());
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
    // the method for when the button is clicked
    public void myClick(View view){
        // create a string to grab the text of the edit text
        String myString = urlText.getText().toString();
        // replace the spaces with + to encode into the url
        String encodedString = myString.replace(" ","+");
        //check to see if online and if so continue to get the JSON data if not toast a message telling the user no connection
        if (isOnline()){
            requestData("https://maps.googleapis.com/maps/api/place/textsearch/json?query="+encodedString+"&key=AIzaSyB9iOw6wF4FwbOdUTZYiU_MxsbfWM5iMOI");
        }else Toast.makeText(this, "Network isn't available", Toast.LENGTH_SHORT).show();

    }
    // method to update th ui
    protected  void updateDisplay(){
        if (placeList != null){
            for (Places place: placeList){
                textView.append(place.getName());
            }
        }


    }
    // method to check internet connectivity
    protected boolean isOnline(){
        ConnectivityManager cm =(ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo  netInfo = cm.getActiveNetworkInfo();
        return netInfo != null && netInfo.isConnectedOrConnecting();

    }
    // method to get the data from ASYNC task
    private void requestData(String uri) {
        MyTask task = new MyTask();
        task.execute(uri);
    }
    // Async task method to do network action in
    private class MyTask extends AsyncTask<String ,String ,String>{

        @Override
        protected void onPreExecute() {
            if (tasks.size() == 0){
                // set the progress bar to visible while Async task is running
                pb.setVisibility(View.VISIBLE);
            }
            // add this to the task
            tasks.add(this);
        }

        @Override
        protected String doInBackground(String... params) {
            return HttpManager.getData(params[0]);
        }

        @Override
        protected void onPostExecute(String result) {

            placeList = JSONParser.parseFeed(result);
            Log.v("message:", String.valueOf(placeList));
            updateDisplay();

            tasks.remove(this);
            if (tasks.size() == 0){
                // remove progress bar visibility from ui
                pb.setVisibility(View.INVISIBLE);
            }

        }

        @Override
        protected void onProgressUpdate(String... values) {
            //updateDisplay(values[0]);
        }
    }
}