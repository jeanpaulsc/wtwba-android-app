package edu.harvad.law.librarylab.wtwba;

import java.util.ArrayList;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;


public class MainActivity extends ListActivity implements OnItemClickListener {

	public final static String EXTRA_MESSAGE = "edu.harvad.law.librarylab.wtwba.MESSAGE";
	ListView msgList;
    ArrayList<MessageDetails> details;
    AdapterView.AdapterContextMenuInfo info;
    
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.activity_main_menu, menu);
        return true;
    }
    	
    @Override
    public void onCreate(Bundle savedInstanceState) {
    	
    	
    	
    	// TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        msgList = getListView();//(ListView) findViewById(R.id.android.list);
        
        msgList.setOnItemClickListener(this);
        
        details = new ArrayList<MessageDetails>();
            
        MessageDetails Detail;
        Detail = new MessageDetails();
        Detail.setIcon(R.drawable.ic_launcher);
        Detail.setName("Bob");
        Detail.setSub("Dinner");
        Detail.setDesc("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nulla auctor.");
        Detail.setTime("12/12/2012 12:12");
        details.add(Detail);
        
        Detail = new MessageDetails();
        Detail.setIcon(R.drawable.ic_launcher);
        Detail.setName("Rob");
        Detail.setSub("Party");
        Detail.setDesc("Dolor sit amet, consectetur adipiscing elit. Nulla auctor.");
        Detail.setTime("13/12/2012 10:12");
        details.add(Detail);
        
        Detail = new MessageDetails();
        Detail.setIcon(R.drawable.ic_launcher);
        Detail.setName("Mike");
        Detail.setSub("Mail");
        Detail.setDesc("Lorem ipsum dolor sit amet, consectetur adipiscing elit.");
        Detail.setTime("13/12/2012 02:12");
        details.add(Detail);        
        
        msgList.setAdapter(new CustomAdapter(details , this));
    	
    	
    	
	
    	
    	
    	
    	
    	
    	
    	/*super.onCreate(savedInstanceState);
    	
    	
        setContentView(R.layout.activity_main);
    	
    	DatabaseHandler db = new DatabaseHandler(this);
    	
    	
    	String[] values = new String[] { "Android", "iPhone", "WindowsMobile",
    	  "Blackberry", "WebOS", "Ubuntu", "Windows7", "Max OS X",
    	  "Linux", "OS/2", "asdfa", "asds dssdsd" };

    	    	
    	
    	
    	//Context context = getApplicationContext();
   	//context.deleteDatabase("wtwbaManager");
    	
    	
    	
    	


        
        List<Item> items = db.get_all_items();
        List<String> items_for_view = new ArrayList<String>();
        
        
        
        for (Item item : items) {
            items_for_view.add(item.get_title());
            Log.w("adding item title to list: ", item.get_title());
        }
        
        Log.w("size of view items", String.valueOf(items_for_view.size()));

        String[] v_ar = new String[items_for_view.size()];
        v_ar = items_for_view.toArray(v_ar);
        
        Log.w("size of view items", v_ar[0]);
        
      //add header to list
        ListView lv = getListView();
        
        
      //add some list items
        String listItems[] = {"List Item One", "List Item Two", "List Item Three", "List Item Four", "List Item Five"};
        ArrayAdapter adapter = new ArrayAdapter<String>(this,  android.R.layout.simple_list_item_1, listItems);
        
        
        ListView listView = (ListView) findViewById(R.layout.list_item);
        listView.setAdapter(adapter);
        
        */
        
        
    }

    /*@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }*/
    
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
        case R.id.scanButton:
        	this.scanCode();
            return true;
        case R.id.managerUserButton:
        	this.scanCode();
            return true;
        default:
            return super.onOptionsItemSelected(item);
        }
    }
    
    /** Called when the user clicks the scan button */
    public void scanCode() {
    	
    	Intent intent = new Intent(this, ScanActivity.class);
        startActivity(intent);
    	
        //Intent intent = new Intent("com.google.zxing.client.android.SCAN");
        //intent.putExtra("SCAN_MODE", "QR_CODE_MODE");
        //startActivityForResult(intent, 0);
    }

	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
		// TODO Auto-generated method stub
		
		MessageDetails detail = details.get(arg2);

		
		Log.d("from listview: ", detail.getName());
	} 
}