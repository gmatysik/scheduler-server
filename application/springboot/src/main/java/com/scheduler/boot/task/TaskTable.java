/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.scheduler.boot.task;

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
@Table(name = "Task")
public class TaskTable implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String name;
    private String description;
    
    @Temporal(TemporalType.TIMESTAMP)
    private Date deadline;

    @Temporal(TemporalType.TIMESTAMP)
    private Date endDate;

    public TaskTable(){
        
    }
    
    public TaskTable(Integer id, String name, Date deadline, Date endDate, String description){
        this.id = id;
        this.name = name;
        this.deadline = deadline;
        this.endDate = endDate;
        this.description = description;
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
        if(getDeadline() == null){
            setDeadline(new Date());
        }        
        
        String endDateStr = null;
        if(getEndDate() != null){
            endDateStr = df.format(getEndDate());
        }
        
        return new TaskDTO(getId(), getName(), df.format(getDeadline()), endDateStr, getDescription(), 0);
    }

    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return the endDate
     */
    public Date getEndDate() {
        return endDate;
    }

    /**
     * @param endDate the endDate to set
     */
    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }
}
