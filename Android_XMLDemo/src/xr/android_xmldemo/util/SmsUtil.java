package xr.android_xmldemo.util;

/*
 * ����XML�ļ������ַ�ʽ
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

	// ����XML�ļ��ĵ�һ�ַ�ʽ �� ֱ��д�ļ�
	public static boolean SaveSms(Context context) {

		ArrayList<SmsBean> al = SmsDao.getSms();

		StringBuffer sb = new StringBuffer();

		// ��װһ������ͷ
		sb.append("<?xml version='1.0' encoding='utf-8' standalone='yes' ?>");
		// ��װ���ڵ�
		sb.append("<Smss>");
		// ѭ������list���Ϸ�װ���еĶ���
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

	// ����XML�ļ��ĵڶ��ַ�ʽ �� ����XMLSerializer��������XML�ļ�

	public static boolean SaveSmsTwo(Context context) {

		ArrayList<SmsBean> al = SmsDao.getSms();

		// ��һ�� ����һ��XML���л�����
		XmlSerializer xs = Xml.newSerializer();

		try {
			// ��һ������ �� �����ļ������� �ڶ������� �ļ���ģʽ ˽��ģʽ ������ �ļ��ı����ʽ
			xs.setOutput(context.openFileOutput("SmsSaveXML.xml", Context.MODE_PRIVATE), "utf-8");

			// XML�ļ�������ͷ �����ʽ �Ƿ����
			xs.startDocument("utf-8", true);

			// XML�ļ��Ŀ�ʼ�ڵ� ���ڵ� ��һ������ �����ռ� �ڶ������� �ڵ�����
			xs.startTag(null, "Smss");

			// ѭ������ڵ㼰������
			for (SmsBean smsBean : al) {
				// ��ʼ�ڵ�
				xs.startTag(null, "Sms");

				// ��ʼ�ڵ��е�����

				xs.attribute(null, "id", smsBean.id + "");

				// �ڵ��ڵĽڵ�
				xs.startTag(null, "num");
				// �ڵ������
				xs.text(smsBean.num);
				xs.endTag(null, "num");

				xs.startTag(null, "msg");
				xs.text(smsBean.msg);
				xs.endTag(null, "msg");

				xs.startTag(null, "date");
				xs.text(smsBean.date);
				xs.endTag(null, "date");

				// �п�ʼ ���н����ڵ�
				xs.endTag(null, "Sms");
			}

			// �п�ʼ һ���н��� �����ڵ�
			xs.endTag(null, "Smss");

			// �����ļ� ���XML�����л�
			xs.endDocument();

			return true;

		} catch (Exception e) {
			System.out.println(e.toString());
		}

		return false;
	}
}
