package com.demo.cloud.controller;

import com.demo.cloud.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RequestMapping("/movies")
@RestController
public class MovieController {
    @Autowired
    private RestTemplate restTemplate;
    @GetMapping("/users/{id}")
    public User findUser(@PathVariable long id){
        User user = this.restTemplate.getForObject("http://localhost:8000/{id}",User.class, id);
        return user;
    }
}