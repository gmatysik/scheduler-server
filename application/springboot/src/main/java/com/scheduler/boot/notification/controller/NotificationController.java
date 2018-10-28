/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.scheduler.boot.notification.controller;

import com.scheduler.boot.notification.service.NotificationSpringService;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
/**
 *
 * @author Grzegorz
 */
@RestController
@CrossOrigin
public class NotificationController {
    
        
    @Autowired
    private NotificationSpringService notificationService;
    
    @RequestMapping(path = "/remind", produces = "application/json")
    public void sendNotifications(){
        notificationService.sendNotificationsForUser();
    }
}