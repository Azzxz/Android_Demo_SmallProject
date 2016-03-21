package xr.android_databasedemo;

/*
 * 数据库的增删改查
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
import android.widget.Toast;
import xr.android_databasedemo.bean.InfoBean;
import xr.android_databasedemo.dao.InfoDao;
import xr.android_databasedemo.dao.InfoDao2;
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

		// 通过上下文创建一个SQLiteOpenHelper对象
		XrSQLiteOpenHelper helper = new XrSQLiteOpenHelper(mContext);

		// 初始化数据库的创建
		SQLiteDatabase db = helper.getReadableDatabase();

		addButton.setOnClickListener(this);
		delButton.setOnClickListener(this);
		updateButton.setOnClickListener(this);
		checkButton.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		// 通过上下文创建一个dao对象
		// 第一种方式 增删改查
		// InfoDao dao = new InfoDao(mContext);
		// 第二种方式增删改查
		InfoDao2 dao = new InfoDao2(mContext);
		switch (v.getId()) {
		case R.id.addButton:

			// 调用增加方法 添加数据
			InfoBean bean1 = new InfoBean();
			bean1.name = "小明";
			bean1.phone = "18800000000";
			boolean addInfo = dao.addInfo(bean1);

			InfoBean bean2 = new InfoBean();
			bean2.name = "小黄";
			bean2.phone = "16699999999";
			boolean addInfo2 = dao.addInfo(bean2);

			if (addInfo && addInfo2) {
				Toast.makeText(mContext, "添加成功", Toast.LENGTH_SHORT).show();
			} else {
				Toast.makeText(mContext, "添加失败", Toast.LENGTH_SHORT).show();
			}
			break;
		case R.id.delButton:
			// 删除数据
			int delInfo = dao.delInfo("小黄");
			Toast.makeText(mContext, "成功删除" + delInfo + "条数据", Toast.LENGTH_SHORT).show();
			break;
		case R.id.updateButton:
			// 更新数据
			InfoBean bean3 = new InfoBean();
			bean3.name = "小明";
			bean3.phone = "10000000000";
			int updateInfo = dao.updateInfo(bean3);

			Toast.makeText(mContext, "成功更新" + updateInfo + "条数据", Toast.LENGTH_SHORT).show();
			break;
		case R.id.checkButton:
			// 查找数据
			dao.checkInfo("小明");
			dao.checkInfo("小黄");
			break;
		default:
			break;
		}
	}

}
