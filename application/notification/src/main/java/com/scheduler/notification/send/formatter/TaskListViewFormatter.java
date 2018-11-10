/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.scheduler.notification.send.formatter;

import com.scheduler.tasks.TaskDTO;
import java.util.List;

/**
 *
 * @author Grzegorz
 */
public abstract class TaskListViewFormatter {
    protected final TaskViewFormatter notificationFormatter;
    
    public TaskListViewFormatter(TaskViewFormatter notificationFormatter){        
        this.notificationFormatter = notificationFormatter;        
    }
    
    public abstract String format(List<TaskDTO> tasks);
}
