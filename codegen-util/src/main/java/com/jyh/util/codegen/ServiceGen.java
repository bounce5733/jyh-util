package com.jyh.util.codegen;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.base.CaseFormat;

import freemarker.template.TemplateExceptionHandler;

public class ServiceGen {

	private static final Logger log = LoggerFactory.getLogger(ServiceGen.class);

	private static final String AUTHOR = "jiangyonghua";// @author

	private static final String DATE = new SimpleDateFormat("yyyy/MM/dd").format(new Date());// @date

	/**
	 * 基础包
	 */
	private static final String BASE_PACKAGE = "com.jyh.plat";

	private static final String JAVA_PATH = "/src/main/java"; // java文件路径

	/**
	 * 项目在硬盘上的基础路径
	 */
	private static final String PROJECT_PATH = "/Users/jiangyonghua/Repositorys/git/sample/jyh-util/util-codegen";

	private static final String TEMPLATE_FILE_PATH = PROJECT_PATH + "/src/main/resources/template";// 模板位置

	private String projectName;

	public ServiceGen(String projectName) {
		this.projectName = projectName;
	}

	public void gen(String tableName, String modelName) {
		try {
			freemarker.template.Configuration cfg = getConfiguration();
			Map<String, Object> data = new HashMap<>();
			data.put("date", DATE);
			data.put("author", AUTHOR);
			String modelNameUpperCamel = StringUtils.isEmpty(modelName) ? tableNameConvertUpperCamel(tableName)
					: modelName;
			data.put("modelNameUpperCamel", modelNameUpperCamel);
			data.put("modelNameLowerCamel", tableNameConvertLowerCamel(tableName));
			data.put("basePackage", BASE_PACKAGE + "." + projectName);

			File file = new File(
					PROJECT_PATH + JAVA_PATH + packageConvertPath(BASE_PACKAGE + "." + projectName + ".service")
							+ modelNameUpperCamel + "Service.java");
			if (!file.getParentFile().exists()) {
				file.getParentFile().mkdirs();
			}
			cfg.getTemplate("service.ftl").process(data, new FileWriter(file));
			log.info(modelNameUpperCamel + "Service.java 生成成功");

			File file1 = new File(
					PROJECT_PATH + JAVA_PATH + packageConvertPath(BASE_PACKAGE + "." + projectName + ".service.impl")
							+ modelNameUpperCamel + "ServiceImpl.java");
			if (!file1.getParentFile().exists()) {
				file1.getParentFile().mkdirs();
			}
			cfg.getTemplate("service-impl.ftl").process(data, new FileWriter(file1));

			log.info(modelNameUpperCamel + "ServiceImpl.java 生成成功");
		} catch (Exception e) {
			throw new RuntimeException("生成Service失败", e);
		}
	}

	private static freemarker.template.Configuration getConfiguration() throws IOException {
		freemarker.template.Configuration cfg = new freemarker.template.Configuration(
				freemarker.template.Configuration.VERSION_2_3_23);
		cfg.setDirectoryForTemplateLoading(new File(TEMPLATE_FILE_PATH));
		cfg.setDefaultEncoding("UTF-8");
		cfg.setTemplateExceptionHandler(TemplateExceptionHandler.IGNORE_HANDLER);
		return cfg;
	}

	private static String tableNameConvertUpperCamel(String tableName) {
		return CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.UPPER_CAMEL, tableName.toLowerCase());
	}

	private static String tableNameConvertLowerCamel(String tableName) {
		return CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.LOWER_CAMEL, tableName.toLowerCase());
	}

	private static String packageConvertPath(String packageName) {
		return String.format("/%s/", packageName.contains(".") ? packageName.replaceAll("\\.", "/") : packageName);
	}
}
