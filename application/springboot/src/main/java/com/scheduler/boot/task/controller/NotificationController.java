/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.scheduler.boot.task.controller;

import com.scheduler.boot.SendEmail;
import com.scheduler.boot.task.service.TaskSpringService;
import com.scheduler.tasks.TaskDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Grzegorz
 */
@RestController
@CrossOrigin
public class NotificationController {
 
    @Autowired
    private SendEmail sendEmail;
    
    @RequestMapping(path = "/email", produces = "application/json")
    public void sendEmail(){
        sendEmail.sendMail("jomjom@poczta.onet.pl", "test");
    }
}
