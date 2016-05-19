package com.quickfind.adapter;

import java.util.ArrayList;

import com.quickfind.R;
import com.quickfind.bean.FriendBean;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListAdapter;
import android.widget.TextView;

/**
 * @ClassName: FriendListAdapter
 * @Description:好友列表适配器
 * @author: iamxiarui@foxmail.com
 * @date: 2016年5月17日 下午9:29:26
 */
public class FriendListAdapter extends BaseAdapter implements ListAdapter {
	private Context context;
	private ArrayList<FriendBean> list;

	public FriendListAdapter(Context context, ArrayList<FriendBean> list) {
		this.context = context;
		this.list = list;
	}

	@Override
	public int getCount() {

		return list.size();
	}

	@Override
	public Object getItem(int position) {

		return list.get(position);
	}

	@Override
	public long getItemId(int position) {

		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		if (convertView == null) {
			convertView = View.inflate(context, R.layout.list_friend, null);
		}

		// 得到一个ViewHolder
		ViewHolder holder = ViewHolder.getHolder(convertView);
		FriendBean friend = list.get(position);
		holder.nameText.setText(friend.getName());
		// 填充拼音首字母
		String currentpinyinFirstWord = friend.getPinyin().charAt(0) + "";
		// 当前的条目不是第一个
		if (position > 0) {
			// 找到上一个的首字母
			String lastpinyinFirstWord = list.get(position - 1).getPinyin().charAt(0) + "";
			if (currentpinyinFirstWord.equals(lastpinyinFirstWord)) {
				// 设置条目不可见
				holder.firstWordText.setVisibility(View.GONE);
			} else {
				// 设置条目可见
				holder.firstWordText.setVisibility(View.VISIBLE);
				holder.firstWordText.setText(currentpinyinFirstWord);
			}

		} else {
			holder.firstWordText.setVisibility(View.VISIBLE);
			holder.firstWordText.setText(currentpinyinFirstWord);
		}
		return convertView;
	}

	// 创建一个ViewHolder
	static class ViewHolder {
		TextView firstWordText, nameText;

		// 在构造函数中 初始化其他View
		public ViewHolder(View convertView) {
			nameText = (TextView) convertView.findViewById(R.id.tv_name);
			firstWordText = (TextView) convertView.findViewById(R.id.tv_firstword);
		}

		// 得到一个ViewHolder
		public static ViewHolder getHolder(View convertView) {
			// 先判断有没有
			ViewHolder holder = (ViewHolder) convertView.getTag();
			if (holder == null) {
				// 没有的话new一个
				holder = new ViewHolder(convertView);
				convertView.setTag(holder);
			}
			return holder;
		}
	}

}
