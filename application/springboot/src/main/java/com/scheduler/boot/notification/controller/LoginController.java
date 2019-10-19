/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.scheduler.boot.notification.controller;

import com.scheduler.users.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Grzegorz
 */
@RestController
public class LoginController {
    
    @Autowired
    private UserDetailsService userDetailService;
    
    @RequestMapping( path = "/login", method = RequestMethod.POST)
    public boolean login(@RequestBody User user) {
        //TODO: catch usernme not found
        /*UserDetails details = userDetailService.loadUserByUsername(user.getUser());
        if(details == null){
            return false;
        }
        
        return new BCryptPasswordEncoder().matches(user.getPassword(), details.getPassword().replace("{bcrypt}", ""));*/
        return false;
    }
        
}
