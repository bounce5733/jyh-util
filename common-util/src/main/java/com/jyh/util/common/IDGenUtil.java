package com.jyh.util.common;

import java.util.UUID;

/**
 * 主键生成器
 * 
 * @author jiangyonghua
 * @date 2016年6月12日 下午1:04:37
 */
public class IDGenUtil {

	/**
	 * @return UUID 去 "-"
	 */
	public static String UUID() {
		String originalUUID = UUID.randomUUID().toString();
		return originalUUID.replaceAll("-", "");
	}

	public static void main(String[] args) {
		System.out.println(UUID());
	}
}
