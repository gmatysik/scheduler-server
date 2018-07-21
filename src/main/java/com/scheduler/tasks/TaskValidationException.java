/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.scheduler.tasks;

import java.util.List;

/**
 *
 * @author Grzegorz
 */
public class TaskValidationException extends Exception{
    
    private List<String> messages;

    /**
     * @return the messages
     */
    public List<String> getMessages() {
        return messages;
    }

    public TaskValidationException(List<String> messages){
        super(messages.toString());
        this.messages = messages;
    }
    
}
