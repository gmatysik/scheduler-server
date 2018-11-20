/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.scheduler.boot.security;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 *
 * @author Grzegorz
 */
//@Service
public class UserDetailsServiceImpl implements UserDetailsService{

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println("username: " + username);
         if(username.equals("user")) {
            return User.withDefaultPasswordEncoder()
                       .username("user")
                       .password("password")
                       .roles("test")
                       .build();
        } else if(username.equals("user2")) {
            return User.withDefaultPasswordEncoder()
                       .username("user2")
                       .password("password")
                       .roles("test")
                       .build();
        } else {
            return null;
        }
    }
    
}
