/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.scheduler.boot.repository;

import com.scheduler.tasks.TaskRepository;
import com.scheduler.tasks.TaskDTO;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author Grzegorz
 */
@Component
class TaskRepositoryImpl implements TaskRepository{
       
    private static final Logger logger = LoggerFactory.getLogger(TaskRepositoryImpl.class);
    
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
        Date startDate;
        try {
            startDate = new SimpleDateFormat(TaskDTO.DATE_FORMAT).parse(task.getStart());
        } catch (ParseException ex) {
            startDate = new Date();
            logger.error(ex.getMessage(), ex);
        }

        TaskTable t = new TaskTable(null, task.getTitle(), startDate);                
        TaskTable res = taskRepository.save(t);
        return res.getDTOObject();
    }

    @Override
    public TaskDTO updateTask(TaskDTO task) {
        Date deadline;
        TaskTable t = taskRepository.findById(task.getId()).get();
        try {
            deadline = new SimpleDateFormat(TaskDTO.DATE_FORMAT).parse(task.getStart());
        } catch (ParseException ex) {
            deadline = new Date();
            logger.error(ex.getMessage(), ex);
        }
        t.setDeadline(deadline);
        t.setName(task.getTitle());
        return taskRepository.save(t).getDTOObject();
   }

    @Override
    public void removeTask(int taskId) {
        taskRepository.deleteById(taskId);
    }
}