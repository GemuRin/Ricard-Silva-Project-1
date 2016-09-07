package com.example.casopratico1;

import java.util.Calendar;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		//Find the button components
		Button botao1 = (Button) findViewById(R.id.button1);
		Button botao2 = (Button) findViewById(R.id.button2);
		Button botao3 = (Button) findViewById(R.id.button3);
		Button botao4 = (Button) findViewById(R.id.button4);
		Button botao5 = (Button) findViewById(R.id.button5);
		Button botao6 = (Button) findViewById(R.id.button6);
		Button botao7 = (Button) findViewById(R.id.button7);

		//By pressing button1 coractivity will open
		botao1.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Intent intent = new Intent();
				intent.setClass(getApplicationContext(),coractivity.class);
				startActivity(intent);
			}});

		//By pressing button2 citiesactivity will open
		botao2.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Intent intent = new Intent();
				intent.setClass(getApplicationContext(),citiesactivity.class);
				startActivity(intent);
			}});

		//By pressing button3 mapactivity will open
		botao3.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Intent intent = new Intent();
				intent.setClass(getApplicationContext(),mapactivity.class);
				startActivity(intent);
			}});

		//By pressing button4 dataactivity will open
		botao4.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Intent intent = new Intent();
				intent.setClass(getApplicationContext(),dataactivity.class);
				startActivity(intent);
			}});

		//By pressing button5 contactosactivity will open
		botao5.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Intent intent = new Intent();
				intent.setClass(getApplicationContext(),contactosactivity.class);
				startActivity(intent);
			}});

		//By pressing button6 smsactivity will open
		botao6.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Intent intent = new Intent();
				intent.setClass(getApplicationContext(),smsactivity.class);
				startActivity(intent);
			}});

		//By pressing button8 developeractivity will open
		botao7.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Intent intent = new Intent();
				intent.setClass(getApplicationContext(),developeractivity.class);
				startActivity(intent);
			}});

		//"Procurar" as textview que pretendemos preencher
		TextView dias = (TextView) findViewById(R.id.textView6);
		TextView aniv = (TextView) findViewById(R.id.textView7);

		//Recuperacao de Recursos
		SharedPreferences dados = getSharedPreferences("dados",0);
		int dia = dados.getInt("dia", 0);
		int mes = dados.getInt("mes", 0)+1;
		int ano = dados.getInt("ano", 0);


		//Realizar contas para determinar quantos dias para o aniversario e que aniversario sera

		//Criar duas instancias do calendario
		Calendar cal1 = Calendar.getInstance();
		Calendar cal2 = Calendar.getInstance();

		//Vamos obter a data actual do sistema
		int anoatual = cal2.get(Calendar.YEAR);
		int mesatual = cal2.get(Calendar.MONTH);
		int diaatual = cal2.get(Calendar.DAY_OF_MONTH);

		if(mesatual>mes-1){
			anoatual=anoatual+1;
		}

		if(mesatual==mes-1&&diaatual>dia){
			anoatual=anoatual+1;
		}


		//Vamos definir a proxima data de aniversario, tendo como base os dados recuperados
		cal1.set(anoatual, mes-1, dia);

		//Precisamos de representar a data em milisegundos para podermos realizar a diferenca entre as mesmas
		long milis1 = cal1.getTimeInMillis();
		long milis2 = cal2.getTimeInMillis();

		//Calcular a diferenca entre as datas
		long diff = milis1 - milis2;

		//Converter a diferenca em milisegundos para dias
		long diffDias = diff / (24 * 60 * 60 * 1000);
		long diffAno = (anoatual - ano);
				
		dias.setText(""+diffDias);
		aniv.setText(""+diffAno);

	}
}
