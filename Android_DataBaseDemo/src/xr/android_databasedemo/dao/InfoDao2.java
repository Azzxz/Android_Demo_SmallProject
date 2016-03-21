package xr.android_databasedemo.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import xr.android_databasedemo.bean.InfoBean;
import xr.android_databasedemo.db.XrSQLiteOpenHelper;

public class InfoDao2 {

	private XrSQLiteOpenHelper helper;

	public InfoDao2(Context context) {
		helper = new XrSQLiteOpenHelper(context);
	}

	// 增加数据
	public boolean addInfo(InfoBean bean) {

		SQLiteDatabase db = helper.getWritableDatabase();

		ContentValues values = new ContentValues();

		values.put("name", bean.name);
		values.put("phone", bean.phone);
		long addResult = db.insert("info", null, values);
		db.close();

		if (addResult != -1) {
			return true;
		} else {
			return false;
		}

	}

	// 删除数据
	public int delInfo(String name) {

		SQLiteDatabase db = helper.getReadableDatabase();

		int delResult = db.delete("info", "name = ?", new String[] { name });

		db.close();

		return delResult;
	}

	// 更新数据
	public int updateInfo(InfoBean bean) {
		SQLiteDatabase db = helper.getReadableDatabase();
		ContentValues values = new ContentValues();

		values.put("phone", bean.phone);
		int updateResult = db.update("info", values, "name = ?", new String[] { bean.name });
		db.close();

		return updateResult;

	}

	// 查找数据
	public void checkInfo(String name) {

		SQLiteDatabase db = helper.getReadableDatabase();

		Cursor cursor = db.query("info", new String[] { "_id", "name", "phone" }, "name = ?", new String[] { name },
				null, null, "_id desc");

		// 如果游标不为空 而且行数大于0
		if (cursor != null && cursor.getCount() > 0) {

			// 不断移动游标 查找下一个
			while (cursor.moveToNext()) {
				// 得到游标所在行的 每一列的数值
				int id = cursor.getInt(0);
				String namestr = cursor.getString(1);
				String phone = cursor.getString(2);
				System.out.println("_id:" + id + ";name:" + namestr + ";phone:" + phone);
			}

			// 关闭游标
			cursor.close();

		}

		db.close();

	}
}
