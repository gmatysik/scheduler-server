/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.scheduler.boot.repository;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author Grzegorz
 */
public interface TaskSpringRepository extends CrudRepository<TaskTable, Integer>{
    
}
