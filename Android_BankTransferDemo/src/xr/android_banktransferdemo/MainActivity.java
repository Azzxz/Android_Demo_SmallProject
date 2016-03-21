package xr.android_banktransferdemo;

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
				BankSQLiteOpenHelper bankSQLiteOpenHelper = new BankSQLiteOpenHelper(thisContext);

				SQLiteDatabase db = bankSQLiteOpenHelper.getReadableDatabase();

				db.beginTransaction();
				try {
					db.execSQL("update account set money= money-200 where name=?", new String[] { "小黄" });
					//int i = 100 / 0;// 模拟一个异常
					db.execSQL("update account set money= money+200 where name=?", new String[] { "小明" });
					db.setTransactionSuccessful();// 标记事务中的sql语句全部成功执行
				} finally {
					db.endTransaction();
				}
			}
		});
	}

}
