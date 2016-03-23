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
 * @Description: ����ListViewģ�����Ż��߲��Ϳͻ���
 * @author iamxiarui@foxmail.com
 * @date 2016��3��23�� ����10:29:21
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

		// �õ�һ��List

		ArrayList<BlogBean> list = BlogUtil.getBlogs(thisContext);

		// �õ�һ��������
		BlogAdapter adapter = new BlogAdapter(thisContext, list);

		// ��ListView��������
		listView.setAdapter(adapter);

		// ��ListView��ӵ���¼�����
		listView.setOnItemClickListener(this);

	}

	/*
	 * Title: onItemClick Description: ListView�ĵ���¼�
	 * 
	 * @param parent ����ListView
	 * 
	 * @param view ��ʾ���item�ϵ�view
	 * 
	 * @param position item��λ��
	 * 
	 * @param id item��id
	 * 
	 * 
	 */
	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		// ��õ�ǰListView�ϵ�Blog����
		BlogBean blog = (BlogBean) parent.getItemAtPosition(position);
		// �õ���ַ
		String url = blog.url;
		// ��ת�����
		Intent intent = new Intent();
		intent.setAction(Intent.ACTION_VIEW);
		intent.setData(Uri.parse(url));
		thisContext.startActivity(intent);
	}

}
