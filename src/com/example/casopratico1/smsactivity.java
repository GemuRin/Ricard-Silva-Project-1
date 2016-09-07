package com.example.casopratico1;

import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v4.widget.SimpleCursorAdapter;
import android.view.View;
import android.widget.ListView;


public class smsactivity extends ListActivity {
	private Cursor cursor;
	private Context context;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.contactosactivity);

		context = this;

		//We need to show the contacts
		String[] campos = new String [] { ContactsContract.Contacts.DISPLAY_NAME };
		int[] views = new int [] { android.R.id.text1};

		//We get the contacts to the list
		cursor = getContentResolver().query(
				ContactsContract.Contacts.CONTENT_URI, null, null, null, null);
		SimpleCursorAdapter adapter = new SimpleCursorAdapter(context,
				android.R.layout.simple_list_item_1, //View item
				cursor, // cursor with data
				campos, // Fields
				views // Views
				);
		setListAdapter(adapter);
	}

	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {

		Cursor tlfCur = getContentResolver().query(
				ContactsContract.CommonDataKinds.Phone.CONTENT_URI, //Url of
				// search
				null, //fields projection
				ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = ?", //Condition
				new String[] { "" + id }, //Fields for the condition
				null); //Order

		//Generate the list to create a dialog
		int nTelefones = tlfCur.getCount();
		final String[] telefones = new String [nTelefones];
		int x = 0;
		while (tlfCur.moveToNext()) {
			int col = tlfCur
					.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER);
			telefones[x++] = tlfCur.getString(col);
		}
		tlfCur.close();

		//Create the dialog
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setTitle(R.string.selecionanumero);
		builder.setItems(telefones, new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int item) {

				SharedPreferences dados = getSharedPreferences("dados",0);
				SharedPreferences.Editor editor = dados.edit();

				//Save the number in the sharedpreferences
				String numerotelefone = telefones[item];

				editor.putString("numero", numerotelefone);

				editor.commit();

				//phones[item]);
				Intent intent = new Intent();
				intent.setClass(getApplicationContext(), writesmsactivity.class);
				startActivity(intent);
			}
		});
		AlertDialog alert = builder.create();
		alert.show();
	}

}
