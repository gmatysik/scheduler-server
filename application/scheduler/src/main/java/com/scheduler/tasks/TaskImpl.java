/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.scheduler.tasks;

import com.scheduler.tasks.repository.TaskRepository;
import com.scheduler.tasks.validation.TaskValidationException;
import com.scheduler.tasks.validation.TaskValidator;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 *
 * @author Grzegorz
 */
class TaskImpl implements Task {
    private final static Logger LOGGER = LogManager.getLogger(TaskImpl.class);
    
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
        LOGGER.info("task: " + task);
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
        List<TaskDTO> tasks = taskRepository.findNextTasksForUserForNextNDays(userId, Task.DAYS_TO_FIND_TASKS);
        tasks.sort(new TasksComparator());
        return tasks;
    }

       private class TasksComparator implements Comparator<TaskDTO>{

        @Override
        public int compare(TaskDTO task1, TaskDTO task2) {
            int compare = -1;
            try {                
                Date startDate1 = new SimpleDateFormat(TaskDTO.DATE_FORMAT).parse(task1.getStart());
                Date startDate2 = new SimpleDateFormat(TaskDTO.DATE_FORMAT).parse(task2.getStart());
                if(startDate1.before(startDate2)){
                    compare = -1;
                }
                if(startDate1.after(startDate2)){
                    compare = 1;
                }
                if(startDate1.equals(startDate2)){
                    compare = 0;
                }                
            } catch (ParseException ex) {
                java.util.logging.Logger.getLogger(TaskImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
            return compare;
        }  
    }
} 