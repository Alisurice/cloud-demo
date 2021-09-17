package com.demo.cloud.controller;

import com.demo.cloud.mapper.UserMapper;
import com.demo.cloud.model.User;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@Controller
public class UserController {

    @Resource
    private UserMapper userMapper;

    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    @ResponseBody
    public User findById(@PathVariable Long id) {
        return this.userMapper.selectByPrimaryKey(id);
    }


}
