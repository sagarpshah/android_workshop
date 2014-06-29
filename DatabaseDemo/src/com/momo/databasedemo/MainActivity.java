package com.momo.databasedemo;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity {

	EditText etName, etMarks;
	Button btn_add;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

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
					DatabaseController controller = new DatabaseController(
							MainActivity.this);
					isInserted = controller.addValuesIntoTable(name, marks);
					if (isInserted) {
						Toast.makeText(getBaseContext(),
								"Successfuly Inserted", Toast.LENGTH_SHORT)
								.show();
					}

					// OR //
					// DatabaseManager manager = DatabaseManager
					// .getInstance(MainActivity.this);
					// isInserted = manager.addValuesIntoTable(name, marks);
					// if (isInserted) {
					// Toast.makeText(getBaseContext(),
					// "Successfuly Inserted", Toast.LENGTH_SHORT)
					// .show();
					// }
				}
			}
		});
	}
}
