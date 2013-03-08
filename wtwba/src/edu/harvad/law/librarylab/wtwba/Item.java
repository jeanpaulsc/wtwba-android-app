package edu.harvad.law.librarylab.wtwba;

import android.util.Log;

public class Item {
 
    //private variables
	int id;
    String barcode;
    String title;
    String due_date;
    // other attributes here
 
    // Empty constructor
    public Item(){
 
    }
    // constructor
    public Item(String barcode, String title, String due_date){
    	Log.w("item container", "barcode: " + barcode + ", title: " + title + ", due_date: " + due_date);
        this.barcode = barcode;
        this.title = title;
        this.due_date = due_date;
    }
    
    // constructor
    public Item(int id, String barcode, String title, String due_date){
    	this.id = id;
        this.barcode = barcode;
        this.title = title;
        this.due_date = due_date;
    }
    
    // getting id
    public int get_id(){
        return this.id;
    }
 
    // setting id
    public void set_id(int id){
        this.id = id;
    }

	// getting barcode
    public String get_barcode(){
        return this.barcode;
    }
 
    // setting barcode
    public void set_barcode(String barcode){
        this.barcode = barcode;
    }
    
    public String get_title() {
		return this.title;
	}
	public void set_title(String title) {
		this.title = title;
	}
	
	public String get_due_date() {
		Log.w("item container", "returning due_date " + this.due_date);
		return this.due_date;
	}
	public void set_due_date(String due_date) {
		this.due_date = due_date;
	}
 

}