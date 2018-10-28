/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.scheduler.notification.send;

import com.scheduler.notification.send.validation.NotificationValidatorImpl;
import com.scheduler.tasks.Task;

/**
 *
 * @author Grzegorz
 */
public class NotificationFactory {
    public static Notification createNotification(NotificationSender sender, Task task){
        return new NotificationImpl(sender, task, new NotificationValidatorImpl());
    }    
}