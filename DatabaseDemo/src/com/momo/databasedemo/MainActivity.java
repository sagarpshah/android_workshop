package com.momo.databasedemo;

import java.util.ArrayList;

import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends Activity {

	EditText etName, etMarks;
	Button btn_add;

	private ListView listView;
	private ArrayAdapter<String> arrayAdapter;
	private ArrayList<String> arrayList;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		listView = (ListView) findViewById(R.id.listView);

		DatabaseManager manager = DatabaseManager.getInstance(this);
		Cursor cursor = manager.getValues();

		arrayList = new ArrayList<String>();
		arrayAdapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, arrayList);
		listView.setAdapter(arrayAdapter);

		if (cursor.moveToFirst()) {
			do {
				String name = cursor.getString(cursor
						.getColumnIndex(DatabaseManager.NAME));
				String marks = cursor.getString(cursor
						.getColumnIndex(DatabaseManager.MARKS));

				arrayList.add(name + "\t\t\t\t\t" + marks);
				arrayAdapter.notifyDataSetChanged();
			} while (cursor.moveToNext());
		}

		etName = (EditText) findViewById(R.id.editText_name);
		etMarks = (EditText) findViewById(R.id.editText_marks);

		btn_add = (Button) findViewById(R.id.buttonAddDetail);
		btn_add.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				boolean isInserted = false;
				String name = etName.getText().toString();
				String marks = etMarks.getText().toString();
				if (etName.getText().length() <= 0) {
					Toast.makeText(MainActivity.this, "Please Enter name",
							Toast.LENGTH_SHORT).show();
				} else if (etMarks.getText().length() <= 0) {
					Toast.makeText(MainActivity.this, "Please Enter Marks",
							Toast.LENGTH_SHORT).show();
				} else {
					// DatabaseController controller = new DatabaseController(
					// MainActivity.this);
					// isInserted = controller.addValuesIntoTable(name, marks);
					// if (isInserted) {
					// Toast.makeText(getBaseContext(),
					// "Successfuly Inserted", Toast.LENGTH_SHORT)
					// .show();
					// }

					// OR //
					DatabaseManager manager = DatabaseManager
							.getInstance(MainActivity.this);
					isInserted = manager.addValuesIntoTable(name, marks);
					if (isInserted) {
						Toast.makeText(getBaseContext(),
								"Successfuly Inserted", Toast.LENGTH_SHORT)
								.show();

						arrayList.add(name + "\t\t\t\t\t" + marks);
						arrayAdapter.notifyDataSetChanged();
					}
				}
			}
		});
	}
}
