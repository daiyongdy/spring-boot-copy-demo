/**
 * <b>项目名：</b>sprint-boot-copy-demo<br/>
 * <b>包名：</b>com.hbdiy.sb.controller<br/>
 * <b>文件名：</b>CustomerController.java<br/>
 * <b>描述：</b><br/>
 * <b>版本信息：</b>v1.0.0<br/>
 * <b>日期：</b>2016年9月7日-下午2:05:21<br/>
 * <b>Copyright (c)</b> 2016<br/>
 */

package com.hbdiy.sb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hbdiy.sb.model.dto.Customer;

/**
 * <b>类名称：</b>CustomerController <br/>
 * <b>类描述：</b><br/>
 * <b>创建人：</b>daiyong<br/>
 * <b>修改人：</b><br/>
 * <b>修改时间：</b>2016年9月7日 下午2:05:21<br/>
 * <b>修改备注：</b><br/>
 * <b>版本信息：</b>v1.0.0<br/>
 */

//@RestController
public class CustomerController {

	@Autowired
	private Customer customer;
	
	@RequestMapping("/get")
	private Customer get() {
		return this.customer;
	}
	
}
