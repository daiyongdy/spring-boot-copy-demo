/**
 * <b>项目名：</b>spring-boot-copy-demo<br/>
 * <b>包名：</b>com.hbdiy.sb.controller<br/>
 * <b>文件名：</b>UserController.java<br/>
 * <b>描述：</b><br/>
 * <b>版本信息：</b>v1.0.0<br/>
 * <b>日期：</b>2016年9月3日-上午10:25:29<br/>
 * <b>Copyright (c)</b> 2016<br/>
 */

package com.hbdiy.sb.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.hbdiy.sb.model.User;
import com.hbdiy.sb.service.UserService;
import com.hbdiy.sb.util.redis.RedisUtil;

/**
 * <b>类名称：</b>UserController <br/>
 * <b>类描述：</b><br/>
 * <b>创建人：</b>daiyong<br/>
 * <b>修改人：</b><br/>
 * <b>修改时间：</b>2016年9月3日 上午10:25:29<br/>
 * <b>修改备注：</b><br/>
 * <b>版本信息：</b>v1.0.0<br/>
 */

@Controller
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private UserService userService;

	@RequestMapping("/add")
	@ResponseBody
	public String add(User user) {
		/*User user = new User();
		user.setUserId("0");
		user.setUsername("daiyong");
		user.setPassword("111111");*/
		
		this.userService.addByTemplate(user);
		
		return "创建用户成功!";
	}
	
	@RequestMapping("/delete")
	@ResponseBody
	public String delete(String id) {
		this.userService.deleteByTemplate(id);
		
		return "删除用户成功!";
	}
	
	@RequestMapping("/update")
	@ResponseBody
	public String delete(User user) {
		this.userService.updateByTemplate(user);
		
		return "更新用户成功!";
	}
	
	@RequestMapping("/get")
	public String get(String id, Model model) {
		User user = this.userService.selectByTemplate(id);
		model.addAttribute("user", user);
		return "/user/show";
	}
	
	@RequestMapping("/get2")
	public String get2(String id, Model model) {
		User user = this.userService.selectByMybatis(id);
		model.addAttribute("user", user);
		return "/user/show";
	}
	
	/** 
	 * 测试读写分离与分页插件
	 * @author daiyong
	 */
	@RequestMapping("/getByPage")
	public String getByPage(Model model, int page, int rows) {
		List<User> users = this.userService.selectByPage(page, rows);
		model.addAttribute("users", users);
		return "/user/list";
	}
	
	@RequestMapping("/redis")
	@ResponseBody
	public String redis() {
		User user = this.userService.selectByMybatis("0");
		RedisUtil.set("aa", JSON.toJSONString(user));
		return "redis success!";
	}
	
}	
