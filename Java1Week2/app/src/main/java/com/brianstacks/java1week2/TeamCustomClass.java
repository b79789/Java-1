package com.brianstacks.java1week2;


/**
 * Created by Brian Stacks
  on 9/17/14
  for FullSail.edu.
 */
//TeamCustomClass.java
public class TeamCustomClass {
    // Variables
    private int mImage;
    private String mTeamName;
    //Class Constructor
    public TeamCustomClass(){
        mTeamName = "";
        mImage = 0;
    }
    public static   TeamCustomClass newTeamClass(String _name,int _image) {
        TeamCustomClass teamCustomClass = new TeamCustomClass();
        teamCustomClass.setmImage(_image);
        teamCustomClass.setmTeamName(_name);
        return teamCustomClass;
    }

    public int getmImage(){
        return mImage;
    }
    public void setmImage(int _image){
        mImage=_image;
    }
    public String getmTeamName(){
        return mTeamName;
    }
    public void setmTeamName(String _name){
        mTeamName=_name;
    }

}
