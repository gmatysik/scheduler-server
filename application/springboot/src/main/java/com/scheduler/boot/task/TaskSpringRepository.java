/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.scheduler.boot.task;
import java.util.Date;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author Grzegorz
 */
public interface TaskSpringRepository extends CrudRepository<TaskTable, Long>{

    @Query("SELECT t FROM TaskTable t WHERE t.deadline >= :from AND t.deadline <= :to")
    public List<TaskTable> findTasksInDateRange(@Param("from") Date date, @Param("to") Date to);
    
}
