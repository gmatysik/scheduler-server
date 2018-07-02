/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.scheduler.boot.restcontroller;

import com.scheduler.boot.dto.TaskDTO;
import org.springframework.web.bind.annotation.RestController;
import com.scheduler.boot.service.TaskService;
import java.util.Date;
import java.util.List;
import javax.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
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
    public TaskDTO getTask(@PathVariable String taskId){
        return taskService.getTask(new Integer(taskId));
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @RequestMapping(path = "/tasks", produces = "application/json")
    public List<TaskDTO> getTasks(){
        return taskService.getAllTasks();
    }
   
    @PostMapping(path = "/tasks/task", consumes = "application/json", produces = "application/json")
    public TaskDTO addTask(@RequestBody TaskDTO task){
        System.out.println("start: " + task.getStart());
        TaskDTO t = taskService.addTask(task);
        return t;
    }

    @DeleteMapping(path = "/tasks/{taskId}")
    public void removeTask(@PathVariable int taskId ){
        System.out.println("removeTask");
        taskService.removeTask(taskId);
    }
    
}
