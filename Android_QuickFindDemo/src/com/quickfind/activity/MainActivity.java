package com.quickfind.activity;

import java.util.ArrayList;

import com.quickfind.R;
import com.quickfind.adapter.FriendListAdapter;
import com.quickfind.bean.FriendBean;
import com.quickfind.view.QuickFindBar;
import com.quickfind.view.QuickFindBar.OnTouchLetterListener;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

public class MainActivity extends Activity {
	private QuickFindBar letterQFBar;
	private ListView friendList;
	private ArrayList<FriendBean> friendData = new ArrayList<FriendBean>();

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
		friendList = (ListView) findViewById(R.id.lv_friend);
	}

	/**
	 * @Title: initAdapter
	 * @Description:��������
	 * @return: void
	 */
	private void initAdapter() {
		friendList.setAdapter(new FriendListAdapter(this, friendData));
	}

	/**
	 * @Title: initData
	 * @Description:���ݴ���
	 * @return: void
	 */
	private void initData() {
		addFriendList();
		letterQFBar.setOnTouchLetterListener(new OnTouchLetterListener() {

			@Override
			public void onTouchLetter(String currentLetter) {
				Log.e("currentLetter", "currentLetter : " + currentLetter);
			}
		});
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
		friendData.add(new FriendBean("����"));
		friendData.add(new FriendBean("�ֿ���1"));
		friendData.add(new FriendBean("����2"));
		friendData.add(new FriendBean("����a"));
		friendData.add(new FriendBean("�ֿ���a"));
		friendData.add(new FriendBean("����"));
		friendData.add(new FriendBean("�ֿ���"));
		friendData.add(new FriendBean("����"));
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
