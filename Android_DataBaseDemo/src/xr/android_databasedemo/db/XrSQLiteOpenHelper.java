package xr.android_databasedemo.db;

/*
 * 创建SQLiteOpenHelper对象
 * 
 * 
 * */
import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class XrSQLiteOpenHelper extends SQLiteOpenHelper {

	/*
	 * 构造函数 第一个参数 上下文对象 第二个参数 数据库名称 第三参数 结果集 默认为空 第四个参数 版本号 从1开始 只能升级不能降级
	 * 只有版本号的升级才能调用 onUpgrade方法
	 */
	public XrSQLiteOpenHelper(Context context) {
		super(context, "info.db", null, 1);
	}

	// onCreate方法 只在第一次创建数据库的时候调用
	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL("create table info (_id integer primary key autoincrement,name varchar(20),phone varchar(11))");
	}

	// onUpgrade方法 只在版本号升级的时候调用该方法
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

	}

}
