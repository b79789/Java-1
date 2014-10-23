package com.brianstacks.asynctest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.io.StringReader;

/**
 * Created by Brian Stacks
 * on 10/20/14
 * for FullSail.edu.
 */
public class JSONparser {

    public static List<Teams> parseFeed(String content){
        JSONArray ar = null;
        try {
            ar = new JSONArray(content);
            List<Teams> teamList =new ArrayList<>();

            for (int i = 0; i < ar.length();i++){

                JSONObject obj = ar.getJSONObject(i);
                Teams team = new Teams();

                team.setName(obj.getString("name"));
                //team.setNickName(obj.getString("nick"));
                //team.setPhoto(obj.getString("photourl"));
                //team.setWebSite(obj.getString("website"));

                teamList.add(team);
            }
            return teamList;
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }

    }

}
