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

		XmlPullParser xpp = Xml.newPullParser();

		ArrayList<SmsBean> arrayList = null;

		SmsBean smsBean = null;

		try {
			xpp.setInput(context.openFileInput("SmsSaveXML.xml"), "utf-8");

			int eventType = xpp.getEventType();

			while (eventType != XmlPullParser.END_DOCUMENT) {
				switch (eventType) {
				case XmlPullParser.START_TAG:
					if (xpp.getName().equals("Smss")) {
						arrayList = new ArrayList<SmsBean>();

					} else if (xpp.getName().equals("Sms")) {
						smsBean = new SmsBean();
						smsBean.id = Integer.valueOf(xpp.getAttributeValue(null, "id"));
					} else if (xpp.getName().equals("num")) {
						smsBean.num = xpp.getAttributeValue(null, "num");
					} else if (xpp.getName().equals("msg")) {
						smsBean.msg = xpp.getAttributeValue(null, "msg");
					} else if (xpp.getName().equals("date")) {
						smsBean.date = xpp.getAttributeValue(null, "date");
					}
					break;

				case XmlPullParser.END_TAG:
					if (xpp.getName().equals("Sms")) {
						arrayList.add(smsBean);
					}
					break;
				default:
					break;
				}
				eventType = xpp.next();
			}

			return arrayList.size();

		} catch (Exception e) {
			System.out.println(e.toString());
		}
		return 0;

	}

}
