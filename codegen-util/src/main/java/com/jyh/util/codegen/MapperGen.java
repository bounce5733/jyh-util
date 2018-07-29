package com.jyh.util.codegen;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.Context;
import org.mybatis.generator.config.GeneratedKey;
import org.mybatis.generator.config.JDBCConnectionConfiguration;
import org.mybatis.generator.config.JavaClientGeneratorConfiguration;
import org.mybatis.generator.config.JavaModelGeneratorConfiguration;
import org.mybatis.generator.config.ModelType;
import org.mybatis.generator.config.PluginConfiguration;
import org.mybatis.generator.config.PropertyRegistry;
import org.mybatis.generator.config.SqlMapGeneratorConfiguration;
import org.mybatis.generator.config.TableConfiguration;
import org.mybatis.generator.internal.DefaultShellCallback;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.base.CaseFormat;

public class MapperGen {

	private static final Logger log = LoggerFactory.getLogger(MapperGen.class);

	/**
	 * 项目在硬盘上的基础路径
	 */
	private static final String PROJECT_PATH = "/Users/jiangyonghua/Repositorys/git/sample/jyh-util/util-codegen";

	/**
	 * java文件路径
	 */
	private static final String JAVA_PATH = "/src/main/java";

	/**
	 * 资源文件路径
	 */
	private static final String RESOURCES_PATH = "/src/main/resources";

	/**
	 * 驱动名称
	 */
	private static final String JDBC_DRIVER_CLASS_NAME = "com.mysql.cj.jdbc.Driver";

	/**
	 * 基础包
	 */
	private static final String BASE_PACKAGE = "com.jyh.plat";

	/**
	 * 模型包名称
	 */
	private static final String MODEL_PACKAGE = "entity";

	/**
	 * mapper包名称
	 */
	private static final String MAPPER_PACKAGE = "dao";

	/**
	 * MBG执行上下文
	 */
	private static final Context context;

	static {
		context = new Context(ModelType.FLAT);
		context.setId("abc");
		context.setTargetRuntime("MyBatis3Simple");
		context.addProperty(PropertyRegistry.CONTEXT_BEGINNING_DELIMITER, "`");
		context.addProperty(PropertyRegistry.CONTEXT_ENDING_DELIMITER, "`");
	}

	public MapperGen(String projectName, String jdbcUrl, String jdbcUsername, String jdbcPassword) {
		JDBCConnectionConfiguration jdbcConnectionConfiguration = new JDBCConnectionConfiguration();
		jdbcConnectionConfiguration.setConnectionURL(jdbcUrl);
		jdbcConnectionConfiguration.setUserId(jdbcUsername);
		jdbcConnectionConfiguration.setPassword(jdbcPassword);
		jdbcConnectionConfiguration.setDriverClass(JDBC_DRIVER_CLASS_NAME);
		context.setJdbcConnectionConfiguration(jdbcConnectionConfiguration);

		PluginConfiguration pluginConfiguration = new PluginConfiguration();
		pluginConfiguration.setConfigurationType("tk.mybatis.mapper.generator.MapperPlugin");
		pluginConfiguration.addProperty("mappers", "com.jyh.util.codegen.Mapper");
		context.addPluginConfiguration(pluginConfiguration);

		JavaModelGeneratorConfiguration javaModelGeneratorConfiguration = new JavaModelGeneratorConfiguration();
		javaModelGeneratorConfiguration.setTargetProject(PROJECT_PATH + JAVA_PATH);
		javaModelGeneratorConfiguration.setTargetPackage(BASE_PACKAGE + "." + projectName + "." + MODEL_PACKAGE);
		context.setJavaModelGeneratorConfiguration(javaModelGeneratorConfiguration);

		SqlMapGeneratorConfiguration sqlMapGeneratorConfiguration = new SqlMapGeneratorConfiguration();
		sqlMapGeneratorConfiguration.setTargetProject(PROJECT_PATH + RESOURCES_PATH);
		sqlMapGeneratorConfiguration.setTargetPackage("mapper");
		context.setSqlMapGeneratorConfiguration(sqlMapGeneratorConfiguration);

		JavaClientGeneratorConfiguration javaClientGeneratorConfiguration = new JavaClientGeneratorConfiguration();
		javaClientGeneratorConfiguration.setTargetProject(PROJECT_PATH + JAVA_PATH);
		javaClientGeneratorConfiguration.setTargetPackage(BASE_PACKAGE + "." + projectName + "." + MAPPER_PACKAGE);
		javaClientGeneratorConfiguration.setConfigurationType("XMLMAPPER");
		context.setJavaClientGeneratorConfiguration(javaClientGeneratorConfiguration);
	}

	/**
	 * 生成数据模型与mybatis mapper
	 * 
	 * @param tableName
	 *            表名
	 * @param modelName
	 *            模型名，空时表名下划线转驼峰式
	 */
	public void gen(String tableName, String modelName) {
		TableConfiguration tableConfiguration = new TableConfiguration(context);
		tableConfiguration.setTableName(tableName);
		if (StringUtils.isNotEmpty(modelName))
			tableConfiguration.setDomainObjectName(modelName);
		tableConfiguration.setGeneratedKey(new GeneratedKey("id", "Mysql", true, null));
		context.addTableConfiguration(tableConfiguration);

		List<String> warnings;
		MyBatisGenerator generator;
		try {
			Configuration config = new Configuration();
			config.addContext(context);
			config.validate();

			boolean overwrite = true;
			DefaultShellCallback callback = new DefaultShellCallback(overwrite);
			warnings = new ArrayList<String>();
			generator = new MyBatisGenerator(config, callback, warnings);
			generator.generate(null);
		} catch (Exception e) {
			throw new RuntimeException("Mapper生成失败", e);
		}

		if (generator.getGeneratedJavaFiles().isEmpty() || generator.getGeneratedXmlFiles().isEmpty()) {
			throw new RuntimeException("Mapper生成失败：" + warnings);
		}
		if (StringUtils.isEmpty(modelName)) {
			modelName = tableNameConvertUpperCamel(tableName);
		}
		log.info(modelName + ".java 生成成功");
		log.info(modelName + "Mapper.java 生成成功");
		log.info(modelName + "Mapper.xml 生成成功");
	}

	/**
	 * @param tableName
	 * @return
	 */
	private static String tableNameConvertUpperCamel(String tableName) {
		return CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.UPPER_CAMEL, tableName.toLowerCase());
	}

}
