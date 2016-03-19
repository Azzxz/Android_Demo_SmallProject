package xr.android_xmldemo.util;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import org.xmlpull.v1.XmlSerializer;

import android.content.Context;
import android.util.Xml;
import xr.android_xmldemo.bean.Sms;
import xr.android_xmldemo.dao.SmsDao;

public class SmsUtil {

	public static boolean SaveSms(Context context) {

		ArrayList<Sms> al = SmsDao.getSms();

		StringBuffer sb = new StringBuffer();

		// 封装一个声明头
		sb.append("<?xml version='1.0' encoding='utf-8' standalone='yes' ?>");
		// 封装根节点
		sb.append("<Smss>");
		// 循环遍历list集合封装所有的短信
		for (Sms sms : al) {
			sb.append("<Sms id = \"" + sms.id + "\">");

			sb.append("<num>");
			sb.append(sms.num);
			sb.append("</num>");

			sb.append("<msg>");
			sb.append(sms.msg);
			sb.append("</msg>");

			sb.append("<date>");
			sb.append(sms.date);
			sb.append("</date>");

			sb.append("</Sms>");
		}

		sb.append("</Smss>");
		FileOutputStream openFileOutput = null;

		try {
			openFileOutput = context.openFileOutput("smsxml.xml", Context.MODE_PRIVATE);

			openFileOutput.write(sb.toString().getBytes());

			return true;

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (openFileOutput != null) {
				try {
					openFileOutput.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		return false;
	}

	public static boolean SaveSmsTwo(Context context) {

		ArrayList<Sms> al = SmsDao.getSms();

		XmlSerializer xs = Xml.newSerializer();

		try {
			xs.setOutput(context.openFileOutput("SmsSaveXML.xml", Context.MODE_PRIVATE), "utf-8");
			xs.startDocument("utf-8", true);

			xs.startTag(null, "Smss");

			for (Sms sms : al) {
				xs.startTag(null, "Sms");

				xs.attribute(null, "id", sms.id + "");

				xs.startTag(null, "num");
				xs.text(sms.num);
				xs.endTag(null, "num");

				xs.startTag(null, "msg");
				xs.text(sms.msg);
				xs.endTag(null, "msg");

				xs.startTag(null, "date");
				xs.text(sms.date);
				xs.endTag(null, "date");

				xs.endTag(null, "Sms");
			}

			xs.endTag(null, "Smss");

			xs.endDocument();

			return true;

		} catch (Exception e) {
			System.out.println(e.toString());
		}

		return false;
	}
}
