package xr.android_databasedemo.dao;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import xr.android_databasedemo.bean.InfoBean;
import xr.android_databasedemo.db.XrSQLiteOpenHelper;

public class InfoDao {

	private XrSQLiteOpenHelper helper;

	public InfoDao(Context context) {
		helper = new XrSQLiteOpenHelper(context);
	}

	// ��������
	public void addInfo(InfoBean bean) {

		SQLiteDatabase db = helper.getWritableDatabase();
		// �ڶ��������� ռλ�� �� ������������е���ֵ
		db.execSQL("insert into info (name,phone) values(?,?);", new Object[] { bean.name, bean.phone });

		db.close();

	}

	// ɾ������
	public void delInfo(String name) {

		SQLiteDatabase db = helper.getReadableDatabase();

		db.execSQL("delete from info where name = ?;", new Object[] { name });

		db.close();
	}

	// ��������
	public void updateInfo(InfoBean bean) {
		SQLiteDatabase db = helper.getReadableDatabase();

		db.execSQL("update info set phone=? where name=?;", new Object[] { bean.phone, bean.name });

		db.close();

	}

	// ��������
	public void checkInfo(String name) {

		SQLiteDatabase db = helper.getReadableDatabase();

		// �����α����
		Cursor cursor = db.rawQuery("select _id, name,phone from info where name = ?;", new String[] { name });

		// ����α겻Ϊ�� ������������0
		if (cursor != null && cursor.getCount() > 0) {

			// �����ƶ��α� ������һ��
			while (cursor.moveToNext()) {
				// �õ��α������е� ÿһ�е���ֵ
				int id = cursor.getInt(0);
				String namestr = cursor.getString(1);
				String phone = cursor.getString(2);
				System.out.println("_id:" + id + ";name:" + namestr + ";phone:" + phone);
			}

			// �ر��α�
			cursor.close();

		}

		db.close();

	}
}
