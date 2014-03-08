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
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;

public class Help extends Activity {

	ListView listContacts;
	Button go;
	String phoneNumber;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.activity_help);

		listContacts = (ListView) findViewById(R.id.ListView_Contacts);
		go = (Button) findViewById(R.id.go_help);
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
				String name = (String) ((Cursor) listContacts.getAdapter()
						.getItem(position)).getString(0);
				String toNum = null;

				ArrayList<String> phones = new ArrayList<String>();

				Cursor cursor = getContentResolver().query(
						CommonDataKinds.Phone.CONTENT_URI, null,
						CommonDataKinds.Phone.CONTACT_ID + " = ?",
						new String[] { name }, null);

				if (cursor.moveToNext()) {
					phones.add(cursor.getString(cursor
							.getColumnIndex(CommonDataKinds.Phone.NUMBER)));
				}
				cursor.close();
				toNum = phones.toString().substring(1,
						phones.toString().length() - 1);
				phoneNumber = toNum;

			}

		});
		
		go.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View view){
				String msg = "Test:  Automatic SMS Message for iNav HELP Function!";
				SmsManager smsManager = SmsManager.getDefault();
				smsManager.sendTextMessage(phoneNumber, null, msg, null, null);

				AlertDialog.Builder adb = new AlertDialog.Builder(Help.this);
				adb.setTitle("ListView OnClick");
				adb.setMessage("Selected Item is = " + phoneNumber);
				adb.setPositiveButton("Ok", null);
				adb.show();
			}

		});
		

	}

}