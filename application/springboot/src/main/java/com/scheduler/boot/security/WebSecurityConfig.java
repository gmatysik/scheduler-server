/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.scheduler.boot.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.session.SessionManagementFilter;

/**
 *
 * @author Grzegorz
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    
    @Bean
    MyCorsFilter corsFilter() {
        
        MyCorsFilter filter = new MyCorsFilter();
        return filter;
        
    }
    
    
    @Override
    protected void configure(HttpSecurity http) 
      throws Exception {
        http
                .csrf().disable()// -> /login (POST) will not be authenticated
                .addFilterBefore(corsFilter(), SessionManagementFilter.class)//adds cors filter!!!
            .authorizeRequests()
                .antMatchers(HttpMethod.OPTIONS, "/**").permitAll()
                .antMatchers("/login*").permitAll()  
                .antMatchers(HttpMethod.OPTIONS,"/tasks").permitAll()
                 .antMatchers(HttpMethod.OPTIONS,"/tasks/task").permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .httpBasic();
        
    }
    
    
    @Override
    protected void configure(AuthenticationManagerBuilder auth)
      throws Exception {
        auth
          .inMemoryAuthentication()
          .withUser("user")
          .password("{noop}password")
          //  .password("password")
          .roles("USER");
    }
    /*
    @Bean
    CorsConfigurationSource corsConfigurationSource() {
         CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList("http://localhost:4200"));
        configuration.setAllowedMethods(Arrays.asList("GET","POST"));
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
 }*/
}
