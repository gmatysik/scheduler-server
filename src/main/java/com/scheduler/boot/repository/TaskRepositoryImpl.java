/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.scheduler.boot.repository;

import com.scheduler.boot.repository.TaskSpringRepository;
import com.scheduler.boot.repository.TaskTable;
import com.scheduler.tasks.TaskRepository;
import com.scheduler.tasks.TaskDTO;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author Grzegorz
 */
@Component
class TaskRepositoryImpl implements TaskRepository{
       
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
            startDate = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").parse(task.getStart());
        } catch (ParseException ex) {
            ex.printStackTrace();
            //Logger.getLogger(TaskService.class.getName()).log(Level.SEVERE, null, ex);
        }

        TaskTable t = new TaskTable(null, task.getTitle(), startDate);                
        TaskTable res = taskRepository.save(t);
        return res.getDTOObject();
    }

    @Override
    public TaskDTO updateTask(TaskDTO task) {
                 Date deadline = null;
        TaskTable t = taskRepository.findById(task.getId()).get();
        try {
            deadline = new SimpleDateFormat("dd/MM/yyyy HH:mm").parse(task.getStart());
        } catch (ParseException ex) {
            ex.printStackTrace();
            //Logger.getLogger(TaskService.class.getName()).log(Level.SEVERE, null, ex);
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