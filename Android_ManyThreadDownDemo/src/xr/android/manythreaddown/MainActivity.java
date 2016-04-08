package xr.android.manythreaddown;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.RandomAccessFile;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

/**
 * @ClassName: MainActivity
 * @Description:���߳����ذ�׿�ͻ���
 * @author iamxiarui@foxmail.com
 * @date 2016��4��7�� ����11:11:24
 * 
 */
public class MainActivity extends Activity implements OnClickListener {

	private Context thisContext = MainActivity.this;

	private Button downButton, pauseButton;
	private EditText threadCountText;
	private LinearLayout progressBarLayout;
	private ProgressBar progressBar;

	private String DOWN_PATH = "http://172.25.10.172:8080/Web_UploadExeDemo/testApp.exe";

	//ÿ���߳������ļ��Ĵ�С
	private int BLOCK_SIZE = 0;
	//�������е��߳�����
	private int RUN_COUNT = 0;
	//�߳���
	private int THREAD_COUNT = 0;
	
	//�����������ݼ���
	private Map <Integer,ProgressBar> map = new HashMap<Integer,ProgressBar> ();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		downButton = (Button) findViewById(R.id.downButton);
		pauseButton = (Button) findViewById(R.id.pauseButton);
		threadCountText = (EditText) findViewById(R.id.threadCountText);

		downButton.setOnClickListener(this);
		pauseButton.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {

		// �õ��̵߳�����
		String threadCount_str = threadCountText.getText().toString().trim();
		THREAD_COUNT = Integer.parseInt(threadCount_str);
		// ��ʼ�������������ļ�
		progressBarLayout = (LinearLayout) findViewById(R.id.progressBarLayout);

		switch (v.getId()) {
		case R.id.downButton:
			// ������Ѿ����ڵĽ�����
		    progressBarLayout.removeAllViews();
			for (int i = 0; i < THREAD_COUNT; i++) {
				// ���ν����������벼����
				progressBar = (ProgressBar) View.inflate(thisContext, R.layout.progress_bar_layout, null);
				//�����������뼯����
				map.put(i, progressBar);
				progressBarLayout.addView(progressBar);
			}

			// �����߳�����
			new Thread(new Runnable() {

				@Override
				public void run() {
					downFile();
				}
			}).start();

			break;
		case R.id.pauseButton:
			Toast.makeText(thisContext, "������ʱ�޷�ʹ��", Toast.LENGTH_SHORT).show();
			break;
		default:
			break;
		}

	}

	/**
	* @Title: downFile
	* @Description:���ط�������Ҫ������Ԥ���ռ�ͷ����߳����ش�С
	* @param 
	* @return void
	* @throws
	*/
	public void downFile() {
		try {
			URL url = new URL(DOWN_PATH);
			HttpURLConnection openConnection = (HttpURLConnection) url.openConnection();
			openConnection.setRequestMethod("GET");
			openConnection.setConnectTimeout(5 * 1000);

			int responseCode = openConnection.getResponseCode();

			if (responseCode == 200) {
				// �õ��ļ��ĳ���
				int fileLength = openConnection.getContentLength();
				RandomAccessFile randomAccessFile = new RandomAccessFile(new File(getFileName(DOWN_PATH)), "rw");
				// �����ص��ļ�Ԥ���ռ�
				randomAccessFile.setLength(fileLength);

				// ÿ���߳��������صĴ�СΪ �ļ���С ���� �߳�����
				BLOCK_SIZE = fileLength / THREAD_COUNT;

				// ѭ�������߳�
				for (int THREAD_ID = 0; THREAD_ID < THREAD_COUNT; THREAD_ID++) {
					// �߳�������ʼλ��
					int START_INDEX = THREAD_ID * BLOCK_SIZE;
					// �߳����ؽ���λ��
					int END_INDEX = (THREAD_ID + 1) * BLOCK_SIZE - 1;

					// ��������һ���߳�
					if (THREAD_ID == THREAD_COUNT - 1) {
						// ����λ��Ϊ�ļ���С-1
						END_INDEX = fileLength - 1;
					}

					// �����߳�����
					new UploadThread(THREAD_ID, START_INDEX, END_INDEX).start();
				}

			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/** 
	* @ClassName: UploadThread 
	* @Description:ÿ���߳����صķ���
	* @author iamxiarui@foxmail.com
	* @date 2016��4��7�� ����11:13:38 
	*  
	*/ 
	public class UploadThread extends Thread {

		private int threadId;
		private int startIndex;
		private int endIndex;
		// �ϴ��ļ����ص�λ��
		private int lastPostion;
		
		//��ǰ�߳��ܹ����ص����ݽ���
		private int currentThreadTotalProgress;

		public UploadThread(int threadId, int startIndex, int endIndex) {
			this.threadId = threadId;
			this.startIndex = startIndex;
			this.endIndex = endIndex;
			this.currentThreadTotalProgress = endIndex -startIndex +1;
		}

		@Override
		public void run() {
			
			//��ȡ��ǰ�̶߳�ӦProgressBar
			ProgressBar progressBar = map.get(threadId);

			// ͬ������� ֻҪ���� �߳�����һ
			synchronized (UploadThread.class) {
				RUN_COUNT = RUN_COUNT + 1;
			}

			try {
				URL url = new URL(DOWN_PATH);

				HttpURLConnection openConnection = (HttpURLConnection) url.openConnection();
				openConnection.setRequestMethod("GET");
				openConnection.setConnectTimeout(5 * 1000);

				System.out.println("���������أ�   �̣߳� " + threadId + ",��ʼλ�ã� " + startIndex + ",����λ�ã�" + endIndex);

				// ��ȡ��һ�����ؽ�����λ�ã���������������ǰλ������ ��������ӿ�ʼ����
				File fileLast = new File(getFilePath() + threadId + ".txt");
				if (fileLast.exists()) {
					BufferedReader bufferedReader = new BufferedReader(
							new InputStreamReader(new FileInputStream(fileLast)));
					// ��ȡ�ļ��ϴ�����λ��
					String lastPostion_str = bufferedReader.readLine();
					lastPostion = Integer.parseInt(lastPostion_str);
					openConnection.setRequestProperty("Range", "bytes:" + lastPostion + "-" + endIndex);
					System.out.println("ʵ�����أ�  �̣߳�" + threadId + "����ʼλ�ã�" + lastPostion + ";����λ��:" + endIndex);
					bufferedReader.close();
				} else {
					// ������״����� ��ô �ϴ�����λ�� ���ǿ�ʼλ��
					lastPostion = startIndex;
					openConnection.setRequestProperty("Range", "bytes:" + lastPostion + "-" + endIndex);
					System.out.println("ʵ�����أ�  �̣߳�" + threadId + "����ʼλ�ã�" + lastPostion + ";����λ��:" + endIndex);
				}

				// 200 Ϊȫ����Դ������Ϣ 206 Ϊ������Դ������Ϣ
				if (openConnection.getResponseCode() == 206) {
					InputStream inputStream = openConnection.getInputStream();
					RandomAccessFile randomAccessFile = new RandomAccessFile(new File(getFileName(DOWN_PATH)), "rw");
					// ���ļ���ƫ������ʼ�洢
					randomAccessFile.seek(lastPostion);

					byte[] buf = new byte[512];
					int length = -1;
					int totalFile = 0;
					while ((length = inputStream.read(buf)) != -1) {
						randomAccessFile.write(buf, 0, length);

						totalFile = totalFile + length;
						// ȥ���浱ǰ�߳����ص�λ�ã����浽�ļ���
						int currentThreadPostion = lastPostion + totalFile;// �������ǰ�̱߳������ص�λ��
						// ��������ļ����浱ǰ�߳����ص�λ��
						File filePause = new File(getFilePath() + threadId + ".txt");
						RandomAccessFile accessfile = new RandomAccessFile(filePause, "rwd");
						accessfile.write(String.valueOf(currentThreadPostion).getBytes());
						accessfile.close();
						
						//�����߳����صĽ��Ȳ����ý���
						int currentprogress = currentThreadPostion -startIndex;
						progressBar.setMax(currentThreadTotalProgress);//���ý����������ֵ
						progressBar.setProgress(currentprogress);//���ý�������ǰ����
					}

					inputStream.close();
					randomAccessFile.close();

					System.out.println("�̣߳� " + threadId + ",�������");

					// ͬ������� ����߳�������� ɾ������Ԥ�����ļ�
					synchronized (UploadThread.class) {
						RUN_COUNT = RUN_COUNT - 1;
						
						//������� ����UI
						runOnUiThread(new Runnable() {
							@Override
							public void run() {
								Toast.makeText(thisContext, "�������", 0).show();
							}
						});
						
						if (RUN_COUNT == 0) {
							System.out.println("�����߳��������");
							for (int i = 0; i < THREAD_COUNT; i++) {
								File fileDelete = new File(getFilePath() + i + ".txt");
								fileDelete.delete();
							}
						}

					}
				}

			} catch (Exception e) {
				e.printStackTrace();
			}
			super.run();
		}

	}

	/**
	* @Title: getFileName
	* @Description:�õ��ļ���·������
	* @param @param url
	* @param @return
	* @return String
	* @throws
	*/
	public String getFileName(String url) {

		//lastIndexOf : ����ָ�����ַ����ڴ��ַ��������ұ߳��ִ���������
		//Environment.getExternalStorageDirectory() �õ�SD��λ��
		return Environment.getExternalStorageDirectory() + "/" + url.substring(url.lastIndexOf("/"));

	}

	/**
	* @Title: getFilePath
	* @Description:�õ��ļ�·��
	* @param @return
	* @return String
	* @throws
	*/
	public String getFilePath() {

		return Environment.getExternalStorageDirectory() + "/";
	}

}
