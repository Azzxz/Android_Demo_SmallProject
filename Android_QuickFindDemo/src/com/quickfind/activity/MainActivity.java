package com.quickfind.activity;

import java.util.ArrayList;
import java.util.Collections;

import com.quickfind.R;
import com.quickfind.adapter.FriendListAdapter;
import com.quickfind.bean.FriendBean;
import com.quickfind.view.QuickFindBar;
import com.quickfind.view.QuickFindBar.OnTouchLetterListener;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends Activity {
	private QuickFindBar letterQFBar;
	private ListView friendListView;
	private TextView currentWordText;
	private ArrayList<FriendBean> friendData = new ArrayList<FriendBean>();
	private Handler handler = new Handler();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		// ��ʼ��View
		initView();

		// ��������
		initAdapter();

		// ���ݴ���
		initData();
	}

	/**
	 * @Title: initView
	 * @Description:��ʼ��View
	 * @return: void
	 */
	private void initView() {
		letterQFBar = (QuickFindBar) findViewById(R.id.qfb_letter);
		friendListView = (ListView) findViewById(R.id.lv_friend);
		currentWordText = (TextView) findViewById(R.id.tv_currentWord);
	}

	/**
	 * @Title: initAdapter
	 * @Description:��������
	 * @return: void
	 */
	private void initAdapter() {
		friendListView.setAdapter(new FriendListAdapter(this, friendData));
	}

	/**
	 * @Title: initData
	 * @Description:���ݴ���
	 * @return: void
	 */
	private void initData() {
		// �������
		addFriendList();
		// �Ժ��ּ��Ͻ���ƴ������
		Collections.sort(friendData);
		letterQFBar.setOnTouchLetterListener(new OnTouchLetterListener() {
			@Override
			public void onTouchLetter(String currentLetter) {
				// ���ݵ�ǰ��������ĸ��ȥ���������Ǹ�item������ĸ��letterһ����Ȼ�󽫶�Ӧ��item�ŵ���Ļ����
				for (int i = 0; i < friendData.size(); i++) {
					String firstWord = friendData.get(i).getPinyin().charAt(0) + "";
					if (currentLetter.equals(firstWord)) {
						// ˵���ҵ��ˣ���ôӦ�ý���ǰ��item�ŵ���Ļ����
						friendListView.setSelection(i);
						// ֻ��Ҫ�ҵ���һ������
						break;
					}
				}
				// ��ʾ��ǰѡ����ĸЧ��
				showCurrentWord(currentLetter);
			}
		});
	}

	/**
	 * @Title: showCurrentWord
	 * @Description:������ĸ�� ʵʱ��ʾ��ǰ��ĸ
	 * @param currentLetter
	 * @return: void
	 */
	protected void showCurrentWord(String currentLetter) {
		currentWordText.setVisibility(View.VISIBLE);
		currentWordText.setText(currentLetter);

		// �Ƴ�֮ǰ����������
		handler.removeCallbacksAndMessages(null);

		// ������ʱ
		handler.postDelayed(new Runnable() {

			@Override
			public void run() {
				currentWordText.setVisibility(View.GONE);
			}
		}, 1500);
	}

	/**
	 * @Title: addFriendList
	 * @Description:ģ���������
	 * @return: void
	 */
	private void addFriendList() {
		// ��������
		friendData.add(new FriendBean("��ΰ"));
		friendData.add(new FriendBean("����"));
		friendData.add(new FriendBean("����"));
		friendData.add(new FriendBean("����"));
		friendData.add(new FriendBean("����"));
		friendData.add(new FriendBean("������"));
		friendData.add(new FriendBean("������"));
		friendData.add(new FriendBean("�߽�"));
		friendData.add(new FriendBean("����"));
		friendData.add(new FriendBean("�ֿ���1"));
		friendData.add(new FriendBean("����2"));
		friendData.add(new FriendBean("����a"));
		friendData.add(new FriendBean("�ֿ���a"));
		friendData.add(new FriendBean("����"));
		friendData.add(new FriendBean("�ֿ���"));
		friendData.add(new FriendBean("����"));
		friendData.add(new FriendBean("�չ�"));
		friendData.add(new FriendBean("����b"));
		friendData.add(new FriendBean("����"));
		friendData.add(new FriendBean("����"));
		friendData.add(new FriendBean("������"));
		friendData.add(new FriendBean("����1"));
		friendData.add(new FriendBean("��ΰ1"));
		friendData.add(new FriendBean("�ν�"));
		friendData.add(new FriendBean("�ν�1"));
		friendData.add(new FriendBean("��ΰ3"));
	}

}
