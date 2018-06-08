/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.scheduler.boot.dto;

/**
 *
 * @author Grzegorz
 */
public class TaskDTO {
    private String number;
    private String name;
    private String deadline;

    public void TaskDTO(String number, String name, String deadline){        
        this.number = number;
        this.name = name;
        this.deadline = deadline;
    }
    
    /**
     * @return the number
     */
    public String getNumber() {
        return number;
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
    public String getDeadline() {
        return deadline;
    }
}
