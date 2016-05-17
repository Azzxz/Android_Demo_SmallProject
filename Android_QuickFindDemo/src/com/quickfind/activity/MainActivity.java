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

		// 初始化View
		initView();

		// 绑定适配器
		initAdapter();

		// 数据处理
		initData();
	}

	/**
	 * @Title: initView
	 * @Description:初始化View
	 * @return: void
	 */
	private void initView() {
		letterQFBar = (QuickFindBar) findViewById(R.id.qfb_letter);
		friendList = (ListView) findViewById(R.id.lv_friend);
	}

	/**
	 * @Title: initAdapter
	 * @Description:绑定适配器
	 * @return: void
	 */
	private void initAdapter() {
		friendList.setAdapter(new FriendListAdapter(this, friendData));
	}

	/**
	 * @Title: initData
	 * @Description:数据处理
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
	 * @Description:模拟填充数据
	 * @return: void
	 */
	private void addFriendList() {
		// 虚拟数据
		friendData.add(new FriendBean("李伟"));
		friendData.add(new FriendBean("张三"));
		friendData.add(new FriendBean("阿三"));
		friendData.add(new FriendBean("阿四"));
		friendData.add(new FriendBean("段誉"));
		friendData.add(new FriendBean("段正淳"));
		friendData.add(new FriendBean("张三丰"));
		friendData.add(new FriendBean("陈坤"));
		friendData.add(new FriendBean("林俊杰1"));
		friendData.add(new FriendBean("陈坤2"));
		friendData.add(new FriendBean("王二a"));
		friendData.add(new FriendBean("林俊杰a"));
		friendData.add(new FriendBean("张四"));
		friendData.add(new FriendBean("林俊杰"));
		friendData.add(new FriendBean("王二"));
		friendData.add(new FriendBean("王二b"));
		friendData.add(new FriendBean("赵四"));
		friendData.add(new FriendBean("杨坤"));
		friendData.add(new FriendBean("赵子龙"));
		friendData.add(new FriendBean("杨坤1"));
		friendData.add(new FriendBean("李伟1"));
		friendData.add(new FriendBean("宋江"));
		friendData.add(new FriendBean("宋江1"));
		friendData.add(new FriendBean("李伟3"));
	}

}
