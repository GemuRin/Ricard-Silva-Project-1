package com.example.casopratico1;

import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v4.widget.SimpleCursorAdapter;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

public class contactosactivity extends ListActivity {
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

		//Contacts to the list
		cursor = getContentResolver().query(
				ContactsContract.Contacts.CONTENT_URI, null, null, null, null);
		SimpleCursorAdapter adapter = new SimpleCursorAdapter(context,
				android.R.layout.simple_list_item_1, //View item
				cursor, // Cursor with data
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
				null, //Campos(retiramos todos)
				ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = ?", //Condition
				new String[] {"" + id }, //Fields for condition
				null); //Order

		//Generate the list of phones to create a dialog
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
				//Make the call
				try {
					Intent chamada = new Intent(Intent.ACTION_CALL);
					Uri uriTlf = Uri.parse("tel:" + telefones[item]);
					chamada.setData(uriTlf);
					startActivity(chamada);
				} catch (Exception e) {
					Toast.makeText(context, R.string.redemovel,
							Toast.LENGTH_LONG).show();
				}
			}
		});
		AlertDialog alert = builder.create();
		alert.show();
	}

}
