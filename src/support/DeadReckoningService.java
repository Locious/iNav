package support;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.IBinder;
import android.util.Log;

public class DeadReckoningService extends Service{
	 private SensorManager SensorManager;
     private SensorEventListener SensorListener;
     int AccCount = 0;
     int PosCount = 0;
     float acc[] = new float[3];
     float[] pos = new float[3];
     float RealAcc[] = new float[3];
     float Distance[][] = new float[3][5];
     float DeltaDistance[] = new float[3];
     Position p = new Position(0,0,0,0);
	@Override
	public IBinder onBind(Intent arg0) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	  public int onStartCommand(Intent intent, int flags, int startId) {
		Log.e("X is: ","1");
		Log.e("Y is: ","2");
		Log.e("Z is: ","3");
		 SensorManager = (SensorManager) this.getSystemService(Context.SENSOR_SERVICE);
	        SensorListener = new SensorEventListener() {
	            @Override
	            public void onAccuracyChanged(Sensor arg0, int arg1) {
	            }

	            @Override
	            public void onSensorChanged(SensorEvent event) {
	                Sensor sensor = event.sensor;
	                //Using the Accelerometer and Orientation
//	                if (sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
//	                	acc=event.values.clone();
//	                }
//	                else if (sensor.getType() == Sensor.TYPE_ORIENTATION) {
//	                	pos=event.values.clone();
//	                }
//	                if (pos != null && acc != null) {
//	                	RealAcc[0] =(float) (acc[0]*(Math.cos(pos[2])*Math.cos(pos[0])+Math.sin(pos[2])*Math.sin(pos[1])*Math.sin(pos[0])) + acc[1]*(Math.cos(pos[1])*Math.sin(pos[0])) + acc[2]*(-Math.sin(pos[2])*Math.cos(pos[0])+Math.cos(pos[2])*Math.sin(pos[1])*Math.sin(pos[0])));
//	            		RealAcc[1] = (float) (acc[0]*(-Math.cos(pos[2])*Math.sin(pos[0])+Math.sin(pos[2])*Math.sin(pos[1])*Math.cos(pos[0])) + acc[1]*(Math.cos(pos[1])*Math.cos(pos[0])) + acc[2]*(Math.sin(pos[2])*Math.sin(pos[0])+ Math.cos(pos[2])*Math.sin(pos[1])*Math.cos(pos[0])));
//	            		RealAcc[2] = (float) ((acc[0]*(Math.sin(pos[2])*Math.cos(pos[1])) + acc[1]*(-Math.sin(pos[1])) + acc[2]*(Math.cos(pos[2])*Math.cos(pos[1])))-9.8);
//	            		Log.e("X is: ",Float.toString(RealAcc[0]));
//	            		Log.e("Y is: ",Float.toString(RealAcc[1]));
//	            		Log.e("Z is: ",Float.toString(RealAcc[2]));
//	            		getDistance();
//	                }
	                if (sensor.getType() == Sensor.TYPE_LINEAR_ACCELERATION) {
	                	acc=event.values.clone();
	                } if (pos != null){
		                RealAcc[0] = acc[0];
		                RealAcc[1] = acc[1];
		                RealAcc[2] = acc[2];
		                getDistance();
	                }
	            }
	        };
	        SensorManager.registerListener(SensorListener, SensorManager.getDefaultSensor(Sensor.TYPE_LINEAR_ACCELERATION), SensorManager.SENSOR_DELAY_NORMAL);
	        //SensorManager.registerListener(SensorListener, SensorManager.getDefaultSensor(Sensor.TYPE_ORIENTATION), SensorManager.SENSOR_DELAY_NORMAL);
			return startId;
	    }
	    
	    public void getDistance(){
	    	for(int i=0;i<5;i++){
	    		Distance[0][i] =(float)((RealAcc[0] * .2) + (.50*RealAcc[0]*.04));
	    		Distance[1][i] =(float)((RealAcc[1] * .2) + (.50*RealAcc[1]*.04));
	    		Distance[2][i] =(float)((RealAcc[2] * .2) + (.50*RealAcc[2]*.04));
	    	}
	    	FindDeltaDistance();
	    }
	    public void FindDeltaDistance(){
	    	float Azimuth =0;
	    	DeltaDistance[0] = 0;
	    	DeltaDistance[1] = 0;
	    	DeltaDistance[2] = 0;
	    	for(int i=0;i<5;i++){
	    		DeltaDistance[0] += Distance[0][i];
	    		DeltaDistance[1] += Distance[1][i];
	    		DeltaDistance[2] += Distance[2][i];
	    	}
	    	Azimuth = (float) Math.atan2((double)DeltaDistance[0], (double)DeltaDistance[0]);
	    	p.offset(DeltaDistance[0], DeltaDistance[1],DeltaDistance[2],Azimuth);
	    }
}
