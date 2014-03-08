package com.example.inav;

import android.os.Bundle;
import android.os.Vibrator;
import android.app.Activity;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class Navigate extends Activity {

    private SensorManager sensor_manager;
    private Sensor accelerometer;
	boolean nav_on = true;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_navigate);
		sensor_manager = (SensorManager) getSystemService(SENSOR_SERVICE);
		accelerometer = (Sensor) 
		final Vibrator vibe = (Vibrator) Navigate.this.getSystemService(Context.VIBRATOR_SERVICE);
		Button nav_button = (Button) findViewById(R.id.nav);
		nav_button.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View view){
				vibe.vibrate(80);
				if(nav_on) {
					((Button) view).setText("Stop");
					//turn off
				} else {
					((Button) view).setText("Start");
					//turn on
				}
				nav_on = !nav_on;
			}
		});
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.navigate, menu);
		return true;
	}

}
