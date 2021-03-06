package edu.harvad.law.librarylab.wtwba;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import android.app.Activity;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class ItemFoundActivity extends Activity {
	
	// If we successfully add a new item from a barcode, display the title
	// and bookcover here. This is a confirmation screen that all went well.
	
	String barcode;
	String title;
	String due;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_item_found);

		Bundle bundle = getIntent().getExtras();
		this.barcode = bundle.getString("barcode");
		this.title = bundle.getString("title");

		ImageView image = (ImageView) findViewById(R.id.icon_preview);
		TextView titleView = (TextView) findViewById(R.id.title_preview);

		FileInputStream in;
		try {
			in = getApplicationContext().openFileInput(this.barcode);
			image.setImageBitmap(BitmapFactory.decodeStream(in));
		} catch (FileNotFoundException e) {
			image.setImageResource(R.drawable.cover_default);
			// e.printStackTrace();
		}

		titleView.setText(this.title);

	}

	/** Called when the user sets the user name */
	public void returnToList(View view) {

		Intent intent = new Intent(this, MainActivity.class);
		startActivity(intent);
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		// We don't want the user to go back to the scan screen, so make them go home
		
		if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
			Intent intent = new Intent(this, MainActivity.class);
			startActivity(intent);
			return true;
		}

		return super.onKeyDown(keyCode, event);
	}

}