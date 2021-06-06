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

/*��������,��̨����,ʵ�ֲ�����ͣ��һ�׹���*/
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

        //�ȿ�������
        Intent intent = new Intent(this, MusicService.class);
        startService(intent);
        //�󶨷���
        bindService(intent, myServiceConnection, BIND_AUTO_CREATE);

    }

    /*��ʼ��View �󶨰�ť�����¼�*/
    private void InitView() {
        playButton = (Button) findViewById(R.id.play_button);
        pauseButton = (Button) findViewById(R.id.pause_button);
        nextButton = (Button) findViewById(R.id.next_button);

        playButton.setOnClickListener(this);
        pauseButton.setOnClickListener(this);
        nextButton.setOnClickListener(this);

        myServiceConnection = new MyServiceConnection();
    }

    /*��ť�¼�*/
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

    /*��������� ����ʵ�����е���������*/
    private class MyServiceConnection implements ServiceConnection {

        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            //��ȡ�Զ���IBinder����
            iMusicService = (IMusicService) service;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    }

    /*Activity����ʱ ����ҲҪ�������*/
    @Override
    protected void onDestroy() {
        unbindService(myServiceConnection);
        super.onDestroy();
    }

}
