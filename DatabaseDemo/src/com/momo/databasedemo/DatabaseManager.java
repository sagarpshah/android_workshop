package com.momo.databasedemo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseManager extends SQLiteOpenHelper {

	private static String DB_NAME = "SampleDatabase";
	private static int DB_VERSION = 1;
	public static final String TABLE_STUDENT = "Student";
	private static final String ID = "Id";
	public static final String NAME = "Name";
	public static final String MARKS = "Marks";
	private static final String TABLE_STUDENT_CREATE = "CREATE TABLE "
			+ TABLE_STUDENT + " (" + ID + " INTEGER NOT NULL PRIMARY KEY, "
			+ NAME + " TEXT NOT NULL, " + MARKS + " TEXT NOT NULL);";

	private static DatabaseManager manager = null;

	private SQLiteDatabase db = null;

	public static DatabaseManager getInstance(Context context) {
		if (manager == null) {
			manager = new DatabaseManager(context.getApplicationContext(),
					DB_NAME, DB_VERSION);
		}

		return manager;
	}

	private DatabaseManager(final Context context, final String name,
			final int version) {
		super(context, name, null, version);
		db = getWritableDatabase();
	}

	public DatabaseManager(Context context) {
		super(context, DB_NAME, null, DB_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(TABLE_STUDENT_CREATE);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.execSQL("DROP TABLE " + TABLE_STUDENT + "IF EXISTS");
		this.onCreate(db);
	}

	public Cursor getValues() {
		Cursor c = db.query(TABLE_STUDENT, null, null, null, null, null, null);
		return c;
	}

	public boolean addValuesIntoTable(String name, String marks) {

		boolean inserted = false;
		ContentValues values = new ContentValues();
		values.put(NAME, name);
		values.put(MARKS, marks);
		try {
			long inserted_row = db.insert(TABLE_STUDENT, null, values);

			if (inserted_row > 0) {
				inserted = true;
			}

		} catch (Exception exception) {
			exception.printStackTrace();
		}

		return inserted;
	}

	public void closeDatabase() {
		if (null != db)
			db.close();

		manager = null;
	}

	public void finalize() throws Throwable {
		if (null != manager)
			manager.close();
		if (null != db)
			db.close();

		super.finalize();
	}
}
