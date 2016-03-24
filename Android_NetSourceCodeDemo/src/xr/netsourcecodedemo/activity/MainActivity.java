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

				String url_str = urlEText.getText().toString().trim();

				if (TextUtils.isEmpty(url_str)) {
					Toast.makeText(MainActivity.this, "不能为空", Toast.LENGTH_SHORT).show();
					return;
				}
				try {
					URL url = new URL(url_str);

					System.out.println(url.toString());
					HttpURLConnection connection = (HttpURLConnection) url.openConnection();

					connection.setRequestMethod("GET");
					connection.setConnectTimeout(5000);

					int responseCode = connection.getResponseCode();
					System.out.println(responseCode);
					if (responseCode == 200) {
						InputStream inputStream = connection.getInputStream();
						String codeResult = CodeStreamUtil.getCodeStream(inputStream);
						System.out.println(codeResult);
						sourceText.setText(codeResult);
					} else {
						sourceText.setText("请求失败");
					}

				} catch (Exception e) {
					System.out.println(e.toString());
				}
			}
		});
	}

}
