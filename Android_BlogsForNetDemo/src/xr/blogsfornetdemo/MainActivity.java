package xr.blogsfornetdemo;

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
import xr.blogsfornetd.R;
import xr.blogsfornetdemo.adapter.BlogAdapter;
import xr.blogsfornetdemo.bean.BlogBean;
import xr.blogsfornetdemo.utils.BlogUtil;

/**
 * @ClassName: MainActivity
 * @Description: ��ҳ���Ϳͻ��� ��ȡ��������JSON����
 * @author iamxiarui@foxmail.com
 * @date 2016��3��23�� ����10:29:21
 * 
 */
public class MainActivity extends Activity implements OnItemClickListener {

	private Context thisContext = MainActivity.this;
	private ListView listView;
	// ���߳̽���handler�ϵ����� ����UI
	private Handler handler = new Handler() {
		public void handleMessage(Message msg) {
			// �õ� ���ص�list
			// ����ǿ��ת�������ľ���
			@SuppressWarnings("unchecked")
			ArrayList<BlogBean> blogListForNet = (ArrayList<BlogBean>) msg.obj;
			// ����ӷ���˻�ȡ�������� ��Ϊ�� �����UI
			if (blogListForNet != null && blogListForNet.size() > 0) {
				// �õ�һ��������
				BlogAdapter adapter = new BlogAdapter(thisContext, blogListForNet);
				// ��ListView��������
				listView.setAdapter(adapter);
			}
		};
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		listView = (ListView) findViewById(R.id.listView);

		// �ӱ��ػ�ȡlist
		ArrayList<BlogBean> blogsListForDB = BlogUtil.getBlogsForDB(thisContext);

		// ������ػ�ȡ�����ݿ� ��Ϊ�� �����UI
		if (blogsListForDB != null && blogsListForDB.size() > 0) {
			// �õ�һ��������
			BlogAdapter adapter = new BlogAdapter(thisContext, blogsListForDB);
			// ��ListView��������
			listView.setAdapter(adapter);
		}

		// �������߳�
		new Thread(new Runnable() {

			@Override

			public void run() {

				// ����ҳ��ȡ����
				ArrayList<BlogBean> blogListForNet = BlogUtil.getBlogsForNet(thisContext);

				// ����ȡ������ ͨ��handler���͵����߳�
				Message msg = Message.obtain();
				msg.obj = blogListForNet;
				handler.sendMessage(msg);

			}
		}).start();

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
