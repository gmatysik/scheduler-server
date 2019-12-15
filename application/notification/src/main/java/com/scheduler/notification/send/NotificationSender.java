/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.scheduler.notification.send;

import com.scheduler.notification.send.formatter.TaskListViewFormatter;
import com.scheduler.tasks.TaskDTO;
import com.scheduler.users.User;
import com.scheduler.users.UserDTO;
import com.scheduler.users.repository.UserRepository;

import java.util.List;

/**
 *
 * @author Grzegorz
 */
public abstract class NotificationSender {
 
    private final TaskListViewFormatter notificationListFormatter;
    private final User user;

    public NotificationSender(TaskListViewFormatter notificationListFormatter, User user){
        this.notificationListFormatter = notificationListFormatter;
        this.user = user;
    }
    
    private String formatNotificationList(List<TaskDTO> task){
        return notificationListFormatter.format(task);
    }
    
    public void sendTaskNotificationToUser(List<TaskDTO> task, long userId){
        UserDTO userDTO = user.get(userId);
        sendTaskNotification(formatNotificationList(task), userDTO);
    }
    
    public abstract void sendTaskNotification(String fomattedNotification, UserDTO userDTO);
}
