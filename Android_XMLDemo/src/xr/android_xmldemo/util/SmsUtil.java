package xr.android_xmldemo.util;

import java.io.FileOutputStream;
import java.util.ArrayList;

import android.content.Context;
import xr.android_xmldemo.bean.Sms;
import xr.android_xmldemo.dao.SmsDao;

public class SmsUtil {

	public static boolean SaveSms(Context context) {

		ArrayList<Sms> al = SmsDao.getSms();

		StringBuffer sb = new StringBuffer();

		// ��װһ������ͷ
		sb.append("<?xml version='1.0' encoding='utf-8' standalone='yes' ?>");
		// ��װ���ڵ�
		sb.append("<Smss>");
		// ѭ������list���Ϸ�װ���еĶ���
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
}
