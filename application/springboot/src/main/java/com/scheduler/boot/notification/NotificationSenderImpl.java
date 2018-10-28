/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.scheduler.boot.notification;

import com.scheduler.notification.send.NotificationSender;
import com.scheduler.tasks.TaskDTO;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author Grzegorz
 */
@Component
public class NotificationSenderImpl implements NotificationSender{
    
    @Autowired
    private EmailSender sendEmail;
    
    @Override
    public void sendTaskNotificationToUser(List<TaskDTO> tasks, long userId) { 
        tasks.stream().forEach((task) -> {
            sendEmail.sendMail("jomjom@poczta.onet.pl", task.getTitle(), task.getDescription());
        });
    }
    
}
