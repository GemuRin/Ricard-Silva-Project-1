package com.example.casopratico1;
import com.example.casopratico1.R.color;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

public class coractivity extends Activity {
	private RelativeLayout linLay;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activitycor);

		linLay = (RelativeLayout) findViewById(R.id.RelativeLayout1);

		Button b1 = (Button)findViewById(R.id.button1);
		Button b2 = (Button)findViewById(R.id.button2);
		Button b3 = (Button)findViewById(R.id.button3);
		Button b4 = (Button)findViewById(R.id.button4);
		Button b5 = (Button)findViewById(R.id.button5);
		Button b6 = (Button)findViewById(R.id.button6);
		Button b7 = (Button)findViewById(R.id.button7);
		Button b8 = (Button)findViewById(R.id.button8);
		Button b9 = (Button)findViewById(R.id.button9);
		Button b10 = (Button)findViewById(R.id.button10);
		Button b11 = (Button)findViewById(R.id.button11);
		Button b12 = (Button)findViewById(R.id.button12);
		
		b1.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				
				linLay.setBackgroundColor(color.green);
			}
		});
		
		b2.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				
				linLay.setBackgroundColor(color.blue);
			}
		});
		
		b3.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				
				linLay.setBackgroundColor(color.yellow);
			}
		});
		
		b4.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				
				linLay.setBackgroundColor(color.red);
			}
		});
		
		b5.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				
				linLay.setBackgroundColor(color.pink);
			}
		});
		
		b6.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				
				linLay.setBackgroundColor(color.lightgreen);
			}
		});
		
	}
}
		
		
		
		
	