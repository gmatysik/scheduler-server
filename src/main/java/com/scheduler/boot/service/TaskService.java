/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.scheduler.boot.service;

import com.scheduler.boot.dto.TaskDTO;
import java.util.ArrayList;
import java.util.List;
import com.scheduler.boot.model.Task;
import com.scheduler.boot.repository.TaskRepository;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Grzegorz
 */
@Service
public class TaskService {
    private List<TaskDTO> tasks = null; 
    
    @Autowired
    private TaskRepository taskRepository;
    
    public List<TaskDTO> getAllTasks(){
        List<TaskDTO> result = new ArrayList<>();
        
        Iterable<Task> source = taskRepository.findAll();
        for(Task task : source){
            result.add(task.getDTOObject());
        }
        return result;
    }
    
    public TaskDTO getTask(int id){
        Iterable<Task> source = taskRepository.findAll();
        for(Task task : source){
            if(task.getId() == id){
                return task.getDTOObject();
            }
        }

        return new TaskDTO();
    }
    
    public TaskDTO addTask(TaskDTO task){
        System.out.println("task: " +   task);
        Date deadline = null;
        try {
            deadline = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").parse(task.getStart());
        } catch (ParseException ex) {
            ex.printStackTrace();
            //Logger.getLogger(TaskService.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        Task t = new Task(null, task.getTitle(), deadline);
                
        Task res = taskRepository.save(t);
        
        //task.setId(t.getId());
        return res.getDTOObject();
    }

     public TaskDTO updateTask(TaskDTO task){
         Task t = taskRepository.findById(task.getId()).get();
         
                 Date deadline = null;
        try {
            deadline = new SimpleDateFormat("dd/MM/yyyy HH:mm").parse(task.getStart());
        } catch (ParseException ex) {
            ex.printStackTrace();
            //Logger.getLogger(TaskService.class.getName()).log(Level.SEVERE, null, ex);
        }
         t.setDeadline(deadline);
         t.setName(task.getTitle());
         taskRepository.save(t);
         return task;
    }

    public void removeTask(int taskId) {
        taskRepository.deleteById(taskId);
    }
    
}
