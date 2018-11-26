/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.scheduler.boot.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.session.SessionManagementFilter;

/**
 *
 * @author Grzegorz
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    
    @Autowired
    UserDetailsService userDetailsService;
    
    @Bean
    MyCorsFilter corsFilter() {        
        MyCorsFilter filter = new MyCorsFilter();
        return filter;        
    }
    
    
/*    @Bean
    public UserDetailsService createUserDetailsService() {
        return new UserDetailsServiceImpl();
    }
  */  
    /*
 @Override
  public void configure(AuthenticationManagerBuilder builder)
          throws Exception {
      builder.userDetailsService(userDetailsService);
  }
    */
    
    @Bean
    @Override
    public UserDetailsService userDetailsService() {
        UserDetails user =
             User.withDefaultPasswordEncoder()
                .username("user")
                .password("password")
                .roles("USER")
                .build();

        return new InMemoryUserDetailsManager(user);
    }
    
    @Override
    protected void configure(HttpSecurity http) 
      throws Exception {
        http
                .csrf().disable()// -> /login (POST) will not be authenticated
                .addFilterBefore(corsFilter(), SessionManagementFilter.class)//adds cors filter!!!
            .authorizeRequests()
                .antMatchers(HttpMethod.OPTIONS, "/**").permitAll()
                //.antMatchers("/login*").permitAll()  
                .antMatchers(HttpMethod.OPTIONS,"/tasks").permitAll()
                 .antMatchers(HttpMethod.OPTIONS,"/tasks/task").permitAll()
                .anyRequest()
                .authenticated()
                //.and().formLogin().loginPage("/dupa")
                .and()
                .httpBasic();
        
    }
    
    /*
    @Override
    protected void configure(AuthenticationManagerBuilder auth)
      throws Exception {
        auth
          .inMemoryAuthentication()
          .withUser("user")
          .password("{noop}password")
          //  .password("password")
          .roles("USER");
    }*/
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
