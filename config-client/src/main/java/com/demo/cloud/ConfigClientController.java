package com.demo.cloud;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ConfigClientController {
    @Value("${server.port}")
    //@Value("${profile}")

    private String profile;

    @GetMapping("/profile")
    public @ResponseBody String hello() {
        return this.profile;
    }
}
