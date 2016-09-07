package com.example.casopratico1;

import android.app.ListActivity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.provider.BaseColumns;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SimpleCursorAdapter;

public class citiesactivity extends ListActivity {
	private Cursor cursor;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activitycities);

		//ligamos a classe que tratara da interacao com a base de dados
		final CitiesSQL dbHelper = new CitiesSQL(citiesactivity.this);

		//ficamos com uma variavel do tipo base de dados
		final SQLiteDatabase db = dbHelper.getWritableDatabase();

		final String[] camposDb = {"city", BaseColumns._ID};

		//mostramos todos os elementos da base de dados "cities" de forma ascendente
		cursor = db.query("cities", camposDb, null, null, null, null, "city ASC");

		//os elementos da lista sao passados para o campo text1 que e interno do sistema para usar nas listas
		int[] camposView = new int[] {android.R.id.text1};

		//sao colocados os elementos na lista
		final SimpleCursorAdapter adapter = new SimpleCursorAdapter(citiesactivity.this, android.R.layout.two_line_list_item, cursor, camposDb, camposView);
		setListAdapter(adapter);

		//associamos os objetos do layout a variaveis
		final EditText texto=(EditText) findViewById (R.id.editText1);
		Button bt1=(Button) findViewById (R.id.button1);

		//colocamos a acao do botao1
		bt1.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				//capturamos o que se encontrava no edit text, e convertemos para uma string
				String x=texto.getText().toString();

				//chamamos o metodo adicionar que se encontra na classe SQLHelper
				dbHelper.adicionar(db,x);

				//Atualizamos a lista
				cursor = db.query("cities", camposDb, null, null, null, null, "city ASC");
				int[] camposView = new int[] {android.R.id.text1};
				final SimpleCursorAdapter adapter = new SimpleCursorAdapter(citiesactivity.this, android.R.layout.two_line_list_item, cursor, camposDb, camposView);
				setListAdapter(adapter);
			}
		});
	}

}