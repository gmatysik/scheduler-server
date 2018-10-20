/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.scheduler.reminder.send;

/**
 *
 * @author Grzegorz
 */
public class ReminderValidationException extends Exception{
    
    public ReminderValidationException(String message){
        super(message);
    }
}
