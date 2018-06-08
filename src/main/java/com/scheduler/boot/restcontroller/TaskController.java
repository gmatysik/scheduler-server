/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.scheduler.boot.restcontroller;

import org.springframework.web.bind.annotation.RestController;
import com.scheduler.boot.model.Task;
import com.scheduler.boot.service.TaskService;
import java.util.Date;
import java.util.List;
import javax.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author Grzegorz
 */
@RestController
@CrossOrigin
public class TaskController {
    
    @Autowired
    private TaskService taskService;
    
    @RequestMapping(path = "/tasks/task/{taskId}", produces = "application/json")
    public Task getTask(@PathVariable String taskId){
        return taskService.getTask(new Integer(taskId));
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @RequestMapping(path = "/tasks", produces = "application/json")
    public List<Task> getTasks(){
        return taskService.getAllTasks();
    }
   
    @PostMapping(path = "/tasks/task", consumes = "application/json", produces = "application/json")
    public Task addTask(@RequestBody Task task){
        taskService.addTask(task);
        return task;
    }

    @DeleteMapping(path = "/tasks/{taskId}")
    public void removeTask(@PathVariable int taskId ){
        taskService.removeTask(taskId);
    }
    
}
