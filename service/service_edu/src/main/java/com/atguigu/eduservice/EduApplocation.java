package com.atguigu.eduservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @version 1.0
 * @auther 刘欢龙
 */
@SpringBootApplication
@ComponentScan(basePackages = {"com.atguigu"})
public class EduApplocation {
    public static void main(String[] args) {
        SpringApplication.run(EduApplocation.class,args);
    }
}
