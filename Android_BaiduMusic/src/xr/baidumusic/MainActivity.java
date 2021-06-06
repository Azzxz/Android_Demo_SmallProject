package xr.baidumusic;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;

/**
 * @ClassName: MainActivity
 * @Description: ��̨�������ֹ���
 * @author: iamxiarui@foxmail.com
 * @date: 2016��5��4�� ����3:31:31
 */
public class MainActivity extends Activity implements OnClickListener {

	private Button playButton, pauseButton, nextButton;
	private static SeekBar musicSeekBar;
	private IMusicService iMusicService;
	private MyServiceConnection myServiceConnection;
	private Context thisContext = MainActivity.this;

	public static Handler handler = new Handler() {
		public void handleMessage(android.os.Message msg) {
			Bundle bundle = msg.getData();
			int duration = bundle.getInt("duration");
			int currentPosition = bundle.getInt("currentPosition");
			musicSeekBar.setMax(duration);
			musicSeekBar.setProgress(currentPosition);
		};
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		InitView();

		// �ȿ�������
		Intent intent = new Intent(this, MusicService.class);
		startService(intent);
		myServiceConnection = new MyServiceConnection();
		// �󶨷���
		bindService(intent, myServiceConnection, BIND_AUTO_CREATE);

	}

	/**
	 * @Title: InitView
	 * @Description:��ʼ��View ���󶨼����¼�
	 * @return: void
	 */
	private void InitView() {
		playButton = (Button) findViewById(R.id.button_play);
		pauseButton = (Button) findViewById(R.id.button_pause);
		nextButton = (Button) findViewById(R.id.button_continue);
		musicSeekBar = (SeekBar) findViewById(R.id.seekBar_music);

		playButton.setOnClickListener(this);
		pauseButton.setOnClickListener(this);
		nextButton.setOnClickListener(this);
		musicSeekBar.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {

			@Override
			public void onStopTrackingTouch(SeekBar seekBar) {
				// ���ò��ŵ�λ��
				iMusicService.toSeekToPosition(seekBar.getProgress());
			}

			@Override
			public void onStartTrackingTouch(SeekBar seekBar) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
				// TODO Auto-generated method stub

			}
		});
	}

	/* ��ť�¼� */
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.button_play:
			iMusicService.toPlayMusic();
			break;
		case R.id.button_pause:
			iMusicService.toPauseMusic();
			break;
		case R.id.button_continue:
			iMusicService.toContinueMusic();
			break;
		}
	}

	/* ��������� ����ʵ�����е��������� */
	private class MyServiceConnection implements ServiceConnection {

		@Override
		public void onServiceConnected(ComponentName name, IBinder service) {
			// ��ȡ�Զ���IBinder����
			iMusicService = (IMusicService) service;
		}

		@Override
		public void onServiceDisconnected(ComponentName name) {

		}
	}

	/* Activity����ʱ ����ҲҪ������� */
	@Override
	protected void onDestroy() {
		unbindService(myServiceConnection);
		super.onDestroy();
	}

}
