package com.jyh.util.common;

import org.apache.commons.lang3.StringUtils;

/**
 * 字符串处理工具类
 * 
 * @author jiangyonghua
 * @date 2017年3月18日 下午9:32:34
 */
public class StringUtil {

	/**
	 * 驼峰式转下划线形式
	 * 
	 * @param name
	 * @return
	 */
	public static String camelToUnderline(String name) {
		if (StringUtils.isBlank(name)) {
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
