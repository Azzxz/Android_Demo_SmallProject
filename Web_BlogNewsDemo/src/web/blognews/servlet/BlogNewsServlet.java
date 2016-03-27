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
			//�����ݿ��ȡ��������
			ArrayList<BlogBean> blogs = BlogNewsDao.getBlogs();

			//��list�е����ݷ�װ��һ��jsonArray����
			JSONArray jsonArray = new JSONArray();
			for (BlogBean blogBean : blogs) {
				JSONObject newsJson = new JSONObject();
				newsJson.put("id", blogBean.getId());
				newsJson.put("title", blogBean.getTitle());
				newsJson.put("des", blogBean.getDes());
				newsJson.put("url", blogBean.getUrl());
				jsonArray.put(newsJson); //��3��{} json������ӵ�jsonArray��

			}

			//��jsonArray������Ϊһ��json�����������ظ��ͻ���
			JSONObject allBlogsJson = new JSONObject();
			allBlogsJson.put("blogs", jsonArray);  //"blogs"--��Ϊ�ⲿjson������(��)�� jsonArray---��Ϊ�ⲿjson����ֵ����(ֵ) 

			//�ⲿjson�����ʼ���������ݸ�ֵ��ϣ�ת���ַ�������Ӧ���ͻ���
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
