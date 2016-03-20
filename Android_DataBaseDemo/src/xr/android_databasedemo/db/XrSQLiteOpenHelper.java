package xr.android_databasedemo.db;

/*
 * ����SQLiteOpenHelper����
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
	 * ���캯�� ��һ������ �����Ķ��� �ڶ������� ���ݿ����� �������� ����� Ĭ��Ϊ�� ���ĸ����� �汾�� ��1��ʼ ֻ���������ܽ���
	 * ֻ�а汾�ŵ��������ܵ��� onUpgrade����
	 */
	public XrSQLiteOpenHelper(Context context) {
		super(context, "info.db", null, 1);
	}

	// onCreate���� ֻ�ڵ�һ�δ������ݿ��ʱ�����
	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL("create table info (_id integer primary key autoincrement,name varchar(20),phone varchar(11))");
	}

	// onUpgrade���� ֻ�ڰ汾��������ʱ����ø÷���
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

	}

}
