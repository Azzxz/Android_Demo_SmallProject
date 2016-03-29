package xr.blogsfornetdemo.adapter;

import java.util.ArrayList;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import xr.blogsfornetd.R;
import xr.blogsfornetdemo.bean.BlogBean;
import xr.blogsfornetdemo.view.UrlToPicImageView;

/**
 * @ClassName: BlogAdapter
 * @Description: ListView适配器
 * @author iamxiarui@foxmail.com
 * @date 2016年3月23日 上午10:56:36
 * 
 */
public class BlogAdapter extends BaseAdapter {

	private Context context;
	private ArrayList<BlogBean> list;

	/**
	 * Title: BlogAdapter Description: 构造函数
	 * 
	 * @param context
	 *            上下文对象
	 * @param list
	 *            集合
	 */
	public BlogAdapter(Context context, ArrayList<BlogBean> list) {
		this.context = context;
		this.list = list;
	}

	/*
	 * Title: getCount Description: 给定ListView展示item的数量
	 * 
	 * @return 数量
	 */
	@Override
	public int getCount() {
		return list.size();
	}

	/*
	 * Title: getItem Description: 得到每一个item对象
	 * 
	 * @param position 第几个item
	 * 
	 * @return 任意对象
	 */
	@Override
	public Object getItem(int position) {
		return list.get(position);
	}

	/*
	 * Title: getItemId Description: 获得item的ID
	 * 
	 * @param position 第几个item
	 * 
	 * @return item 的 ID
	 */
	@Override
	public long getItemId(int position) {
		return position;
	}

	/*
	 * Title: getView Description: 设置展示View内容
	 * 
	 * @param position 第几个条目
	 * 
	 * @param convertView 已经使用的View
	 * 
	 * @param parent View组合
	 * 
	 * @return
	 */
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		View view = null;

		// 如果已有view对象 则复用
		if (convertView != null) {
			view = convertView;

		} else {
			// 第一个参数 下文 第二个参数 item布局文件的ID 第三个参数
			// 将layout用root(ViewGroup)包一层作为codify的返回值,一般传null
			view = View.inflate(context, R.layout.list_item, null);

			// 第二种方式： 通过LayoutInflater将布局转换成view对象
			// view =
			// LayoutInflater.from(context).inflate(R.layout.item_news_layout,
			// null);

			// 第三种方式：
			// 通过context获取系统服务得到一个LayoutInflater，通过LayoutInflater将一个布局转换为view对象
			// LayoutInflater layoutInflater = (LayoutInflater) c
			// ontext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			// view = layoutInflater.inflate(R.layout.item_news_layout, null);
		}

		// 得到当前view上的控件对象
		UrlToPicImageView item_pic = (UrlToPicImageView) view.findViewById(R.id.item_pic);
		TextView item_title = (TextView) view.findViewById(R.id.item_title);
		TextView item_des = (TextView) view.findViewById(R.id.item_des);

		BlogBean blog = list.get(position);

		item_title.setText(blog.title);
		item_des.setText(blog.des);
		item_pic.setImageUrl(blog.icon);

		return view;
	}

}
