package xr.android_dblistviewdemo.activity;

import java.util.ArrayList;

/*
 * ���ݿ����ɾ�Ĳ�
 * 
 * 
 * */
import android.app.Activity;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;
import xr.android_dblistviewdemo.adapter.DBListAdapter;
import xr.android_dblistviewdemo.bean.InfoBean;
import xr.android_dblistviewdemo.dao.InfoDao2;
import xr.android_dblistviewdemo.db.XrSQLiteOpenHelper;

public class MainActivity extends Activity implements OnClickListener {

	private Context mContext = MainActivity.this;

	private Button addButton, delButton, updateButton, checkButton;

	private ListView dblistView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		addButton = (Button) findViewById(R.id.addButton);
		delButton = (Button) findViewById(R.id.delButton);
		updateButton = (Button) findViewById(R.id.updateButton);
		checkButton = (Button) findViewById(R.id.checkButton);

		dblistView = (ListView) findViewById(R.id.dblistView);

		// ͨ�������Ĵ���һ��SQLiteOpenHelper����
		XrSQLiteOpenHelper helper = new XrSQLiteOpenHelper(mContext);

		// ��ʼ�����ݿ�Ĵ���
		SQLiteDatabase db = helper.getReadableDatabase();

		addButton.setOnClickListener(this);
		delButton.setOnClickListener(this);
		updateButton.setOnClickListener(this);
		checkButton.setOnClickListener(this);

	}

	@Override
	public void onClick(View v) {
		// ͨ�������Ĵ���һ��dao����
		// ��һ�ַ�ʽ ��ɾ�Ĳ�
		// InfoDao dao = new InfoDao(mContext);
		// �ڶ��ַ�ʽ��ɾ�Ĳ�
		InfoDao2 dao = new InfoDao2(mContext);
		switch (v.getId()) {
		case R.id.addButton:

			// �������ӷ��� �������
			InfoBean bean1 = new InfoBean();
			bean1.name = "С��";
			bean1.phone = "18800000000";
			boolean addInfo = dao.addInfo(bean1);

			InfoBean bean2 = new InfoBean();
			bean2.name = "С��";
			bean2.phone = "16699999999";
			boolean addInfo2 = dao.addInfo(bean2);

			if (addInfo && addInfo2) {
				Toast.makeText(mContext, "��ӳɹ�", Toast.LENGTH_SHORT).show();
			} else {
				Toast.makeText(mContext, "���ʧ��", Toast.LENGTH_SHORT).show();
			}
			break;
		case R.id.delButton:
			// ɾ������
			int delInfo = dao.delInfo("С��");
			Toast.makeText(mContext, "�ɹ�ɾ��" + delInfo + "������", Toast.LENGTH_SHORT).show();
			break;
		case R.id.updateButton:
			// ��������
			InfoBean bean3 = new InfoBean();
			bean3.name = "С��";
			bean3.phone = "10000000000";
			int updateInfo = dao.updateInfo(bean3);

			Toast.makeText(mContext, "�ɹ�����" + updateInfo + "������", Toast.LENGTH_SHORT).show();
			break;
		case R.id.checkButton:
			// ��������
			ArrayList<InfoBean> list = dao.checkInfo("С��");
			DBListAdapter dbListAdapter = new DBListAdapter(mContext, list);
			dblistView.setAdapter(dbListAdapter);
			break;
		default:
			break;
		}
	}

}
