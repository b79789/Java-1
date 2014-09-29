package com.brianstacks.java1week4;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import org.apache.commons.io.IOUtils;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;



public class MyActivity extends Activity {

    List<MyTask> tasks;
    Spinner spin;
    Button myButton;
    TextView myTextView1;
    ProgressBar pb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);


        spin = (Spinner) findViewById(R.id.mySpinner);
        myButton = (Button) findViewById(R.id.button1);
        myTextView1 = (TextView) findViewById(R.id.myTextView1);
        myTextView1.setMovementMethod(new ScrollingMovementMethod());
        pb = (ProgressBar) findViewById(R.id.progressBar);
        pb.setVisibility(View.INVISIBLE);
        tasks = new ArrayList<>();
        List myList = new ArrayList();
        myList.add("Select item");
        myList.add("MLB");
        myList.add("NBA");
        myList.add("NFL");
        myList.add("NHL");
        ArrayAdapter dataAdapter = new ArrayAdapter(this,android.R.layout.simple_spinner_item, myList);
        spin.setAdapter(dataAdapter);
        spin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                // go code here
               // check to see which spinner category was selected
                if (String.valueOf(spin.getSelectedItem()) == "MLB"){
                    View inflatedView = getLayoutInflater().inflate(R.layout.activitytwo, null);
                    TextView actTwoLV = (TextView) inflatedView.findViewById(R.id.leagueListView);
                   String mlbApi = "http://api.espn.com/v1/sports/baseball/mlb/teams?apikey=73w2tfvcdfvt6m7m86x4mdvt";
                    //Toast.makeText(MyActivity.this,"MLB was clicked " ,Toast.LENGTH_SHORT).show();
                    //+ String.valueOf(spin.getSelectedItem())
                }else if (String.valueOf(spin.getSelectedItem()) == "NBA"){
                    String nbaApi = "http://api.espn.com/v1/sports/basketball/nba/teams?apikey=73w2tfvcdfvt6m7m86x4mdvt";
                    Toast.makeText(MyActivity.this,"NBA was clicked " ,Toast.LENGTH_SHORT).show();
                }else if (String.valueOf(spin.getSelectedItem()) == "NFL"){
                    String nflApi = "http://api.espn.com/v1/sports/football/nfl/teams?apikey=73w2tfvcdfvt6m7m86x4mdvt";
                    Toast.makeText(MyActivity.this,"NFL was clicked " ,Toast.LENGTH_SHORT).show();
                }else if (String.valueOf(spin.getSelectedItem()) == "NHL"){
                    String nhlApi = "http://api.espn.com/v1/sports/hockey/nhl/teams?apikey=73w2tfvcdfvt6m7m86x4mdvt";
                    Toast.makeText(MyActivity.this,"NHL was clicked " ,Toast.LENGTH_SHORT).show();
                }else {
                    //do nothing
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // nothing code here
            }

        });
        myButton.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                spin = (Spinner) findViewById(R.id.mySpinner);
                myButton = (Button) findViewById(R.id.button1);

                myButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        MyTask theTask = new MyTask();
                        theTask.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR,"Param1","Param2","Param3");
                            //updateDisplay(String.valueOf(spin.getSelectedItem().toString()));

                    }


                });


            }
        });

    }



    public void changelayout(View view){
        setContentView(R.layout.activitytwo);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.my, menu);
        return true;
    }

    public void updateDisplay(String message){

        myTextView1.append(message+"\n");

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        return id == R.id.action_settings || super.onOptionsItemSelected(item);
        
    }


    private class MyTask extends AsyncTask<String,String,String>{

        @Override
        protected void onPreExecute() {
            updateDisplay("Starting Network Detection");
            if (tasks.size() == 0){
                pb.setVisibility(View.VISIBLE);
            }tasks.add(this);


        }

        @Override
        protected String doInBackground(String... params) {

            // The URL string that points to our web resource.
            String urlString = "http://api.espn.com/v1/sports/basketball/nba/teams?apikey=73w2tfvcdfvt6m7m86x4mdvt";
// Creating the URL object that points to our web resource.
            URL url = null;
            try {
                url = new URL(urlString);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
// Establish a connection to the resource at the URL.
            HttpURLConnection connection = null;
            try {
                connection = (HttpURLConnection) url.openConnection();
            } catch (IOException e) {
                e.printStackTrace();
            }
// Setting connection properties.
            try {
                connection.setRequestMethod("GET");
            } catch (ProtocolException e) {
                e.printStackTrace();
            }
            connection.setConnectTimeout(10000); // 10 seconds
            connection.setReadTimeout(10000); // 10 seconds
// Refreshing the connection.
            try {
                connection.connect();
            } catch (IOException e) {
                e.printStackTrace();
            }
// Optionally check the status code. Status 200 means everything went OK.
            int statusCode = 0;
            try {
                statusCode = connection.getResponseCode();
            } catch (IOException e) {
                e.printStackTrace();
            }
// Getting the InputStream with the data from our resource.
            InputStream stream = null;
            try {
                stream = connection.getInputStream();
            } catch (IOException e) {
                e.printStackTrace();
            }
// Reading data from the InputStream using the Apache library.
            String resourceData = null;
            try {
                resourceData = IOUtils.toString(stream);
            } catch (IOException e) {
                e.printStackTrace();
            }

// Cleaning up our connection resources.
            assert stream != null;
            try {
                stream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            connection.disconnect();
// The resourceData string should now have our data.
            Log.v("resourceData:", resourceData);

            for (int i = 0; i < params.length; i++) {
                publishProgress("Working with" + params[i]);

                try {
                    {
                        Thread.sleep(1000);
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            return resourceData;
        }

        @Override
        protected void onPostExecute(String result) {

            updateDisplay(result);
            tasks.remove(this);
            if (tasks.size() == 0){
                pb.setVisibility(View.INVISIBLE);
            }tasks.add(this);


        }

        @Override
        protected  void  onProgressUpdate(String... values){

            updateDisplay(values[0]);
        }



    }


}
