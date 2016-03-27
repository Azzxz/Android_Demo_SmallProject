package xr.blogsfornetdemo.utils;

import java.util.ArrayList;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import xr.blogsforner.dao.BlogsOpenHelper;
import xr.blogsfornetdemo.bean.BlogBean;

/** 
* @ClassName: BlogDaoUtil 
* @Description: 本地保存数据库的工具类
* @author iamxiarui@foxmail.com
* @date 2016年3月27日 下午11:12:36 
*  
*/ 
public class BlogDaoUtil {

	private BlogsOpenHelper blogsOpenHelper;

	public BlogDaoUtil(Context context) {

		blogsOpenHelper = new BlogsOpenHelper(context);

	}

	/**
	* @Title: getBlog
	* @Description:返回数据库 用来取数据 本地回显list
	* @param @return
	* @return ArrayList<BlogBean>
	* @throws
	*/
	public ArrayList<BlogBean> getBlog() {

		ArrayList<BlogBean> list = new ArrayList<BlogBean>();

		SQLiteDatabase db = blogsOpenHelper.getReadableDatabase();
	    //获取所有数据
		Cursor cursor = db.rawQuery("select * from blogs", null);
		//将取得的数据 封装到list中
		if (cursor != null && cursor.getCount() > 0) {
			while (cursor.moveToNext()) {
				
				BlogBean blogBean = new BlogBean();
				blogBean.id = cursor.getInt(0);
				blogBean.title = cursor.getString(1);
				blogBean.des = cursor.getString(2);
				blogBean.url = cursor.getString(3);

				list.add(blogBean);
			}
		}
		db.close();
		cursor.close();
		
		return list;
	}

	/**
	* @Title: saveBlog
	* @Description:将从服务端获得的数据 缓存到本地数据库
	* @param @param list
	* @return void
	* @throws
	*/
	public void saveBlog(ArrayList<BlogBean> list) {
		SQLiteDatabase db = blogsOpenHelper.getReadableDatabase();
		//遍历数据并插入本地数据库
		for (BlogBean blogBean : list) {
			ContentValues values = new ContentValues();
			values.put("_id", blogBean.id);
			values.put("title", blogBean.title);
			values.put("des", blogBean.des);
			values.put("url", blogBean.url);
			
			db.insert("blogs", null, values);

			db.close();
		}
	}

	/**
	* @Title: deleteBlog
	* @Description:删除本地数据库中缓存的数据
	* @param 
	* @return void
	* @throws
	*/
	public void deleteBlog() {

		SQLiteDatabase db = blogsOpenHelper.getReadableDatabase();

		db.delete("blogs", null, null);

		db.close();
	}

}
