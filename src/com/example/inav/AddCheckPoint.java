package com.example.inav;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddCheckPoint extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.activity_add_check_point);
		Button write = (Button) findViewById(R.id.write);
		write.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View view){
	            Context context = getApplicationContext();
				int duration = Toast.LENGTH_SHORT;
				EditText text = (EditText) findViewById(R.id.editText1);
				String out = text.getText().toString();
				Toast toast = Toast.makeText(context, out+" was written", duration);
				toast.show();
			}

		});

	}

}