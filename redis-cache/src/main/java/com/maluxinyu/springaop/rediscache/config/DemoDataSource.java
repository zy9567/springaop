package com.maluxinyu.springaop.rediscache.config;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import com.baomidou.mybatisplus.extension.spring.MybatisSqlSessionFactoryBean;
import com.maluxinyu.springaop.rediscache.persistence.mybatisplus.mapper.SchoolMapper;

/**
 * @ClassName DemoDataSource
 * @Author zhangyu
 * @Description TODO
 * @Date 2023/8/10
 **/
@Configuration
@MapperScan(basePackageClasses = SchoolMapper.class, sqlSessionFactoryRef = DemoDataSource.sqlSessionFactory)
public class DemoDataSource {

    final static String sqlSessionTemplate = "DemoDbSqlSessionTemplate";

    final static String sqlSessionFactory = "DemoSqlSessionFactory";

    final static String dataSource = "DemoDataSource";

    final static String transactionManager = "DemoDbTransactionManager";

    @Bean(name = DemoDataSource.dataSource)
    @ConfigurationProperties("spring.datasource.demo")
    public DataSource dataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = DemoDataSource.sqlSessionFactory)
    public SqlSessionFactory sqlSessionFactory(@Qualifier(DemoDataSource.dataSource) DataSource dataSource) throws Exception {
        // MyBatis-Plus使用MybatisSqlSessionFactoryBean  MyBatis直接使用SqlSessionFactoryBean
        MybatisSqlSessionFactoryBean factoryBean = new MybatisSqlSessionFactoryBean();
        // 给MyBatis-Plus注入数据源
        factoryBean.setDataSource(dataSource);

        return factoryBean.getObject();
    }

    @Bean(name = DemoDataSource.sqlSessionTemplate)
    public SqlSessionTemplate sqlSessionTemplate(@Qualifier(DemoDataSource.sqlSessionFactory) SqlSessionFactory sqlSessionFactory) {
        return new SqlSessionTemplate(sqlSessionFactory);
    }

    @Bean(name = DemoDataSource.transactionManager)
    public DataSourceTransactionManager transactionManager(@Qualifier(DemoDataSource.dataSource) DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

}
