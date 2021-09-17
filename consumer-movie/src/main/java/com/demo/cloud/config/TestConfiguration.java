package com.demo.cloud.config;


import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.context.annotation.Configuration;

@Configuration
//貌似2020.3的spring cloud不支持这种方式，Hoxton.SR6倒是支持
@RibbonClient(name = "provider-user",configuration = RibbonConfiguration.class)
public class TestConfiguration {
}
