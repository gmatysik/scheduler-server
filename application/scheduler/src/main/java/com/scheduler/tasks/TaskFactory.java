/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.scheduler.tasks;

import com.scheduler.tasks.repository.InMemoryTaskRepositoryImpl;
import com.scheduler.tasks.repository.TaskRepository;
import com.scheduler.tasks.validation.TaskValidatorImpl;

/**
 *
 * @author Grzegorz
 */
public class TaskFactory {
    public static Task createTask(TaskRepository taskRepository){
        return new TaskImpl(taskRepository, new TaskValidatorImpl());
    }
    
    public static Task createTask(){
        return new TaskImpl(new InMemoryTaskRepositoryImpl(), new TaskValidatorImpl());
    }    
}
