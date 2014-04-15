package com.example.inav;

import android.nfc.NdefMessage;
import android.nfc.NdefRecord;
import android.nfc.NfcAdapter;
import android.os.Bundle;
import android.os.Parcelable;
import android.os.Vibrator;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

/*
 * onResume based on the excellent tutorial by Jesse Chen
 * http://www.jessechen.net/blog/how-to-nfc-on-the-android-platform/
 */
public class Navigate extends Activity implements SensorEventListener {

    private SensorManager sensor_manager;
    private Sensor accelerometer;
	private float accel_vec[] = new float[3];
	boolean nav_off = true;
	String placeId;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//TextView textView = (TextView)findViewById(R.id.textView1);
		 //String text= getDirectionsToPrint();
		// textView.setText(text);
		// textView.setMovementMethod(new ScrollingMovementMethod());
		// setContentView(textView);
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
	
	
	private NdefMessage[] getNdefMessages(Intent intent) {
	    // Parse the intent
	    NdefMessage[] msgs = null;
	    String action = intent.getAction();
	    if (NfcAdapter.ACTION_TAG_DISCOVERED.equals(action)
	        || NfcAdapter.ACTION_NDEF_DISCOVERED.equals(action)) {
	        Parcelable[] rawMsgs = 
	            intent.getParcelableArrayExtra(NfcAdapter.EXTRA_NDEF_MESSAGES);
	        if (rawMsgs != null) {
	            msgs = new NdefMessage[rawMsgs.length];
	            for (int i = 0; i < rawMsgs.length; i++) {
	                msgs[i] = (NdefMessage) rawMsgs[i];
	            }
	        } else {
	            // Unknown tag type
	            byte[] empty = new byte[] {};
	            NdefRecord record = 
	                new NdefRecord(NdefRecord.TNF_UNKNOWN, empty, empty, empty);
	            NdefMessage msg = new NdefMessage(new NdefRecord[] {
	                record
	            });
	            msgs = new NdefMessage[] {
	                msg
	            };
	        }
	    } else {
	        finish();
	    }
	    return msgs;
	}
	
	
	@Override
	protected void onResume() {
	    super.onResume();
	    if (NfcAdapter.ACTION_NDEF_DISCOVERED.equals(getIntent().getAction())) {
	        NdefMessage[] messages = getNdefMessages(getIntent());
	        byte[] payload = messages[0].getRecords()[0].getPayload();
	        placeId = new String(payload);

            Context context = getApplicationContext();
			CharSequence text = placeId + " was read";
			int duration = Toast.LENGTH_SHORT;
			Toast toast = Toast.makeText(context, text, duration);
			toast.show();
	    }
	}
}
