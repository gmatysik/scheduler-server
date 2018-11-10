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
public interface TaskRepository {
    
    public List<TaskDTO> getAll();

    public TaskDTO getTask(int id);

    public TaskDTO createTask(TaskDTO task);

    public TaskDTO updateTask(TaskDTO task);

    public void removeTask(int taskId);

    public List<TaskDTO> findNextTasksForUserForNextNDays(long userId, int days);

}
