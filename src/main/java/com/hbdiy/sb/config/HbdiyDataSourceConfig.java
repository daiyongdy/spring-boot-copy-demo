/**
 * <b>项目名：</b>spring-boot-copy-demo<br/>
 * <b>包名：</b>com.hbdiy.sb.config<br/>
 * <b>文件名：</b>HbdiyDataSourceConfig.java<br/>
 * <b>描述：</b><br/>
 * <b>版本信息：</b>v1.0.0<br/>
 * <b>日期：</b>2016年9月3日-上午10:00:12<br/>
 * <b>Copyright (c)</b> 2016<br/>
 */

package com.hbdiy.sb.config;

import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * <b>类名称：</b>HbdiyDataSourceConfig <br/>
 * <b>类描述：</b><br/>
 * <b>创建人：</b>daiyong<br/>
 * <b>修改人：</b><br/>
 * <b>修改时间：</b>2016年9月3日 上午10:00:12<br/>
 * <b>修改备注：</b><br/>
 * <b>版本信息：</b>v1.0.0<br/>
 */

@Configuration	//相当于xml配置文件
public class HbdiyDataSourceConfig {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(HbdiyDataSourceConfig.class);

	/**
	 * 读写库
	 */
	@Bean(name="primaryDataSource")
	@Primary  
    @ConfigurationProperties(prefix="datasource.primary") 
	public DataSource primaryDataSource() {
		logger.debug("hbdiy primary datasource init...");
		return DataSourceBuilder.create().build();
	}
	
	/**
	 * 只读库  生产环境可以使用dns在多个读库中负载均衡
	 */
	@Bean(name="readDataSource")  
    @ConfigurationProperties(prefix="datasource.read")  
    public DataSource secondaryDataSource() {  
		logger.debug("hbdiy read datasource init...");
        return DataSourceBuilder.create().build();  
    }  
	
	@Bean(name = "primaryJdbcTemplate")
    public JdbcTemplate primaryJdbcTemplate(
            @Qualifier("primaryDataSource") DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }

    @Bean(name = "readJdbcTemplate")
    public JdbcTemplate secondaryJdbcTemplate(
            @Qualifier("readDataSource") DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }
	
}
