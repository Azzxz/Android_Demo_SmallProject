package xr.baidumusic;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

/*开启服务,后台运行,实现播放暂停下一首功能*/
public class MainActivity extends Activity implements OnClickListener {

    private Button playButton, pauseButton, nextButton;
    private IMusicService iMusicService;
    private MyServiceConnection myServiceConnection;
    private Context thisContext = MainActivity.this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
	
        InitView();

        //先开启服务
        Intent intent = new Intent(this, MusicService.class);
        startService(intent);
        //绑定服务
        bindService(intent, myServiceConnection, BIND_AUTO_CREATE);

    }

    /*初始化View 绑定按钮监听事件*/
    private void InitView() {
        playButton = (Button) findViewById(R.id.play_button);
        pauseButton = (Button) findViewById(R.id.pause_button);
        nextButton = (Button) findViewById(R.id.next_button);

        playButton.setOnClickListener(this);
        pauseButton.setOnClickListener(this);
        nextButton.setOnClickListener(this);

        myServiceConnection = new MyServiceConnection();
    }

    /*按钮事件*/
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.play_button:
                iMusicService.toPlayMusic(thisContext);
                break;
            case R.id.pause_button:
                iMusicService.toPauseMusic(thisContext);
                break;
            case R.id.next_button:
                iMusicService.toNextMusic(thisContext);
                break;
        }
    }

    /*服务监视类 必须实现其中的两个方法*/
    private class MyServiceConnection implements ServiceConnection {

        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            //获取自定义IBinder对象
            iMusicService = (IMusicService) service;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    }

    /*Activity销毁时 服务也要解绑并销毁*/
    @Override
    protected void onDestroy() {
        unbindService(myServiceConnection);
        super.onDestroy();
    }

}
