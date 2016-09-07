package com.example.casopratico1;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class writesmsactivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.writesmsactivity);

		TextView texto=(TextView) findViewById(R.id.textView1);
		//we get the number saved
		SharedPreferences dados= getSharedPreferences("dados",0);
		final String numeropassado= dados.getString("numero", "");
		//we present the number saved in the textview
		texto.setText(numeropassado);

		Button bt1=(Button) findViewById(R.id.button1);

		bt1.setOnClickListener(new View.OnClickListener() {
			@SuppressWarnings("deprecation")
			public void onClick(View v) {
				final EditText mensagem=(EditText) findViewById(R.id.editText1);
				final String mensagemtexto=mensagem.getText().toString();

				android.telephony.SmsManager sms= android.telephony.SmsManager.getDefault();

				sms.sendTextMessage(numeropassado, null, mensagemtexto, null, null);
				finish();
			}
		}
				);

	}

}
