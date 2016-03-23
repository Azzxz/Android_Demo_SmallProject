package xr.android_blogdemo.util;

import java.util.ArrayList;
import android.annotation.SuppressLint;
import android.content.Context;
import xr.android_blogdemo.activity.R;
import xr.android_blogdemo.bean.BlogBean;

/**
 * @ClassName: BlogUtil
 * @Description: Blog的工具类 给每个item上添加内容
 * @author iamxiarui@foxmail.com
 * @date 2016年3月23日 上午10:46:17
 * 
 */
// 屏蔽android lint错误
@SuppressLint("NewApi")
public class BlogUtil {

	/**
	* @Title: getBlogs
	* @Description: 给每一个item上添加内容 并返回List集合
	* @param @param context 上下文对象
	* @param @return 集合对象
	* @return ArrayList<BlogBean>
	* @throws
	*/
	@SuppressLint("NewApi")
	public static ArrayList<BlogBean> getBlogs(Context context) {

		ArrayList<BlogBean> list = new ArrayList<BlogBean>();

		for (int i = 0; i < 50; i++) {

			BlogBean blog1 = new BlogBean();
			blog1.title = "Java : 设计模式 —— 单例设计模式";
			blog1.des = "一、什么是单例设计   在Java开发过程中，经常需要程序某个类中在内存中只存在一个对象。比如线程的同步";
			blog1.url = "http://www.iamxiarui.com/2016/03/22/java-设计模式1-单例设计模式/";
			blog1.pic = context.getDrawable(R.drawable.pic1);
			list.add(blog1);

			BlogBean blog2 = new BlogBean();
			blog2.title = "Java ：正则表达式的简单运用";
			blog2.des = "一、基本概念曾经在知乎上看到某大神的一串代码，当时并不知道这是正则表达式。第一反应是这是代码？！";
			blog2.url = "http://www.iamxiarui.com/2016/03/19/java-：正则表达式的简单运用/";
			blog2.pic = context.getDrawable(R.drawable.pic2);
			list.add(blog2);

			BlogBean blog3 = new BlogBean();
			blog3.title = "Java : 泛型的概念与用法";
			blog3.des = "一、什么是泛型在之前开发Android应用的时候，经常需要用到Map集合往ListView中添加图片和文字。所以经常用到下面的语句：";
			blog3.url = "http://www.iamxiarui.com/2016/03/18/java-泛型的概念与用法/";
			blog3.pic = context.getDrawable(R.drawable.pic3);
			list.add(blog3);

		}

		return list;

	}
}
