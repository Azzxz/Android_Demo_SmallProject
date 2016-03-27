package xr.blogsfornetdemo.utils;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

import android.annotation.SuppressLint;
import android.content.Context;
import xr.blogsfornetdemo.bean.BlogBean;

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

	public static String NET_URL = "http://183.161.59.199:8080/Web_BlogNewsDemo/servlet/BlogNewsServlet";

	/**
	 * @Title: getBlogs @Description: 给每一个item上添加内容 并返回List集合 @param @param
	 * context 上下文对象 @param @return 集合对象 @return ArrayList<BlogBean> @throws
	 */
	@SuppressLint("NewApi")
	public static ArrayList<BlogBean> getBlogsForNet(Context context) {

		ArrayList<BlogBean> list = new ArrayList<BlogBean>();

		try {
			URL net_url = new URL(NET_URL);
			HttpURLConnection connection = (HttpURLConnection) net_url.openConnection();
			connection.setRequestMethod("GET");
			connection.setReadTimeout(5000);

			int responseCode = connection.getResponseCode();
			if (responseCode == 200) {
				InputStream inputStream = connection.getInputStream();
				String result = StreamUtil.getStream(inputStream);

				JSONObject rootJson = new JSONObject(result);
				JSONArray jsonArray = rootJson.getJSONArray("blogs");

				for (int i = 0; i < jsonArray.length(); i++) {
					JSONObject jsonObject = jsonArray.getJSONObject(i);

					BlogBean blogBean = new BlogBean();

					blogBean.id = jsonObject.getInt("id");
					blogBean.des = jsonObject.getString("des");
					blogBean.title = jsonObject.getString("title");
					blogBean.url = jsonObject.getString("url");

					list.add(blogBean);

				}

				new BlogDaoUtil(context).deleteBlog();
				new BlogDaoUtil(context).saveBlog(list);

			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;

	}

	public static ArrayList<BlogBean> getBlogsForDB(Context context) {

		ArrayList<BlogBean> blogList = new BlogDaoUtil(context).getBlog();

		return blogList;

	}
}
