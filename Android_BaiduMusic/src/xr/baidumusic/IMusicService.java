package xr.baidumusic;

import android.content.Context;

/**
 * @Description:服务接口
 */
public interface IMusicService {

    public void toPlayMusic(Context context);

    public void toPauseMusic(Context context);

    public void toNextMusic(Context context);
}
