package com.brianstacks.java1week3ver2;

/**
 * Created by Brian Stacks
 * on 10/8/14
 * for FullSail.edu.
 */
public class Team {
        //Team variables
    private String mTeamName;
    private String mTeamNickname;
    private int mTeamImage;

    public Team(){
        mTeamName ="";
        mTeamNickname="";
        mTeamImage=-0;
    }

    public Team(String _name, String _nick, int _tImage) {
        mTeamImage = _tImage;
        mTeamName = _name;
        mTeamNickname = _nick;
    }

    // Getter and setter methods.
    public int getImages() {
        return mTeamImage;
    }
    public void setImages(int _service) {
        mTeamImage = _service;
    }
    public String getName() {
        return mTeamName;
    }
    public void setName(String _name) {
        mTeamName = _name;
    }
    public String getNick() {
        return mTeamNickname;
    }
    public void setNick(String _nick) {
        mTeamName = _nick;
    }

}
