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
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;


@SpringBootApplication
@EntityScan("com.scheduler.boot.*")
@ComponentScan("com.scheduler.boot.*")
@EnableResourceServer
public class StringBootRestApplication extends ResourceServerConfigurerAdapter {

    private static final String RESOURCE_ID = "messages-resource";

    @Override
    public void configure(ResourceServerSecurityConfigurer security) throws Exception {
        security.resourceId(RESOURCE_ID).stateless(false);
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http
                .antMatcher("/*")
                .authorizeRequests().anyRequest().authenticated();
    }

    public static void main(String[] args) {
        SpringApplication springApplication=new SpringApplication(StringBootRestApplication.class);
        springApplication.run(args);
    }    
}