package com.scheduler.boot.user;

import com.scheduler.users.User;
import com.scheduler.users.UserImpl;
import com.scheduler.users.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UserBean {

    @Autowired
    UserRepository userRepository;

    @Bean
    public User user(){
        return new UserImpl(userRepository);
    }
}
