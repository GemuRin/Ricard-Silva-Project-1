package com.example.casopratico1;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

public class CitiesSQL extends SQLiteOpenHelper{
	Context context;
	private static final String DB_NAME = "Cities.db";
	private static final int DB_VERSION = 1;

	public CitiesSQL(Context context)
	{
		super(context, DB_NAME, null, DB_VERSION);

	}
	@Override
	public void onCreate(SQLiteDatabase db)
	{
		if(db.isReadOnly())
		{
			db = getWritableDatabase();
		}
		//cria-se a base de dados Cities.db e a tabela cities com os campos id e city
		db.execSQL("CREATE TABLE cities (" + BaseColumns._ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + " city TEXT" + ");");

	}

	//metodo que se chama pelo clique do botao
	public void adicionar(SQLiteDatabase db,String reg)
	{
		//apanhamos a variavel do tipo string que passou na chamada da funcao e convertemos para string
		String x= reg.toString();

		//executamos o comando sql que nos permite inserir novos dados na base de dados e introduzimos a string x
		db.execSQL("INSERT INTO cities(city) VALUES('"+x+"')");
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
	{
		db.execSQL("DROP TABLE IF EXISTS cities");
		onCreate(db);
	}
}
