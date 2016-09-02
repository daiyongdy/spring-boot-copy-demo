/**
 * <b>项目名：</b>sprint-boot-copy-demo<br/>
 * <b>包名：</b>com.hbdiy.sb.config<br/>
 * <b>文件名：</b>HbdiyFreeMarkerConfig.java<br/>
 * <b>描述：</b><br/>
 * <b>版本信息：</b>v1.0.0<br/>
 * <b>日期：</b>2016年9月2日-下午5:23:15<br/>
 * <b>Copyright (c)</b> 2016<br/>
 */

package com.hbdiy.sb.config;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import com.hbdiy.sb.util.spring.SpringContextUtil;

/**
 * <b>类名称：</b>HbdiyFreeMarkerConfig <br/>
 * <b>类描述：</b><br/>
 * <b>创建人：</b>daiyong<br/>
 * <b>修改人：</b><br/>
 * <b>修改时间：</b>2016年9月2日 下午5:23:15<br/>
 * <b>修改备注：</b><br/>
 * <b>版本信息：</b>v1.0.0<br/>
 */

@Configuration
public class HbdiyFreeMarkerConfig {
	
	private static final Logger logger = LoggerFactory.getLogger(HbdiyFreeMarkerConfig.class);
	
	@Bean
	public FreeMarkerConfigurer hbdiyFreeMarkerConfigurer() {
		
		logger.debug("HbdiyFreeMarkerConfig init...");
		
		//定义模板路径
		FreeMarkerConfigurer configure = new FreeMarkerConfigurer();
		configure.setTemplateLoaderPaths("/WEB-INF/page", "/WEB-INF/page2");
		
		//自定义组件
		Map<String, Object> variables = new HashMap<String, Object>();
		variables.put("stringDirective", SpringContextUtil.getBean("stringDirective"));
		configure.setFreemarkerVariables(variables);
		
		//配置
		Properties settings = new Properties();
		settings.setProperty("template_update_delay", "0");
		configure.setFreemarkerSettings(settings);
		
		return configure;
	}
	
}
