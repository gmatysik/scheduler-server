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
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Grzegorz
 */
@Service
@Transactional
class TaskSpringServiceImpl implements TaskSpringService{
    
    private Task task;
    
    @Autowired
    private TaskRepository taskRepository;

    @PostConstruct
    public void init() {
        task = TaskServiceFactory.createTaskService(taskRepository);
    }
    
    @Override
    public List<TaskDTO> getAllTasks(){
        return task.getAllTasks();
    }

    @Override
    public TaskDTO getTask(Integer taskId) {
        return task.getTask(taskId);
    }

    @Override
    public TaskDTO addTask(TaskDTO taskDto) throws TaskValidationException{
        
        return task.addTask(taskDto);
    }

    @Override
    public void removeTask(int taskId) {
        task.removeTask(taskId);
    }    

    @Override
    public List<TaskDTO> getNextNTasks(Integer numberOfTasks) {
        return task.getNextNTasks(numberOfTasks);
    }

    @Override
    public TaskDTO updateTask(TaskDTO taskDto) throws TaskValidationException{
        return task.updateTask(taskDto);
    }
}