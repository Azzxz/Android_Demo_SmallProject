package xr.blogsforner.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * @ClassName: BlogsOpenHelper
 * @Description:���ز������ݿ�
 * @author iamxiarui@foxmail.com
 * @date 2016��3��27�� ����11:09:01
 * 
 */
public class BlogsOpenHelper extends SQLiteOpenHelper {

	public BlogsOpenHelper(Context context) {
		super(context, "BlogNews", null, 1);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(
				"create table blogs (_id integer,title varchar(200),des varchar(200),url varchar(200),icon varchar(200))");
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
	}

}
