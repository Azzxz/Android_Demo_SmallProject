package xr.android_banktransferdemo;

/*
 * 数据库中事务的使用
 * 
 * 事务 要么同时成功 要么同时失败   
 * */
import android.app.Activity;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import xr.android_banktransferdemo.db.BankSQLiteOpenHelper;

public class MainActivity extends Activity {

	private Button tranButton;

	private Context thisContext = MainActivity.this;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		tranButton = (Button) findViewById(R.id.tranButton);

		tranButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				// 创建数据库帮助类对象 并通过帮助类对象 创建数据库
				BankSQLiteOpenHelper bankSQLiteOpenHelper = new BankSQLiteOpenHelper(thisContext);
				SQLiteDatabase db = bankSQLiteOpenHelper.getReadableDatabase();

				// 事务代码块
				db.beginTransaction(); // 开启一个事务
				try {
					// 模拟转账的过程
					db.execSQL("update account set money= money-200 where name=?", new String[] { "小黄" });
					// int i = 100 / 0;// 模拟一个异常
					db.execSQL("update account set money= money+200 where name=?", new String[] { "小明" });
					db.setTransactionSuccessful();// 标记事务中的sql语句全部成功执行
				} finally {
					// 判断事务的标记是否成功，如果不成功，回滚错误之前执行的sql语句
					db.endTransaction();
				}
			}
		});
	}

}
