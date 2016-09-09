/**
 * <b>项目名：</b>sprint-boot-copy-demo<br/>
 * <b>包名：</b>com.hbdiy.sb<br/>
 * <b>文件名：</b>runner.java<br/>
 * <b>描述：</b><br/>
 * <b>版本信息：</b>v1.0.0<br/>
 * <b>日期：</b>2016年9月7日-下午1:36:48<br/>
 * <b>Copyright (c)</b> 2016<br/>
 */

package com.hbdiy.sb.runner;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * <b>类名称：</b>runner <br/>
 * <b>类描述：</b><br/>
 * <b>创建人：</b>daiyong<br/>
 * <b>修改人：</b><br/>
 * <b>修改时间：</b>2016年9月7日 下午1:36:48<br/>
 * <b>修改备注：</b><br/>
 * <b>版本信息：</b>v1.0.0<br/>
 */

@Component
public class runner implements CommandLineRunner{

	/**
	 * @author daiyong
	 */
	@Override
	public void run(String... args) throws Exception {
		System.out.println("command line...");
		for (String arg : args) {
			System.out.println(">>" + arg);
		}
	}
	
}
