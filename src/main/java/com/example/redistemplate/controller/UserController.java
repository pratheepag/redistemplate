package com.example.redistemplate.controller;

import java.util.Map;
import java.util.Optional;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.*;

import com.example.redistemplate.model.User;
import com.example.redistemplate.repository.UserRepository;

@RestController
public class UserController {

  private static final Logger LOG = Logger.getLogger(UserController.class.getName());

  private final UserRepository userRepository;

  @Autowired
  public UserController(UserRepository userRepository) {
    this.userRepository = userRepository;
  }
  
  @GetMapping("/add/{id}/{name}")
  public User add(@PathVariable("id") final String id, @PathVariable("name") final String name) {
      userRepository.save(new User(id, name, 20000L));
      return userRepository.findById(id);
  }
  
  @GetMapping("/update/{id}/{name}")
  public User update(@PathVariable("id") final String id, @PathVariable("name") final String name) {
      userRepository.update(new User(id, name, 10000L));
      return userRepository.findById(id);
  }
  
  @GetMapping("/all")
  public Map<String, User> getAll() {
      return userRepository.findAll();
  }

}
