package xr.android_xmldemo;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;
import xr.android_xmldemo.util.SmsUtil;

public class MainActivity extends Activity implements OnClickListener {

	private Button saveButton, backButton;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		saveButton = (Button) findViewById(R.id.saveButton);
		backButton = (Button) findViewById(R.id.backButton);

		saveButton.setOnClickListener(this);
		backButton.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.saveButton:
			if (!SmsUtil.SaveSms(MainActivity.this)) {
				Toast.makeText(MainActivity.this, "���ű���ɹ�", Toast.LENGTH_SHORT).show();
			} else {
				Toast.makeText(MainActivity.this, "���ű���ʧ��", Toast.LENGTH_SHORT).show();
			}
			break;

		case R.id.backButton:
			Toast.makeText(MainActivity.this, "������ʱû�п�ͨ", Toast.LENGTH_SHORT).show();
			break;
		default:
			break;
		}
	}

}
