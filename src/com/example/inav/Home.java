package com.example.inav;

import android.os.Bundle;
import android.os.Vibrator;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.content.Context;
import android.content.Intent;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

public class Home extends Activity {
		
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//Remove title bar
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.activity_home);
		final Vibrator vibe = (Vibrator) Home.this.getSystemService(Context.VIBRATOR_SERVICE);
		Button settingsButton = (Button) findViewById(R.id.settings);
		settingsButton.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View view){
				vibe.vibrate(80);
				Intent ni = new Intent(Home.this, TempPage.class);
				Home.this.startActivity(ni);
			}
		});
		
		Button helpButton = (Button) findViewById(R.id.help);
		helpButton.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View view){
				vibe.vibrate(80);
				Intent ni = new Intent(Home.this, Help.class);
				Home.this.startActivity(ni);
			}
		});
		
		Button troubleshootButton = (Button) findViewById(R.id.troubleshoot);
		troubleshootButton.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View view){
				vibe.vibrate(80);
				Intent ni = new Intent(Home.this, ConnectTest.class);
				Home.this.startActivity(ni);
			}
		});
		
		Button locationButton = (Button) findViewById(R.id.location);
		locationButton.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View view){
				vibe.vibrate(80);
				Intent ni = new Intent(Home.this, TempPage.class);
				Home.this.startActivity(ni);
			}
		});
		
		Button navigateButton = (Button) findViewById(R.id.navigate);
		navigateButton.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View view){
				vibe.vibrate(80);
				Intent ni = new Intent(Home.this, Navigate.class);
				Home.this.startActivity(ni);
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
