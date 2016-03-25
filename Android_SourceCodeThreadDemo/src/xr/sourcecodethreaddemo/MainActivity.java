package xr.sourcecodethreaddemo;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import xr.sourcecodethreaddemo.utils.CodeStreamUtil;

/**
 * @ClassName: MainActivity
 * @Description: ����û�������ַ��Դ���룬���̵߳�ʹ�� Handler��ʹ��
 * @author iamxiarui@foxmail.com
 * @date 2016��3��24�� ����5:10:37
 * 
 */
public class MainActivity extends Activity implements OnClickListener {

	private EditText urlEText;
	private Button lookButton;
	private TextView codeText;
	// ����һ��Handler����
	private Handler handler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			// ��ȡ���͵���Ϣ ������UI
			String result = msg.obj.toString();
			codeText.setText(result);
		}
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		urlEText = (EditText) findViewById(R.id.urlEText);
		lookButton = (Button) findViewById(R.id.lookButton);
		codeText = (TextView) findViewById(R.id.codeText);

		lookButton.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		// ����û�������ַ
		final String url_str = urlEText.getText().toString().trim();

		// ������ַ����Ϊ��
		if (TextUtils.isEmpty(url_str)) {
			Toast.makeText(MainActivity.this, "����Ϊ��", Toast.LENGTH_SHORT).show();
			return;
		}
		// �������߳�
		new Thread(new Runnable() {

			@Override
			public void run() {
				// ֻ�������߳�����������
				try {
					// ���û�������ַת��Ϊ url����
					URL url = new URL(url_str);
					// �õ�һ���������Ӷ���
					HttpURLConnection connection = (HttpURLConnection) url.openConnection();
					// �����������ֹ����ΪGet
					connection.setRequestMethod("GET");
					// ��ʱʱ������Ϊ5s
					connection.setConnectTimeout(5000);

					// �õ��������󷵻���
					int responseCode = connection.getResponseCode();
					// �����������200 ������ɹ�
					if (responseCode == 200) {
						// �õ����ص�������
						InputStream inputStream = connection.getInputStream();
						// ��������ת��Ϊ�ַ���
						String codeResult = CodeStreamUtil.getCodeStream(inputStream);
						// ʹ��Message���� ��msgЯ�������Ϣ ��ͨ��handler����
						Message msg = new Message();
						msg.obj = codeResult;
						handler.sendMessage(msg);
					} else {
						Message msg = new Message();
						msg.obj = "����ʧ�ܣ���������";
						handler.sendMessage(msg);
					}

				} catch (Exception e) {
					System.out.println(e.toString());
				}

			}
		}).start();
	}
}
