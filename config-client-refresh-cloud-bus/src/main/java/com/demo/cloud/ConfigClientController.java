package com.demo.cloud;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RefreshScope //开启更新功能
public class ConfigClientController {
    //@Value("${server.port}")
    @Value("${profile}")

    private String profile;

    @GetMapping("/profile")
    public @ResponseBody
    String hello() {
        return this.profile;
    }
}
