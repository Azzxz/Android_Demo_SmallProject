package xr.android_databasedemo;

/*
 * ���ݿ����ɾ�Ĳ�
 * 
 * 
 * */
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import xr.android_databasedemo.db.XrSQLiteOpenHelper;

public class MainActivity extends Activity {

	private Context mContext = MainActivity.this;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		XrSQLiteOpenHelper helper = new XrSQLiteOpenHelper(mContext);

		// �������ݿⴴ������
		helper.getReadableDatabase();

	}

}
