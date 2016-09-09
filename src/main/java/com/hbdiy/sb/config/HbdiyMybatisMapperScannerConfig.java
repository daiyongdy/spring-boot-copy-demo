package com.hbdiy.sb.config;

import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by john on 2016/9/5.
 */
@Configuration
@AutoConfigureAfter(HbdiyMybatisConfig.class)
public class HbdiyMybatisMapperScannerConfig {
    @Bean
    public MapperScannerConfigurer mapperScannerConfigurer() {
        MapperScannerConfigurer mapperScannerConfigurer = new MapperScannerConfigurer();
        mapperScannerConfigurer.setSqlSessionFactoryBeanName("primarySqlSessionFactory");
        mapperScannerConfigurer.setBasePackage("com.hbdiy.**.dao");
        return mapperScannerConfigurer;
    }
}
