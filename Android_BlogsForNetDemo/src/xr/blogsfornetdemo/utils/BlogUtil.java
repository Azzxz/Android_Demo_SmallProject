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
 * @Description: Blog�Ĺ����� ��ÿ��item���������
 * @author iamxiarui@foxmail.com
 * @date 2016��3��23�� ����10:46:17
 * 
 */
// ����android lint����
@SuppressLint("NewApi")
public class BlogUtil {

	public static String NET_URL = "http://183.161.59.199:8080/Web_BlogNewsDemo/servlet/BlogNewsServlet";

	/**
	 * @Title: getBlogs @Description: ��ÿһ��item��������� ������List���� @param @param
	 * context �����Ķ��� @param @return ���϶��� @return ArrayList<BlogBean> @throws
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
