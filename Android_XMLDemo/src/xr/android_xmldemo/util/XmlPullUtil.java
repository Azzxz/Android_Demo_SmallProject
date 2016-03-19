package xr.android_xmldemo.util;

import java.util.ArrayList;

import org.xmlpull.v1.XmlPullParser;

import android.content.Context;
import android.util.Xml;
import xr.android_xmldemo.bean.SmsBean;

/*
 * XML�ļ��Ľ���
 * 
 * ʹ��Pull��ʽ
 * */
public class XmlPullUtil {

	public static int backPull(Context context) {

		// 1������һ��XML��������
		XmlPullParser xpp = Xml.newPullParser();

		ArrayList<SmsBean> arrayList = null;

		SmsBean smsBean = null;

		try {
			// 2�����ý������������ ��һ����������Ҫ�������ļ����� �ڶ������� ���ļ��ı���
			xpp.setInput(context.openFileInput("SmsSaveXML.xml"), "utf-8");

			// ���ؽڵ��¼�������
			int eventType = xpp.getEventType();

			// ��������ļ������ڵ� ��ѭ��
			while (eventType != XmlPullParser.END_DOCUMENT) {
				switch (eventType) {
				// ����Ǳ�ǩ��ʼ�ڵ�
				case XmlPullParser.START_TAG:
					// ��ʼ�ڵ� ��ʼ���ڵ����
					if (xpp.getName().equals("Smss")) {
						arrayList = new ArrayList<SmsBean>();
					} else if (xpp.getName().equals("Sms")) {
						// ��ʼ��Sms����
						smsBean = new SmsBean();
						// �õ���ǩ�е�ֵ
						smsBean.id = Integer.valueOf(xpp.getAttributeValue(null, "id"));
					} else if (xpp.getName().equals("num")) {
						smsBean.num = xpp.getAttributeValue(null, "num");
					} else if (xpp.getName().equals("msg")) {
						smsBean.msg = xpp.getAttributeValue(null, "msg");
					} else if (xpp.getName().equals("date")) {
						smsBean.date = xpp.getAttributeValue(null, "date");
					}
					break;

				// ����Ǳ�ǩ�����ڵ� ��ô����ǰ�������list������
				case XmlPullParser.END_TAG:
					if (xpp.getName().equals("Sms")) {
						arrayList.add(smsBean);
					}
					break;
				default:
					break;
				}
				// ��ȡ��һ���¼��Ľڵ�����
				eventType = xpp.next();
			}

			return arrayList.size();

		} catch (Exception e) {
			System.out.println(e.toString());
		}
		return 0;

	}

}
