package com.momo.sharedusagedemo;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.CompoundButton;

public class Calc extends Activity {

	public static final String PREFS_NAME = "MyPrefsFile";

	private boolean mSilentMode = false;

	private CheckBox checkBox;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_calc);

		// Restore preferences
		SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
		boolean silent = settings.getBoolean("silentMode", false);
		setSilent(silent);

		checkBox = (CheckBox) findViewById(R.id.checkBox);
		checkBox.setChecked(silent);
		checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				setSilent(isChecked);
			}
		});
	}

	private void setSilent(boolean silent) {
		this.mSilentMode = silent;
	}

	@Override
	protected void onStop() {
		super.onStop();

		// We need an Editor object to make preference changes.
		// All objects are from android.context.Context
		SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
		SharedPreferences.Editor editor = settings.edit();
		editor.putBoolean("silentMode", mSilentMode);

		// Commit the edits!
		editor.commit();
	}
}
