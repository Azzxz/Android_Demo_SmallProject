package xr.android_blogdemo.util;

import java.util.ArrayList;
import android.annotation.SuppressLint;
import android.content.Context;
import xr.android_blogdemo.activity.R;
import xr.android_blogdemo.bean.BlogBean;

/**
 * @ClassName: BlogUtil
 * @Description: Blog�Ĺ����� ��ÿ��item���������
 * @author iamxiarui@foxmail.com
 * @date 2016��3��23�� ����10:46:17
 * 
 */
// ����android lint����
@SuppressLint("NewApi")
public class BlogUtil {

	/**
	* @Title: getBlogs
	* @Description: ��ÿһ��item��������� ������List����
	* @param @param context �����Ķ���
	* @param @return ���϶���
	* @return ArrayList<BlogBean>
	* @throws
	*/
	@SuppressLint("NewApi")
	public static ArrayList<BlogBean> getBlogs(Context context) {

		ArrayList<BlogBean> list = new ArrayList<BlogBean>();

		for (int i = 0; i < 50; i++) {

			BlogBean blog1 = new BlogBean();
			blog1.title = "Java : ���ģʽ ���� �������ģʽ";
			blog1.des = "һ��ʲô�ǵ������   ��Java���������У�������Ҫ����ĳ���������ڴ���ֻ����һ�����󡣱����̵߳�ͬ��";
			blog1.url = "http://www.iamxiarui.com/2016/03/22/java-���ģʽ1-�������ģʽ/";
			blog1.pic = context.getDrawable(R.drawable.pic1);
			list.add(blog1);

			BlogBean blog2 = new BlogBean();
			blog2.title = "Java ��������ʽ�ļ�����";
			blog2.des = "һ����������������֪���Ͽ���ĳ�����һ�����룬��ʱ����֪������������ʽ����һ��Ӧ�����Ǵ��룿��";
			blog2.url = "http://www.iamxiarui.com/2016/03/19/java-��������ʽ�ļ�����/";
			blog2.pic = context.getDrawable(R.drawable.pic2);
			list.add(blog2);

			BlogBean blog3 = new BlogBean();
			blog3.title = "Java : ���͵ĸ������÷�";
			blog3.des = "һ��ʲô�Ƿ�����֮ǰ����AndroidӦ�õ�ʱ�򣬾�����Ҫ�õ�Map������ListView�����ͼƬ�����֡����Ծ����õ��������䣺";
			blog3.url = "http://www.iamxiarui.com/2016/03/18/java-���͵ĸ������÷�/";
			blog3.pic = context.getDrawable(R.drawable.pic3);
			list.add(blog3);

		}

		return list;

	}
}
