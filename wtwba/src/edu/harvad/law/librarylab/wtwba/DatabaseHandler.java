package edu.harvad.law.librarylab.wtwba;
 
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
 
// Thanks to http://www.androidhive.info/2011/11/android-sqlite-database-tutorial/

public class DatabaseHandler extends SQLiteOpenHelper {
 
    // All Static variables
    // Database Version
    private static final int DATABASE_VERSION = 2;
 
    // Database Name
    private static final String DATABASE_NAME = "wtwbaManager";
 
    // Table names
    private static final String TABLE_ITEMS = "ITEMS";
    private static final String TABLE_ENTRIES = "ENTRIES";
    

    // Common table columns names
    private static final String KEY_ID = "id";
    private static final String KEY_BARCODE = "barcode";
    
    // Items table columns names
    private static final String KEY_TITLE = "title";
    private static final String KEY_DUE = "due";

 
    // entries table columns names
    private static final String KEY_LOCATION = "location";
    private static final String KEY_DATE = "date";
 
 
    
    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
 
    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_ITEMS_TABLE = "CREATE TABLE " + TABLE_ITEMS + "("
                + KEY_ID + " INTEGER PRIMARY KEY," + KEY_BARCODE + " TEXT,"
                + KEY_TITLE + " TEXT," + KEY_DUE + " TEXT" + ")";
        db.execSQL(CREATE_ITEMS_TABLE);
        
        String CREATE_EENTRIES_TABLE = "CREATE TABLE " + TABLE_ENTRIES + "("
                + KEY_ID + " INTEGER PRIMARY KEY," + KEY_BARCODE + " TEXT,"
                + KEY_LOCATION + " TEXT," + KEY_DATE + " TEXT" + ")";
        db.execSQL(CREATE_EENTRIES_TABLE);
    }
 
    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ENTRIES);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ITEMS);
 
        // Create tables again
        onCreate(db);
    }
 
    /**
     * All CRUD(Create, Read, Update, Delete) Operations
     */
    
    ////////
	  // Item
	  ////////
	
//    private static final String KEY_ID = "id";
//    private static final String KEY_BARCODE = "barcode";
//    
//    // Items table columns names
//    private static final String KEY_TITLE = "title";
//    private static final String KEY_DUE = "due";
    
	  // Adding new Item
	  void add_item(Item item) {
	      SQLiteDatabase db = this.getWritableDatabase();
	
	      ContentValues values = new ContentValues();
	      values.put(KEY_BARCODE, item.get_barcode());
	      values.put(KEY_TITLE, item.get_title());
	      values.put(KEY_DUE, item.get_due_date());
	      
	      Log.w("wtwba inserting db", item.get_barcode() + ", " + item.get_title() + ", " + item.get_due_date());

	
	      // Inserting Row
	      db.insert(TABLE_ITEMS, null, values);
	      db.close(); // Closing database connection
	  }
	
	  // Getting single contact
	  Item get_item(int id) {
	      SQLiteDatabase db = this.getReadableDatabase();
	
	      Cursor cursor = db.query(TABLE_ITEMS, new String[] { KEY_ID,
	      		KEY_BARCODE, KEY_TITLE, KEY_DUE }, KEY_ID + "=?",
	          new String[] { String.valueOf(id) }, null, null, null, null);
	      if (cursor != null){
		      cursor.moveToFirst();
	      }
	  		Item item = new Item(Integer.parseInt(cursor.getString(0)), cursor.getString(1), cursor.getString(2), cursor.getString(3));

	      return item;
	  }
	
	  // Getting All items
	  public List<Item> get_all_items() {
	      List<Item> item_list = new ArrayList<Item>();
	      // Select All Query
	  String selectQuery = "SELECT  * FROM " + TABLE_ITEMS;
	
	  SQLiteDatabase db = this.getWritableDatabase();
	  Cursor cursor = db.rawQuery(selectQuery, null);
	
	  // looping through all rows and adding to list
	  if (cursor.moveToFirst()) {
	      do {
	      	Item item = new Item();
	      	item.set_id(Integer.parseInt(cursor.getString(0)));
	      	item.set_barcode(cursor.getString(1));
	      	item.set_title(cursor.getString(2));
	      	item.set_due_date(cursor.getString(3));
	      	Log.w("in handler get all item", cursor.getString(2));
	      	
	          // Adding contact to list
	      	item_list.add(item);
	      } while (cursor.moveToNext());
	  }
	
	  // return contact list
	      return item_list;
	  }
	
	  // Updating single contact
	  /*public int updateContact(Contact contact) {
	      SQLiteDatabase db = this.getWritableDatabase();
	
	      ContentValues values = new ContentValues();
	      values.put(KEY_NAME, contact.getName());
	      values.put(KEY_PH_NO, contact.getPhoneNumber());
	
	      // updating row
	      return db.update(TABLE_CONTACTS, values, KEY_ID + " = ?",
	              new String[] { String.valueOf(contact.getID()) });
	  }*/
	
	  // Deleting single item by id
	  public void delete_item(Item item) {
	      SQLiteDatabase db = this.getWritableDatabase();
	      db.delete(TABLE_ITEMS, KEY_ID + " = ?",
	              new String[] { String.valueOf(item.get_id()) });
	      db.close();
	  }
	  
	// Deleting single item by barcode
	  public void delete_item_by_barcode(String barcode) {
		  Log.w("db", "trying to delete by barcode");
	      SQLiteDatabase db = this.getWritableDatabase();
	      db.delete(TABLE_ITEMS, KEY_BARCODE + " = ?",
	              new String[] { barcode });
	      db.close();
	  }
	
	  // Getting contacts Count
	  /*public int getContactsCount() {
	      String countQuery = "SELECT  * FROM " + TABLE_CONTACTS;
	      SQLiteDatabase db = this.getReadableDatabase();
	      Cursor cursor = db.rawQuery(countQuery, null);
	      cursor.close();
	
	      // return count
	      return cursor.getCount();
	  }*/
    
    
	  
	  
    ////////
    // Entry
    ////////
 
    // Adding new Entry
    void add_entry(Entry entry) {
        SQLiteDatabase db = this.getWritableDatabase();
 
        ContentValues values = new ContentValues();
        values.put(KEY_BARCODE, entry.get_barcode());
        values.put(KEY_LOCATION, entry.get_location());
        values.put(KEY_DATE, entry.getDate().toString());
 
        // Inserting Row
        db.insert(TABLE_ENTRIES, null, values);
        db.close(); // Closing database connection
    }
 
    // Getting single entry
    Entry get_entry(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
 
        Cursor cursor = db.query(TABLE_ENTRIES, new String[] { KEY_ID,
        		KEY_BARCODE, KEY_LOCATION, KEY_DATE }, KEY_ID + "=?",
                new String[] { String.valueOf(id) }, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();
 
        Entry entry = new Entry(Integer.parseInt(cursor.getString(0)), cursor.getString(1), cursor.getString(2), Timestamp.valueOf(cursor.getString(3)));

        return entry;
    }
 
    // Getting All entries
    public List<Entry> get_all_entries() {
        List<Entry> entry_list = new ArrayList<Entry>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_ENTRIES;
 
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
 
        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
            	Entry entry = new Entry();
            	entry.set_id(Integer.parseInt(cursor.getString(0)));
            	entry.set_barcode(cursor.getString(1));
            	entry.set_location(cursor.getString(2));
            	entry.setDate( Timestamp.valueOf(cursor.getString(3)));
                // Adding contact to list
                entry_list.add(entry);
            } while (cursor.moveToNext());
        }
 
        // return contact list
        return entry_list;
    }
 
    // Updating single contact
    /*public int updateContact(Contact contact) {
        SQLiteDatabase db = this.getWritableDatabase();
 
        ContentValues values = new ContentValues();
        values.put(KEY_NAME, contact.getName());
        values.put(KEY_PH_NO, contact.getPhoneNumber());
 
        // updating row
        return db.update(TABLE_CONTACTS, values, KEY_ID + " = ?",
                new String[] { String.valueOf(contact.getID()) });
    }*/
 
    // Deleting single contact
    public void delete_entry(Entry entry) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_ENTRIES, KEY_ID + " = ?",
                new String[] { String.valueOf(entry.get_id()) });
        db.close();
    }
 
    // Getting contacts Count
    /*public int getContactsCount() {
        String countQuery = "SELECT  * FROM " + TABLE_CONTACTS;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        cursor.close();
 
        // return count
        return cursor.getCount();
    }*/
 
}
