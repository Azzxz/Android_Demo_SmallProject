package xr.baidumusic;

import java.util.Timer;
import java.util.TimerTask;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.os.Binder;
import android.os.Bundle;
import android.os.Environment;
import android.os.IBinder;
import android.os.Message;

/**
 * @ClassName: MusicService
 * @Description: 后台播放音乐服务
 * @author: iamxiarui@foxmail.com
 * @date: 2016年5月4日 下午3:33:14
 */
public class MusicService extends Service {

	private MediaPlayer musicPlayer;
	private Timer timer;
	private TimerTask task;

	/* 实现onBind方法 返回自定义IBinder对象 */
	@Override
	public IBinder onBind(Intent intent) {
		return new MyBinder();
	}

	@Override
	public void onCreate() {
		// 实例化MediaPlayer对象
		musicPlayer = new MediaPlayer();
		super.onCreate();
	}

	/* 服务中的具体方法 */
	public void playMusic() {
		try {
			// 重新设置
			musicPlayer.reset();
			// 设置播放的音乐 SD卡的根目录
			musicPlayer.setDataSource(Environment.getExternalStorageDirectory().getPath() + "//xpg.mp3");
			// 准备播放
			musicPlayer.prepare();
			// 开始播放
			musicPlayer.start();

			// 更新进度条
			updateSeekBar();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/* 服务中的具体方法 */
	public void pauseMusic() {
		musicPlayer.pause();
	}

	/* 服务中的具体方法 */
	public void continueMusic() {
		musicPlayer.start();
	}

	// 设置播放音乐指定位置的方法
	public void seekToPosition(int position) {
		musicPlayer.seekTo(position);
	}

	/**
	 * @Title: updateSeekBar
	 * @Description:更新进度条
	 * @return: void
	 */
	public void updateSeekBar() {
		// 得到音频的总长度
		final int duration = musicPlayer.getDuration();
		timer = new Timer();
		task = new TimerTask() {
			@Override
			public void run() {
				// 循环获取音频当前位置
				int currentPosition = musicPlayer.getCurrentPosition();

				// 通过Message传递多个参数
				Message msg = Message.obtain();
				Bundle bundle = new Bundle();
				bundle.putInt("duration", duration);
				bundle.putInt("currentPosition", currentPosition);
				msg.setData(bundle);

				MainActivity.handler.sendMessage(msg);
			}

		};
		// 1s后开启定时器 并且每个1s循环一次
		timer.schedule(task, 300, 1000);
		// 当播放结束时 取消定时
		musicPlayer.setOnCompletionListener(new OnCompletionListener() {

			@Override
			public void onCompletion(MediaPlayer mp) {
				timer.cancel();
				task.cancel();
			}
		});

	}

	/* 自定义IBinder对象 实现服务接口中的方法 */
	private class MyBinder extends Binder implements IMusicService {

		@Override
		public void toPlayMusic() {
			playMusic();
		}

		@Override
		public void toPauseMusic() {
			pauseMusic();
		}

		@Override
		public void toContinueMusic() {
			continueMusic();
		}

		@Override
		public void toSeekToPosition(int position) {
			seekToPosition(position);
		}
	}

}
