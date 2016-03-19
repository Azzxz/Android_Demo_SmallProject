package xr.android_xmldemo.dao;

import java.util.ArrayList;

import xr.android_xmldemo.bean.SmsBean;

public class SmsDao {

	public static ArrayList<SmsBean> getSms() {
		ArrayList<SmsBean> al = new ArrayList<SmsBean>();

		SmsBean sms1 = new SmsBean();
		sms1.num = "123";
		sms1.msg = "xia rui";
		sms1.date = "2016-03-18";
		sms1.id = 1;
		al.add(sms1);

		SmsBean sms2 = new SmsBean();
		sms2.num = "456";
		sms2.msg = "i am xia rui";
		sms2.date = "2016-03-18";
		sms2.id = 1;
		al.add(sms2);

		SmsBean sms3 = new SmsBean();
		sms3.num = "789";
		sms3.msg = "yes,i am xia rui";
		sms3.date = "2016-03-18";
		sms3.id = 1;
		al.add(sms3);

		return al;

	}

}
