/**
 * <b>项目名：</b>sprint-boot-copy-demo<br/>
 * <b>包名：</b>com.hbdiy.sb.model.dto<br/>
 * <b>文件名：</b>Customer.java<br/>
 * <b>描述：</b><br/>
 * <b>版本信息：</b>v1.0.0<br/>
 * <b>日期：</b>2016年9月7日-下午2:04:10<br/>
 * <b>Copyright (c)</b> 2016<br/>
 */

package com.hbdiy.sb.model.dto;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * <b>类名称：</b>Customer <br/>
 * <b>类描述：</b><br/>
 * <b>创建人：</b>daiyong<br/>
 * <b>修改人：</b><br/>
 * <b>修改时间：</b>2016年9月7日 下午2:04:10<br/>
 * <b>修改备注：</b><br/>
 * <b>版本信息：</b>v1.0.0<br/>
 */

//@Component
@ConfigurationProperties(prefix="test.customer")
public class Customer {

	private String username;
	
	private String password;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
}
