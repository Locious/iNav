package com.example.inav;

import java.util.ArrayList;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.provider.ContactsContract;
import android.provider.ContactsContract.CommonDataKinds;
import android.app.Activity;
import android.app.AlertDialog;
import android.support.v4.content.CursorLoader;
import android.support.v4.widget.CursorAdapter;
import android.support.v4.widget.SimpleCursorAdapter;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ToggleButton;

public class Settings extends Activity {
	
	//private static final String LOG_TAG = null;
	ListView listContacts;
	Button go;
	String phoneNumber;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//Remove title bar
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.activity_settings);
		
		listContacts = (ListView) findViewById(R.id.Contacts);
		go = (Button) findViewById(R.id.setContact);
		Uri queryUri = ContactsContract.Contacts.CONTENT_URI;

		String[] projection = new String[] { ContactsContract.Contacts._ID,
				ContactsContract.Contacts.DISPLAY_NAME };

		String selection = ContactsContract.Contacts.DISPLAY_NAME
				+ " IS NOT NULL";
		selection += " AND " + ContactsContract.Contacts.HAS_PHONE_NUMBER
				+ " = 1";

		String sortOrder = ContactsContract.CommonDataKinds.GroupMembership.DISPLAY_NAME
				+ " COLLATE LOCALIZED ASC";

		CursorLoader cursorLoader = new CursorLoader(this, queryUri,
				projection, selection, null, sortOrder);

		Cursor cursor = cursorLoader.loadInBackground();

		String[] from = { ContactsContract.Contacts._ID,
				ContactsContract.Contacts.DISPLAY_NAME };
		int[] to = { android.R.id.text1, android.R.id.text1 };

		ListAdapter adapter = new SimpleCursorAdapter(this, R.layout.text_view,
				cursor, from, to, CursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER);
		listContacts.setAdapter(adapter);
		listContacts.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				String name = (String) ((Cursor) listContacts.getAdapter().getItem(position)).getString(0);
				String toNum = null;

				ArrayList<String> phones = new ArrayList<String>();

				Cursor cursor = getContentResolver().query(
						CommonDataKinds.Phone.CONTENT_URI, null,
						CommonDataKinds.Phone.CONTACT_ID + " = ?",
						new String[] { name }, null);

				if (cursor.moveToNext()) {
					phones.add(cursor.getString(cursor.getColumnIndex(CommonDataKinds.Phone.NUMBER)));
				}
				cursor.close();
				toNum = phones.toString().substring(1,
						phones.toString().length() - 1);
				phoneNumber = toNum;

			}

		});

        final SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(this);
		go.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View view){
	            SharedPreferences.Editor editor = pref.edit();
	            editor.putString("emergency_contact", phoneNumber);
	            editor.commit();
	            
				AlertDialog.Builder adb = new AlertDialog.Builder(Settings.this);
				adb.setTitle("ListView OnClick");
				adb.setMessage("Selected Item is = " + phoneNumber);
				adb.setPositiveButton("Ok", null);
				adb.show();
			}

		});
		
		//final Vibrator vibe = (Vibrator) Settings.this.getSystemService(Context.VIBRATOR_SERVICE);
		final ToggleButton buttonSound =  (ToggleButton)findViewById(R.id.sound);		   
		
		buttonSound.setOnCheckedChangeListener (new CompoundButton.OnCheckedChangeListener() {
	        @Override
	        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
	            SharedPreferences pref = getSharedPreferences("silent", MODE_PRIVATE);
	            SharedPreferences.Editor editor = pref.edit();
	            editor.putBoolean("silentMode", isChecked);
	            editor.commit();
	        }
	    });
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
}
