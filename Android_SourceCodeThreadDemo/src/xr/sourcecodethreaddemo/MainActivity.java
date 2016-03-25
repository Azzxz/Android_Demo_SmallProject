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
 * @Description: 获得用户输入网址的源代码，子线程的使用 Handler的使用
 * @author iamxiarui@foxmail.com
 * @date 2016年3月24日 下午5:10:37
 * 
 */
public class MainActivity extends Activity implements OnClickListener {

	private EditText urlEText;
	private Button lookButton;
	private TextView codeText;
	// 创建一个Handler对象
	private Handler handler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			// 获取发送的信息 并更新UI
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
		// 获得用户输入网址
		final String url_str = urlEText.getText().toString().trim();

		// 输入网址不能为空
		if (TextUtils.isEmpty(url_str)) {
			Toast.makeText(MainActivity.this, "不能为空", Toast.LENGTH_SHORT).show();
			return;
		}
		// 开启子线程
		new Thread(new Runnable() {

			@Override
			public void run() {
				// 只能在子线程中请求网络
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
						// 使用Message对象 让msg携带结果信息 并通过handler发送
						Message msg = new Message();
						msg.obj = codeResult;
						handler.sendMessage(msg);
					} else {
						Message msg = new Message();
						msg.obj = "请求失败，请检查网络";
						handler.sendMessage(msg);
					}

				} catch (Exception e) {
					System.out.println(e.toString());
				}

			}
		}).start();
	}
}
