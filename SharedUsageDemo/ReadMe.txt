1) Shared Preference Usage Demo

2) SharePreferences API
— General Framework to save and retrieve persistent key-value paris of primitive data types.
— Single Instance of the class that all clients share.
— booleans, floats, ints, longs & strings.

— public abstract SharedPreferences getSharedPreferences (String name, int mode) - to store preferences in different files.
— public SharedPreferences getPreferences (int mode) - one preferences file for your activity.

3) How to use

— Use getSharedPreferences() or getPreferences() to get the SharedPreferences references.
	SharedPreferences settings = getSharedPreferences(FILE_NAME, 0);

— Use getters to get stored value from preferences.
	settings.getBoolean(KEY, default_value);

— Use SharedPreferences.Editor to save any value inside preferences.
	SharedPreferences.Editor editor = settings.edit();

— Use putters to save any value for the key inside preferences.
	editor.putBoolean(KEY, value);

— Use remove() method to remove the key-value pair stored.