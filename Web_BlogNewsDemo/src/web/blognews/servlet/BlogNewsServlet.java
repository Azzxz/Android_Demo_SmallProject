package web.blognews.servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;

import web.blognews.bean.BlogBean;
import web.blognews.dao.BlogNewsDao;

/**
 * Servlet implementation class Servlet
 */
@WebServlet("/Servlet")
public class BlogNewsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BlogNewsServlet() {
        super();
    }

	/**
	 * @see BlogNewsServlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		
	}

	/**
	 * @see BlogNewsServlet#destroy()
	 */
	public void destroy() {
		super.destroy();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try{
			//从数据库获取新闻数据
			ArrayList<BlogBean> blogs = BlogNewsDao.getBlogs();

			//将list中的数据封装成一个jsonArray对象
			JSONArray jsonArray = new JSONArray();
			for (BlogBean blogBean : blogs) {
				JSONObject newsJson = new JSONObject();
				newsJson.put("id", blogBean.getId());
				newsJson.put("title", blogBean.getTitle());
				newsJson.put("des", blogBean.getDes());
				newsJson.put("url", blogBean.getUrl());
				jsonArray.put(newsJson); //把3个{} json对象添加到jsonArray里

			}

			//将jsonArray对象作为一个json对象，用来返回给客户端
			JSONObject allBlogsJson = new JSONObject();
			allBlogsJson.put("blogs", jsonArray);  //"blogs"--作为外部json对象名(键)， jsonArray---作为外部json对象值内容(值) 

			//外部json对象初始化新闻数据赋值完毕，转成字符串，响应给客户端
			response.getOutputStream().write(allBlogsJson.toString().getBytes("utf-8")); 

		}catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

	
}
