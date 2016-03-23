package xr.android_blogdemo.adapter;

import java.util.ArrayList;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import xr.android_blogdemo.activity.R;
import xr.android_blogdemo.bean.BlogBean;

public class BlogAdapter extends BaseAdapter {

	private Context context;
	private ArrayList<BlogBean> list;

	public BlogAdapter(Context context, ArrayList<BlogBean> list) {
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
			view = convertView;

		} else {
			view = View.inflate(context, R.layout.list_item, null);
		}

		ImageView item_pic = (ImageView) view.findViewById(R.id.item_pic);
		TextView item_title = (TextView) view.findViewById(R.id.item_title);
		TextView item_des = (TextView) view.findViewById(R.id.item_des);

		BlogBean blog = list.get(position);

		item_pic.setImageDrawable(blog.pic);
		item_title.setText(blog.title);
		item_des.setText(blog.des);

		return view;
	}

}
