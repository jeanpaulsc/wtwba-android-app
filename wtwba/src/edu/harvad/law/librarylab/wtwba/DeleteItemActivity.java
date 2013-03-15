package edu.harvad.law.librarylab.wtwba;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.text.style.StyleSpan;
import android.util.Log;
import android.view.View;
import android.widget.TextView;


public class DeleteItemActivity extends Activity {

	private String barcode;
	private String title;
	
	DatabaseHandler db;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        setContentView(R.layout.activity_delete_item);
        
        this.db = new DatabaseHandler(this);
        
        
        Bundle bundle = getIntent().getExtras();
        this.barcode = bundle.getString("barcode");
        this.title = bundle.getString("title");
        
        String delete_message = "Have you returned " + title + "? If so, delete it.";
        
        TextView text_title = (TextView) findViewById(R.id.title_preview);
           

        Spannable string_span = new SpannableString(delete_message);
        string_span.setSpan(new ForegroundColorSpan(Color.rgb(76, 17, 169)), 18, this.title.length() + 18, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        string_span.setSpan(new StyleSpan(Typeface.ITALIC), 18, this.title.length() + 18, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

        
      text_title.setText(string_span);
        
                
        
        
        
        
    }
    
    /** Called when the user hits the delete button */
	public void deleteItem(View view) {

		Log.w("in dia", this.barcode);
		db.delete_item_by_barcode(this.barcode);

	    Intent intent = new Intent(this, MainActivity.class);
		startActivity(intent);
	    
	}
	
    
}