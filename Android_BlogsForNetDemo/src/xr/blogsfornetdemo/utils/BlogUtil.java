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
 * @Description: Blog的工具类 从服务端获取数据 给每个item上添加内容
 * @author iamxiarui@foxmail.com
 * @date 2016年3月23日 上午10:46:17
 * 
 */
// 屏蔽android lint错误
@SuppressLint("NewApi")
public class BlogUtil {

	// 封装服务器地址 注意 这里一定要写IP地址 否则模拟器无法访问本地服务器
	public static String NET_URL = "http://183.161.59.199:8080/Web_BlogNewsDemo/servlet/BlogNewsServlet";

	/**
	 * @Title: getBlogs 
	 * @Description: 给每一个item上添加内容 并返回List集合 
	 * @param @param context 上下文对象 
	 * @param @return 集合对象 
	 * @return ArrayList<BlogBean> 
	 * @throws
	 */
	@SuppressLint("NewApi")
	public static ArrayList<BlogBean> getBlogsForNet(Context context) {

		ArrayList<BlogBean> list = new ArrayList<BlogBean>();

		try {
			// 打开连接
			URL net_url = new URL(NET_URL);
			HttpURLConnection connection = (HttpURLConnection) net_url.openConnection();
			connection.setRequestMethod("GET");
			connection.setReadTimeout(5000);

			int responseCode = connection.getResponseCode();
			if (responseCode == 200) {

				InputStream inputStream = connection.getInputStream();
				String result = StreamUtil.getStream(inputStream);

				// 将获取的数据 封装到JSONObject中 rootJson为根JSON
				JSONObject rootJson = new JSONObject(result);
				// 根JSON下面有一个JSONArray
				JSONArray jsonArray = rootJson.getJSONArray("blogs");

				// 循环将数据插入JSONArray中
				for (int i = 0; i < jsonArray.length(); i++) {
					// JSONArray中也有三个JSONObject
					JSONObject jsonObject = jsonArray.getJSONObject(i);
					BlogBean blogBean = new BlogBean();

					blogBean.id = jsonObject.getInt("id");
					blogBean.des = jsonObject.getString("des");
					blogBean.title = jsonObject.getString("title");
					blogBean.url = jsonObject.getString("url");

					// 并将结果存入list中
					list.add(blogBean);

				}

				// 先将本地原有的数据全部清空
				new BlogDaoUtil(context).deleteBlog();
				// 再将新的数据缓存到本地
				new BlogDaoUtil(context).saveBlog(list);

			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;

	}

	/**
	 * @Title: getBlogsForDB 
	 * @Description: 从本地数据库中得到数据 
	 * @param @param context 
	 * @param @return 
	 * @return ArrayList<BlogBean> 
	 * @throws
	 */
	public static ArrayList<BlogBean> getBlogsForDB(Context context) {

		ArrayList<BlogBean> blogList = new BlogDaoUtil(context).getBlog();

		return blogList;

	}
}
