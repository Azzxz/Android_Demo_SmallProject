package xr.android_xmldemo.util;

/*
 * 生成XML文件的两种方式
 * 
 * */
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import org.xmlpull.v1.XmlSerializer;

import android.content.Context;
import android.util.Xml;
import xr.android_xmldemo.bean.SmsBean;
import xr.android_xmldemo.dao.SmsDao;

public class SmsUtil {

	// 生成XML文件的第一种方式 ： 直接写文件
	public static boolean SaveSms(Context context) {

		ArrayList<SmsBean> al = SmsDao.getSms();

		StringBuffer sb = new StringBuffer();

		// 封装一个声明头
		sb.append("<?xml version='1.0' encoding='utf-8' standalone='yes' ?>");
		// 封装根节点
		sb.append("<Smss>");
		// 循环遍历list集合封装所有的短信
		for (SmsBean smsBean : al) {
			sb.append("<Sms id = \"" + smsBean.id + "\">");

			sb.append("<num>");
			sb.append(smsBean.num);
			sb.append("</num>");

			sb.append("<msg>");
			sb.append(smsBean.msg);
			sb.append("</msg>");

			sb.append("<date>");
			sb.append(smsBean.date);
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

	// 生成XML文件的第二种方式 ： 利用XMLSerializer类来生成XML文件

	public static boolean SaveSmsTwo(Context context) {

		ArrayList<SmsBean> al = SmsDao.getSms();

		// 第一步 生成一个XML序列化对象
		XmlSerializer xs = Xml.newSerializer();

		try {
			// 第一个参数 ： 存入文件的名称 第二个参数 文件的模式 私有模式 第三个 文件的编码格式
			xs.setOutput(context.openFileOutput("SmsSaveXML.xml", Context.MODE_PRIVATE), "utf-8");

			// XML文件的声明头 编码格式 是否独立
			xs.startDocument("utf-8", true);

			// XML文件的开始节点 根节点 第一个参数 命名空间 第二个参数 节点名称
			xs.startTag(null, "Smss");

			// 循环插入节点及其内容
			for (SmsBean smsBean : al) {
				// 开始节点
				xs.startTag(null, "Sms");

				// 开始节点中的属性

				xs.attribute(null, "id", smsBean.id + "");

				// 节点内的节点
				xs.startTag(null, "num");
				// 节点的属性
				xs.text(smsBean.num);
				xs.endTag(null, "num");

				xs.startTag(null, "msg");
				xs.text(smsBean.msg);
				xs.endTag(null, "msg");

				xs.startTag(null, "date");
				xs.text(smsBean.date);
				xs.endTag(null, "date");

				// 有开始 就有结束节点
				xs.endTag(null, "Sms");
			}

			// 有开始 一定有结束 结束节点
			xs.endTag(null, "Smss");

			// 结束文件 完成XML的序列化
			xs.endDocument();

			return true;

		} catch (Exception e) {
			System.out.println(e.toString());
		}

		return false;
	}
}
