/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.scheduler.boot.restcontroller;

import com.scheduler.boot.service.TaskSpringService;
import com.scheduler.tasks.TaskDTO;
import com.scheduler.tasks.TaskValidationException;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
    private TaskSpringService taskService;
    
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
    public ResponseEntity<TaskDTO> addTask(@RequestBody TaskDTO task){
        System.out.println("start: " + task.getStart());
        TaskDTO t = null;
        ResponseEntity responseEntity = null;
        try {
            t = taskService.addTask(task);
            responseEntity = ResponseEntity.ok(t);
        } catch (TaskValidationException ex) {
            responseEntity = new ResponseEntity(ex.getMessages(), HttpStatus.NOT_ACCEPTABLE);
        }
        return responseEntity;
    }

   @PutMapping(path = "/tasks/{id}", consumes = "application/json", produces = "application/json")
    public ResponseEntity<TaskDTO> updateTask(@RequestBody TaskDTO task){
        System.out.println("start: " + task.getStart());
        TaskDTO t = null;
        ResponseEntity responseEntity = null;
        try {
            t = taskService.updateTask(task);
            responseEntity = ResponseEntity.ok(t);
        } catch (TaskValidationException ex) {
            responseEntity = new ResponseEntity(ex.getMessages(), HttpStatus.NOT_ACCEPTABLE);
        }
        return responseEntity;
        
    }
    
    @DeleteMapping(path = "/tasks/{taskId}")
    public void removeTask(@PathVariable int taskId ){
        System.out.println("removeTask");
        taskService.removeTask(taskId);
    }

    @RequestMapping(path = "/tasks/next/{numberOfTasks}", produces = "application/json")
    public List<TaskDTO> getNextNTasks(@PathVariable String numberOfTasks){
        return taskService.getNextNTasks(new Integer(numberOfTasks));
    }
    
}
