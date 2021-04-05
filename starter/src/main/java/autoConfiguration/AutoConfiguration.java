package autoConfiguration;

import config.OkkristenProperties;
import config.PrefixConfig;
import config.MybatisConfig;
import hello.HelloWorld;
import hello.TestController;
import mapper.TestMapper;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import service.UserService;
import service.impl.UserServiceImpl;

/**
 * @author okkristen
 * @date 2021年03月27日 15:35
 * 自动装配配置类
 */
@Configuration // 配置注解
//@EnableConfigurationProperties(PersonServiceProperties.class) // 开启指定类的配置
//@ConditionalOnClass(HelloWorld.class)// 当PersonService这个类在类路径中时，且当前容器中没有这个Bean的情况下，开始自动配置
@ConditionalOnProperty(prefix= PrefixConfig.prefix, value="enabled", matchIfMissing=true)// 指定的属性是否有指定的值
public class AutoConfiguration {

    @Bean
    public HelloWorld helloWorld(){
        HelloWorld  helloWorld = new HelloWorld();
        return helloWorld;
    }

    @Bean
    public TestController testController(){
        TestController  testController = new TestController();
        return testController;
    }


    @Bean
    public MybatisConfig mybatisConfig() {
        return new MybatisConfig();
    }
    @Bean
    public UserService userService(){
        UserService  userService = new UserServiceImpl();
        return userService;
    }



}
