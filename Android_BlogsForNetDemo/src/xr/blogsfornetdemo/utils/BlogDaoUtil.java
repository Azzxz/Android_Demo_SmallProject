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
* @Description: ���ر������ݿ�Ĺ�����
* @author iamxiarui@foxmail.com
* @date 2016��3��27�� ����11:12:36 
*  
*/ 
public class BlogDaoUtil {

	private BlogsOpenHelper blogsOpenHelper;

	public BlogDaoUtil(Context context) {

		blogsOpenHelper = new BlogsOpenHelper(context);

	}

	/**
	* @Title: getBlog
	* @Description:�������ݿ� ����ȡ���� ���ػ���list
	* @param @return
	* @return ArrayList<BlogBean>
	* @throws
	*/
	public ArrayList<BlogBean> getBlog() {

		ArrayList<BlogBean> list = new ArrayList<BlogBean>();

		SQLiteDatabase db = blogsOpenHelper.getReadableDatabase();
	    //��ȡ��������
		Cursor cursor = db.rawQuery("select * from blogs", null);
		//��ȡ�õ����� ��װ��list��
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
	* @Description:���ӷ���˻�õ����� ���浽�������ݿ�
	* @param @param list
	* @return void
	* @throws
	*/
	public void saveBlog(ArrayList<BlogBean> list) {
		SQLiteDatabase db = blogsOpenHelper.getReadableDatabase();
		//�������ݲ����뱾�����ݿ�
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
	* @Description:ɾ���������ݿ��л��������
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
