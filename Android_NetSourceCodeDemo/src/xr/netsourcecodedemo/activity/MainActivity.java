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
 * @Description: 获得用户输入网址的源代码
 * @author iamxiarui@foxmail.com
 * @date 2016年3月24日 下午5:10:37
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

				// 获得用户输入网址
				String url_str = urlEText.getText().toString().trim();

				// 输入网址不能为空
				if (TextUtils.isEmpty(url_str)) {
					Toast.makeText(MainActivity.this, "不能为空", Toast.LENGTH_SHORT).show();
					return;
				}
				// 请求网络
				try {
					// 将用户输入网址转换为 url对象
					URL url = new URL(url_str);
					// 得到一个网络连接对象
					HttpURLConnection connection = (HttpURLConnection) url.openConnection();
					// 将网络请求防止设置为Get
					connection.setRequestMethod("GET");
					// 超时时间设置为5s
					connection.setConnectTimeout(5000);

					// 得到网络请求返回码
					int responseCode = connection.getResponseCode();
					// 如果返回码是200 则请求成功
					if (responseCode == 200) {
						// 得到返回的流对象
						InputStream inputStream = connection.getInputStream();
						// 将流对象转换为字符串
						String codeResult = CodeStreamUtil.getCodeStream(inputStream);
						// 显示在主UI中
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
