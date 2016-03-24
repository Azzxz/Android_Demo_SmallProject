package xr.netpicturethreaddemo;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
import xr.netpicturethreaddemo.utils.CodeStreamUtil;

/**
 * @ClassName: MainActivity
 * @Description: 获得用户输入网址的图片，子线程的使用 Handler的使用
 * @author iamxiarui@foxmail.com
 * @date 2016年3月24日 下午5:10:37
 * 
 */
public class MainActivity extends Activity implements OnClickListener {

	private EditText urlEText;
	private Button lookButton;
	private ImageView picImage;
	// 创建一个Handler对象
	private Handler handler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			// 主线程获取发送的图片信息 并更新UI
			Bitmap bitmap = (Bitmap) msg.obj;
			picImage.setImageBitmap(bitmap);
		}
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		urlEText = (EditText) findViewById(R.id.urlEText);
		lookButton = (Button) findViewById(R.id.lookButton);
		picImage = (ImageView) findViewById(R.id.picImage);

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
				// 子线程请求网络
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
						// 获得bitmap对象
						Bitmap bitmap = BitmapFactory.decodeStream(inputStream);

						// 使用Message对象 让msg携带结果信息 并通过handler发送
						// obtain() 方法 如果内存中存在message对象 则不创建 如果没有 则创建
						Message msg = Message.obtain();
						msg.obj = bitmap;
						handler.sendMessage(msg);
					} else {
						Toast.makeText(MainActivity.this, "请求失败", Toast.LENGTH_SHORT).show();
					}

				} catch (Exception e) {
					e.printStackTrace();
				}

			}
		}).start();
	}
}
