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
		friendListView = (ListView) findViewById(R.id.lv_friend);
		currentWordText = (TextView) findViewById(R.id.tv_currentWord);
	}

	/**
	 * @Title: initAdapter
	 * @Description:绑定适配器
	 * @return: void
	 */
	private void initAdapter() {
		friendListView.setAdapter(new FriendListAdapter(this, friendData));
	}

	/**
	 * @Title: initData
	 * @Description:数据处理
	 * @return: void
	 */
	private void initData() {
		// 添加数据
		addFriendList();
		// 对汉字集合进行拼音排序
		Collections.sort(friendData);
		letterQFBar.setOnTouchLetterListener(new OnTouchLetterListener() {
			@Override
			public void onTouchLetter(String currentLetter) {
				// 根据当前触摸的字母，去集合中找那个item的首字母和letter一样，然后将对应的item放到屏幕顶端
				for (int i = 0; i < friendData.size(); i++) {
					String firstWord = friendData.get(i).getPinyin().charAt(0) + "";
					if (currentLetter.equals(firstWord)) {
						// 说明找到了，那么应该讲当前的item放到屏幕顶端
						friendListView.setSelection(i);
						// 只需要找到第一个就行
						break;
					}
				}
				// 显示当前选中字母效果
				showCurrentWord(currentLetter);
			}
		});
	}

	/**
	 * @Title: showCurrentWord
	 * @Description:滑动字母条 实时显示当前字母
	 * @param currentLetter
	 * @return: void
	 */
	protected void showCurrentWord(String currentLetter) {
		currentWordText.setVisibility(View.VISIBLE);
		currentWordText.setText(currentLetter);

		// 移除之前的所有任务
		handler.removeCallbacksAndMessages(null);

		// 进行延时
		handler.postDelayed(new Runnable() {

			@Override
			public void run() {
				currentWordText.setVisibility(View.GONE);
			}
		}, 1500);
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
		friendData.add(new FriendBean("高进"));
		friendData.add(new FriendBean("凯哥"));
		friendData.add(new FriendBean("林俊杰1"));
		friendData.add(new FriendBean("陈坤2"));
		friendData.add(new FriendBean("王二a"));
		friendData.add(new FriendBean("林俊杰a"));
		friendData.add(new FriendBean("张四"));
		friendData.add(new FriendBean("林俊杰"));
		friendData.add(new FriendBean("王二"));
		friendData.add(new FriendBean("陶光"));
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
