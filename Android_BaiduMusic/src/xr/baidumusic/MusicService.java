package xr.baidumusic;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.widget.Toast;

/**
 * @Description:音乐服务
 */
public class MusicService extends Service {

	/* 实现onBind方法 返回自定义IBinder对象 */
	@Override
	public IBinder onBind(Intent intent) {
		return new MyBinder();
	}

	@Override
	public void onCreate() {
		super.onCreate();
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
	}

	/* 服务中的具体方法 */
	public void playMusic(Context context) {
		Toast.makeText(context, "鎾斁", Toast.LENGTH_SHORT).show();
	}

	/* 服务中的具体方法 */
	public void pauseMusic(Context context) {
		Toast.makeText(context, "鏆傚仠", Toast.LENGTH_SHORT).show();
	}

	/* 服务中的具体方法 */
	public void nextMusic(Context context) {
		Toast.makeText(context, "涓嬩竴棣�", Toast.LENGTH_SHORT).show();
	}

	/* 自定义IBinder对象 实现服务接口中的方法 */
	private class MyBinder extends Binder implements IMusicService {

		@Override
		public void toPlayMusic(Context context) {
			playMusic(context);
		}

		@Override
		public void toPauseMusic(Context context) {
			pauseMusic(context);
		}

		@Override
		public void toNextMusic(Context context) {
			nextMusic(context);
		}
	}

}
