package com.brianstacks.java1week4v2;

/**
 * Created by Brian Stacks
 * on 10/22/14
 * for FullSail.edu.
 */
public class Places {

    private String pName;
    private String pTypes;
    private String pFormatted_address;
    private String pPhotos;


    public  Places(){
        pName ="";
        pTypes ="";
        pFormatted_address = "";
        pPhotos="";
    }

    public Places (String _name, String _types, String _formatted_address, String _photos){
        pName = _name;
        pTypes =_types;
        pFormatted_address = _formatted_address;
        pPhotos = _photos;
    }
    public String getName() {
        return pName;
    }
    public void setName(String name) {
        pName = name;
    }
    public String getTypes() {
        return pTypes;
    }
    public void  setTypes(String types) {
        pTypes= types;
    }
    public String getFormatted_address() {
        return pFormatted_address;
    }
    public void setFormatted_address(String formatted_address) {
        pFormatted_address=formatted_address;
    }
    public String getPhotos() {
        return pPhotos;
    }
    public void setPhotos(String photos) {
        pPhotos= photos;
    }


}
