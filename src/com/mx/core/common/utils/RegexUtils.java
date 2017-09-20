package com.mx.core.common.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexUtils {
	
	/**
	 * 查找字符串
	 * @param baseStr 查找的文本
	 * @param findStr 正则表达式
	 * @param index 下标
	 * @return
	 */
	public static String findString(String baseStr, String findStr, int index) {
		try {
			Pattern pattern = Pattern.compile(findStr);
			Matcher matcher = pattern.matcher(baseStr);
			String data = "";
			int i = 0;
			while (matcher.find()){
				data = matcher.group();
				if(i++ == index)
					break;
			}
			return data;
		} catch (Exception e) {
			return "";
		}
	}	
	
	/**
	 * 查找字符串
	 * @param baseStr 查找的文本
	 * @param findStr 正则表达式
	 * @return
	 */
	public static String findString(String baseStr, String findStr) {
		return findString(baseStr, findStr, 0);
	}	


	public static void main(String[] args) {
		String s = "123.111.22.123,122.113.5.111,122.113.5.222";
//		System.out.println(RegexUtils.findString(s,
//				"\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}",2));
		System.out
				.println(s.replaceAll(",?"+ RegexUtils.findString(s,"\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}",0)+",?", ""));;
	}
}
