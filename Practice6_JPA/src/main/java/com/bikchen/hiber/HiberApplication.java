package com.bikchen.hiber;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.util.List;

@SpringBootApplication
public class HiberApplication {

    public static void main(String[] args) {
        ApplicationContext applicationContext = SpringApplication.run(HiberApplication.class, args);
        UserService userService = applicationContext.getBean(UserService.class);

        UserEntity user1 = userService.createUser("John", "Doe");
        UserEntity user2 = userService.createUser("Jane", "Doe");
        UserEntity user3 = userService.createUser("Obi-Wan", "Kenobi");
        UserEntity user4 = userService.createUser("Plo", "Koon");
        UserEntity user5 = userService.createUser("Otto", "Kombi");

        System.out.println("Saved user: " + user1);
        System.out.println("Saved user: " + user2);
        System.out.println("Saved user: " + user3);
        System.out.println("Saved user: " + user4);
        System.out.println("Saved user: " + user5);

        System.out.println("Fetching all users");

        List<UserEntity> allUsers = userService.getAllUsers();

        for (UserEntity user : allUsers) {
            System.out.println(user);
        }

        System.out.println("Fetching by last name");

        List<UserEntity> allDoes = userService.getUsersByLastName("Doe");

        for (UserEntity user : allDoes) {
            System.out.println(user);
        }

        System.out.println("Fetching by substring");

        List<UserEntity> haveBi = userService.getUsersBySubstring("bi");

        for (UserEntity user : haveBi) {
            System.out.println(user);
        }
    }

}
