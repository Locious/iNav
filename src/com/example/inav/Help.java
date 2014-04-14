package com.example.inav;

import android.os.Bundle;
import android.preference.PreferenceManager;
import android.telephony.SmsManager;
import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

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
		final SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(this);
	    phoneNumber = pref.getString("emergency_contact", phoneNumber);
		go = (Button) findViewById(R.id.go_help);
		
		go.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View view){
				Context context = getApplicationContext();
				CharSequence text;
				int duration = Toast.LENGTH_SHORT;
				String msg = "Test:  Automatic SMS Message for iNav HELP Function!";
				if(phoneNumber != null && phoneNumber != "") {
					SmsManager smsManager = SmsManager.getDefault();
					smsManager.sendTextMessage(phoneNumber, null, msg, null, null);
					text = "Emergency message sent to " + phoneNumber;
				} else {
					text = "Please set an emergency contact";
				}
				Toast toast = Toast.makeText(context, text, duration);
				toast.show();
			}

		});

	}

}