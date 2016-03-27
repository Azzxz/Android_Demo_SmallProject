package xr.blogsforner.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class BlogsOpenHelper extends SQLiteOpenHelper {

	public BlogsOpenHelper(Context context) {
		super(context, "Blogs_db", null, 1);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL("create table blogs (_id integer,title varchar(200),des varchar(200),url varchar(200))");
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
	}

}
