package com.jyh.util.common;

import java.util.Random;

/**
 * 随机数
 * 
 * @author jiangyonghua
 * @date 2018年1月11日 下午11:57:39
 */
public class RandomUtil {

	/**
	 * 字符随机数
	 * 
	 * @param len
	 * @return
	 */
	public static String stringRandom(int len) {
		StringBuilder str = new StringBuilder();
		Random random = new Random();
		for (int i = 0; i < len; i++) {
			str.append((char) (random.nextInt(26) + 97));
		}
		return str.toString();
	}

	public static void main(String[] args) {
		for (int i = 0; i < 10; i++) {
			System.out.println(stringRandom(8));
		}
	}

}
