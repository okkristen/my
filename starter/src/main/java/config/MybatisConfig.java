package config;

import com.alibaba.druid.pool.DruidDataSource;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.defaults.DefaultSqlSession;
import org.apache.ibatis.session.defaults.DefaultSqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.core.annotation.Order;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;

/**
 * @author okkristen
 * @date 2021年03月27日 20:43
 */
@Component
//@MapperScan("mapper")
public class MybatisConfig {

    @Bean
    public OkkristenProperties okkristenProperties() {
        return new OkkristenProperties();
    }

    @Bean
    public DataSource dataSource(@Qualifier("okkristenProperties") OkkristenProperties okkristenProperties) {
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setDriverClassName(okkristenProperties.getDriverClass());
        dataSource.setUrl(okkristenProperties.getJdbcUrl());
        dataSource.setUsername(okkristenProperties.getUsername());
        dataSource.setPassword(okkristenProperties.getPassword());
        return dataSource;
    }

    @Bean
    public SqlSessionFactoryBean sqlSessionFactoryBean(@Qualifier("dataSource") DataSource dataSource) {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSource);
        sqlSessionFactoryBean.setTypeAliasesPackage("entity");
        return  sqlSessionFactoryBean;
    }



    @Bean
    public MapperScannerConfigurer mapperScannerConfigurer() {
        MapperScannerConfigurer mapperScannerConfigurer  = new MapperScannerConfigurer();
        mapperScannerConfigurer.setSqlSessionFactoryBeanName("sqlSessionFactoryBean");
        mapperScannerConfigurer.setBasePackage("mapper");
        return mapperScannerConfigurer;
    }
    @Bean
    public DataSourceTransactionManager DataSourceTransactionManager(@Qualifier("dataSource") DataSource dataSource) {
        DataSourceTransactionManager sourceTransactionManager = new DataSourceTransactionManager(dataSource);
        return  sourceTransactionManager;
    }



}
