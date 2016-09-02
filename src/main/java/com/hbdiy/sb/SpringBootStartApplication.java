/**
 * <b>项目名：</b>sprint-boot-copy-demo<br/>
 * <b>包名：</b>com.hbdiy.sb<br/>
 * <b>文件名：</b>SpringBootStartApplication.java<br/>
 * <b>描述：</b><br/>
 * <b>版本信息：</b>v1.0.0<br/>
 * <b>日期：</b>2016年9月2日-下午5:10:48<br/>
 * <b>Copyright (c)</b> 2016<br/>
 */

package com.hbdiy.sb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * <b>类名称：</b>SpringBootStartApplication <br/>
 * <b>类描述：</b><br/>
 * <b>创建人：</b>daiyong<br/>
 * <b>修改人：</b><br/>
 * <b>修改时间：</b>2016年9月2日 下午5:10:48<br/>
 * <b>修改备注：</b><br/>
 * <b>版本信息：</b>v1.0.0<br/>
 */

@SpringBootApplication
public class SpringBootStartApplication {
	
	public static void main(String[] args) {

		//简单启动方式

		SpringApplication application = new SpringApplication(SpringBootStartApplication.class);
		//添加监听器
		application.run(args);
	
	}
	
}
