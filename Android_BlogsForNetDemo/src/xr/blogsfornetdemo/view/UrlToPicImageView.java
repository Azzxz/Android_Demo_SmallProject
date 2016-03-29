package xr.blogsfornetdemo.view;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.widget.ImageView;

/** 
* @ClassName: UrlToPicImageView 
* @Description:将网页图片的Url地址转换为图片
* @author iamxiarui@foxmail.com
* @date 2016年3月29日 下午2:10:10 
*  
*/ 
public class UrlToPicImageView extends ImageView {

	
	/** 
	* Title: UrlToPicImageView
	* Description:构造方法  注意 必须所有的是实现方法都要实现
	* @param context 
	*/ 
	public UrlToPicImageView(Context context) {
		super(context);
	}

	public UrlToPicImageView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public UrlToPicImageView(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
	}

	@SuppressLint("NewApi")
	public UrlToPicImageView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
		super(context, attrs, defStyleAttr, defStyleRes);
	}

	//主线程接收子线程发来的Bitmap对象，并更新UI
	private Handler handler = new Handler() {

		public void handleMessage(Message msg) {

			Bitmap bitmap = (Bitmap) msg.obj;

			UrlToPicImageView.this.setImageBitmap(bitmap);

		};
	};

	/**
	* @Title: setImageUrl
	* @Description:主要实现方法
	* @param @param url_str
	* @return void
	* @throws
	*/
	public void setImageUrl(final String url_str) {
		//开启子线程 
		new Thread(new Runnable() {

			@Override
			public void run() {
				try {

					URL url = new URL(url_str);
					HttpURLConnection openConnection = (HttpURLConnection) url.openConnection();
					openConnection.setRequestMethod("GET");
					openConnection.setConnectTimeout(10000);

					int responseCode = openConnection.getResponseCode();

					if (responseCode == 200) {

						InputStream inputStream = openConnection.getInputStream();

						//将流转换为Bitmap对象 
						Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
						//发送到主线程
						Message msg = Message.obtain();
						msg.obj = bitmap;
						handler.sendMessage(msg);
					}

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}).start();
	}
}
