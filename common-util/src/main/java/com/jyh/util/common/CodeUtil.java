package com.jyh.util.common;

import java.util.Objects;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.digest.DigestUtils;

/**
 * 编码/解码工具类
 * 
 * @author jiangyonghua
 * @date 2016年9月12日 下午2:08:28
 */
public class CodeUtil {

	/**
	 * MD5编码
	 * 
	 * @param msg
	 *            待编码字符
	 * @return 字符摘要
	 */
	public static String md5Encode(String msg) {
		if (Objects.isNull(msg)) {
			return null;
		}
		return DigestUtils.md5Hex(msg);
	}

	/**
	 * Base64编码
	 * 
	 * @param plainMsg
	 *            字符串
	 * @return
	 */
	public static String base64Encode(String plainMsg) {
		if (Objects.isNull(plainMsg)) {
			return null;
		}
		return new String(new Base64().encode(plainMsg.getBytes()));
	}

	/**
	 * Base64编码
	 * 
	 * @param binaryMsg
	 *            字节数组
	 * @return
	 */
	public static String base64Encode(byte[] binaryMsg) {
		if (Objects.isNull(binaryMsg)) {
			return null;
		}
		return new String(new Base64().encode(binaryMsg));
	}

	/**
	 * Base64解码
	 * 
	 * @param encodeMsg
	 *            编码base64字符
	 * @return
	 */
	public static String base64Decode(String encodeMsg) {
		if (Objects.isNull(encodeMsg)) {
			return null;
		}
		return new String(new Base64().decode(encodeMsg.getBytes()));
	}

	/**
	 * base64(md5)编码
	 * 
	 * @param msg
	 *            待编码字符
	 * @return base64编码字符
	 */
	public static String base64MD5(String msg) {
		if (Objects.isNull(msg)) {
			return null;
		}
		return Base64.encodeBase64String(DigestUtils.md5(msg));
	}

	public static void main(String[] args) {
		String msg = "123";
		System.out.println(md5Encode(msg));
	}
}
