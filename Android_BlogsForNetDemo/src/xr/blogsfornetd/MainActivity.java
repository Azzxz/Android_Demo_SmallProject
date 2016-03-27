package xr.blogsfornetd;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import xr.blogsfornetdemo.adapter.BlogAdapter;
import xr.blogsfornetdemo.bean.BlogBean;
import xr.blogsfornetdemo.utils.BlogUtil;

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
	private Handler handler = new Handler() {
		public void handleMessage(Message msg) {
			ArrayList<BlogBean> blogListForNet = (ArrayList<BlogBean>) msg.obj;
			if (blogListForNet != null && blogListForNet.size() > 0) {
				// 得到一个适配器
				BlogAdapter adapter = new BlogAdapter(thisContext, blogListForNet);
				// 给ListView绑定适配器
				listView.setAdapter(adapter);
			}
		};
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		listView = (ListView) findViewById(R.id.listView);

		ArrayList<BlogBean> blogsListForDB = BlogUtil.getBlogsForDB(thisContext);

		if (blogsListForDB != null && blogsListForDB.size() > 0) {
			// 得到一个适配器
			BlogAdapter adapter = new BlogAdapter(thisContext, blogsListForDB);
			// 给ListView绑定适配器
			listView.setAdapter(adapter);
		}

		new Thread(new Runnable() {

			@Override

			public void run() {

				ArrayList<BlogBean> blogListForNet = BlogUtil.getBlogsForNet(thisContext);

				Message msg = Message.obtain();
				msg.obj = blogListForNet;
				handler.sendMessage(msg);

			}
		}).start();

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
