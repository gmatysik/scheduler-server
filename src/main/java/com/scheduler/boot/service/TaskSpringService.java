/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.scheduler.boot.service;

import com.scheduler.tasks.TaskDTO;
import java.util.List;

/**
 *
 * @author Grzegorz
 */
public interface TaskSpringService {
    public List<TaskDTO> getAllTasks();

    public TaskDTO getTask(Integer taskId);

    public TaskDTO addTask(TaskDTO task);

    public void removeTask(int taskId);
}