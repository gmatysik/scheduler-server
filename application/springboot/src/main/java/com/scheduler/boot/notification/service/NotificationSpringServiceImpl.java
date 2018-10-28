/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.scheduler.boot.notification.service;

import com.scheduler.notification.send.Notification;
import com.scheduler.notification.send.NotificationFactory;
import com.scheduler.notification.send.NotificationSender;
import com.scheduler.notification.send.NotificationValidationException;
import com.scheduler.tasks.TaskRepository;
import com.scheduler.tasks.TaskServiceFactory;
import javax.annotation.PostConstruct;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Grzegorz
 */
@Service
class NotificationSpringServiceImpl implements NotificationSpringService{

    private final static Logger LOGGER = LogManager.getLogger(NotificationSpringServiceImpl.class);
    
    private Notification notification;
    
    @Autowired
    private NotificationSender sender;

    @Autowired
    private TaskRepository taskRepository;
    
     @PostConstruct
    public void init() {
        notification = NotificationFactory.createNotification(sender, TaskServiceFactory.createTaskService(taskRepository));
    }

   
    @Override
    public void sendNotificationsForUser() {
        try {
            notification.sendNextSevenDaysReminderForUser(0);
        } catch (NotificationValidationException ex) {
            LOGGER.error("NotificationValidationException: ", ex);
        }
    }
}