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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

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
		
		FreeMarkerConfigurer configure = new FreeMarkerConfigurer();
		configure.setTemplateLoaderPaths("/WEB-INF/page");
		return configure;
	}
	
}
