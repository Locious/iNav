package support;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorManager;

public class PositioningUtil {
	
	//private static final float NS2S = 1.0f / 1000000000.0f;
	//private float timestamp;

	//Needs to go in the Splash.java
	//private SensorManager mSensorManager;
	//private Sensor mOrientation;
	//mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
	//mOrientation = mSensorManager.getDefaultSensor(Sensor.TYPE_ORIENTATION);

	
	public void onSensorChanged(SensorEvent event,float Positioning[]){
		Positioning[0] = event.values[0];
		Positioning[1] = event.values[1];
		Positioning[2] = event.values[2];
	}
}
