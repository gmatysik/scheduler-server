/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.scheduler.boot.model;

import java.util.Date;

/**
 *
 * @author Grzegorz
 */
public class Task {
    private int id;
    private String name;
    private Date deadline;

    public Task(){
        
    }
    
    public Task(int id, String name, Date deadline){
        this.id = id;
        this.name = name;
        this.deadline = deadline;
    }
    
    /**
     * @return the number
     */
    public int getId() {
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
}
