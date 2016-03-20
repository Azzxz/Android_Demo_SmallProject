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

	// 增加数据
	public void addInfo(InfoBean bean) {

		SQLiteDatabase db = helper.getWritableDatabase();
		// 第二个参数是 占位符 ？ 代表后面数组中的数值
		db.execSQL("insert into info (name,phone) values(?,?);", new Object[] { bean.name, bean.phone });

		db.close();

	}

	// 删除数据
	public void delInfo(String name) {

		SQLiteDatabase db = helper.getReadableDatabase();

		db.execSQL("delete from info where name = ?;", new Object[] { name });

		db.close();
	}

	// 更新数据
	public void updateInfo(InfoBean bean) {
		SQLiteDatabase db = helper.getReadableDatabase();

		db.execSQL("update info set phone=? where name=?;", new Object[] { bean.phone, bean.name });

		db.close();

	}

	// 查找数据
	public void checkInfo(String name) {

		SQLiteDatabase db = helper.getReadableDatabase();

		// 创建游标对象
		Cursor cursor = db.rawQuery("select _id, name,phone from info where name = ?;", new String[] { name });

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
