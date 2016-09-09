/**
 * <b>项目名：</b>spring-boot-copy-demo<br/>
 * <b>包名：</b>com.hbdiy.sb.config<br/>
 * <b>文件名：</b>HbiydMybatisConfig.java<br/>
 * <b>描述：</b><br/>
 * <b>版本信息：</b>v1.0.0<br/>
 * <b>日期：</b>2016年9月3日-下午5:55:53<br/>
 * <b>Copyright (c)</b> 2016<br/>
 */

package com.hbdiy.sb.config;

import com.github.pagehelper.PageHelper;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.TransactionManagementConfigurer;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.io.IOException;
import java.util.Properties;

/**
 * <b>类名称：</b>HbiydMybatisConfig <br/>
 * <b>类描述：</b><br/>
 * <b>创建人：</b>daiyong<br/>
 * <b>修改人：</b><br/>
 * <b>修改时间：</b>2016年9月3日 下午5:55:53<br/>
 * <b>修改备注：</b><br/>
 * <b>版本信息：</b>v1.0.0<br/>
 */

@Configuration
@EnableTransactionManagement
public class HbdiyMybatisConfig implements TransactionManagementConfigurer {

    @Resource(name = "primaryDataSource")
    private DataSource primaryDataSource;

    @Resource(name = "readDataSource")
    private DataSource readDataSource;

    @Bean(name = "primarySqlSessionFactory")
    public SqlSessionFactory getPrimarySqlSessionFactory() {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(primaryDataSource);
        setCommonSqlSessionFactory(bean);

        try {
            return bean.getObject();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("sqlSessionFactory init fail", e);
        }
    }

    @Bean(name = "readSqlSessionFactory")
    public SqlSessionFactory getReadSqlSessionFactory() {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(readDataSource);
        setCommonSqlSessionFactory(bean);

        try {
            return bean.getObject();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("sqlSessionFactory init fail", e);
        }
    }

    /**
     * sqlSessionFactoryBean 公共配置
     */
    private void setCommonSqlSessionFactory(SqlSessionFactoryBean bean) {
        bean.setTypeAliasesPackage("com.hbdiy.sb.model");

        //配置
        org.apache.ibatis.session.Configuration configuration = new org.apache.ibatis.session.Configuration();
        configuration.setCacheEnabled(false);
        configuration.setUseGeneratedKeys(true);
        configuration.setDefaultExecutorType(ExecutorType.REUSE);
        bean.setConfiguration(configuration);

        //分页插件
        PageHelper pageHelper = new PageHelper();
        Properties properties = new Properties();
        properties.setProperty("dialect", "mysql");
        properties.setProperty("offsetAsPageNum", "true");
        properties.setProperty("rowBoundsWithCount", "true");
        properties.setProperty("pageSizeZero", "true");
        properties.setProperty("reasonable", "true");
        properties.setProperty("params", "pageNum=start;pageSize=limit;pageSizeZero=zero;reasonable=heli;count=contsql");
        pageHelper.setProperties(properties);
        bean.setPlugins(new Interceptor[]{pageHelper});

        //添加XML目录
        ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        try {
            bean.setMapperLocations(resolver.getResources("classpath*:/com/hbdiy/**/mapper/**/*Mapper.xml"));
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }

    /**
     * 主库sqlsessionTemplate
     */
    @Bean(name = "primarySqlSessionTemplate")
    public SqlSessionTemplate setPrimarySqlSessionTemplate() {
        SqlSessionTemplate template = new SqlSessionTemplate(this.getPrimarySqlSessionFactory());
        return template;
    }

    /**
     * 从库sqlsessionTemplate
     */
    @Bean(name = "readSqlSessionTemplate")
    public SqlSessionTemplate setReadSqlSessionTemplate() {
        SqlSessionTemplate template = new SqlSessionTemplate(this.getReadSqlSessionFactory());
        return template;
    }

    /**
     * 事务注解
     */
    @Override
    @Bean(name = "transactionManager")
    public PlatformTransactionManager annotationDrivenTransactionManager() {
        return new DataSourceTransactionManager(primaryDataSource);
    }


}
