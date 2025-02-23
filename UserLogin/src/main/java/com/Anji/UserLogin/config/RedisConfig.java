package com.Anji.UserLogin.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;

@Configuration
public class RedisConfig {

    // Bean to configure RedisTemplate for interacting with Redis
    @Bean
    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory connectionFactory) {
        RedisTemplate<String, Object> template = new RedisTemplate<>();

        // Set the Redis connection factory (handles Redis connections)
        template.setConnectionFactory(connectionFactory);

        // Set up the serializer for the keys and values in Redis
        template.setKeySerializer(new StringRedisSerializer());  // Use StringSerializer for keys
        template.setValueSerializer(new GenericJackson2JsonRedisSerializer());  // Use JSON serializer for values
        return template;
    }
}
