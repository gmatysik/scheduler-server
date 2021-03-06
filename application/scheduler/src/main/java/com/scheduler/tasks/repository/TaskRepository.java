/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.scheduler.tasks.repository;

import com.scheduler.tasks.TaskDTO;
import java.util.List;

/**
 *
 * @author Grzegorz
 */
public interface TaskRepository {
    
    public List<TaskDTO> getAll();

    public TaskDTO getTask(long id);

    public TaskDTO createTask(TaskDTO task);

    public TaskDTO updateTask(TaskDTO task);

    public void removeTask(long taskId);

    public List<TaskDTO> findNextTasksForUserForNextNDays(long userId, int days);

}
