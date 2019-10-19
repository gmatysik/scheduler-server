/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.scheduler.tasks;

import com.scheduler.tasks.validation.TaskValidationException;
import java.util.List;

/**
 *
 * @author Grzegorz
 */
public interface Task {

    public static int DAYS_TO_FIND_TASKS = 14;
    
    public List<TaskDTO> getAllTasks();
    
    public TaskDTO get(int id);
    
    public TaskDTO addTask(TaskDTO task) throws TaskValidationException;

    public TaskDTO updateTask(TaskDTO task) throws TaskValidationException;

    public void removeTask(long taskId);

    public List<TaskDTO> getNextNTasks(Integer numberOfTasks);

    public List<TaskDTO> getTasksFromNextSevenDaysForUser(long userId);
        
}
