package com.example.inav;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorListener;
import android.hardware.SensorManager;
import android.app.Activity;


public class DeadReckon extends Activity implements SensorEventListener {

    public DeadReckon() {
        mSensorManager = (SensorManager)getSystemService(SENSOR_SERVICE);
        mAccelerometer = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
    }

    protected void onResume() {
        super.onResume();
        mSensorManager.registerListener((SensorEventListener) this, mAccelerometer, SensorManager.SENSOR_DELAY_NORMAL);
    }

    protected void onPause() {
        super.onPause();
        mSensorManager.unregisterListener((SensorListener) this);
    }

    public void onAccuracyChanged(Sensor sensor, int accuracy) {
    	
    }

    public void onSensorChanged(SensorEvent event) {
    	
    }
}