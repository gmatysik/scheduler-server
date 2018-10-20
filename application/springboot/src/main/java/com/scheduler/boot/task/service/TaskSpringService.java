/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.scheduler.boot.task.service;

import com.scheduler.tasks.TaskDTO;
import com.scheduler.tasks.TaskValidationException;
import java.util.List;

/**
 *
 * @author Grzegorz
 */
public interface TaskSpringService {
    public List<TaskDTO> getAllTasks();

    public TaskDTO getTask(Integer taskId);

    public TaskDTO addTask(TaskDTO task) throws TaskValidationException;

    public TaskDTO updateTask(TaskDTO task) throws TaskValidationException;

    public void removeTask(int taskId);

    public List<TaskDTO> getNextNTasks(Integer numberOfTasks);
}