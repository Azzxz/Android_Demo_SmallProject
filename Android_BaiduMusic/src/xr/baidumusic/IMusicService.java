package xr.baidumusic;

import android.content.Context;

/**
 * @Description:
 */
public interface IMusicService {

	public void toPlayMusic();

	public void toPauseMusic();

	public void toContinueMusic();

	public void toSeekToPosition(int position);
}
