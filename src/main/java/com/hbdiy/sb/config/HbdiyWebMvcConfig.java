/**
 * <b>项目名：</b>sprint-boot-copy-demo<br/>
 * <b>包名：</b>com.hbdiy.sb.config<br/>
 * <b>文件名：</b>HbdiyWebMvcConfig.java<br/>
 * <b>描述：</b><br/>
 * <b>版本信息：</b>v1.0.0<br/>
 * <b>日期：</b>2016年9月2日-下午5:24:11<br/>
 * <b>Copyright (c)</b> 2016<br/>
 */

package com.hbdiy.sb.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.freemarker.FreeMarkerViewResolver;

import com.hbdiy.sb.interceptor.LoginInterceptor;

/**
 * <b>类名称：</b>HbdiyWebMvcConfig <br/>
 * <b>类描述：</b><br/>
 * <b>创建人：</b>daiyong<br/>
 * <b>修改人：</b><br/>
 * <b>修改时间：</b>2016年9月2日 下午5:24:11<br/>
 * <b>修改备注：</b><br/>
 * <b>版本信息：</b>v1.0.0<br/>
 */

@Configuration
public class HbdiyWebMvcConfig extends WebMvcConfigurerAdapter{
	
	private static final Logger logger = LoggerFactory.getLogger(HbdiyWebMvcConfig.class);
	
	/**
	 * @author daiyong
	 */
	@Override
	public void configureViewResolvers(ViewResolverRegistry registry) {
		
		logger.debug("HbdiyWebMvcConfig init...");
		
		FreeMarkerViewResolver viewResolver = new FreeMarkerViewResolver();
		viewResolver.setCache(false);
		viewResolver.setSuffix(".ftl");
		viewResolver.setContentType("text/html;charset=UTF-8");
		viewResolver.setRequestContextAttribute("request");
		viewResolver.setExposeSpringMacroHelpers(true);
		viewResolver.setExposeRequestAttributes(true);
		viewResolver.setExposeSessionAttributes(true);
		
		registry.viewResolver(viewResolver);
		// FDY Auto-generated method stub
		super.configureViewResolvers(registry);
	}

	/**
	 * 配置拦截器
	 * @author daiyong
	 */
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(new LoginInterceptor()).addPathPatterns("/**");
	}
}
