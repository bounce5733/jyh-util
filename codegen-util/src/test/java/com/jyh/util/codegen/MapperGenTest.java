package com.jyh.util.codegen;

import org.junit.Ignore;
import org.junit.Test;

import com.jyh.util.codegen.MapperGen;

public class MapperGenTest {

	private static final String JDBC_URL = "jdbc:mysql://localhost:3306/plat_console";

	private static final String JDBC_USERNAME = "root";

	private static final String JDBC_PASSWORD = "passw0rd";

	private static final String PROJECT_NAME = "console";

	@Test
	@Ignore
	public void gen() {
		String tableName = "sys_code";
		new MapperGen(PROJECT_NAME, JDBC_URL, JDBC_USERNAME, JDBC_PASSWORD).gen(tableName, null);
	}
}
