package com.demo.cloud.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.RuleConstant;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRule;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRuleManager;
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

import javax.annotation.PostConstruct;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;


@RequestMapping(value = "/movies")
@RestController
public class MovieController {
    private static final Logger LOG = LoggerFactory.getLogger(MovieController.class);
    @Autowired
    private UserFeignClient userFeignClient;
    @Autowired
    private LoadBalancerClient loadBalancer;
    @Autowired
    private RestTemplate restTemplate;


    @PostConstruct
    public void test(){
        LOG.info("init initFlowRules");
        initFlowRules();
    }
    @GetMapping("/users/{id}")
    @SentinelResource(value = "findUser", fallback = "findUserFallBack")
    public User findUser(@PathVariable("id") long id){
        //User user = this.restTemplate.getForObject("http://provider-user/user/{id}",User.class, id);

        //负载均衡
        User user = this.userFeignClient.findById(id);
        //打印当前选择的微服务节点
        ServiceInstance instance = loadBalancer.choose("provider-user");
        LOG.info("{}:{}:{}",instance.getServiceId(),instance.getHost(), instance.getPort());


        return user;
    }

    public User findUserFallBack(@PathVariable("id") long id, Throwable throwable){
        LOG.error("进入回退方法", throwable);
        return new User(id,"默认用户","默认用户",0,new BigDecimal(1));
    }

    //流控规则
    private static void initFlowRules(){
        List<FlowRule> rules = new ArrayList<>();
        FlowRule rule = new FlowRule();
        rule.setResource("findUser");
        rule.setGrade(RuleConstant.FLOW_GRADE_QPS);
        // Set limit QPS to 20.
        rule.setCount(10);
        rules.add(rule);
        FlowRuleManager.loadRules(rules);
    }

}
