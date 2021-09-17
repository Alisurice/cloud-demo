package com.demo.cloud.feign;

import com.demo.cloud.model.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "provider-user")
public interface UserFeignClient {
    @GetMapping("/{id}")
    User findById(@PathVariable("id") Long id);
}
