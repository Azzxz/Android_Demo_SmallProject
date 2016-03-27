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
            /*****填写数据库相关信息(请查找数据库详情页)*****/
            String databaseName = "blog_db";
            String host = "localhost";
            String port = "3306";
            String username = "root"; //用户AK
            String password = "XF802369"; //用户SK
            String driverName = "com.mysql.jdbc.Driver";
            String dbUrl = "jdbc:mysql://";
            String serverName = host + ":" + port + "/";
            String connName = dbUrl + serverName + databaseName;

            /******接着连接并选择数据库名为databaseName的服务器******/
            Class.forName(driverName);
            connection = DriverManager.getConnection(connName, username,
                    password);
            stmt = connection.createStatement();
            /******至此连接已完全建立，就可对当前数据库进行相应的操作了*****/
            /******接下来就可以使用其它标准mysql函数操作进行数据库操作*****/
            //创建一个数据库表
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
