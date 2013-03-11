package edu.harvad.law.librarylab.wtwba;

import java.util.ArrayList;
import java.util.List;

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
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ListView;


public class MainActivity extends ListActivity implements OnItemClickListener {

	public final static String EXTRA_MESSAGE = "edu.harvad.law.librarylab.wtwba.MESSAGE";
	
	
	
	//ListView msgList;
	//ArrayList<MessageDetails> details;
	
	
	ListView item_list;
	ArrayList<ItemDetailsInList> items_for_view;
	
	
	
	//AdapterView.AdapterContextMenuInfo info;
	DatabaseHandler db;

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


		 this.db = new DatabaseHandler(this);


		 // DB delete code (for debugging)
			//Context context = getApplicationContext();
		   	//context.deleteDatabase("wtwbaManager");





		/*super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_main);

    	DatabaseHandler db = new DatabaseHandler(this);


    	String[] values = new String[] { "Android", "iPhone", "WindowsMobile",
    	  "Blackberry", "WebOS", "Ubuntu", "Windows7", "Max OS X",
    	  "Linux", "OS/2", "asdfa", "asds dssdsd" };




    







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
	public void onResume() {
		// Draw the list of already checked out items
		super.onResume();

		setContentView(R.layout.activity_main);

		
		
		List<Item> items = this.db.get_all_items();
		
		items_for_view = new ArrayList<ItemDetailsInList>();
		



        for (Item item : items) {
        	ItemDetailsInList idil = new ItemDetailsInList();
        	idil.setIcon(R.drawable.ic_launcher);
        	idil.setTitle(item.get_title());
        	idil.setDue(item.get_due_date());
        	idil.setBarcode(item.get_barcode());
            //items_for_view.add(item.get_title());
            //Log.w("adding item title to list: ", item.get_title());
            //Log.w("adding item barcode to list: ", item.get_barcode());
            //Log.w("adding item due to list: ", item.get_due_date());
        	items_for_view.add(idil);
        }

        Log.w("size of view items", String.valueOf(items_for_view.size()));

        //String[] v_ar = new String[this.items_for_view.size()];
        //v_ar = items_for_view.toArray(v_ar);


        item_list = getListView();
		
		item_list.setOnItemClickListener(this);
		item_list.setLongClickable(true);
		item_list.setAdapter(new ItemDetailsListAdapter(items_for_view, this));

		
		
		
		item_list.setOnItemLongClickListener(new OnItemLongClickListener() {

            public boolean onItemLongClick(AdapterView<?> arg0, View arg1, int pos, long id) {
        		ItemDetailsInList item = items_for_view.get(pos);

        		Log.w("from lc: ", item.barcode);
        		

        		Intent intent = new Intent(getBaseContext(), DeleteItemActivity.class);
        		intent.putExtra("barcode", item.barcode);
        		intent.putExtra("title", item.title);
        		startActivity(intent);
        		
                return true;
            }
        }); 
		
		
		
		
		
		
		
		/*
		
		
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

		msgList.setAdapter(new CustomAdapter(details , this));*/
	}




	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle item selection from the menu
		switch (item.getItemId()) {
		case R.id.scanButton:
			this.scanCode();
			return true;
		case R.id.manageUserButton:
			this.manageUser();
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}

	/** Called when the user clicks the scan button */
	public void scanCode() {
		// When user selects the Add Item option from the menu

		//Intent intent = new Intent(this, ScanActivity.class);
		//startActivity(intent);

		Intent intent = new Intent("com.google.zxing.client.android.SCAN");
		//intent.putExtra("SCAN_MODE", "QR_CODE_MODE");
		startActivityForResult(intent, 0);
	}

	
	/** Called when the user clicks the manage user button (in the menu)*/
	public void manageUser() {

		Intent intent = new Intent(this, ManageUserActivity.class);
		startActivity(intent);

	}

	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
		// When a user clicks an item (a book they've already scanned) in the list
		ItemDetailsInList item = items_for_view.get(arg2);

		Log.d("from listview: ", item.barcode);
		
		Intent intent = new Intent(this, LocationActivity.class);
		intent.putExtra("barcode", item.barcode);
		startActivity(intent);

	}
	

	public void onActivityResult(int requestCode, int resultCode, Intent intent) {
		// When our zxing intent returns, we call this method	
		Log.w("wtwba", "running on activity result");

		String message = "no message";

		if (requestCode == 0) {
			if (resultCode == RESULT_OK) {
				String contents = intent.getStringExtra("SCAN_RESULT");
				String format = intent.getStringExtra("SCAN_RESULT_FORMAT");
				// Handle successful scan

				message = contents;

				//Intent rid_intent = new Intent(this, RetrieveItemData.class);
				//rid_intent.putExtra("barcode", message);
				//startActivity(rid_intent);

				//new RetrieveItemData().execute("");

				//Intent ani_intent = new Intent(this, AddNewItem.class);
				//ani_intent.putExtra("barcode", message);
				//startActivity(ani_intent);


				//Intent rid_intent = new Intent("edu.harvad.law.librarylab.wtwba.RetrieveItemData");
				//rid_intent.putExtra("barcode", contents);
				//startActivityForResult(rid_intent, 0);

				Intent display_intent = new Intent(this, DisplayItemDetailsActivity.class);
				display_intent.putExtra("barcode", message);
				startActivity(display_intent);



			} else if (resultCode == RESULT_CANCELED) {
				// Handle cancel	
				message = "canceled";
			}
		} else{

			message = "requestcode not 0";
		}


	} 
}