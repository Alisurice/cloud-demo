package com.demo.cloud.controller;

import com.demo.cloud.model.User;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@SpringBootTest(classes = MovieControllerTest.class)
@RunWith(SpringRunner.class)
class MovieControllerTest {
    //@Resource
    //private MovieController movieController;
    //
    //private MockMvc mockmvc;
    //@Before
    //public void init(){
    //    mockmvc = MockMvcBuilders.standaloneSetup(movieController).build();
    //}

    private int port = 8010;
    @Test
    void testCircuitBreaker2FindUser() throws Exception {
        //mockmvc.perform(get("movies/users/2"));
        System.out.println("$$$$$"+port);
        RestTemplate restTemplate = new RestTemplate();
        for(int j = 0; j < 10; j++){
            Thread thread = new Thread(() -> {
                for(int i = 0; i < 200; i++){
                    System.out.println(Thread.currentThread().getName()+": "+restTemplate.getForObject("http://localhost:{port}/movies/users/1", User.class, port));
                    try {
                        Thread.sleep(300);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
            thread.setName(String.valueOf(j));
            thread.start();

        }


        //必须sleep，不然主线程结束，junit会带着所有的子线程go die，属实恶心人。
       Thread.sleep(20000);

    }
}