package com.demo.cloud.controller;

import com.demo.cloud.mapper.UserMapper;
import com.demo.cloud.model.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
@RefreshScope
@Controller
public class UserController {

    @Resource
    private UserMapper userMapper;

    @RequestMapping(value = "/user/{id}",method = RequestMethod.GET)
    @ResponseBody
    public User findById(@PathVariable Long id) {
        return this.userMapper.selectByPrimaryKey(id);
    }

    @Value("${profile}")
    private String profile;
    @GetMapping("/profile")
    @ResponseBody
    public String hello() {
        return this.profile;
    }
}
