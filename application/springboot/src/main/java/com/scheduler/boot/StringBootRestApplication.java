/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the templatei n the editor.
 */
package com.scheduler.boot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EntityScan("com.scheduler.boot.*")
@ComponentScan("com.scheduler.boot.*")
public class StringBootRestApplication {
 
     public static void main(String[] args) {
        SpringApplication springApplication=new SpringApplication(StringBootRestApplication.class);
        springApplication.run(args);
    }    
}