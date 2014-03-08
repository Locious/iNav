package com.example.inav;

import java.util.ArrayList;

import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.provider.ContactsContract.CommonDataKinds;
import android.support.v4.content.CursorLoader;
import android.support.v4.widget.CursorAdapter;
import android.support.v4.widget.SimpleCursorAdapter;
import android.telephony.SmsManager;
import android.app.Activity;
import android.app.AlertDialog;
import android.database.Cursor;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListAdapter;
import android.widget.ListView;

public class Help extends Activity {

	ListView listContacts;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.activity_help);

		listContacts = (ListView) findViewById(R.id.ListView_Contacts);

		Uri queryUri = ContactsContract.Contacts.CONTENT_URI;

		String[] projection = new String[] { ContactsContract.Contacts._ID,
				ContactsContract.Contacts.DISPLAY_NAME};
		String selection = ContactsContract.Contacts.DISPLAY_NAME + " IS NOT NULL";

		CursorLoader cursorLoader = new CursorLoader(this, queryUri,
				projection, selection, null, null);
		
		Cursor cursor = cursorLoader.loadInBackground();

		String[] from = { ContactsContract.Contacts._ID, ContactsContract.Contacts.DISPLAY_NAME};
		int[] to = { android.R.id.text1, android.R.id.text1};

		ListAdapter adapter = new SimpleCursorAdapter(this, R.layout.text_view,
				cursor, from, to, CursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER);
		listContacts.setAdapter(adapter);
		listContacts.setOnItemClickListener(new OnItemClickListener() {

		    @Override
		    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		    	String name = (String)((Cursor)listContacts.getAdapter().getItem(position)).getString(0);
		        
		    	
		    	ArrayList<String> phones = new ArrayList<String>();

		    	Cursor cursor = getContentResolver().query(
		    	        CommonDataKinds.Phone.CONTENT_URI, 
		    	        null, 
		    	        CommonDataKinds.Phone.CONTACT_ID +" = ?", 
		    	        new String[]{name}, null);

		    	if (cursor.moveToNext()) 
		    	{
		    	    phones.add(cursor.getString(cursor.getColumnIndex(CommonDataKinds.Phone.NUMBER)));
		    	} 

		    	cursor.close();
		    	String toNum = phones.toString().substring(1, phones.toString().length()-1);
		    	String msg = "Test:  Help, I've fallen and I can't get up!";
		    	SmsManager smsManager = SmsManager.getDefault();
		    	smsManager.sendTextMessage(toNum, null, msg, null, null);
		    	
		    	
		    	AlertDialog.Builder adb = new AlertDialog.Builder(
		        		Help.this);
		        adb.setTitle("ListView OnClick");
		        adb.setMessage("Selected Item is = "
		        + phones.toString());
		        adb.setPositiveButton("Ok", null);
		        adb.show();    

		        // Don't store the item returned by this method in Contact object.
		    }
		    
		}

		);
	}

}