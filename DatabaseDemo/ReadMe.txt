1) Using Databases

— SQLite databases are supported.
— Databases are private for the application and not available publicly.
— Any database created by the application will be accessible by name to any class in the application but not outside the application.

2) SQLiteOpenHelper

	public class DictionaryOpenHelper extends SQLiteOpenHelper {

	    private static final int DATABASE_VERSION = 2;
	    private static final String DICTIONARY_TABLE_NAME = "dictionary";
	    private static final String DICTIONARY_TABLE_CREATE =
	                "CREATE TABLE " + DICTIONARY_TABLE_NAME + " (" +
	                KEY_WORD + " TEXT, " +
	                KEY_DEFINITION + " TEXT);";

	    DictionaryOpenHelper(Context context) {
	        super(context, DATABASE_NAME, null, DATABASE_VERSION);
	    }

	    @Override
	    public void onCreate(SQLiteDatabase db) {
	        db.execSQL(DICTIONARY_TABLE_CREATE);
	    }
	}

3) How to
- Create subclass of SQLiteOpenHelper
- Override public abstract void onCreate (SQLiteDatabase db)
- Inside onCreate execute a SQLite command to create tables in the database.
- Create constructor of the subclass of SQLiteOpenHelper.
- public SQLiteDatabase getWritableDatabase () - create and/or open a database that will be used for reading and writing.
- Execute SQLite queries using the SQLiteDatabase query() methods.
- public synchronized void close () - close the open database object.

