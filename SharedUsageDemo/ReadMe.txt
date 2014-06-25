1) Storage of application data

— Database
— Files
— Prefrences
— Internal or Removable Storage
— Data backup services

2) Storing persistent data
— private accessible by your application only
— public accessible to other applications.
— how much space your data requires

3) Storage Options

— Shared Preferences - store private primitive data in key-value pairs
— Internal Storage - store private data on the device memory.
— External Storage - store public data on the shared external storage.
— SQLite Databases - store structured data in a private database.
— Network Connection - store data on the web site your own network server.

4) Android provides a way for you to expose even your private data to other applications — with a content provider. A content provider is an optional component that exposes read/write access to your application data, subject to whatever restrictions you want to impose.

5) Shared Preference Usage Demo

6) SharePreferences API
— General Framework to save and retrieve persistent key-value paris of primitive data types.
— Single Instance of the class that all clients share.
— booleans, floats, ints, longs & strings.

— public abstract SharedPreferences getSharedPreferences (String name, int mode) - to store preferences in different files.
— public SharedPreferences getPreferences (int mode) - one preferences file for your activity.

7) How to use

— Use getSharedPreferences() or getPreferences() to get the SharedPreferences references.
	SharedPreferences settings = getSharedPreferences(FILE_NAME, 0);

— Use getters to get stored value from preferences.
	settings.getBoolean(KEY, default_value);

— Use SharedPreferences.Editor to save any value inside preferences.
	SharedPreferences.Editor editor = settings.edit();

— Use putters to save any value for the key inside preferences.
	editor.putBoolean(KEY, value);


