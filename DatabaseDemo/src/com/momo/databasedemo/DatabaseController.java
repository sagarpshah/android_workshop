package com.momo.databasedemo;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

public class DatabaseController {
	private Context context;

	public DatabaseController(Context context) {
		// TODO Auto-generated constructor stub
		this.context = context;
	}

	public boolean addValuesIntoTable(String name, String marks) {

		boolean inserted = false;
		ContentValues values = new ContentValues();
		values.put("Name", name);
		values.put("Marks", marks);
		try {
			SQLiteDatabase sqlDb = new DatabaseManager(context)
					.getWritableDatabase();

			if (sqlDb.isOpen()) {
				long inserted_row = sqlDb.insert(DatabaseManager.TABLE_STUDENT,
						null, values);
				if (inserted_row > 0) {
					inserted = true;
				}
			}

			sqlDb.close();
		} catch (Exception exception) {
			exception.printStackTrace();
		}

		// TODO Auto-generated method stub
		return inserted;

	}
}
