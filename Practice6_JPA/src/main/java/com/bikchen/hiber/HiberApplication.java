package com.bikchen.hiber;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class HiberApplication {

    public static void main(String[] args) {
        ApplicationContext applicationContext = SpringApplication.run(HiberApplication.class, args);
        UserService userService = applicationContext.getBean(UserService.class);
    }

}
