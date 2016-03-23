package xr.android_dblistviewdemo.adapter;

import java.util.ArrayList;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import xr.android_dblistviewdemo.activity.R;
import xr.android_dblistviewdemo.bean.InfoBean;

public class DBListAdapter extends BaseAdapter {

	private Context context;
	private ArrayList<InfoBean> list;

	public DBListAdapter(Context context, ArrayList<InfoBean> list) {
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
		View view = null;

		if (convertView != null) {
			view = new View(context);
		} else {
			view = View.inflate(context, R.layout.item_db, null);
		}

		// 找到控件
		TextView item_id = (TextView) view.findViewById(R.id.item_id);
		TextView item_name = (TextView) view.findViewById(R.id.item_name);
		TextView item_phone = (TextView) view.findViewById(R.id.item_phone);
		// 找到内容
		InfoBean infoBean = list.get(position);

		// 设置内容
		item_id.setText(infoBean.id);
		item_name.setText(infoBean.name);
		item_phone.setText(infoBean.phone);

		return view;
	}

}
