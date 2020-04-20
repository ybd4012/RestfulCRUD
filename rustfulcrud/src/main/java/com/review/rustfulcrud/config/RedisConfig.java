package com.review.rustfulcrud.config;

import com.review.rustfulcrud.component.FastJsonRedisSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.*;

import java.net.UnknownHostException;
import java.time.Duration;


@Configuration
public class RedisConfig  {
    /**
     * 自己的猜想  失败
     * @return
     *   @Bean
    public RedisTemplate<Object, Object> employeeRedisTemplate(RedisConnectionFactory redisConnectionFactory)
            throws UnknownHostException {
        RedisTemplate<Object, Object> template = new RedisTemplate<Object, Object>();
        template.setConnectionFactory(redisConnectionFactory);
        //关键的两步骤 1.获取一个jackson序列化 2.将这个序列化引入到模板中
        Jackson2JsonRedisSerializer<Object> serializer = new Jackson2JsonRedisSerializer<Object>(Object.class) ;
        template.setDefaultSerializer(serializer);
        return template;
    }

    @Bean
    public RedisTemplate<Object,Object> redisTemplate(RedisConnectionFactory redisConnectionFactory){
        RedisTemplate<Object, Object> template = new RedisTemplate<>();
        template.setConnectionFactory(redisConnectionFactory);
        // 设置默认的序列化方式为json
        template.setValueSerializer(RedisSerializer.json());
        template.setKeySerializer(RedisSerializer.string());

        template.setHashKeySerializer(RedisSerializer.string());
        template.setHashValueSerializer(RedisSerializer.json());
        template.setDefaultSerializer(RedisSerializer.json());
        return template;
    }
    @Bean
    public StringRedisTemplate stringRedisTemplate(RedisConnectionFactory redisConnectionFactory)
            throws UnknownHostException {
        StringRedisTemplate template = new StringRedisTemplate();
        template.setValueSerializer(RedisSerializer.json());
        template.setKeySerializer(RedisSerializer.string());
        template.setHashKeySerializer(RedisSerializer.string());
        template.setHashValueSerializer(RedisSerializer.json());
        template.setDefaultSerializer(RedisSerializer.json());
        template.setConnectionFactory(redisConnectionFactory);
        return template;
    }
    */
@Bean
public RedisCacheConfiguration redisCacheConfiguration() {

    FastJsonRedisSerializer<Object> fastJsonRedisSerializer = new FastJsonRedisSerializer<Object>(Object.class);
    //获取一个默认的cacheConfig
    RedisCacheConfiguration configuration = RedisCacheConfiguration.defaultCacheConfig();
    /**
     * 不理解的代码
     */
    configuration = configuration.serializeValuesWith(RedisSerializationContext
            .SerializationPair
            .fromSerializer(fastJsonRedisSerializer))
            .entryTtl(Duration.ofDays(30));

    return configuration;
}
}
