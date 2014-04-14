//package com.example.inav;
//
//import java.io.IOException;
//import java.io.OutputStream;
//import java.lang.reflect.Method;
//import java.util.UUID;
//
//
//import android.app.Activity;
//import android.bluetooth.BluetoothAdapter;
//import android.bluetooth.BluetoothDevice;
//import android.bluetooth.BluetoothSocket;
//import android.content.Intent;
//import android.os.Build;
//import android.os.Bundle;
//import android.util.Log;
//import android.view.View;
//import android.view.Window;
//import android.view.WindowManager;
//import android.view.View.OnClickListener;
//import android.widget.Button;
//import android.widget.Toast;
//
//public class Motors extends Activity {
//
//	private static final String TAG = "bluetooth1";
//
//	private BluetoothAdapter btAdapter = null;
//	private BluetoothSocket btSocket = null;
//	private OutputStream outStream = null;
//
//	final UUID navUUID = UUID
//			.fromString("00001101-0000-1000-8000-00805F9B34FB");
//	final String ADDRESS = "00:13:12:09:54:90"; // MAC ADDRESS
//
//	@Override
//	protected void onCreate(Bundle savedInstanceState) {
//		super.onCreate(savedInstanceState);
//		// Remove title bar
//		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
//		this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
//				WindowManager.LayoutParams.FLAG_FULLSCREEN);
//		setContentView(R.layout.activity_motors);
//
//		Button leftButton = (Button) findViewById(R.id.leftMotor);
//		Button stopButton = (Button) findViewById(R.id.stopMotor);
//		Button rightButton = (Button) findViewById(R.id.rightMotor);
//
//		btAdapter = BluetoothAdapter.getDefaultAdapter();
//		checkBTState();
//
//		leftButton.setOnClickListener(new OnClickListener() {
//			@Override
//			public void onClick(View view) {
//				sendData("H");
//				Toast.makeText(getBaseContext(), "Left Motor",
//						Toast.LENGTH_SHORT).show();
//			}
//		});
//
//		stopButton.setOnClickListener(new OnClickListener() {
//			@Override
//			public void onClick(View view) {
//				sendData("2");
//				Toast.makeText(getBaseContext(), "Stop Motor",
//						Toast.LENGTH_SHORT).show();
//			}
//		});
//
//		rightButton.setOnClickListener(new OnClickListener() {
//			@Override
//			public void onClick(View view) {
//				sendData("3");
//				Toast.makeText(getBaseContext(), "Right Motor",
//						Toast.LENGTH_SHORT).show();
//			}
//		});
//	}
//
//	private BluetoothSocket createBluetoothSocket(BluetoothDevice device)
//			throws IOException {
//		if (Build.VERSION.SDK_INT >= 10) {
//			try {
//				final Method m = device.getClass().getMethod(
//						"createInsecureRfcommSocketToServiceRecord",
//						new Class[] { UUID.class });
//				return (BluetoothSocket) m.invoke(device, navUUID);
//			} catch (Exception e) {
//				Log.e(TAG, "Could not create Insecure RFComm Connection", e);
//			}
//		}
//		return device.createRfcommSocketToServiceRecord(navUUID);
//	}
//
//	@Override
//	public void onResume() {
//		super.onResume();
//		Log.d(TAG, "...onResume - try connect...");
//
//		BluetoothDevice device = btAdapter.getRemoteDevice(ADDRESS);
//
//		try {
//			btSocket = createBluetoothSocket(device);
//		} catch (IOException e1) {
//			errorExit("Fatal Error", "In onResume() and socket create failed: "
//					+ e1.getMessage());
//		}
//
//		btAdapter.cancelDiscovery();
//
//		Log.d(TAG, "...Connecting...");
//		try {
//			btSocket.connect();
//			Log.d(TAG, "...Connection OK...");
//		} catch (IOException e) {
//			try {
//				btSocket.close();
//			} catch (IOException e2) {
//				errorExit("Fatal Error",
//						"In onResume() and unable to close socket during connection failure"
//								+ e2.getMessage() + ".");
//			}
//		}
//		Log.d(TAG, "...Create Socket...");
//		try {
//			outStream = btSocket.getOutputStream();
//		} catch (IOException e) {
//			errorExit(
//					"Fatal Error",
//					"In onResume() and output stream creation failed: "
//							+ e.getMessage());
//
//		}
//	}
//
//	@Override
//	public void onPause() {
//		super.onPause();
//
//		Log.d(TAG, "...In onPause()...");
//		if (outStream != null) {
//			try {
//				outStream.flush();
//			} catch (IOException e) {
//				errorExit(
//						"Fatal Error",
//						"In onPause() and failed to flush output stream: "
//								+ e.getMessage() + ".");
//			}
//		}
//
//		try {
//			btSocket.close();
//		} catch (IOException e2) {
//			errorExit("Fatal Error", "In onPause() and failed to close socket."
//					+ e2.getMessage() + ".");
//		}
//	}
//
//	private void checkBTState(){
//		if(btAdapter==null) { 
//		      errorExit("Fatal Error", "Bluetooth not support");
//		    } else {
//		      if (btAdapter.isEnabled()) {
//		        Log.d(TAG, "...Bluetooth ON...");
//		      } else {
//		        //Prompt user to turn on Bluetooth
//		        Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
//		        startActivityForResult(enableBtIntent, 1);
//		      }
//		    }
//	}
//		  
//	private void errorExit(String title, String message){
//		 Toast.makeText(getBaseContext(), title + " - " + message, Toast.LENGTH_LONG).show();
//		 finish();
//	}
//	
//	private void sendData(String message) {
//	    byte[] msgBuffer = message.getBytes();
//	    Toast.makeText(getBaseContext(), "Sending " + message ,
//				Toast.LENGTH_SHORT).show();
//	    Log.d(TAG, "...Send data: " + message + "...");
//	  
//	    try {
//	      outStream.write(msgBuffer);
//	    } catch (IOException e) {
//	      String msg = "In onResume() and an exception occurred during write: " + e.getMessage();
//	      if (ADDRESS.equals("00:00:00:00:00:00")) 
//	        msg = msg + ".\n\nUpdate your server ADDRESS from 00:00:00:00:00:00 to the correct ADDRESS on line 35 in the java code";
//	        msg = msg +  ".\n\nCheck that the SPP UUID: " + navUUID.toString() + " exists on server.\n\n";
//	        
//	        errorExit("Fatal Error", msg);       
//	    }
//	  }
//}