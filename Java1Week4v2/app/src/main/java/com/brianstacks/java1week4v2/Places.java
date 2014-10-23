package com.brianstacks.java1week4v2;

/**
 * Created by Brian Stacks
 * on 10/22/14
 * for FullSail.edu.
 */
public class Places {

    private String name;
    private String types;
    private String formatted_address;
    private String photos;


    public  Places(){
        name ="";
        types ="";
        formatted_address = "";
        photos="";
    }

    public Places(String _name, String _types, String _formatted_address, String _photos){
        name = _name;
        types =_types;
        formatted_address = _formatted_address;
        photos = _photos;
    }
    public String getName() {
        return name;
    }
    public String setName() {
        return name;
    }
    public String getTypes() {
        return types;
    }
    public String setTypes() {
        return types;
    }
    public String getFormatted_address() {
        return formatted_address;
    }
    public String setFormatted_address() {
        return formatted_address;
    }
    public String getPhotos() {
        return photos;
    }
    public String setPhotos() {
        return photos;
    }

    public static void getName(String name) {
    }
    public static void setName(String name) {
    }
    public static void getTypes(String types) {
    }
    public static void setTypes(String types) {
    }
    public static void getFormatted_address(String formatted_address) {
    }
    public static void setFormatted_address(String formatted_address) {
    }
    public static void getPhotos(String photos) {
    }
    public static void setPhotos(String photos) {
    }
}
