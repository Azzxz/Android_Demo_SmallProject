package com.quickfind.bean;

import com.quickfind.utils.PinyinUtil;

/**
 * @ClassName: FriendBean
 * @Description:封装好友对象
 * @author: iamxiarui@foxmail.com
 * @date: 2016年5月17日 下午8:57:46
 */
public class FriendBean implements Comparable<FriendBean> {

	private String name;
	private String pinyin;

	public FriendBean(String name) {
		this.name = name;
		// 直接转换成拼音
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

	// 进行拼音比较
	@Override
	public int compareTo(FriendBean another) {
		return getPinyin().compareTo(another.getPinyin());
	}

}
