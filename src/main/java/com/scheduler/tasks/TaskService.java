/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.scheduler.tasks;

import java.util.List;

/**
 *
 * @author Grzegorz
 */
public interface TaskService {

    public List<TaskDTO> getAllTasks();
    
    public TaskDTO getTask(int id);
    
    public TaskDTO addTask(TaskDTO task) throws TaskValidationException;

    public TaskDTO updateTask(TaskDTO task) throws TaskValidationException;

    public void removeTask(int taskId);

    public List<TaskDTO> getNextNTasks(Integer numberOfTasks);
        
}
