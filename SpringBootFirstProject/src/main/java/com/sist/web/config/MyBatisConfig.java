package com.sist.web.config;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
@MapperScan(basePackages="com.sist.web.mapper")
public class MyBatisConfig {
	@Bean
	public SqlSessionFactory SqlSessionFactory(DataSource dataSource) throws Exception{
		SqlSessionFactoryBean fb = new SqlSessionFactoryBean();
		fb.setDataSource(dataSource);
		return fb.getObject();
	}
}
