package com.ov3rk1ll.pokedroiddex.db;

import java.util.Arrays;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class DataSource {

	public static final String TABLE = "gen_";

	public static final String COLUMN_NR = "_id";
	public static final String COLUMN_NAME = "name_";
	public static final String COLUMN_TYPE_1 = "type_1";
	public static final String COLUMN_TYPE_2 = "type_2";

	public static final String[] SUPPORTED_LANGUAGES = {"en", "de", "fr"}; //, "jp", "kr"};
	
	private SQLiteDatabase database;
	private AssetDatabaseOpenHelper dbHelper;
	Context context;
	
	private String language = "en";
	private int generation = 6;

	private static DataSource instance = null;
	
	public static void init(Context context, String language, int gen) {
		instance = new DataSource(context, language, gen);
		Log.i("DataSource", "init to " + language + ", generation v" + gen);
	}
	
	public static DataSource getInstance(){
		return instance;
	}
	
	public DataSource(Context context, String language, int gen) {
		dbHelper = new AssetDatabaseOpenHelper(context);
		this.context = context;
		if(Arrays.asList(SUPPORTED_LANGUAGES).contains(language)){
			this.language = language;
		}else{
			this.language = "en";
		}
		this.generation = gen;
	}

	public SQLiteDatabase open() throws SQLException {
		if(database != null && database.isOpen()) database.close();
		database = dbHelper.openDatabase();
		return database;
	}

	
	
	public Cursor getFilteredCursor(CharSequence constraint) {
		String s = "'#'||substr('000'||" + COLUMN_NR + ", -3, 3)||' '||" + COLUMN_NAME + language;
		if(constraint == null) return null;
		return database.query(TABLE + generation,
				new String[]{COLUMN_NR, s + " AS name", COLUMN_TYPE_1, COLUMN_TYPE_2},
				s + " LIKE '%" + constraint.toString() + "%'",
				null, null, null, COLUMN_NR);
	}

	public Cursor getCursor() {
		String s = "'#'||substr('000'||" + COLUMN_NR + ", -3, 3)||' '||" + COLUMN_NAME + language + " AS name";
		return database.query(TABLE + generation,
				new String[]{COLUMN_NR, s, COLUMN_TYPE_1, COLUMN_TYPE_2},
				null, null, null, null, COLUMN_NR);
	}
	
	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public int getGeneration() {
		return generation;
	}

	public void setGeneration(int generation) {
		this.generation = generation;
	}
	
}
