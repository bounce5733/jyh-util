package com.jyh.util.codegen;

import org.junit.Ignore;
import org.junit.Test;

import com.jyh.util.codegen.ServiceGen;

public class ServiceGenTest {

	private static final String PROJECT_NAME = "console";

	@Test
	@Ignore
	public void gen() {
		new ServiceGen(PROJECT_NAME).gen("user", null);
	}
}
