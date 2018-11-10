/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.scheduler.boot.notification;

import com.scheduler.tasks.Task;
import com.scheduler.tasks.TaskDTO;
import java.util.ArrayList;
import java.util.List;

/**
 * TODO: move to notification project
 * @author Grzegorz
 */
public class FormattedNotificationList {
    private final List<TaskDTO> tasks;
    
    public FormattedNotificationList(List<TaskDTO> tasks){
        this.tasks = tasks;
    }
    
    public String getNotifications(){
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("Task list for the next %s days\n\n", Task.DAYS_TO_FIND_TASKS));
        
        tasks.stream().forEach((task) -> {
            sb.append(new NotificationFormatter(task));
            sb.append("\n\n");
        });
 
        return sb.toString();
    }
}
