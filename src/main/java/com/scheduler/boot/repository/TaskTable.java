/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.scheduler.boot.repository;

import com.scheduler.tasks.TaskDTO;
import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Grzegorz
 */
@Entity
@Table(name = "task")
public class TaskTable implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String name;
    
    @Temporal(TemporalType.TIMESTAMP)
    private Date deadline;

    public TaskTable(){
        
    }
    
    public TaskTable(Integer id, String name, Date deadline){
        this.id = id;
        this.name = name;
        this.deadline = deadline;
    }
    
    /**
     * @return the number
     */
    public Integer getId() {
        return id;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @return the deadline
     */
    public Date getDeadline() {
        return deadline;
    }

    /**
     * @param id the id to set
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @param deadline the deadline to set
     */
    public void setDeadline(Date deadline) {
        this.deadline = deadline;
    }
    
    public TaskDTO getDTOObject(){
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        if(deadline == null){
            deadline = new Date();
        }
        return new TaskDTO(id, name, df.format(deadline), df.format(deadline));
    }
}
