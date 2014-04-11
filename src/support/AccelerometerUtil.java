package support;

import android.hardware.SensorEvent;

public class AccelerometerUtil {

	public void onSensorChanged(SensorEvent event,float linear_acceleration[]){
		  linear_acceleration[0] = event.values[0];
		  linear_acceleration[1] = event.values[1];
		  linear_acceleration[2] = event.values[2];
	}
}
