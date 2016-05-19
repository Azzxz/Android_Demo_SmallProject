package com.quickfind.utils;

import android.text.TextUtils;
import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

/**
 * @ClassName: PinyinUtil
 * @Description:����ת����ƴ���Ĺ�����
 * @author: iamxiarui@foxmail.com
 * @date: 2016��5��19�� ����10:58:28
 */
public class PinyinUtil {

	public static String getPinyin(String chinese) {
		// ���ж��ǲ��ǿ�
		if (TextUtils.isEmpty(chinese)) {
			return null;
		}

		String chinesePinyin = "";

		// ��������ת����ƴ���Ĵ�Сд����������
		HanyuPinyinOutputFormat chineseFormat = new HanyuPinyinOutputFormat();
		// ����ת����ƴ���Ǵ�д��ĸ
		chineseFormat.setCaseType(HanyuPinyinCaseType.UPPERCASE);
		// ����ת����ƴ����������
		chineseFormat.setToneType(HanyuPinyinToneType.WITHOUT_TONE);

		// ������ת��������
		char[] chineseArray = chinese.toCharArray();
		// ѭ����������
		for (int i = 0; i < chineseArray.length; i++) {
			// �ȹ��˿ո�
			if (Character.isWhitespace(chineseArray[i])) {
				continue;
			}
			// ���ж��Ƿ�Ϊ���� ����ռ2���ֽ� �϶�����127
			if (chineseArray[i] > 127) {
				// �����Ǻ���
				try {
					String[] pinyinArray = PinyinHelper.toHanyuPinyinStringArray(chineseArray[i], chineseFormat);
					if (pinyinArray != null) {
						// ֻ��ȡ��һ�� �޷��ж϶��������
						chinesePinyin += pinyinArray[0];
					} else {
						// û���ҵ�ƴ�� �����κδ���
					}
				} catch (BadHanyuPinyinOutputFormatCombination e) {
					e.printStackTrace();
				}
			} else {
				// һ�����Ǻ��� ֱ��ƴ��
				chinesePinyin += chineseArray[i];
			}
		}

		return chinesePinyin;

	}

}
