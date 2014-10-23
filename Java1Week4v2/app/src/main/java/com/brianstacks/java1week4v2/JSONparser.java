package com.brianstacks.java1week4v2;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Brian Stacks
 * on 10/20/14
 * for FullSail.edu.
 */
public class JSONParser {

    public static List<Places> parseFeed(String content) {

        JSONObject myObj;
        try {
            myObj = new JSONObject(content);
            JSONArray result = myObj.getJSONArray("results");
            List<Places> placeList = new ArrayList<>();

            for (int i = 0; i <= result.length(); i++) {
                JSONObject obj = result.getJSONObject(i);
                Places place = new Places();
                place.setName(obj.getString("name"));
                place.setFormatted_address(obj.getString("formatted_address"));
                place.setTypes(obj.getString("types"));
                place.setPhotos(obj.getString("photos"));

                placeList.add(place);

            }

            return placeList;
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }

    }

}