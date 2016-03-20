package xr.android_databasedemo;

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
import xr.android_databasedemo.bean.InfoBean;
import xr.android_databasedemo.dao.InfoDao;
import xr.android_databasedemo.db.XrSQLiteOpenHelper;

public class MainActivity extends Activity implements OnClickListener {

	private Context mContext = MainActivity.this;

	private Button addButton, delButton, updateButton, checkButton;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		addButton = (Button) findViewById(R.id.addButton);
		delButton = (Button) findViewById(R.id.delButton);
		updateButton = (Button) findViewById(R.id.updateButton);
		checkButton = (Button) findViewById(R.id.checkButton);

		XrSQLiteOpenHelper helper = new XrSQLiteOpenHelper(mContext);
		SQLiteDatabase db = helper.getReadableDatabase();

		addButton.setOnClickListener(this);
		delButton.setOnClickListener(this);
		updateButton.setOnClickListener(this);
		checkButton.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		InfoDao dao = new InfoDao(mContext);
		switch (v.getId()) {
		case R.id.addButton:

			InfoBean bean1 = new InfoBean();
			bean1.name = "С��";
			bean1.phone = "18800000000";
			dao.addInfo(bean1);

			InfoBean bean2 = new InfoBean();
			bean2.name = "С��";
			bean2.phone = "16699999999";
			dao.addInfo(bean2);

			break;
		case R.id.delButton:
			dao.delInfo("С��");
			break;
		case R.id.updateButton:
			InfoBean bean3 = new InfoBean();
			bean3.name = "С��";
			bean3.phone = "10000000000";
			dao.updateInfo(bean3);
			break;
		case R.id.checkButton:
			dao.checkInfo("С��");
			dao.checkInfo("С��");
			break;
		default:
			break;
		}
	}

}
