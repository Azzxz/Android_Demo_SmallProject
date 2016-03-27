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
 * @Description: Blog�Ĺ����� �ӷ���˻�ȡ���� ��ÿ��item���������
 * @author iamxiarui@foxmail.com
 * @date 2016��3��23�� ����10:46:17
 * 
 */
// ����android lint����
@SuppressLint("NewApi")
public class BlogUtil {

	// ��װ��������ַ ע�� ����һ��ҪдIP��ַ ����ģ�����޷����ʱ��ط�����
	public static String NET_URL = "http://183.161.59.199:8080/Web_BlogNewsDemo/servlet/BlogNewsServlet";

	/**
	 * @Title: getBlogs 
	 * @Description: ��ÿһ��item��������� ������List���� 
	 * @param @param context �����Ķ��� 
	 * @param @return ���϶��� 
	 * @return ArrayList<BlogBean> 
	 * @throws
	 */
	@SuppressLint("NewApi")
	public static ArrayList<BlogBean> getBlogsForNet(Context context) {

		ArrayList<BlogBean> list = new ArrayList<BlogBean>();

		try {
			// ������
			URL net_url = new URL(NET_URL);
			HttpURLConnection connection = (HttpURLConnection) net_url.openConnection();
			connection.setRequestMethod("GET");
			connection.setReadTimeout(5000);

			int responseCode = connection.getResponseCode();
			if (responseCode == 200) {

				InputStream inputStream = connection.getInputStream();
				String result = StreamUtil.getStream(inputStream);

				// ����ȡ������ ��װ��JSONObject�� rootJsonΪ��JSON
				JSONObject rootJson = new JSONObject(result);
				// ��JSON������һ��JSONArray
				JSONArray jsonArray = rootJson.getJSONArray("blogs");

				// ѭ�������ݲ���JSONArray��
				for (int i = 0; i < jsonArray.length(); i++) {
					// JSONArray��Ҳ������JSONObject
					JSONObject jsonObject = jsonArray.getJSONObject(i);
					BlogBean blogBean = new BlogBean();

					blogBean.id = jsonObject.getInt("id");
					blogBean.des = jsonObject.getString("des");
					blogBean.title = jsonObject.getString("title");
					blogBean.url = jsonObject.getString("url");

					// �����������list��
					list.add(blogBean);

				}

				// �Ƚ�����ԭ�е�����ȫ�����
				new BlogDaoUtil(context).deleteBlog();
				// �ٽ��µ����ݻ��浽����
				new BlogDaoUtil(context).saveBlog(list);

			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;

	}

	/**
	 * @Title: getBlogsForDB 
	 * @Description: �ӱ������ݿ��еõ����� 
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
