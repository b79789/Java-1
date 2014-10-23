package com.brianstacks.asynctest;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.JsonReader;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.FileReader;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;


public class MyActivity extends Activity {
    TextView textView;
    ProgressBar pb;
    List<MyTask> tasks;
    Gson gson = new Gson();
    List<Teams> teamList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);
        textView = (TextView) findViewById(R.id.myText);
        pb = (ProgressBar) findViewById(R.id.progressBar);
        pb.setVisibility(View.INVISIBLE);
        tasks = new ArrayList<>();
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
    protected  void updateDisplay(String message){
        textView.append(message);
    }


    protected boolean isOnline(){
        ConnectivityManager cm =(ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo  netInfo = cm.getActiveNetworkInfo();
        return netInfo != null && netInfo.isConnectedOrConnecting();

    }

    public void myClick(View view){
        if (isOnline()){
            requestData("https://maps.googleapis.com/maps/api/place/textsearch/json?query=restaurants+in+Sydney&key=AIzaSyB9iOw6wF4FwbOdUTZYiU_MxsbfWM5iMOI");
        }else Toast.makeText(this, "Network isn't available", Toast.LENGTH_SHORT).show();

    }

    private void requestData(String uri) {
        MyTask task = new MyTask();
        task.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, uri);
    }

    private class MyTask extends AsyncTask<String ,String ,String>{

        @Override
        protected void onPreExecute() {

           updateDisplay("Starting Task");

            if (tasks.size() == 0){

            pb.setVisibility(View.VISIBLE);
            }
            tasks.add(this);
        }

        @Override
        protected String doInBackground(String... params) {
            return HttpManager.getData(params[0]);
        }

        @Override
        protected void onPostExecute(String result) {

            JsonObject jsonObject = gson.fromJson(result, JsonObject.class);

            JsonElement getInfo =jsonObject.get("results");
            //String dataTitle = getInfo.toString();


            updateDisplay(result);
            Log.v("Results", getInfo.toString());



            tasks.remove(this);
            if (tasks.size() == 0){

                pb.setVisibility(View.INVISIBLE);
            }

        }

        @Override
        protected void onProgressUpdate(String... values) {
            updateDisplay(values[0]);
        }
    }


}
