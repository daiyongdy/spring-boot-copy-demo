/**
 * <b>项目名：</b>spring-boot-copy-demo<br/>
 * <b>包名：</b>com.hbdiy.sb.aspect<br/>
 * <b>文件名：</b>HbdiyLogAspect.java<br/>
 * <b>描述：</b><br/>
 * <b>版本信息：</b>v1.0.0<br/>
 * <b>日期：</b>2016年9月4日-上午8:27:07<br/>
 * <b>Copyright (c)</b> 2016<br/>
 */

package com.hbdiy.sb.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * <b>类名称：</b>HbdiyLogAspect <br/>
 * <b>类描述：</b><br/>
 * <b>创建人：</b>daiyong<br/>
 * <b>修改人：</b><br/>
 * <b>修改时间：</b>2016年9月4日 上午8:27:07<br/>
 * <b>修改备注：</b><br/>
 * <b>版本信息：</b>v1.0.0<br/>
 */

@Component
@Aspect
public class HbdiyLogAspect {
	
	@Pointcut("@annotation(com.hbdiy.sb.annotation.HbdiyLog)")
	public void logPointCut() {
		
	}
	
	@Before("logPointCut()")
	public void doBefore() {
		System.out.println("前置通知......");
	}
	
}
