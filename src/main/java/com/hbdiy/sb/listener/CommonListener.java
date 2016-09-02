/**
 * <b>项目名：</b>spring-boot-copy-demo<br/>
 * <b>包名：</b>com.hbdiy.sb.listener<br/>
 * <b>文件名：</b>CommonListener.java<br/>
 * <b>描述：</b><br/>
 * <b>版本信息：</b>v1.0.0<br/>
 * <b>日期：</b>2016年9月2日-下午8:58:42<br/>
 * <b>Copyright (c)</b> 2016<br/>
 */

package com.hbdiy.sb.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * <b>类名称：</b>CommonListener <br/>
 * <b>类描述：</b><br/>
 * <b>创建人：</b>daiyong<br/>
 * <b>修改人：</b><br/>
 * <b>修改时间：</b>2016年9月2日 下午8:58:42<br/>
 * <b>修改备注：</b><br/>
 * <b>版本信息：</b>v1.0.0<br/>
 */

public class CommonListener implements ServletContextListener{

	/**
	 * @author daiyong
	 */
	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		// FDY Auto-generated method stub
		
	}

	/**
	 * @author daiyong
	 */
	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		System.out.println("CommonListener init...");
	}

}
