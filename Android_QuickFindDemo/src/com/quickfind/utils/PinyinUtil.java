package com.quickfind.utils;

import android.text.TextUtils;
import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

/**
 * @ClassName: PinyinUtil
 * @Description:汉字转换成拼音的工具类
 * @author: iamxiarui@foxmail.com
 * @date: 2016年5月19日 上午10:58:28
 */
public class PinyinUtil {

	public static String getPinyin(String chinese) {
		// 先判断是不是空
		if (TextUtils.isEmpty(chinese)) {
			return null;
		}

		String chinesePinyin = "";

		// 用来设置转化的拼音的大小写，或者声调
		HanyuPinyinOutputFormat chineseFormat = new HanyuPinyinOutputFormat();
		// 设置转化的拼音是大写字母
		chineseFormat.setCaseType(HanyuPinyinCaseType.UPPERCASE);
		// 设置转化的拼音不带声调
		chineseFormat.setToneType(HanyuPinyinToneType.WITHOUT_TONE);

		// 将汉字转换成数组
		char[] chineseArray = chinese.toCharArray();
		// 循环遍历数组
		for (int i = 0; i < chineseArray.length; i++) {
			// 先过滤空格
			if (Character.isWhitespace(chineseArray[i])) {
				continue;
			}
			// 再判断是否为汉字 汉字占2个字节 肯定大于127
			if (chineseArray[i] > 127) {
				// 可能是汉字
				try {
					String[] pinyinArray = PinyinHelper.toHanyuPinyinStringArray(chineseArray[i], chineseFormat);
					if (pinyinArray != null) {
						// 只能取第一个 无法判断多音字情况
						chinesePinyin += pinyinArray[0];
					} else {
						// 没有找到拼音 不做任何处理
					}
				} catch (BadHanyuPinyinOutputFormatCombination e) {
					e.printStackTrace();
				}
			} else {
				// 一定不是汉字 直接拼接
				chinesePinyin += chineseArray[i];
			}
		}

		return chinesePinyin;

	}

}
