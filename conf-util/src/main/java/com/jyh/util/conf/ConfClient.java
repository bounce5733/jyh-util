package com.jyh.util.conf;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 应用配置客户端
 * 
 * @author jiangyonghua
 * @date 2018年2月24日 上午6:04:15
 */
public class ConfClient {

	private static final Logger log = LoggerFactory.getLogger(ConfClient.class);

	private static final String IP = "192.168.3.5";

	private static final int PORT = 24322;

	private static final int CONN_TEIMOUT = 60 * 1000;

	/**
	 * 返回yaml格式配置信息或null
	 * 
	 * @param appid
	 * @return
	 */
	public static byte[] getYaml(String appid) {
		String msg = "{\"appid\":\"" + appid + "\"}";
		InputStream is = null;
		OutputStream os = null;
		ByteArrayOutputStream buffer = null;
		Socket socket = null;
		try {
			socket = new Socket();
			SocketAddress endpoint = new InetSocketAddress(IP, PORT);
			socket.connect(endpoint, CONN_TEIMOUT);
			os = socket.getOutputStream();

			os.write(msg.getBytes());
			socket.shutdownOutput();

			while (true) {
				if (socket.getInputStream().available() > 0) {
					// 解析服务端响应
					is = socket.getInputStream();
					byte[] b = new byte[1024];
					int nRead;
					buffer = new ByteArrayOutputStream();
					while ((nRead = is.read(b)) != -1) {
						buffer.write(b, 0, nRead);
					}
					buffer.flush();
					return buffer.toByteArray();
				}
			}

		} catch (IOException e) {
			log.error(e.getMessage(), e);
		} finally {
			try {
				if (socket != null && !socket.isClosed()) {
					socket.close();
				}
			} catch (IOException e) {
				log.error(e.getMessage(), e);
			}
		}
		return null;
	}
}
