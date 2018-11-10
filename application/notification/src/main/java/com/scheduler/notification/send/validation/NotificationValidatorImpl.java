/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.scheduler.notification.send.validation;

import com.scheduler.notification.send.NotificationValidationException;
import com.scheduler.notification.send.NotificationValidator;
import com.scheduler.tasks.Task;
import com.scheduler.tasks.TaskDTO;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Grzegorz
 */
public class NotificationValidatorImpl implements NotificationValidator{
    @Override
    public void validate(List<TaskDTO> taskList) throws NotificationValidationException {
        verifyTasksMaxNDaysInFuture(taskList, Task.DAYS_TO_FIND_TASKS);
    }
    
    private void verifyTasksMaxNDaysInFuture(List<TaskDTO> taskList, int daysInFuture) throws NotificationValidationException{
        for(TaskDTO task : taskList){
            Date startDate;
            try {
                startDate = new SimpleDateFormat(TaskDTO.DATE_FORMAT).parse(task.getStart());
            } catch (ParseException ex) {
                throw new NotificationValidationException("Task " + task.getTitle() + " start date invalid: " + task.getStart());
            }
             Date today = new Date();
             
             long diff = startDate.getTime() - today.getTime();
             long difference = diff / (1000 * 60 * 60 * 24);
             if(daysInFuture - difference < 0 || difference < 0){
                throw new NotificationValidationException("Task " + task.getTitle() + " start date too early/too late: " + task.getStart());                 
             }
        }
    }    
}