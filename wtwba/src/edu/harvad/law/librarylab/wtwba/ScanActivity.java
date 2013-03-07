package edu.harvad.law.librarylab.wtwba;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;


public class ScanActivity extends Activity {

	public final static String EXTRA_MESSAGE = "edu.harvad.law.librarylab.wtwba.MESSAGE";
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);
        
        Intent intent = new Intent("com.google.zxing.client.android.SCAN");
        //intent.putExtra("SCAN_MODE", "QR_CODE_MODE");
        startActivityForResult(intent, 0);

    }

    
    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
    	
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