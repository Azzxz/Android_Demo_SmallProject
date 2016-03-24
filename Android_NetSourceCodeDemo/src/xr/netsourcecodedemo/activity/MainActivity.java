package xr.netsourcecodedemo.activity;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import android.app.Activity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import xr.newsourcecodedemo.utils.CodeStreamUtil;

/**
 * @ClassName: MainActivity
 * @Description: ����û�������ַ��Դ����
 * @author iamxiarui@foxmail.com
 * @date 2016��3��24�� ����5:10:37
 * 
 */
public class MainActivity extends Activity {

	private EditText urlEText;
	private Button lookButton;
	private TextView sourceText;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		urlEText = (EditText) findViewById(R.id.urlEText);
		lookButton = (Button) findViewById(R.id.lookButton);
		sourceText = (TextView) findViewById(R.id.sourceText);

		lookButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				// ����û�������ַ
				String url_str = urlEText.getText().toString().trim();

				// ������ַ����Ϊ��
				if (TextUtils.isEmpty(url_str)) {
					Toast.makeText(MainActivity.this, "����Ϊ��", Toast.LENGTH_SHORT).show();
					return;
				}
				// ��������
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
						// ��ʾ����UI��
						sourceText.setText(codeResult);
					} else {
						sourceText.setText("����ʧ��");
					}

				} catch (Exception e) {
					System.out.println(e.toString());
				}
			}
		});
	}

}
