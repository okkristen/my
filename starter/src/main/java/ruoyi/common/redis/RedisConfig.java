package ruoyi.common.redis;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.stereotype.Component;
import redis.clients.jedis.JedisPoolConfig;
/**
 * redis配置
 * @author okkristen
 * @date 2021年04月04日 17:34
 */
@Component
public class RedisConfig {

    @Value("${redis.pool.max-idle:1}")
    private Integer maxIdle;


    @Value("${redis.pool.max-total:5}")
    private Integer maxTotal;

    @Value("${redis.host-name:127.0.0.1}")
    private String hostName;

    @Value("${redis.port:6379}")
    private Integer port;



    @Bean
    public JedisPoolConfig jedisPoolConfig() {
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMaxIdle(maxIdle);
        jedisPoolConfig.setMaxTotal(maxTotal);
//        jedisPoolConfig.setBlockWhenExhausted(Boolean.TRUE);
//        jedisPoolConfig.setMaxWaitMillis(Long.valueOf("30000"));
//        jedisPoolConfig.setTestOnBorrow(Boolean.TRUE);
        return jedisPoolConfig;
    }

    @Bean
    public JedisConnectionFactory jedisConnectionFactory(@Qualifier("jedisPoolConfig") JedisPoolConfig jedisPoolConfig) {
        JedisConnectionFactory jedisConnectionFactory = new JedisConnectionFactory();
        jedisConnectionFactory.setPoolConfig(jedisPoolConfig);
        jedisConnectionFactory.setHostName(hostName);
        jedisConnectionFactory.setPort(port);
//        jedisConnectionFactory.setUsePool(Boolean.TRUE);
        return  jedisConnectionFactory;
    }


    @Bean
    public RedisTemplate redisTemplate(@Qualifier("jedisConnectionFactory") JedisConnectionFactory jedisConnectionFactory) {
        RedisTemplate  redisTemplate = new RedisTemplate();
        redisTemplate.setConnectionFactory(jedisConnectionFactory);
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setValueSerializer(new JdkSerializationRedisSerializer());
        redisTemplate.setHashKeySerializer(new StringRedisSerializer());
        redisTemplate.setHashValueSerializer(new JdkSerializationRedisSerializer());
        return  redisTemplate;
    }

//    @Bean
//    public RedisCache redisCache() {
//        return  new RedisCache();
//    }

}
