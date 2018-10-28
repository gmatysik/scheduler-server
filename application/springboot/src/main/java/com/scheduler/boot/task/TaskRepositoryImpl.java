/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.scheduler.boot.task;

import com.scheduler.tasks.TaskRepository;
import com.scheduler.tasks.TaskDTO;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

/**
 *
 * @author Grzegorz
 */
@Component
@Profile("production")
class TaskRepositoryImpl implements TaskRepository{
       
    private final static Logger LOGGER = LogManager.getLogger(TaskRepositoryImpl.class);
    
    @Autowired
    private TaskSpringRepository taskRepository;

    @Override
    public List<TaskDTO> getAll() {
        List<TaskDTO> result = new ArrayList<>();
        
        Iterable<TaskTable> source = taskRepository.findAll();
        for(TaskTable task : source){
            result.add(task.getDTOObject());
        }
        return result;
    }

    @Override
    public TaskDTO getTask(int id) {
        Iterable<TaskTable> source = taskRepository.findAll();
        for(TaskTable task : source){
            if(task.getId() == id){
                return task.getDTOObject();
            }
        }
        return new TaskDTO();
    }

    @Override
    public TaskDTO createTask(TaskDTO task) {
        Date startDate = null;
        try {
            if(task.getStart() != null){
                startDate = new SimpleDateFormat(TaskDTO.DATE_FORMAT).parse(task.getStart());                
            }
        } catch (ParseException ex) {
            startDate = new Date();
            LOGGER.error(ex.getMessage(), ex);
        }

        Date endDate = null;
        try {
            if(task.getEnd() != null){
                endDate = new SimpleDateFormat(TaskDTO.DATE_FORMAT).parse(task.getEnd());               
            }
        } catch (ParseException ex) {
            endDate = new Date();
            LOGGER.error(ex.getMessage(), ex);
        }
        
        TaskTable t = new TaskTable(null, task.getTitle(), startDate, endDate, task.getDescription());
        TaskTable res = taskRepository.save(t);
        return res.getDTOObject();
    }

    @Override
    public TaskDTO updateTask(TaskDTO task) {
        TaskTable t = taskRepository.findById(task.getId()).get();

        Date startDate = null;
        try {
            if(task.getStart() != null){
                startDate = new SimpleDateFormat(TaskDTO.DATE_FORMAT).parse(task.getStart());                
            }
        } catch (ParseException ex) {
            startDate = new Date();
            LOGGER.error(ex.getMessage(), ex);
        }

        Date endDate = null;
        try {
            if(task.getEnd() != null){
                endDate = new SimpleDateFormat(TaskDTO.DATE_FORMAT).parse(task.getEnd());               
            }
        } catch (ParseException ex) {
            endDate = new Date();
            LOGGER.error(ex.getMessage(), ex);
        }
        
        t.setDeadline(startDate);
        t.setEndDate(endDate);
        t.setDescription(task.getDescription());
        t.setName(task.getTitle());
        return taskRepository.save(t).getDTOObject();
   }

    @Override
    public void removeTask(int taskId) {
        taskRepository.deleteById(taskId);
    }

    @Override
    public List<TaskDTO> findNextSevenDaysTasksForUser(long userId) {    
        List<TaskDTO> result = new ArrayList<>();        
        List<TaskTable> source = taskRepository.findTasksFromNextSevenDaysForUser(93);
        source.stream().forEach((task) -> {
            result.add(task.getDTOObject());
        });
        return result;    
    }
}