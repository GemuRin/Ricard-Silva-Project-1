package com.example.casopratico1;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.DatePicker.OnDateChangedListener;

public class dataactivity extends Activity {

	@Override 
	protected void onCreate(Bundle savedInstanceState)  {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.dataactivity);

		Button botao1 = (Button) findViewById(R.id.button1);

		final DatePicker data = (DatePicker) findViewById(R.id.datePicker1);
		OnDateChangedListener onDateChangedListener = null;
		data.init(1980, 0, 1, onDateChangedListener);

		botao1.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {

				SharedPreferences dados = getSharedPreferences("dados",0);
				SharedPreferences.Editor editor = dados.edit();
				//Localizar DatePicker

				final int mes = data.getMonth();
				editor.putInt("mes", mes);

				final int dia= data.getDayOfMonth();
				editor.putInt("dia", dia);

				final int ano = data.getYear();
				editor.putInt("ano", ano);

				editor.commit();

			}
		});
	}

}
