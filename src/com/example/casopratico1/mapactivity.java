package com.example.casopratico1;

import java.io.IOException;
import java.util.List;
import java.util.Locale;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.MapFragment;
import android.app.ListActivity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.location.Geocoder;
import android.os.Bundle;
import android.provider.BaseColumns;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

public class mapactivity extends ListActivity {
	private Cursor cursor;
	ListView listview;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.mapactivity);

		//ligamos a classe que tratara da interacao com a base de dados
		final CitiesSQL dbHelper = new CitiesSQL(mapactivity.this);

		//ficamos com uma variavel do tipo base de dados
		final SQLiteDatabase db = dbHelper.getWritableDatabase();

		final String[] camposDb = {"city", BaseColumns._ID};

		//mostramos todos os elementos da base de dados "cities" de forma ascendente
		cursor = db.query("cities", camposDb, null, null, null, null, "city ASC");

		//os elementos da lista sao passados para o campo text1 que e interno do sistema para usar nas listas
		int[] camposView = new int[] {android.R.id.text1};

		//sao colocados os elementos na lista
		final SimpleCursorAdapter adapter = new SimpleCursorAdapter(mapactivity.this, android.R.layout.two_line_list_item, cursor, camposDb, camposView);
		setListAdapter(adapter);
		
		//connect adapter to the view
		final ListView listView = (ListView)findViewById(android.R.id.list);
		listView.setAdapter(adapter);
		
		//need geocoder variable
				final Geocoder geocoder = new Geocoder(this, Locale.ENGLISH);
				
				listView.setOnItemClickListener(new OnItemClickListener() {
					public void onItemClick(AdapterView<?> parent, View v, int position, Long id) {
						ListAdapter la = (ListAdapter) parent.getAdapter();
						Cursor cursor = (Cursor) listView.getAdapter().getItem(position);
						String texto = cursor.getString(cursor.getColumnIndex("column_name_for_city"));
						
						//create variable latitude longitude
						final LatLng coordenadas;

						//geocoder give list of possible
						//results that we need from variable list address
						List<android.location.Address> ads = null;
						try {
							//we get the coordenates from the string textomorada,
							//gives max 3 results
							ads = geocoder.getFromLocationName(texto, 3);
						} catch (IOException e)  {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						//we go to the first element of the list from geocoder
						//and we do the same to a variable from type Address

						android.location.Address address=ads.get(0);

						//we give our coordinates variable the latitude and longitude from the address

						coordenadas = new LatLng(address.getLatitude(), address.getLongitude());

						//we need a variable of GoogleMap type to associate with layout fragment
						GoogleMap map = ((MapFragment) getFragmentManager().findFragmentById(R.id.map))
								.getMap();

						//we move the map to the location of the coordinates with zoom 12
						map.moveCamera(CameraUpdateFactory.newLatLngZoom(coordenadas, 12));	
					}

					@Override
					public void onItemClick(AdapterView<?> arg0, View arg1,
							int arg2, long arg3) {
						// TODO Auto-generated method stub
						
					}
				});
	}
}
