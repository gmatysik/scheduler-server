/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.scheduler.boot.service;

import java.util.ArrayList;
import java.util.List;
import com.scheduler.boot.model.Task;
import org.springframework.stereotype.Service;

/**
 *
 * @author Grzegorz
 */
@Service
public class TaskService {
    private List<Task> tasks = new ArrayList<>(); 
    
    public List<Task> getAllTasks(){
        return tasks;
    }
    
    public Task getTask(int id){
        return tasks.stream()                
                .filter(t-> t.getId() == id)
                .findFirst()
                .get();
    }
    
    public void addTask(Task task){
        tasks.add(task);
    }

    public void removeTask(int taskId) {
        tasks.removeIf(task -> task.getId() == taskId);
    }
    
}
