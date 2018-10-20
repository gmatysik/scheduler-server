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
class TaskImpl implements Task {
    private final TaskRepository taskRepository;
    private final TaskValidator validator;
    
    public TaskImpl(TaskRepository taskRepository, TaskValidator validator){
        this.taskRepository = taskRepository;
        this.validator = validator;
    }
    
    @Override
    public List<TaskDTO> getAllTasks(){
        return taskRepository.getAll();
    }
    
    @Override
    public TaskDTO getTask(int id){
        return taskRepository.getTask(id);
    }
    
    @Override
    public TaskDTO addTask(TaskDTO task) throws TaskValidationException{
        System.out.println("task: " +   task);
        validator.validate(task);
        return taskRepository.createTask(task);
    }

    @Override
     public TaskDTO updateTask(TaskDTO task) throws TaskValidationException{
        validator.validate(task);
        return taskRepository.updateTask(task);
    }

    @Override
    public void removeTask(int taskId) {
        taskRepository.removeTask(taskId);
    }

    @Override
    public List<TaskDTO> getNextNTasks(Integer numberOfTasks) {
        return this.getAllTasks();
    }

    @Override
    public List<TaskDTO> getTasksFromNextSevenDaysForUser(long userId) {
        List<TaskDTO> tasks = taskRepository.findTasksFromNextSevenDaysForUser(userId);
        return tasks;
    }
    
}
