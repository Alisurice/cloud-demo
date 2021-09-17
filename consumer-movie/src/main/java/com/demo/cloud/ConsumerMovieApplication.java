package com.demo.cloud;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import org.springframework.web.client.RestTemplate;

import java.net.InetAddress;
import java.net.UnknownHostException;

@EnableFeignClients
@SpringBootApplication
public class ConsumerMovieApplication {
    @Bean
    @LoadBalanced
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }
    public static void main(String[] args) throws UnknownHostException {
        Logger log = LoggerFactory.getLogger(ConsumerMovieApplication.class);
        ConfigurableApplicationContext application = SpringApplication.run(ConsumerMovieApplication.class, args);
        Environment env = application.getEnvironment();

        String ip = InetAddress.getLocalHost().getHostAddress();

        String port = env.getProperty("server.port");
        String path = env.getProperty("server.servlet.context-path");
        if (path==null || path.isEmpty()) {
            path = "";
        }
        log.info("\n----------------------------------------------------------\n\t" +
                "Application  is running! Access URLs:\n\t" +
                "Local访问网址: \t\thttp://localhost:" + port + path + "\n\t" +
                "External访问网址: \thttp://" + ip + ":" + port + path + "\n\t" +
                "----------------------------------------------------------");
    }

}
