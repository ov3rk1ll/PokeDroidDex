package com.ov3rk1ll.pokedroiddex.db;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
 
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
 
public class AssetDatabaseOpenHelper {
 
    private static final String DB_NAME = "pokemon.db";
    private static final int DB_VERSION = 2;
 
    private Context context;
 
    public AssetDatabaseOpenHelper(Context context) {
        this.context = context;
    }
 
    public SQLiteDatabase openDatabase() {
    	int version = context.getSharedPreferences(DB_NAME, 0).getInt("version", 0);    	
        File dbFile = context.getDatabasePath(DB_NAME);
        dbFile.getParentFile().mkdirs();
 
        if (!dbFile.exists() || DB_VERSION > version) {
            try {
                copyDatabase(dbFile);
                context.getSharedPreferences(DB_NAME, 0).edit().putInt("version", DB_VERSION).commit();
                Log.i("AssetDatabaseOpenHelper", "created " + DB_NAME + " v" + DB_VERSION);
            } catch (IOException e) {
                throw new RuntimeException("Error creating source database", e);
            }
        }
 
        return SQLiteDatabase.openDatabase(dbFile.getPath(), null, SQLiteDatabase.OPEN_READONLY);
    }
 
    private void copyDatabase(File dbFile) throws IOException {
        InputStream is = context.getAssets().open("data/" + DB_NAME);
        OutputStream os = new FileOutputStream(dbFile);
 
        byte[] buffer = new byte[1024];
        while (is.read(buffer) > 0) {
            os.write(buffer);
        }
 
        os.flush();
        os.close();
        is.close();
    }
 
}