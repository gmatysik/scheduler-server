/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.scheduler.tasks;

import com.scheduler.tasks.validation.TaskValidatorImpl;

/**
 *
 * @author Grzegorz
 */
public class TaskServiceFactory {
    public static TaskService createTaskService(TaskRepository taskRepository){
        return new TaskServiceImpl(taskRepository, new TaskValidatorImpl());
    }
}
