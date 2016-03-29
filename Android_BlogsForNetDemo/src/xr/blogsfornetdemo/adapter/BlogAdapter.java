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
 * @Description: ListView������
 * @author iamxiarui@foxmail.com
 * @date 2016��3��23�� ����10:56:36
 * 
 */
public class BlogAdapter extends BaseAdapter {

	private Context context;
	private ArrayList<BlogBean> list;

	/**
	 * Title: BlogAdapter Description: ���캯��
	 * 
	 * @param context
	 *            �����Ķ���
	 * @param list
	 *            ����
	 */
	public BlogAdapter(Context context, ArrayList<BlogBean> list) {
		this.context = context;
		this.list = list;
	}

	/*
	 * Title: getCount Description: ����ListViewչʾitem������
	 * 
	 * @return ����
	 */
	@Override
	public int getCount() {
		return list.size();
	}

	/*
	 * Title: getItem Description: �õ�ÿһ��item����
	 * 
	 * @param position �ڼ���item
	 * 
	 * @return �������
	 */
	@Override
	public Object getItem(int position) {
		return list.get(position);
	}

	/*
	 * Title: getItemId Description: ���item��ID
	 * 
	 * @param position �ڼ���item
	 * 
	 * @return item �� ID
	 */
	@Override
	public long getItemId(int position) {
		return position;
	}

	/*
	 * Title: getView Description: ����չʾView����
	 * 
	 * @param position �ڼ�����Ŀ
	 * 
	 * @param convertView �Ѿ�ʹ�õ�View
	 * 
	 * @param parent View���
	 * 
	 * @return
	 */
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		View view = null;

		// �������view���� ����
		if (convertView != null) {
			view = convertView;

		} else {
			// ��һ������ ���� �ڶ������� item�����ļ���ID ����������
			// ��layout��root(ViewGroup)��һ����Ϊcodify�ķ���ֵ,һ�㴫null
			view = View.inflate(context, R.layout.list_item, null);

			// �ڶ��ַ�ʽ�� ͨ��LayoutInflater������ת����view����
			// view =
			// LayoutInflater.from(context).inflate(R.layout.item_news_layout,
			// null);

			// �����ַ�ʽ��
			// ͨ��context��ȡϵͳ����õ�һ��LayoutInflater��ͨ��LayoutInflater��һ������ת��Ϊview����
			// LayoutInflater layoutInflater = (LayoutInflater) c
			// ontext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			// view = layoutInflater.inflate(R.layout.item_news_layout, null);
		}

		// �õ���ǰview�ϵĿؼ�����
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
