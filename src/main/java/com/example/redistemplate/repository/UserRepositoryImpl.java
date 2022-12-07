package com.example.redistemplate.repository;

import java.util.Map;

import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import com.example.redistemplate.model.User;

@Repository
public class UserRepositoryImpl implements UserRepository {

    private RedisTemplate<String, User> redisTemplate;

    private HashOperations hashOperation;

    public UserRepositoryImpl(RedisTemplate<String, User> redisTemplate) {
        super();
        this.redisTemplate = redisTemplate;
        hashOperation = redisTemplate.opsForHash();
    }

    @Override
    public void save(User user) {
        hashOperation.put("USER", user.getId(), user);
    }

    @Override
    public Map<String, User> findAll() {
        return hashOperation.entries("USER");
    }

    @Override
    public void update(User user) {
        hashOperation.put("USER", user.getId(), user);
    }

    @Override
    public void delete(String id) {
        hashOperation.delete("USER", id);
    }

    @Override
    public User findById(String id) {
        return (User) hashOperation.get("USER", id);
    }

}
