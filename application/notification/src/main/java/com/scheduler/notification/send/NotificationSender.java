/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.scheduler.notification.send;

import com.scheduler.notification.send.formatter.TaskListViewFormatter;
import com.scheduler.tasks.TaskDTO;
import java.util.List;

/**
 *
 * @author Grzegorz
 */
public abstract class NotificationSender {
 
    private final TaskListViewFormatter notificationListFormatter;
    
    public NotificationSender(TaskListViewFormatter notificationListFormatter){
        this.notificationListFormatter = notificationListFormatter;
    }
    
    private String formatNotificationList(List<TaskDTO> task){
        return notificationListFormatter.format(task);
    }
    
    public void sendTaskNotificationToUser(List<TaskDTO> task, long userId){
        sendTaskNotification(formatNotificationList(task));
    }
    
    public abstract void sendTaskNotification(String fomattedNotification);    
}
