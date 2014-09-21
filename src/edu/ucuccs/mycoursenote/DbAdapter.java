package edu.ucuccs.mycoursenote;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DbAdapter {

	// database
	static final int DATABASE_VERSION = 1;
	static final String DATABASE_NAME = "db_user";

	static final String COURSENOTES_ID = "courseNotes_id";
	static final String COURSENOTES_DATE = "courseNotes_date";
	static final String COURSENOTES_MSG = "courseNotes_msg";

	static final String TABLE_NAME = "tb_courseNotes";

	static final String DATABASE_CREATE2 = "CREATE TABLE " + TABLE_NAME + " ( "
			+ COURSENOTES_ID + " integer primary key autoincrement, "
			+ COURSENOTES_DATE + " text not null ," + COURSENOTES_MSG
			+ " text not null  )";

	// DBHELPER
	final Context context;

	DatabaseHelper DBHelper;

	SQLiteDatabase db;

	public DbAdapter(Context ctx) {
		this.context = ctx;
		DBHelper = new DatabaseHelper(context);
	}

	private static class DatabaseHelper extends SQLiteOpenHelper {
		DatabaseHelper(Context context) {
			super(context, DATABASE_NAME, null, DATABASE_VERSION);
		}

		// ON CREATE
		@Override
		public void onCreate(SQLiteDatabase db) {
			// TODO Auto-generated method stub
			try {
				db.execSQL(DATABASE_CREATE2);

			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		// ON UPGRADE (DROP DUPLICATE)
		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			// TODO Auto-generated method stub
			db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);

			onCreate(db);
		}
	}

	public DbAdapter open() throws SQLException {
		db = DBHelper.getWritableDatabase();
		return this;
	}

	public void close() {
		DBHelper.close();
	}

	public long method_compose(String date, String message) {
		ContentValues initialValues = new ContentValues();
		initialValues.put(COURSENOTES_DATE, date);
		initialValues.put(COURSENOTES_MSG, message);
		return db.insert(TABLE_NAME, null, initialValues);
	}

	public Cursor method_showAllRecords() {

		Cursor search_cursor = db.query(true, TABLE_NAME, new String[] {
				COURSENOTES_ID, COURSENOTES_DATE, COURSENOTES_MSG }, null,
				null, null, null, null, null);
		return search_cursor;
	}

}// end