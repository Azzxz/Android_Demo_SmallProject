package xr.android_xmldemo.util;

import java.util.ArrayList;

import org.xmlpull.v1.XmlPullParser;

import android.content.Context;
import android.util.Xml;
import xr.android_xmldemo.bean.SmsBean;

/*
 * XML文件的解析
 * 
 * 使用Pull方式
 * */
public class XmlPullUtil {

	public static int backPull(Context context) {

		// 1、创建一个XML解析对象
		XmlPullParser xpp = Xml.newPullParser();

		ArrayList<SmsBean> arrayList = null;

		SmsBean smsBean = null;

		try {
			// 2、设置解析对象的属性 第一个参数是需要解析的文件名称 第二个参数 是文件的编码
			xpp.setInput(context.openFileInput("SmsSaveXML.xml"), "utf-8");

			// 返回节点事件的类型
			int eventType = xpp.getEventType();

			// 如果不是文件结束节点 就循环
			while (eventType != XmlPullParser.END_DOCUMENT) {
				switch (eventType) {
				// 如果是标签开始节点
				case XmlPullParser.START_TAG:
					// 开始节点 初始化节点对象
					if (xpp.getName().equals("Smss")) {
						arrayList = new ArrayList<SmsBean>();
					} else if (xpp.getName().equals("Sms")) {
						// 初始化Sms对象
						smsBean = new SmsBean();
						// 得到标签中的值
						smsBean.id = Integer.valueOf(xpp.getAttributeValue(null, "id"));
					} else if (xpp.getName().equals("num")) {
						smsBean.num = xpp.getAttributeValue(null, "num");
					} else if (xpp.getName().equals("msg")) {
						smsBean.msg = xpp.getAttributeValue(null, "msg");
					} else if (xpp.getName().equals("date")) {
						smsBean.date = xpp.getAttributeValue(null, "date");
					}
					break;

				// 如果是标签结束节点 那么将当前对象加入list集合中
				case XmlPullParser.END_TAG:
					if (xpp.getName().equals("Sms")) {
						arrayList.add(smsBean);
					}
					break;
				default:
					break;
				}
				// 获取下一个事件的节点类型
				eventType = xpp.next();
			}

			return arrayList.size();

		} catch (Exception e) {
			System.out.println(e.toString());
		}
		return 0;

	}

}
