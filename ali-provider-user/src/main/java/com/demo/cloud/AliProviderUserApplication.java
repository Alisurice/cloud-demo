package com.demo.cloud;

import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.Environment;

import java.net.InetAddress;
import java.net.UnknownHostException;


@MapperScan("com.demo.cloud.mapper")
@SpringBootApplication
public class AliProviderUserApplication {

    public static void main(String[] args) {
        String className = Thread.currentThread().getStackTrace()[1].getClassName();
        //System.out.println(className);
        Logger log = LoggerFactory.getLogger(className);
        ConfigurableApplicationContext application = null;
        try {
            application = SpringApplication.run(Class.forName(className), args);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        Environment env = application.getEnvironment();

        String ip = null;
        try {
            ip = InetAddress.getLocalHost().getHostAddress();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }

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
