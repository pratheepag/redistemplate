package com.example.redistemplate;

import java.time.Duration;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.redis.connection.RedisPassword;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisClientConfiguration;
import org.springframework.data.redis.connection.jedis.JedisClientConfiguration.JedisClientConfigurationBuilder;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

import com.example.redistemplate.model.User;

@SpringBootApplication
public class Redis11Application {

    public static void main(String[] args) {
        SpringApplication.run(Redis11Application.class, args);
    }

    // To make connection to redis server
    @Bean
    JedisConnectionFactory jedisConnectionFactory() {
        
        RedisStandaloneConfiguration redisStandaloneConfiguration = new RedisStandaloneConfiguration("localhost",
                6379);
        redisStandaloneConfiguration.setPassword(RedisPassword.of(""));

        return new JedisConnectionFactory(redisStandaloneConfiguration);

    }

    //
    // To interact with redis server
    @Bean
    RedisTemplate<String, User> redisTemplate() {
        RedisTemplate<String, User> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(jedisConnectionFactory());
        return redisTemplate;
    }

}
