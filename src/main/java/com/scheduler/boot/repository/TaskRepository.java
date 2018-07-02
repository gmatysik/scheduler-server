/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.scheduler.boot.repository;
import org.springframework.data.repository.CrudRepository;
import com.scheduler.boot.model.Task;

/**
 *
 * @author Grzegorz
 */
public interface TaskRepository extends CrudRepository<Task, Integer>{
    
}
