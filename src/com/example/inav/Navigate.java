package com.example.inav;

import android.os.Bundle;
import android.os.Vibrator;
import android.app.Activity;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class Navigate extends Activity implements SensorEventListener {

    private SensorManager sensor_manager;
    private Sensor accelerometer;
	private float accel_vec[] = new float[3];
	boolean nav_off = true;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		TextView textView = (TextView)findViewById(R.id.textView1);
		 String text= getDirectionsToPrint();
		 textView.setText(text);
		 textView.setMovementMethod(new ScrollingMovementMethod());
		 setContentView(textView);
		setContentView(R.layout.activity_navigate);
		sensor_manager = (SensorManager) getSystemService(SENSOR_SERVICE);
		accelerometer = (Sensor) sensor_manager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
		final Vibrator vibe = (Vibrator) Navigate.this.getSystemService(Context.VIBRATOR_SERVICE);
		Button nav_button = (Button) findViewById(R.id.nav);
		nav_button.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View view){
				vibe.vibrate(80);
				if(nav_off) {
					sensor_manager.registerListener((SensorEventListener) Navigate.this, accelerometer,
							SensorManager.SENSOR_DELAY_FASTEST);
					((Button) view).setText("Stop");
				} else {
					sensor_manager.unregisterListener((SensorEventListener) Navigate.this, accelerometer);
					((Button) view).setText("Start");
				}
				nav_off = !nav_off;
			}
		});
		
	}

	
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.navigate, menu);
		return true;
	}

	@Override
	public void onAccuracyChanged(Sensor sensor, int accuracy) {
		// TODO Auto-generated method stub
	}

	@Override
	public void onSensorChanged(SensorEvent event) {
		int type = event.sensor.getType();
		if (type == Sensor.TYPE_ACCELEROMETER) {
			accel_vec[0] = event.values[0];
			accel_vec[1] = event.values[1];
			accel_vec[2] = event.values[2];	
		}
	}

	public void setDirectionText(String Dir){
		TextView textView = (TextView)findViewById(R.id.textView1);
		textView.setText(Dir);
	}
}
