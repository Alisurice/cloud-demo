package com.demo.cloud.providerusersleuth.controller;

import com.demo.cloud.mapper.UserMapper;
import com.demo.cloud.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

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
