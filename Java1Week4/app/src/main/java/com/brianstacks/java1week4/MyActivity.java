package com.brianstacks.java1week4;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;


public class MyActivity extends Activity {
    static InputStream is = null;
    static JSONObject jObj = null;
    static String json = "";
    String url=null;
    List<NameValuePair> nvp=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);


    }

    public void onClickBtn(View v)
    {
        // Get the connectivity manager as a system service.
        // The Context class provides several string constants for
        // accessing various system services.
        ConnectivityManager mgr = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
        // Getting our active network information.
        NetworkInfo netInfo = mgr.getActiveNetworkInfo();
        // We have a network connection, but not necessarily a data connection.
        if(netInfo != null) {
            if(netInfo.getType() == ConnectivityManager.TYPE_MOBILE) {
                // We're on 3G/4G data
                Log.v("Data type:","3G/4G");
                Toast.makeText(this, "Data type:3G/4G", Toast.LENGTH_LONG).show();
            } else if(netInfo.getType() == ConnectivityManager.TYPE_WIFI) {
                // We're on WiFi data
                Log.v("Data type:","WiFi");
                Toast.makeText(this, "Data type:WiFi", Toast.LENGTH_LONG).show();
            }
            if(netInfo.isConnected()) {
                // We have a valid data connection
                Log.v("Data type valid:"," Yes");
                Toast.makeText(this, "Data type valid: Yes", Toast.LENGTH_LONG).show();
            }
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
        return id == R.id.action_settings || super.onOptionsItemSelected(item);
        
    }
    protected void updateDisplay(String message){
        Toast.makeText(this, "Display updated", Toast.LENGTH_LONG).show();
    }

    private class myTask extends AsyncTask<String,String,String>{

        @Override
        protected void onPreExecute() {

        }

        @Override
        protected String doInBackground(String... params) {
            return null;
        }

        @Override
        protected void onPostExecute(String s) {

        }
    }
}
