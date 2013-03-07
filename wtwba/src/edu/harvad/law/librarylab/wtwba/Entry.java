package edu.harvad.law.librarylab.wtwba;

public class Entry {
 
    //private variables
	int id;
    String barcode;
    String location;
 
    // Empty constructor
    public Entry(){
 
    }
    // constructor
    public Entry(int incoming_id, String incoming_barcode, String incoming_location){
    	this.id = incoming_id;
        this.barcode = incoming_barcode;
        this.location = incoming_location;
    }
    // constructor
    public Entry(String incoming_barcode, String incoming_location){
        this.barcode = incoming_barcode;
        this.location = incoming_location;
    }

    // getting id
    public int get_id(){
        return this.id;
    }
 
    // setting id
    public void set_id(int incoming_id){
        this.id = incoming_id;
    }
 
    
    // getting barcode
    public String get_barcode(){
        return this.barcode;
    }
 
    // setting barcode
    public void set_barcode(String incoming_barcode){
        this.barcode = incoming_barcode;
    }
 
    // getting location
    public String get_location(){
        return this.location;
    }
 
    // setting location
    public void set_location(String incoming_location){
        this.location = incoming_location;
    }
}