package xr.android_banktransferdemo.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class BankSQLiteOpenHelper extends SQLiteOpenHelper {

	public BankSQLiteOpenHelper(Context context) {
		super(context, "bank.db", null, 1);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {

		db.execSQL("create table account (_id integer primary key autoincrement,name varchar(20),money varchar(20))");
		db.execSQL("insert into account ('name' ,'money') values('Ð¡Ã÷','2000')");
		db.execSQL("insert into account ('name' ,'money') values('Ð¡»Æ','5000')");

	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
	}

}
