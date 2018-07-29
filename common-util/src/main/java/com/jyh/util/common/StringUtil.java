package com.jyh.util.common;

/**
 * 字符串处理工具类
 * 
 * @author jiangyonghua
 * @date 2017年3月18日 下午9:32:34
 */
public class StringUtil {

	/**
	 * 判断字符串是否为空或null
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isEmpty(String str) {
		if (str == null || str.trim().equals("")) {
			return true;
		}
		return false;
	}

	/**
	 * 判断字符串是否不为空或null
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isNotEmpty(String str) {
		if (str == null || str.trim().equals("")) {
			return false;
		}
		return true;
	}

	/**
	 * 驼峰式转下划线形式
	 * 
	 * @param name
	 * @return
	 */
	public static String camelToUnderline(String name) {
		if (StringUtil.isEmpty(name)) {
			return "";
		}
		int len = name.length();
		StringBuilder sb = new StringBuilder(len);
		for (int i = 0; i < len; i++) {
			char c = name.charAt(i);
			if (Character.isUpperCase(c)) {
				sb.append('_');
				sb.append(Character.toLowerCase(c));
			} else {
				sb.append(c);
			}
		}
		return sb.toString();
	}

}
