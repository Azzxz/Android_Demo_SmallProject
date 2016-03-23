package xr.android_blogdemo.activity;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import xr.android_blogdemo.adapter.BlogAdapter;
import xr.android_blogdemo.bean.BlogBean;
import xr.android_blogdemo.util.BlogUtil;

/**
 * @ClassName: MainActivity
 * @Description: 利用ListView模仿新闻或者博客客户端
 * @author iamxiarui@foxmail.com
 * @date 2016年3月23日 上午10:29:21
 * 
 */
public class MainActivity extends Activity implements OnItemClickListener {

	private Context thisContext = MainActivity.this;
	private ListView listView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		listView = (ListView) findViewById(R.id.listView);

		// 得到一个List

		ArrayList<BlogBean> list = BlogUtil.getBlogs(thisContext);

		// 得到一个适配器
		BlogAdapter adapter = new BlogAdapter(thisContext, list);

		// 给ListView绑定适配器
		listView.setAdapter(adapter);

		// 给ListView添加点击事件监听
		listView.setOnItemClickListener(this);

	}

	/*
	 * Title: onItemClick Description: ListView的点击事件
	 * 
	 * @param parent 代表ListView
	 * 
	 * @param view 表示点击item上的view
	 * 
	 * @param position item的位置
	 * 
	 * @param id item的id
	 * 
	 * 
	 */
	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		// 获得当前ListView上的Blog对象
		BlogBean blog = (BlogBean) parent.getItemAtPosition(position);
		// 拿到网址
		String url = blog.url;
		// 跳转浏览器
		Intent intent = new Intent();
		intent.setAction(Intent.ACTION_VIEW);
		intent.setData(Uri.parse(url));
		thisContext.startActivity(intent);
	}

}
