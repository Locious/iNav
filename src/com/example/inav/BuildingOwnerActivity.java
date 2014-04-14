//package com.example.inav;
//
//import android.hardware.SensorEventListener;
//import android.hardware.SensorManager;
//import android.os.Bundle;
//import android.os.Vibrator;
//import android.app.Activity;
//import android.content.Context;
//import android.view.Menu;
//import android.view.View;
//import android.view.View.OnClickListener;
//import android.widget.Button;
//
//public class BuildingOwnerActivity extends Activity {
//
//	@Override
//	protected void onCreate(Bundle savedInstanceState) {
//		super.onCreate(savedInstanceState);
//		setContentView(R.layout.activity_building_owner);
//		final Vibrator vibe = (Vibrator) BuildingOwnerActivity.this.getSystemService(Context.VIBRATOR_SERVICE);
//		Button write_button = (Button) findViewById(R.id.write);
//		write_button.setOnClickListener(new OnClickListener(){
//			@Override
//			public void onClick(View view){
//				vibe.vibrate(80);
//				if(nav_off) {
//					sensor_manager.registerListener((SensorEventListener) Navigate.this, accelerometer,
//							SensorManager.SENSOR_DELAY_FASTEST);
//					((Button) view).setText("Stop");
//				} else {
//					sensor_manager.unregisterListener((SensorEventListener) Navigate.this, accelerometer);
//					((Button) view).setText("Start");
//				}
//				nav_off = !nav_off;
//			}
//		});
//		
//	}
//
//	@Override
//	public boolean onCreateOptionsMenu(Menu menu) {
//		getMenuInflater().inflate(R.menu.building_owner, menu);
//		return true;
//	}
//
//}
