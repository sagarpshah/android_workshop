package com.momo.externalstoragedemo;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Arrays;

import android.app.Activity;
import android.content.Context;
import android.os.Environment;
import android.view.View;
import android.view.View.OnFocusChangeListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.momo.internalstoragedemo.R;

public class MainActivity extends Activity {

	private ListView listView;

	private EditText editText;

	private Button enterButton;

	private ArrayAdapter<String> adapter;

	private ArrayList<String> fileList;

	@Override
	protected void onCreate(android.os.Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_main);

		listView = (ListView) findViewById(R.id.listView);
		editText = (EditText) findViewById(R.id.fileName);
		enterButton = (Button) findViewById(R.id.enterButton);

		enterButton.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				String text = editText.getText().toString();

				File file = new File(getExternalFilesDir(null), text);

				FileOutputStream fos;
				try {
					fos = new FileOutputStream(file);
					fos.write(text.getBytes());
					fos.close();
				} catch (Exception e) {
					e.printStackTrace();
				}

				fileList.add(text);
				adapter.notifyDataSetChanged();

				editText.setText("");
			}
		});

		editText.setOnFocusChangeListener(new OnFocusChangeListener() {

			@Override
			public void onFocusChange(View v, boolean hasFocus) {
				if (!hasFocus) {
					hideKeyboard();
				}

			}
		});

		String[] files = null;

		File externalFileDirectory = getExternalFilesDir(null);
		if (externalFileDirectory.isDirectory())
			files = externalFileDirectory.list();

		fileList = new ArrayList<String>(Arrays.asList(files));

		adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, fileList);

		listView.setAdapter(adapter);
	}

	private void hideKeyboard() {
		InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
		imm.hideSoftInputFromWindow(editText.getWindowToken(), 0);
	}

	/* Checks if external storage is available for read and write */
	public boolean isExternalStorageWritable() {
		String state = Environment.getExternalStorageState();
		if (Environment.MEDIA_MOUNTED.equals(state)) {
			return true;
		}
		return false;
	}

	/* Checks if external storage is available to at least read */
	public boolean isExternalStorageReadable() {
		String state = Environment.getExternalStorageState();
		if (Environment.MEDIA_MOUNTED.equals(state)
				|| Environment.MEDIA_MOUNTED_READ_ONLY.equals(state)) {
			return true;
		}
		return false;
	}
}
