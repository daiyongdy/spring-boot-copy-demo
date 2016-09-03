/**
 * <b>项目名：</b>spring-boot-copy-demo<br/>
 * <b>包名：</b>com.hbdiy.sb.service<br/>
 * <b>文件名：</b>BaseService.java<br/>
 * <b>描述：</b><br/>
 * <b>版本信息：</b>v1.0.0<br/>
 * <b>日期：</b>2016年9月3日-下午8:03:42<br/>
 * <b>Copyright (c)</b> 2016<br/>
 */

package com.hbdiy.sb.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.hbdiy.sb.mybatis.CommDAO;

/**
 * <b>类名称：</b>BaseService <br/>
 * <b>类描述：</b><br/>
 * <b>创建人：</b>daiyong<br/>
 * <b>修改人：</b><br/>
 * <b>修改时间：</b>2016年9月3日 下午8:03:42<br/>
 * <b>修改备注：</b><br/>
 * <b>版本信息：</b>v1.0.0<br/>
 */

public class BaseService {
	@Autowired
	protected CommDAO commDAO;
}
