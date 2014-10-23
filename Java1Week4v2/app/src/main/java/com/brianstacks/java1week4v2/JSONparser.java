package com.brianstacks.java1week4v2;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Brian Stacks
 * on 10/20/14
 * for FullSail.edu.
 */
public class JSONParser {

    public static List<Places> parseFeed(String content){
        try {
            JSONObject myObj = new JSONObject(content);
            Iterator x = myObj.keys();
            JSONArray jsonArray = new JSONArray();

            while (x.hasNext()){
                String key = (String) x.next();
                jsonArray.put(myObj.get(key));
            }
            List<Places> placeList =new ArrayList<>();

            for (int i = 0; i < jsonArray.length();i++){

                JSONObject obj = jsonArray.getJSONObject(i);
                Places place = new Places();

                Places.setName(obj.getString("name"));
                Places.setFormatted_address(obj.getString("formatted_address"));
                Places.setTypes(obj.getString("types"));
                Places.setPhotos(obj.getString("photos"));

                placeList.add(place);
            }
            return placeList;
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }

    }

}
