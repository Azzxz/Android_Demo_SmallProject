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

public class MainActivity extends Activity implements OnItemClickListener {

	private Context thisContext = MainActivity.this;
	private ListView listView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		listView = (ListView) findViewById(R.id.listView);

		ArrayList<BlogBean> list = BlogUtil.getBlogs(thisContext);

		BlogAdapter adapter = new BlogAdapter(thisContext, list);

		listView.setAdapter(adapter);

		listView.setOnItemClickListener(this);

	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		BlogBean blog = (BlogBean) parent.getItemAtPosition(position);

		String url = blog.url;

		// Ìø×ªä¯ÀÀÆ÷
		Intent intent = new Intent();
		intent.setAction(Intent.ACTION_VIEW);
		intent.setData(Uri.parse(url));
		thisContext.startActivity(intent);
	}

}
