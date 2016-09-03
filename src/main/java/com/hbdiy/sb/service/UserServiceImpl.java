/**
 * <b>项目名：</b>spring-boot-copy-demo<br/>
 * <b>包名：</b>com.hbdiy.sb.service<br/>
 * <b>文件名：</b>UserServiceImpl.java<br/>
 * <b>描述：</b><br/>
 * <b>版本信息：</b>v1.0.0<br/>
 * <b>日期：</b>2016年9月3日-上午10:15:02<br/>
 * <b>Copyright (c)</b> 2016<br/>
 */

package com.hbdiy.sb.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.omg.CORBA.UserException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hbdiy.sb.model.User;
import com.hbdiy.sb.model.UserExample;

/**
 * <b>类名称：</b>UserServiceImpl <br/>
 * <b>类描述：</b><br/>
 * <b>创建人：</b>daiyong<br/>
 * <b>修改人：</b><br/>
 * <b>修改时间：</b>2016年9月3日 上午10:15:02<br/>
 * <b>修改备注：</b><br/>
 * <b>版本信息：</b>v1.0.0<br/>
 */

@Service
public class UserServiceImpl extends BaseService implements UserService{
	
	@Resource(name="primaryJdbcTemplate")
	private JdbcTemplate primaryJdbcTemplate;
	
	@Resource(name="readJdbcTemplate")
	private JdbcTemplate readJdbcTemplate;

	/**
	 * @author daiyong
	 */
	@Override
	@Transactional
	public int addByTemplate(User user) {
		String sql = "insert into user (user_id, username, password) values (\"" + user.getUserId() + "\", \"" + user.getUsername() + "\", \"" + user.getPassword() + "\")";
		this.primaryJdbcTemplate.execute(sql);
		
		//测试事务
//		int i = 1 / 0;	//
		
		return 0;
	}

	/**
	 * @author daiyong
	 */
	@Override
	@Transactional
	public int deleteByTemplate(String id) {
		String sql = "delete from user where user_id = " + id;
		this.primaryJdbcTemplate.execute(sql);
		return 0;
	}

	/**
	 * @author daiyong
	 */
	@Override
	@Transactional
	public int updateByTemplate(User user) {
		String sql = "update user set username = \"" + user.getUsername() + "\", password=\"" + user.getPassword() + "\" where user_id=\"" + user.getUserId() + "\" ";
		this.primaryJdbcTemplate.execute(sql);
		return 0;
	}

	/**
	 * @author daiyong
	 */
	@Override
	public User selectByTemplate(String id) {
		// FDY Auto-generated method stub
		String sql = "select user_id as userId, username as username, password as password from user where user_id = \"" + id + "\"";
		Map map = this.readJdbcTemplate.queryForMap(sql);
		User user = new User();
		user.setUserId((String)map.get("userId"));
		user.setUsername((String)map.get("username"));
		user.setPassword((String)map.get("password"));
		return user;
	}
	
	@Override
	public User selectByMybatis(String id) {
		User user = super.commDAO.init(User.class).selectByPrimaryKey(id);
		return user;
	}

	/**
	 * @author daiyong
	 */
	@Override
	public List<User> selectByPage(int page, int rows) {
		List<User> result = super.commDAO.init(User.class).selectByPage(page, rows, null);
		return result;
	}

}
