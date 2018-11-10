/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.scheduler.boot.notification;

import com.scheduler.tasks.TaskDTO;

/**
 *
 * @author Grzegorz
 */
public class NotificationFormatter {
    private final TaskDTO task;
    
    public NotificationFormatter(TaskDTO task){
        this.task = task;
    }
    
    @Override
    public String toString(){
        String title = task.getTitle() != null ? task.getTitle() : "";
        String description = task.getDescription() != null ? task.getDescription() : "";
        String start = task.getStart()!= null ? task.getStart(): "";
        String end = task.getEnd()!= null ? task.getEnd(): "";

        return String.format("Task: %s \nDescription: %s\nStart: %s\nEnd:%s ", title, description, start, end);   
    }
}
