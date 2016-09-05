/**
 * <b>项目名：</b>spring-boot-copy-demo<br/>
 * <b>包名：</b>com.hbdiy.sb.mybatis<br/>
 * <b>文件名：</b>CommDao.java<br/>
 * <b>描述：</b><br/>
 * <b>版本信息：</b>v1.0.0<br/>
 * <b>日期：</b>2016年9月3日-下午7:12:54<br/>
 * <b>Copyright (c)</b> 2016<br/>
 */

package com.hbdiy.sb.mybatis;

import org.springframework.stereotype.Repository;

/**
 * <b>类名称：</b>CommDao <br/>
 * <b>类描述：</b><br/>
 * <b>创建人：</b>daiyong<br/>
 * <b>修改人：</b><br/>
 * <b>修改时间：</b>2016年9月3日 下午7:12:54<br/>
 * <b>修改备注：</b><br/>
 * <b>版本信息：</b>v1.0.0<br/>
 */

@Repository
public class CommDAO {
	
	public SqlMapper init(String sqlId) {
		String namespace = "com.hbdiy.sb.dao.UserMapper."; //方便测试 写死
		return new SqlMapper(namespace);
	}
	
	public TableMapper init(Class<?> cls) {
		String namespace = "com.hbdiy.sb.dao.UserMapper."; //方便测试 写死
		return new TableMapper(namespace);
	}
	
}
