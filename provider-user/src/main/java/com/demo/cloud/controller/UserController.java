package com.demo.cloud.controller;

import com.demo.cloud.mapper.UserMapper;
import com.demo.cloud.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

@Controller
public class UserController {
    @Resource
    private UserMapper userMapper;

    @GetMapping("/{id}")
    @ResponseBody
    public User findById(@PathVariable Long id) {
        return this.userMapper.selectByPrimaryKey(id);
    }
}