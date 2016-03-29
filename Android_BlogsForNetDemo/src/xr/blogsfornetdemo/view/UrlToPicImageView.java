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
* @Description:����ҳͼƬ��Url��ַת��ΪͼƬ
* @author iamxiarui@foxmail.com
* @date 2016��3��29�� ����2:10:10 
*  
*/ 
public class UrlToPicImageView extends ImageView {

	
	/** 
	* Title: UrlToPicImageView
	* Description:���췽��  ע�� �������е���ʵ�ַ�����Ҫʵ��
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

	//���߳̽������̷߳�����Bitmap���󣬲�����UI
	private Handler handler = new Handler() {

		public void handleMessage(Message msg) {

			Bitmap bitmap = (Bitmap) msg.obj;

			UrlToPicImageView.this.setImageBitmap(bitmap);

		};
	};

	/**
	* @Title: setImageUrl
	* @Description:��Ҫʵ�ַ���
	* @param @param url_str
	* @return void
	* @throws
	*/
	public void setImageUrl(final String url_str) {
		//�������߳� 
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

						//����ת��ΪBitmap���� 
						Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
						//���͵����߳�
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
