/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.scheduler.tasks;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import java.io.Serializable;

/**
 *
 * @author Grzegorz
 */
public class TaskDTO implements Serializable {
    
    //public static final String DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'";
    public static final String DATE_FORMAT = "yyyy-MM-dd HH:mm";
    
    private long id;
    
    @NotEmpty(message = "title.not.empty")
    private String title;
    
    @NotEmpty(message = "start.not.empty")
    @Pattern(message = "start.datetime.format", regexp = "^(\\d{4}-\\d{2}-\\d{2}\\s\\d{2}:\\d{2})?$")
    private String start;
    
    @Pattern(message = "end.datetime.format", regexp = "^(\\d{4}-\\d{2}-\\d{2}\\s\\d{2}:\\d{2})?$")
    private String end;
    
    private String description;

    private long userId;
    
    public TaskDTO(){
        
    }
    
    public TaskDTO(TaskDTO taskDTO){
        this.id = taskDTO.getId();
        this.start = taskDTO.getStart();
        this.end = taskDTO.getEnd();
        this.title = taskDTO.getTitle();
        this.description = taskDTO.getDescription();
        this.userId = taskDTO.getUserId();
    }

    
    public TaskDTO(long id, String title, String start, String end, String description, long userId){
        this.id = id;        
        this.title = title;
        this.start = start;
        this.end = end;
        this.description = description;
        this.userId = userId;
    }
    
    /**
     * @return the number
     */
    public long getId() {
        return id;
    }

    /**
     * @return the name
     */
    
    /**
     * @param id the id to set
     */
    public void setId(long id) {
        this.id = id;
    }


    /**
     * @return the end
     */
    public String getEnd() {
        return end;
    }

    /**
     * @param end the end to set
     */
    public void setEnd(String end) {
        this.end = end;
    }

    /**
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * @param title the title to set
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * @return the start
     */
    public String getStart() {
        return start;
    }

    /**
     * @param start the start to set
     */
    public void setStart(String start) {
        this.start = start;
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
     * @return the userId
     */
    public long getUserId() {
        return userId;
    }

    /**
     * @param userId the userId to set
     */
    public void setUserId(long userId) {
        this.userId = userId;
    }    
}