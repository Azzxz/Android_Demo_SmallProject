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
 * @Description:�����б�������
 * @author: iamxiarui@foxmail.com
 * @date: 2016��5��17�� ����9:29:26
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

		// �õ�һ��ViewHolder
		ViewHolder holder = ViewHolder.getHolder(convertView);
		FriendBean friend = list.get(position);
		holder.nameText.setText(friend.getName());
		// ���ƴ������ĸ
		String currentpinyinFirstWord = friend.getPinyin().charAt(0) + "";
		// ��ǰ����Ŀ���ǵ�һ��
		if (position > 0) {
			// �ҵ���һ��������ĸ
			String lastpinyinFirstWord = list.get(position - 1).getPinyin().charAt(0) + "";
			if (currentpinyinFirstWord.equals(lastpinyinFirstWord)) {
				// ������Ŀ���ɼ�
				holder.firstWordText.setVisibility(View.GONE);
			} else {
				// ������Ŀ�ɼ�
				holder.firstWordText.setVisibility(View.VISIBLE);
				holder.firstWordText.setText(currentpinyinFirstWord);
			}

		} else {
			holder.firstWordText.setVisibility(View.VISIBLE);
			holder.firstWordText.setText(currentpinyinFirstWord);
		}
		return convertView;
	}

	// ����һ��ViewHolder
	static class ViewHolder {
		TextView firstWordText, nameText;

		// �ڹ��캯���� ��ʼ������View
		public ViewHolder(View convertView) {
			nameText = (TextView) convertView.findViewById(R.id.tv_name);
			firstWordText = (TextView) convertView.findViewById(R.id.tv_firstword);
		}

		// �õ�һ��ViewHolder
		public static ViewHolder getHolder(View convertView) {
			// ���ж���û��
			ViewHolder holder = (ViewHolder) convertView.getTag();
			if (holder == null) {
				// û�еĻ�newһ��
				holder = new ViewHolder(convertView);
				convertView.setTag(holder);
			}
			return holder;
		}
	}

}
