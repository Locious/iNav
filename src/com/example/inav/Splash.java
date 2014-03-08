package com.example.inav;

import android.os.Bundle;
import android.app.Activity;
import android.view.Window;
import android.view.WindowManager;
import android.os.Handler;
import android.content.Intent;

public class Splash extends Activity {
	private static int STO = 500;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//Remove title bar
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.activity_splash);
		
		new Handler().postDelayed(new Runnable() {
			@Override
			public void run() {
				Intent i = new Intent(Splash.this, Home.class);
				startActivity(i);
				finish();
			}
		}, STO);
	}
}
