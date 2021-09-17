package com.demo.cloud.controller;

import com.demo.cloud.feign.UserFeignClient;
import com.demo.cloud.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;


@RequestMapping(value = "/movies")
@RestController
public class MovieController {
    private static final Logger LOG = LoggerFactory.getLogger(MovieController.class);
    @Autowired
    private UserFeignClient userFeignClient;
    @Autowired
    private LoadBalancerClient loadBalancer;

    @GetMapping("/users/{id}")
    public User findUser(@PathVariable long id){
        //User user = this.restTemplate.getForObject("http://localhost:8000/users/{id}",User.class, id);

        //负载均衡
        User user = this.userFeignClient.findById(id);
        //打印当前选择的微服务节点
        ServiceInstance instance = loadBalancer.choose("provider-user");
        LOG.info("{}:{}:{}",instance.getServiceId(),instance.getHost(), instance.getPort());


        return user;
    }

}
