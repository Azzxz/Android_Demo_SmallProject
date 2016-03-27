package web.blognews.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import web.blognews.bean.BlogBean;

public class BlogNewsDao {

	public static ArrayList<BlogBean> getBlogs(){
		Connection connection = null;
        Statement stmt = null;
        ResultSet rs = null;
        String sql = null;

        try {
            /*****��д���ݿ������Ϣ(��������ݿ�����ҳ)*****/
            String databaseName = "blog_db";
            String host = "localhost";
            String port = "3306";
            String username = "root"; //�û�AK
            String password = "XF802369"; //�û�SK
            String driverName = "com.mysql.jdbc.Driver";
            String dbUrl = "jdbc:mysql://";
            String serverName = host + ":" + port + "/";
            String connName = dbUrl + serverName + databaseName;

            /******�������Ӳ�ѡ�����ݿ���ΪdatabaseName�ķ�����******/
            Class.forName(driverName);
            connection = DriverManager.getConnection(connName, username,
                    password);
            stmt = connection.createStatement();
            /******������������ȫ�������ͿɶԵ�ǰ���ݿ������Ӧ�Ĳ�����*****/
            /******�������Ϳ���ʹ��������׼mysql���������������ݿ����*****/
            //����һ�����ݿ��
            sql = "select * from blogs order by id desc";
            ResultSet rss = stmt.executeQuery(sql);
            ArrayList<BlogBean> arrayList = new ArrayList<BlogBean>();
            if(rss != null){
            	while(rss.next()){
            		int id = rss.getInt("id");
            		String title = rss.getString("title");
            		String des = rss.getString("des");
            		String url = rss.getString("url");
            		
            		BlogBean blog = new BlogBean();
            		blog.setId(id);
            		blog.setTitle(title);
            		blog.setDes(des);
            		blog.setUrl(url);
            		arrayList.add(blog);            		
            	}
            	return arrayList;          	
            }       
        } catch (Exception e) {
        	e.printStackTrace();
        }
        return null;	
	}
}
