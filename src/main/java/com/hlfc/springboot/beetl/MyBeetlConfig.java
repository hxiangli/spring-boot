package com.hlfc.springboot.beetl;

import org.beetl.core.resource.ClasspathResourceLoader;
import org.beetl.ext.spring.BeetlGroupUtilConfiguration;
import org.beetl.ext.spring.BeetlSpringViewResolver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MyBeetlConfig {
	private static Logger logger = LoggerFactory.getLogger(MyBeetlConfig.class);

	public MyBeetlConfig() {
	}

	@Bean(
			name = {"beetlConfig"}
	)
	public BeetlGroupUtilConfiguration getBeetlGroupUtilConfiguration() {
		logger.info("init =========== beetlConfig");
		BeetlGroupUtilConfiguration beetlGroupUtilConfiguration = new BeetlGroupUtilConfiguration();
		ClassLoader loader = Thread.currentThread().getContextClassLoader();
		if (loader == null) {
			loader = MyBeetlConfig.class.getClassLoader();
		}

		ClasspathResourceLoader cploder = new ClasspathResourceLoader(loader, "views");
		beetlGroupUtilConfiguration.setResourceLoader(cploder);
		beetlGroupUtilConfiguration.init();
		beetlGroupUtilConfiguration.getGroupTemplate().setClassLoader(loader);
		return beetlGroupUtilConfiguration;
	}

	@Bean(
			name = {"beetlViewResolver"}
	)
	public BeetlSpringViewResolver getBeetlSpringViewResolver(@Qualifier("beetlConfig") BeetlGroupUtilConfiguration beetlGroupUtilConfiguration) {
		BeetlSpringViewResolver beetlSpringViewResolver = new BeetlSpringViewResolver();
		beetlSpringViewResolver.setSuffix(".html");
		beetlSpringViewResolver.setContentType("text/html;charset=UTF-8");
		beetlSpringViewResolver.setOrder(0);
		beetlSpringViewResolver.setConfig(beetlGroupUtilConfiguration);
		return beetlSpringViewResolver;
	}
}
