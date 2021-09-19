package com.demo.cloud;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class controller {
    //@Value("${spring.cloud.config.server.native.search-locations}")
    String v;
    @GetMapping("/address")
    public @ResponseBody String getAddress(){
        return v;
    }
}
