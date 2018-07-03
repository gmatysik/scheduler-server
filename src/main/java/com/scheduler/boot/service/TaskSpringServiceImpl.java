/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.scheduler.boot.service;

import com.scheduler.tasks.TaskRepository;
import com.scheduler.tasks.TaskService;
import com.scheduler.tasks.TaskDTO;
import com.scheduler.tasks.TaskServiceFactory;
import java.util.List;
import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Grzegorz
 */
@Service
class TaskSpringServiceImpl implements TaskSpringService{
    
    private TaskService taskService;
    
    @Autowired
    private TaskRepository taskRepository;

    @PostConstruct
    public void init() {
        taskService = TaskServiceFactory.createTaskService(taskRepository);
    }
    
    @Override
    public List<TaskDTO> getAllTasks(){
        return taskService.getAllTasks();
    }

    @Override
    public TaskDTO getTask(Integer taskId) {
        return taskService.getTask(taskId);
    }

    @Override
    public TaskDTO addTask(TaskDTO task) {
        return taskService.addTask(task);
    }

    public void removeTask(int taskId) {
        taskService.removeTask(taskId);
    }    
}