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
    
    public TaskDTO addTask(TaskDTO task);

    public TaskDTO updateTask(TaskDTO task);

    public void removeTask(int taskId);
        
}
