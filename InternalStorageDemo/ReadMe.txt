1) Internal Storage

— Files saved to the internal storage are private.
— No other app can access the file storage by your app inside internal storage.
— When user uninstalls the application, these files are removed.

2) How to
— public abstract FileOutputStream openFileOutput (String name, int mode)
— Opens a private file associated with this Context’s application package for writing.
— No permissions required since it’s your area.

	String FILENAME = "hello_file";
	String string = "hello world!";
	FileOutputStream fos = openFileOutput(FILENAME, Context.MODE_PRIVATE);
	fos.write(string.getBytes());
	fos.close();

3) Other methods
— public abstract File getFilesDir () - gets the absolute path of the directory where your files are saved.
— public abstract File getDir (String name, int mode) - Creates (or opens an existing) directory within your internal storage space.
- public abstract boolean deleteFile (String name) - Deletes a file saved on the internal storage.
- public abstract String[] fileList () - Returns an array of files currently saved by your application. 


