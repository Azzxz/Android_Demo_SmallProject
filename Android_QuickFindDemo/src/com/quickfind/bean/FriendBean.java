package com.quickfind.bean;

import com.quickfind.utils.PinyinUtil;

/**
 * @ClassName: FriendBean
 * @Description:��װ���Ѷ���
 * @author: iamxiarui@foxmail.com
 * @date: 2016��5��17�� ����8:57:46
 */
public class FriendBean implements Comparable<FriendBean> {

	private String name;
	private String pinyin;

	public FriendBean(String name) {
		this.name = name;
		// ֱ��ת����ƴ��
		setPinyin(PinyinUtil.getPinyin(name));
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPinyin() {
		return pinyin;
	}

	public void setPinyin(String pinyin) {
		this.pinyin = pinyin;
	}

	// ����ƴ���Ƚ�
	@Override
	public int compareTo(FriendBean another) {
		return getPinyin().compareTo(another.getPinyin());
	}

}
