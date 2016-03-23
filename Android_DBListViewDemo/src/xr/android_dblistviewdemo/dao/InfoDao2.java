package xr.android_dblistviewdemo.dao;

import java.util.ArrayList;

/*
 * 数据库的增删改查第二种方式
 * 
 *SQLiteDataBase中的方法 
 * */
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import xr.android_dblistviewdemo.bean.InfoBean;
import xr.android_dblistviewdemo.db.XrSQLiteOpenHelper;

public class InfoDao2 {

	private XrSQLiteOpenHelper helper;

	public InfoDao2(Context context) {
		helper = new XrSQLiteOpenHelper(context);
	}

	// 增加数据
	public boolean addInfo(InfoBean bean) {

		SQLiteDatabase db = helper.getWritableDatabase();

		// 创建一个集合 并添加数据
		ContentValues values = new ContentValues();
		values.put("name", bean.name);
		values.put("phone", bean.phone);

		// 第一个参数 ： 数据库名称 第二个参数 默认为空 第三个参数 键值对
		// 返回值 ： Long 类型 -1 则添加失败 其他则为成功
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

		// 第一个参数 数据库名称 第二个参数 占位符 第三个参数 字符数组 占位符中的值
		// 返回值 int 表示几行受到影响
		int delResult = db.delete("info", "name = ?", new String[] { name });

		db.close();

		return delResult;
	}

	// 更新数据
	public int updateInfo(InfoBean bean) {
		SQLiteDatabase db = helper.getReadableDatabase();
		// 创建一个集合 并添加数据
		ContentValues values = new ContentValues();
		values.put("phone", bean.phone);

		// 第一个参数 数据库名称 第二个参数 集合中的数据 第三个参数 占位符 第四个参数 占位符中的值
		// 返回值 int 表示几行受到影响
		int updateResult = db.update("info", values, "name = ?", new String[] { bean.name });
		db.close();

		return updateResult;

	}

	// 查找数据
	public ArrayList<InfoBean> checkInfo(String name) {

		ArrayList<InfoBean> list = new ArrayList<InfoBean>();

		SQLiteDatabase db = helper.getReadableDatabase();

		// 第一个参数 数据库名称 第二个参数 默认为空 表示查找所有元素 第三个参数 占位符 第四个参数 占位符中的值
		// 第五个参数 按照什么分组 第六个参数 分组条件 第七个参数 按照什么顺序排序
		// 返回值 是一个游标
		Cursor cursor = db.query("info", new String[] { "_id", "name", "phone" }, "name = ?", new String[] { name },
				null, null, "_id desc");

		// 如果游标不为空 而且行数大于0
		if (cursor != null && cursor.getCount() > 0) {

			// 不断移动游标 查找下一个
			while (cursor.moveToNext()) {
				// 得到游标所在行的 每一列的数值
				InfoBean info = new InfoBean();
				info.id = cursor.getInt(0) + "";
				info.name = cursor.getString(1);
				info.phone = cursor.getString(2);

				list.add(info);
			}

			// 关闭游标
			cursor.close();

		}

		db.close();

		return list;
	}
}
