package xr.android_xmldemo.dao;

import java.util.ArrayList;

import xr.android_xmldemo.bean.Sms;

public class SmsDao {

	public static ArrayList<Sms> getSms() {
		ArrayList<Sms> al = new ArrayList<Sms>();

		Sms sms1 = new Sms();
		sms1.num = "123";
		sms1.msg = "xia rui";
		sms1.date = "2016-03-18";
		sms1.id = 1;
		al.add(sms1);

		Sms sms2 = new Sms();
		sms2.num = "456";
		sms2.msg = "i am xia rui";
		sms2.date = "2016-03-18";
		sms2.id = 1;
		al.add(sms2);

		Sms sms3 = new Sms();
		sms3.num = "789";
		sms3.msg = "yes,i am xia rui";
		sms3.date = "2016-03-18";
		sms3.id = 1;
		al.add(sms3);

		return al;

	}

}
