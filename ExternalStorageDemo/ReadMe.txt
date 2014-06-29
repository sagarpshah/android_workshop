1) External Storage

— A removable storage media like SD card
- Public space inside internal storage.
- Drawback 1: File saved are publicly readable (any app or user can read) & even user can modify them.
- Drawback 2: File can become unavailable if the user mounts the phone with PC or remove the SD card.

2) How to use

— Get access to external storage by asking permissions
- public static final String READ_EXTERNAL_STORAGE
- public static final String WRITE_EXTERNAL_STORAGE

	<manifest ...>
		<uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE" />
		...
	</manifest>

3) How to use
- Check media availability


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
	    if (Environment.MEDIA_MOUNTED.equals(state) ||
	        Environment.MEDIA_MOUNTED_READ_ONLY.equals(state)) {
	        return true;
	    }
	    return false;
	}

- public static String getExternalStorageState () - to check the current state of media and to identify if media is mounted to a computer, missing, read-only or in some other state.

4) Save public file

- Sample code
	public File getAlbumStorageDir(String albumName) {
 	   // Get the directory for the user's public pictures directory.
	    File file = new File(Environment.getExternalStoragePublicDirectory(
            	Environment.DIRECTORY_PICTURES), albumName);
	    if (!file.mkdirs()) {
	        Log.e(LOG_TAG, "Directory not created");
	    }
	    return file;
	}
- Save file in shared public directories, such as Music/, Pictures/ & Ringtones/
- call public static File getExternalStoragePublicDirectory (String type)
- By saving your files to the corresponding media-type directory, the system’s media scanner can properly categorise your files in the system
- Ringtones appear in system settings as ringtones, not as music.

5) Save private file.
- Use public abstract File getExternalFilesDir (String type) - These files are internal to the applications, and not typically visible to the user as media.
- Difference 1: External files are not always available: they will disappear if the user mounts the external storage on a computer or removes it.
- Difference 2: There is no security enforced with these files. For example, any application holding WRITE_EXTERNAL_STORAGE can write to these files.
- Difference 3: These files are not automatically scanned by the media scanner, you can explicitly add them to the media database with MediaScannerConnection.scanFile.
- Difference 4: The directories returned here are owned by the application, and their contents will be removed when the application is uninstalled.

6) Saving cache file.
- public abstract File[] getExternalCacheDirs () - application-specific directories on all external storage devices where the application can place cache files it owns.
— Internal to the application and not visible to the user as media.