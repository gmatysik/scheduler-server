/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.scheduler.tasks;

import java.util.List;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author Grzegorz
 */
class TaskServiceImpl implements TaskService {
    private List<TaskDTO> tasks = null; 
    
    //private TaskRepository taskRep;
    private final TaskRepository taskRepository;

    
    public TaskServiceImpl(TaskRepository taskRepository){
        //this.taskRep = taskRep;
        this.taskRepository = taskRepository;
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
    public TaskDTO addTask(TaskDTO task){
        System.out.println("task: " +   task);
        Date deadline = null;
        try {
            deadline = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").parse(task.getStart());
        } catch (ParseException ex) {
            ex.printStackTrace();
            //TODO: validate
            //Logger.getLogger(TaskService.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        return taskRepository.createTask(task);
        
        //task.setId(t.getId());
        //return res.getDTOObject();
    }

    @Override
     public TaskDTO updateTask(TaskDTO task){
         //TaskDTO task = taskRepository.getTask(task.getId());
         //Task t = taskRep.findById(task.getId()).get();
         
                 Date deadline = null;
        try {
            deadline = new SimpleDateFormat("dd/MM/yyyy HH:mm").parse(task.getStart());
        } catch (ParseException ex) {
            ex.printStackTrace();
            //Logger.getLogger(TaskService.class.getName()).log(Level.SEVERE, null, ex);
        }
         //t.setDeadline(deadline);
         //t.setName(task.getTitle());
         //taskRep.save(t);
         return taskRepository.updateTask(task);
    }

    @Override
    public void removeTask(int taskId) {
        //taskRep.deleteById(taskId);
        taskRepository.removeTask(taskId);
    }
    
}
