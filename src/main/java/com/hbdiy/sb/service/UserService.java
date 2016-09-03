/**
 * <b>项目名：</b>spring-boot-copy-demo<br/>
 * <b>包名：</b>com.hbdiy.sb<br/>
 * <b>文件名：</b>UserService.java<br/>
 * <b>描述：</b><br/>
 * <b>版本信息：</b>v1.0.0<br/>
 * <b>日期：</b>2016年9月3日-上午10:11:34<br/>
 * <b>Copyright (c)</b> 2016<br/>
 */

package com.hbdiy.sb.service;

import java.util.List;

import com.hbdiy.sb.model.User;

/**
 * <b>类名称：</b>UserService <br/>
 * <b>类描述：</b><br/>
 * <b>创建人：</b>daiyong<br/>
 * <b>修改人：</b><br/>
 * <b>修改时间：</b>2016年9月3日 上午10:11:34<br/>
 * <b>修改备注：</b><br/>
 * <b>版本信息：</b>v1.0.0<br/>
 */

public interface UserService {

	/****************jdbctemplate**********************/
	
	public int addByTemplate(User user);
	
	public int deleteByTemplate(String id);
	
	public int updateByTemplate(User user);
	
	public User selectByTemplate(String id);
	
	
	/****************mybatis**********************/
	public User selectByMybatis(String id);
	
	public List<User> selectByPage(int page, int rows);
}
