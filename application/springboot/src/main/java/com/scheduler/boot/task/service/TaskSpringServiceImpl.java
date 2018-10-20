/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.scheduler.boot.task.service;

import com.scheduler.tasks.TaskRepository;
import com.scheduler.tasks.Task;
import com.scheduler.tasks.TaskDTO;
import com.scheduler.tasks.TaskServiceFactory;
import com.scheduler.tasks.TaskValidationException;
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
    
    private Task taskService;
    
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
    public TaskDTO addTask(TaskDTO task) throws TaskValidationException{
        
        return taskService.addTask(task);
    }

    @Override
    public void removeTask(int taskId) {
        taskService.removeTask(taskId);
    }    

    @Override
    public List<TaskDTO> getNextNTasks(Integer numberOfTasks) {
        return taskService.getNextNTasks(numberOfTasks);
    }

    @Override
    public TaskDTO updateTask(TaskDTO task) throws TaskValidationException{
        return taskService.updateTask(task);
    }
}