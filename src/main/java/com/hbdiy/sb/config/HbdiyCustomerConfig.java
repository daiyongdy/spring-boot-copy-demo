/**
 * <b>项目名：</b>sprint-boot-copy-demo<br/>
 * <b>包名：</b>com.hbdiy.sb.config<br/>
 * <b>文件名：</b>HbdiyCustomerConfig.java<br/>
 * <b>描述：</b><br/>
 * <b>版本信息：</b>v1.0.0<br/>
 * <b>日期：</b>2016年9月7日-下午2:12:58<br/>
 * <b>Copyright (c)</b> 2016<br/>
 */

package com.hbdiy.sb.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.alibaba.fastjson.JSON;
import com.hbdiy.sb.model.dto.Customer;
import com.hbdiy.sb.model.dto.CustomerProperties;

/**
 * <b>类名称：</b>HbdiyCustomerConfig <br/>
 * <b>类描述：</b><br/>
 * <b>创建人：</b>daiyong<br/>
 * <b>修改人：</b><br/>
 * <b>修改时间：</b>2016年9月7日 下午2:12:58<br/>
 * <b>修改备注：</b><br/>
 * <b>版本信息：</b>v1.0.0<br/>
 */

@Configuration
@EnableConfigurationProperties(CustomerProperties.class)
public class HbdiyCustomerConfig {

	@Autowired
	private CustomerProperties customerProperties;
	
	@Bean
	public CustomerProperties test() {
		System.out.println(JSON.toJSONString(customerProperties));
		return customerProperties;
	}
	
}
